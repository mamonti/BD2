package bd2.nonotion.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserCreationRequest {

    @NotBlank(message = "name field must not be null or empty")
    private String name;

    @NotBlank(message = "email field must not be null or empty")
    @Email(message = "format must be like 'myemail@gmail.com")
    private String email;

    @NotBlank(message = "password field must not be null or empty")
    private String password;

}
