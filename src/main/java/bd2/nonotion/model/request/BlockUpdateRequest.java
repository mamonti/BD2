package bd2.nonotion.model.request;

import bd2.nonotion.model.BlockProperties;
import bd2.nonotion.model.BlockType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BlockUpdateRequest {

    @NotBlank(message = "id field must not be null or empty")
    private String id;

    private BlockType type;

    @Valid
    private BlockProperties properties;

    @NotBlank(message = "ownerId field must not be null or empty")
    private String ownerId;

    private String parent;
}
