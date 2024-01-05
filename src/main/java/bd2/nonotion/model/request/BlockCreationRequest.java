package bd2.nonotion.model.request;

import bd2.nonotion.model.BlockProperties;
import lombok.Data;

@Data
public class BlockCreationRequest {

    private String type;

    private BlockProperties properties;

    private String ownerId;

    private String parent;

}
