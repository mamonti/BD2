package bd2.nonotion.model.properties;

import bd2.nonotion.model.BlockProperties;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("blocks")
@Getter
@Setter
public class Table extends BlockProperties {
    @Min(value = 1, message = "table must have at least 1 row")
    @NotNull(message = "rows must not be null")
    private Integer rows;
    @Min(value = 1, message = "table must have at least 1 column")
    @NotNull(message = "columns must not be null")
    private Integer columns;
}
