package bd2.nonotion.model.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BlockShareResponse {
    private String blockId;
}
