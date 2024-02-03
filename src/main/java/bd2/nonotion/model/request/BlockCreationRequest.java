package bd2.nonotion.model.request;

import bd2.nonotion.model.BlockProperties;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BlockCreationRequest {

    @NotBlank(message = "type field must not be null or empty")
    private String type;

    @Valid
    private BlockProperties properties;

    @NotBlank(message = "ownerId field must not be null or empty")
    private String ownerId;

    private String parent;

}
