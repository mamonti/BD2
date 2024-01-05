package bd2.nonotion.repository;

import bd2.nonotion.model.BlockEntity;
import bd2.nonotion.model.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlockRepository extends MongoRepository<BlockEntity, String> {
    List<BlockEntity> findByOwnerId(String id);
}
