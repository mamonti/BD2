package bd2.nonotion.model.properties;

import bd2.nonotion.model.BlockProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("blocks")
@Getter
@Setter
public class Link extends BlockProperties {
    private String linkedBlock;
}
