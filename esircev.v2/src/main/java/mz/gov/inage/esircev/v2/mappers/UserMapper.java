package mz.gov.inage.esircev.v2.mappers;

import mz.gov.inage.esircev.v2.dtos.UserDto;
import mz.gov.inage.esircev.v2.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
}
