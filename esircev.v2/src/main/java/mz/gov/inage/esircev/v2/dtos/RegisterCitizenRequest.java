package mz.gov.inage.esircev.v2.dtos;

import lombok.Data;

@Data
public class RegisterCitizenRequest {
    private String firstName;
    private String lastName;
    private String nuic;
    private String sex;
    private String dob;
    private String bio;
    private String fathersName;
    private String mothersName;
}
