package bd2.nonotion.model.request;

import lombok.Data;

@Data
public class UserCreationRequest {

    private String name;

    private String email;

    private String password;

}
