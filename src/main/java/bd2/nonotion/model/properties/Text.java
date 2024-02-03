package bd2.nonotion.model.properties;

import bd2.nonotion.model.BlockProperties;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.awt.*;

@Document("blocks")
@Getter
@Setter
public class Text extends BlockProperties {

    @NotBlank(message = "text field must not be null or empty")
    private String text;

    private String font = "arial";

    private Integer size = 1;

    private Boolean bold = false;

    private Boolean italicized = false;

    private Boolean underlined = false;

    private Boolean strikethrough = false;

    private String color = "black";

    private String backgroundColor = "white";
}
