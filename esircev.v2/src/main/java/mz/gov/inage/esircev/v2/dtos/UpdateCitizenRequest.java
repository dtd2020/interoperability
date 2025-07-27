package mz.gov.inage.esircev.v2.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import mz.gov.inage.esircev.v2.validation.ExactLength;

@Data
public class UpdateCitizenRequest {
    @NotBlank(message = "Por favor, preencha este campo.")
    @Size(max = 255, message = "Máximo de 255 caracteres permitido.")
    private String firstName;

    @NotBlank(message = "Por favor, preencha este campo.")
    @Size(max = 255, message = "Máximo de 255 caracteres permitido.")
    private String lastName;

    @NotBlank(message = "Por favor, preencha este campo.")
    //@Size(min = 9, max = 9, message = "O NUIC deve conter exactamente 12 caracteres.")
    @ExactLength(9)
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
