package bd2.nonotion.model.properties;

import bd2.nonotion.model.BlockProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("blocks")
@Getter
@Setter
public class BulletProperties extends BlockProperties {

    private List<String> bullets;
}
