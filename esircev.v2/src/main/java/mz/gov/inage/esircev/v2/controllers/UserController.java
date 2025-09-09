package mz.gov.inage.esircev.v2.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import mz.gov.inage.esircev.v2.dtos.RegisterUserRequest;
import mz.gov.inage.esircev.v2.dtos.UpdateUserRequest;
import mz.gov.inage.esircev.v2.dtos.UserDto;
import mz.gov.inage.esircev.v2.mappers.UserMapper;
import mz.gov.inage.esircev.v2.repositories.UserRepository;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
@Tag(name = "Users", description = "Gestão de utilizadors | Mock-eSIRCEV | INAGE, IP")
public class UserController {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @GetMapping
    @Operation(summary = "Retorna todos utilizadores registados")
    public Iterable<UserDto> getAllUsers(
            @RequestHeader(required = false, name = "x-auth-token") String authToken,
            @RequestParam(required = false, defaultValue = "", name = "sort") String sort
    ){
        System.out.println(authToken);

        if(!Set.of("name", "email").contains(sort))
            sort = "name";

        return userRepository.findAll(Sort.by(sort))
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retorna um utilizador pelo ID.")
    public ResponseEntity<UserDto> getUser(
            @Parameter(description = "ID do utilizador.")
            @PathVariable Long id
    ){
        var user = userRepository.findById(id).orElse(null);
        if(user == null){
            //return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            //Melhor abordagem
            return ResponseEntity.notFound().build();
        }
        //return new ResponseEntity<>(user, HttpStatus.OK);
        //Melhor abordagem
        return ResponseEntity.ok(userMapper.toDto(user));
    }

    @PostMapping
    @Operation(summary = "Regista um utilizador")
    public ResponseEntity<?> registerUser(
            @Valid @RequestBody RegisterUserRequest request,
            UriComponentsBuilder uriBuilder){

        if(userRepository.existsByEmail(request.getEmail())) {
            return ResponseEntity.badRequest().body(
                    Map.of("email","Email já registado.")
            );
        }

        var user = userMapper.toEntity(request);
        userRepository.save(user);

        var userDto = userMapper.toDto(user);
        var uri = uriBuilder.path("/users/{id}").buildAndExpand(userDto.getId()).toUri();
        return ResponseEntity.created(uri).body(userDto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualiza um utilizador.")
    public ResponseEntity<UserDto> updateUser(
            @Parameter(description = "ID do utilizador.")
            @PathVariable(name = "id") Long id,
            @Valid @RequestBody UpdateUserRequest request){

        var user = userRepository.findById(id).orElse(null);

        if(user == null)
            return ResponseEntity.notFound().build();

        userMapper.update(request, user);
        userRepository.save(user);

        return ResponseEntity.ok(userMapper.toDto(user));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove um utilizador.")
    public ResponseEntity<Void> deleteUser(
            @Parameter(description = "ID do utilizador.")
            @PathVariable Long id
    ){
        var user = userRepository.findById(id).orElse(null);

        if(user == null)
            return ResponseEntity.notFound().build();

        userRepository.delete(user);
        return ResponseEntity.noContent().build();
    }
}
