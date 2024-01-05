package bd2.nonotion.model;

import bd2.nonotion.model.properties.BulletProperties;
import bd2.nonotion.model.properties.TitleProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("blocks")
@Data
@JsonTypeInfo(use= JsonTypeInfo.Id.DEDUCTION)
@JsonSubTypes({
        @JsonSubTypes.Type(TitleProperties.class),
        @JsonSubTypes.Type(BulletProperties.class) })
public class BlockProperties {

}
