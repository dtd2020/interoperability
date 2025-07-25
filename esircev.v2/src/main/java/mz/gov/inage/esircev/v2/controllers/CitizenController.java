package mz.gov.inage.esircev.v2.controllers;

import lombok.AllArgsConstructor;
import mz.gov.inage.esircev.v2.dtos.CitizenDto;
import mz.gov.inage.esircev.v2.dtos.RegisterCitizenRequest;
import mz.gov.inage.esircev.v2.dtos.UpdateCitizenRequest;
import mz.gov.inage.esircev.v2.entities.Citizen;
import mz.gov.inage.esircev.v2.mappers.CitizenMapper;
import mz.gov.inage.esircev.v2.repositories.CitizenRepository;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/citizens")
public class CitizenController {

    private final CitizenRepository citizenRepository;
    private final CitizenMapper citizenMapper;

    @GetMapping
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
    public ResponseEntity<CitizenDto> getCitizenById(@PathVariable Long id){

        var citizen = citizenRepository.findById(id).orElse(null);

        if(citizen == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(citizenMapper.toDto(citizen));

    }

    @PostMapping
    public ResponseEntity<CitizenDto> createCitizen(
            @RequestBody RegisterCitizenRequest request,
            UriComponentsBuilder uriBuilder
    ){
        var citizen = citizenMapper.toEntity(request);
        citizenRepository.save(citizen);

        var citizenDto = citizenMapper.toDto(citizen);
        var uri = uriBuilder.path("/citizens/{id}").buildAndExpand(citizenDto.getId()).toUri();
        return ResponseEntity.created(uri).body(citizenDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CitizenDto> updateCitizen(
            @PathVariable(name = "id") Long id,
            @RequestBody UpdateCitizenRequest request
            ){

        var citizen = citizenRepository.findById(id).orElse(null);

        if(citizen == null)
            return ResponseEntity.notFound().build();

        citizenMapper.update(request, citizen);
        citizenRepository.save(citizen);

        return ResponseEntity.ok(citizenMapper.toDto(citizen));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CitizenDto> deleteCitizen(
            @PathVariable(name = "id") Long id)
    {
        var citizen = citizenRepository.findById(id).orElse(null);

        if (citizen == null)
            return ResponseEntity.notFound().build();

        citizenRepository.delete(citizen);
        return ResponseEntity.noContent().build();

    }
}
