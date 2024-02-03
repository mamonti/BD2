package bd2.nonotion.model;

import bd2.nonotion.model.properties.*;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("blocks")
@Data
@JsonTypeInfo(use= JsonTypeInfo.Id.DEDUCTION)
@JsonSubTypes({
        @JsonSubTypes.Type(BulletedListItem.class),
        @JsonSubTypes.Type(Divider.class),
        @JsonSubTypes.Type(Link.class),
        @JsonSubTypes.Type(NumberedListItem.class),
        @JsonSubTypes.Type(Page.class),
        @JsonSubTypes.Type(Quote.class),
        @JsonSubTypes.Type(Table.class),
        @JsonSubTypes.Type(Text.class),
        @JsonSubTypes.Type(TodoListItem.class),
        @JsonSubTypes.Type(ToggleListItem.class)})
public class BlockProperties {

}
