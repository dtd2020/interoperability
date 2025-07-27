package mz.gov.inage.esircev.v2.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterCitizenRequest {
    @NotBlank(message = "Por favor, preencha este campo.")
    @Size(max = 255, message = "Máximo de 255 caracteres permitido.")
    private String firstName;

    @NotBlank(message = "Por favor, preencha este campo.")
    @Size(max = 255, message = "Máximo de 255 caracteres permitido.")
    private String lastName;

    @NotBlank(message = "Por favor, preencha este campo.")
    @Pattern(regexp = ".{9}", message = "O NUIC deve conter exactamente 9 caracteres.")
    private String nuic;

    @NotBlank(message = "Por favor, preencha este campo.")
    private String sex;

    @NotBlank(message = "Por favor, preencha este campo.")
    private String dob;

    private String bio;

    @NotBlank(message = "Por favor, preencha este campo.")
    @Size(max = 255, message = "Máximo de 255 caracteres permitido.")
    private String fathersName;

    @NotBlank(message = "Por favor, preencha este campo.")
    @Size(max = 255, message = "Máximo de 255 caracteres permitido.")
    private String mothersName;
}
