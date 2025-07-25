package mz.gov.inage.esircev.v2.mappers;

import mz.gov.inage.esircev.v2.dtos.CitizenDto;
import mz.gov.inage.esircev.v2.dtos.RegisterCitizenRequest;
import mz.gov.inage.esircev.v2.dtos.UpdateCitizenRequest;
import mz.gov.inage.esircev.v2.dtos.UpdateUserRequest;
import mz.gov.inage.esircev.v2.entities.Citizen;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CitizenMapper {
    CitizenDto toDto(Citizen citizen);
    @Mapping(source = "fathersName", target = "fathersName")
    Citizen toEntity(RegisterCitizenRequest request);
    void update(UpdateCitizenRequest request, @MappingTarget Citizen citizen);
}
