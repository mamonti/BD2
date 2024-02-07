package bd2.nonotion.service;

import bd2.nonotion.advice.NotAPageException;
import bd2.nonotion.model.BlockEntity;
import bd2.nonotion.model.BlockType;
import bd2.nonotion.model.UserEntity;
import bd2.nonotion.model.properties.Link;
import bd2.nonotion.model.request.BlockCreationRequest;
import bd2.nonotion.model.request.BlockUpdateRequest;
import bd2.nonotion.repository.BlockRepository;
import bd2.nonotion.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.NoSuchElementException;

@AllArgsConstructor
@Service
public class BlockService {

    private final BlockRepository blockRepository;

    private final UserRepository userRepository;

    @Transactional
    public BlockEntity createBlock(BlockCreationRequest request) {

        if(request.getParent() == null || request.getParent().isBlank()) {
            request.setParent(null);
            if(!request.getType().equals(BlockType.PAGE))
                throw new NotAPageException("only PAGE blocks can have no parent");
        }

        if(request.getParent() != null) {
            blockRepository.findById(request.getParent()).orElseThrow(() -> new NoSuchElementException("Block doesn't exist"));
        }

        UserEntity user = userRepository.findById(request.getOwnerId()).orElseThrow(() -> new NoSuchElementException("User doesn't exist"));

        BlockEntity block = BlockEntity.builder()
                .type(request.getType())
                .properties(request.getProperties())
                .ownerId(request.getOwnerId())
                .parent(request.getParent())
                .content(new ArrayList<>())
                .createdAt(LocalDateTime.now())
                .build();

        switch(block.getType()) {
            case BlockType.LINK:
                Link properties = (Link) block.getProperties();
                blockRepository.findById(properties.getLinkedBlockId()).orElseThrow(() -> new NoSuchElementException("Block doesn't exist"));
                break;
        }

        block = blockRepository.save(block);

        if(block.getParent() == null) {
            user.getPages().add(block.getId());
            userRepository.save(user);
        } else {
            BlockEntity parent = blockRepository.findById(block.getParent()).orElseThrow(() -> new NoSuchElementException("Block doesn't exist"));
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

        if(block.getContent() != null) {
            for(String child : block.getContent()) {
                deleteBlock(child);
            }
        }

        if(block.getParent() == null) {
            UserEntity user = userRepository.findById(block.getOwnerId()).orElseThrow(() -> new NoSuchElementException("User doesn't exist"));
            user.getPages().remove(id);
            userRepository.save(user);
        } else {
            BlockEntity parent = blockRepository.findById(block.getParent()).orElseThrow(() -> new NoSuchElementException("Block doesn't exist"));
            parent.getContent().remove(id);
            blockRepository.save(parent);
        }

        blockRepository.deleteById(id);
    }

    public BlockEntity editBlock(BlockUpdateRequest request) {

        if(request.getParent() == null || request.getParent().isBlank()) {
            request.setParent(null);
            if(!request.getType().equals(BlockType.PAGE))
                throw new NotAPageException("only PAGE blocks can have no parent");
        }

        blockRepository.findById(request.getId()).orElseThrow(() -> new NoSuchElementException("Block doesn't exist"));
        if(request.getParent() != null)
            blockRepository.findById(request.getParent()).orElseThrow(() -> new NoSuchElementException("Block doesn't exist"));
        userRepository.findById(request.getOwnerId()).orElseThrow(() -> new NoSuchElementException("User doesn't exist"));

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
