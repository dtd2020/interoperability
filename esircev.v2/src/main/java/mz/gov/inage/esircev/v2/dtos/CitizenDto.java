package mz.gov.inage.esircev.v2.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CitizenDto {
    @JsonProperty("citizen_id")
    private Long id;
    private String firstName;
    private String lastName;
    private String nuic;
    private String sex;
    private String dob;
}
