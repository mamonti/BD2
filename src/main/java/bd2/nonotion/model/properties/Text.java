package bd2.nonotion.model.properties;

import bd2.nonotion.model.BlockProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.awt.*;

@Document("blocks")
@Getter
@Setter
public class Text extends BlockProperties {

    private String text;

    private String font;

    private Integer size;

    private Boolean bold;

    private Boolean italicized;

    private Boolean underlined;

    private Boolean strikethrough;

    private Color color;

    private Color backGroundcolor;
}
