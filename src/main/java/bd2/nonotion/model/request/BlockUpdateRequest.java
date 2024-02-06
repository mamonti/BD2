package bd2.nonotion.model.request;

import bd2.nonotion.model.BlockProperties;
import bd2.nonotion.model.BlockType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BlockUpdateRequest {

    @Schema(example = "1", description = "Block ID")
    @NotBlank(message = "id field must not be null or empty")
    private String id;

    @Schema(example = "PAGE",
            description = "Must be one of BULLETED_LIST_ITEM, DIVIDER, LINK, NUMBERED_LIST_ITEM, PAGE, QUOTE, TABLE, TEXT, TODO_LIST_ITEM, TOGGLE_LIST_ITEM")
    private BlockType type;

    @Valid
    private BlockProperties properties;
    @Schema(example = "1" ,description = "Owner ID")
    @NotBlank(message = "ownerId field must not be null or empty")
    private String ownerId;

    @Schema(example = "1", description = "Parent Block ID, every type but PAGE must have a parent")
    private String parent;
}
