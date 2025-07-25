package mz.gov.inage.esircev.v2.mappers;

import mz.gov.inage.esircev.v2.dtos.RegisterUserRequest;
import mz.gov.inage.esircev.v2.dtos.UpdateUserRequest;
import mz.gov.inage.esircev.v2.dtos.UserDto;
import mz.gov.inage.esircev.v2.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
    User toEntity(RegisterUserRequest request);
    void update(UpdateUserRequest request, @MappingTarget User user);
}
