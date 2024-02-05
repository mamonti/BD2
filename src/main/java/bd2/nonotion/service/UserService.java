package bd2.nonotion.service;

import bd2.nonotion.model.BlockEntity;
import bd2.nonotion.model.UserEntity;
import bd2.nonotion.model.request.UserCreationRequest;
import bd2.nonotion.repository.BlockRepository;
import bd2.nonotion.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    private final BlockRepository blockRepository;

    private final BlockService blockService;

    public UserEntity getUser(String userId) {
        return userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("User doesn't exist"));
    }

    public UserEntity createUser(UserCreationRequest request) {

        UserEntity user = UserEntity.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .pages(new ArrayList<>())
                .build();

        return userRepository.save(user);
    }

    public List<BlockEntity> getBlocks(String ownerId) {
        userRepository.findById(ownerId).orElseThrow(() -> new NoSuchElementException("User doesn't exist"));
        return blockRepository.findByOwnerId(ownerId);
    }

    public UserEntity updateUser(UserCreationRequest request, String userId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("User doesn't exist"));

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        return userRepository.save(user);
    }

    @Transactional
    public void deleteUser(String userId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("User doesn't exist"));

        for(String blockId : user.getPages()) {
            blockService.deleteBlock(blockId);
        }

        userRepository.delete(user);
    }
}
