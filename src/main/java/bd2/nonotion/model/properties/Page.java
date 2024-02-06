package bd2.nonotion.model.properties;

import bd2.nonotion.model.BlockProperties;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("blocks")
@Getter
@Setter
public class Page extends BlockProperties {
    @Min(value = 1, message = "pageSize must be positive")
    @Max(value = 100, message = "pageSize max is 100%")
    private int pageSize;
}
