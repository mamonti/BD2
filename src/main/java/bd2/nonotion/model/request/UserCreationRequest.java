package bd2.nonotion.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserCreationRequest {
    @Schema(example = "Juan Perez")
    @NotBlank(message = "name field must not be null or empty")
    private String name;

    @Schema(example = "juan.perez@gmail.com")
    @NotBlank(message = "email field must not be null or empty")
    @Email(message = "format must be like 'myemail@gmail.com")
    private String email;

    @Schema(example = "12345")
    @NotBlank(message = "password field must not be null or empty")
    private String password;

}
