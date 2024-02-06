package bd2.nonotion.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BlockShareRequest {
    @NotBlank(message = "userId field must be null or empty")
    private String userId;
    private String parentBlockId;
}
