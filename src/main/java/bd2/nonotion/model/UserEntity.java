package bd2.nonotion.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("users")
@Data
@Builder
public class UserEntity {

    @Id
    private String id;

    private String name;

    private String email;

    private String password;

    private List<String> pages;

}
