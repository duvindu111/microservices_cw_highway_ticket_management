package lk.ijse.gdse66.user_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CredentialDTO {
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Invalid email address")
    private String email;
    @NotBlank(message = "password is mandatory")
    private String password;
}
