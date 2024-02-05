package bd2.nonotion.service;

import bd2.nonotion.model.BlockEntity;
import bd2.nonotion.model.UserEntity;
import bd2.nonotion.model.request.BlockCreationRequest;
import bd2.nonotion.model.request.BlockUpdateRequest;
import bd2.nonotion.repository.BlockRepository;
import bd2.nonotion.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@AllArgsConstructor
@Service
public class BlockService {

    private final BlockRepository blockRepository;

    private final UserRepository userRepository;

    @Transactional
    public BlockEntity createBlock(BlockCreationRequest request) {

        BlockEntity block = BlockEntity.builder()
                .type(request.getType())
                .properties(request.getProperties())
                .ownerId(request.getOwnerId())
                .parent(request.getParent())
                .content(new ArrayList<>())
                .createdAt(LocalDateTime.now())
                .build();

        block = blockRepository.save(block);

        if(block.getParent() == null) {
            UserEntity user = userRepository.findById(block.getOwnerId()).orElseThrow();
            user.getPages().add(block.getId());
            userRepository.save(user);
        } else {
            BlockEntity parent = blockRepository.findById(block.getParent()).orElseThrow();
            parent.getContent().add(block.getId());
            blockRepository.save(parent);
        }

        return block;
    }

    public BlockEntity getBlock(String id) {
        return blockRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Block doesn't exist"));
    }

    @Transactional
    public void deleteBlock(String id) {
        BlockEntity block = blockRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Block doesn't exist"));

        for(String child : block.getContent()) {
            deleteBlock(child);
        }

        if(block.getParent() == null) {
            UserEntity user = userRepository.findById(block.getOwnerId()).orElseThrow();
            user.getPages().remove(id);
            userRepository.save(user);
        } else {
            BlockEntity parent = blockRepository.findById(block.getParent()).orElseThrow();
            parent.getContent().remove(id);
            blockRepository.save(parent);
        }

        blockRepository.deleteById(id);
    }

    public BlockEntity editBlock(BlockUpdateRequest request) {

        blockRepository.findById(request.getId()).orElseThrow(() -> new NoSuchElementException("Block doesn't exist"));

        BlockEntity block = BlockEntity.builder()
                .id(request.getId())
                .type(request.getType())
                .properties(request.getProperties())
                .ownerId(request.getOwnerId())
                .parent(request.getParent())
                .lastEditAt(LocalDateTime.now())
                .build();

        return blockRepository.save(block);
    }
}
