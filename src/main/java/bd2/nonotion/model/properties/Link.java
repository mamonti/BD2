package bd2.nonotion.model.properties;

import bd2.nonotion.model.BlockProperties;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("blocks")
@Getter
@Setter
public class Link extends BlockProperties {
    @NotBlank(message = "linkedBlock field must not be null or empty")
    private String linkedBlockId;
}
