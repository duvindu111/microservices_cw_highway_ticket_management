package lk.ijse.gdse66.user_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDTO {
    @NotBlank(message = "name is mandatory")
    @Pattern(regexp = "[A-Za-z\\s]+", message = "Invalid Name")
    private String name;
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Invalid email address")
    private String email;
    @NotBlank(message = "password is mandatory")
    private String password;
}
