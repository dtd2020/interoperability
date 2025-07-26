package mz.gov.inage.esircev.v2.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import mz.gov.inage.esircev.v2.validation.Lowercase;

@Data
public class RegisterUserRequest {
    @NotBlank(message = "Por favor, preencha este campo.")
    @Size(max = 255, message = "Máximo de 255 caracteres permitido.")
    private String name;

    @NotBlank(message = "Por favor, preencha este campo.")
    @Email(message = "Por favor, introduza um email válido.")
    @Lowercase(message = "O email deve ser escrito em minúsculas.")
    private String email;

    @NotBlank(message = "Por favor, preencha este campo.")
    @Size(min = 6, max = 25, message = "A senha deve ter de 6 a 25 caracteres.")
    private String password;
}
