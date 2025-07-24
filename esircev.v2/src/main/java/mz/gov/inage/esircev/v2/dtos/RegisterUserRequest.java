package mz.gov.inage.esircev.v2.dtos;

import lombok.Data;

@Data
public class RegisterUserRequest {
    private String name;
    private String email;
    private String password;
}
