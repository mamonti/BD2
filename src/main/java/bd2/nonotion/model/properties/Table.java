package bd2.nonotion.model.properties;

import bd2.nonotion.model.BlockProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("blocks")
@Getter
@Setter
public class Table extends BlockProperties {
    private Integer rows;
    private Integer columns;
}
