package mz.gov.inage.esircev.v2.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import mz.gov.inage.esircev.v2.dtos.CitizenDto;
import mz.gov.inage.esircev.v2.dtos.RegisterCitizenRequest;
import mz.gov.inage.esircev.v2.dtos.UpdateCitizenRequest;
import mz.gov.inage.esircev.v2.mappers.CitizenMapper;
import mz.gov.inage.esircev.v2.repositories.CitizenRepository;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/citizens")
@Tag(name = "Citizens", description = "Gestão de Cidadãos | Mock-eSIRCEV | INAGE, IP")
public class CitizenController {

    private final CitizenRepository citizenRepository;
    private final CitizenMapper citizenMapper;

    @GetMapping
    @Operation(summary = "Retorna todos cidadãos registados.")
    public Iterable<CitizenDto> getAllCitizens(
            @RequestParam(required = false, defaultValue = "", name = "sort") String sort
    ){
        if(!Set.of("firstName", "lastName", "nuic", "sex", "dob").contains(sort))
            sort = "firstName";

        return citizenRepository.findAll(Sort.by(sort))
                .stream()
                .map(citizenMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retorna cidadão pelo ID.")
    public ResponseEntity<CitizenDto> getCitizenById(
            @Parameter(description = "ID do cidadão.")
            @PathVariable Long id
    ){

        var citizen = citizenRepository.findById(id).orElse(null);

        if(citizen == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(citizenMapper.toDto(citizen));

    }

    @PostMapping
    @Operation(summary = "Regista um cidadão.")
    public ResponseEntity<?> registerCitizen(
            @Valid @RequestBody RegisterCitizenRequest request,
            UriComponentsBuilder uriBuilder
    ){
        if(citizenRepository.existsByNuic(request.getNuic())){
            return
                    ResponseEntity.badRequest().body(
                            Map.of("nuic", "NUIC já registado.")
                    );
        }

        var citizen = citizenMapper.toEntity(request);
        citizenRepository.save(citizen);

        var citizenDto = citizenMapper.toDto(citizen);
        var uri = uriBuilder.path("/citizens/{id}").buildAndExpand(citizenDto.getId()).toUri();
        return ResponseEntity.created(uri).body(citizenDto);
    }

    @Operation(summary = "Actualiza um cidadão.")
    @PutMapping("/{id}")
    public ResponseEntity<CitizenDto> updateCitizen(
            @Parameter(description = "ID do cidadão.")
            @PathVariable(name = "id") Long id,
            @Valid @RequestBody UpdateCitizenRequest request
            ){

        var citizen = citizenRepository.findById(id).orElse(null);

        if(citizen == null)
            return ResponseEntity.notFound().build();

        citizenMapper.update(request, citizen);
        citizenRepository.save(citizen);

        return ResponseEntity.ok(citizenMapper.toDto(citizen));
    }

    @Operation(summary = "Remove um cidadão.")
    @DeleteMapping("/{id}")
    public ResponseEntity<CitizenDto> deleteCitizen(
            @Parameter(description = "ID do cidadão.")
            @PathVariable(name = "id") Long id)
    {
        var citizen = citizenRepository.findById(id).orElse(null);

        if (citizen == null)
            return ResponseEntity.notFound().build();

        citizenRepository.delete(citizen);
        return ResponseEntity.noContent().build();

    }
}
