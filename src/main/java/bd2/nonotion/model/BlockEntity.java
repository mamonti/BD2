package bd2.nonotion.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document("blocks")
@Data
@Builder
public class BlockEntity {

    @Id
    private String id;

    private BlockType type;

    private BlockProperties properties;

    private String ownerId;

    private String parent;

    private List<String> content;

    private LocalDateTime createdAt;

    private LocalDateTime lastEditAt;
}
