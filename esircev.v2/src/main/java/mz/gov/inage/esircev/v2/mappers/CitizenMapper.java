package mz.gov.inage.esircev.v2.mappers;

import mz.gov.inage.esircev.v2.dtos.CitizenDto;
import mz.gov.inage.esircev.v2.entities.Citizen;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CitizenMapper {
    CitizenDto toDto(Citizen citizen);
}
