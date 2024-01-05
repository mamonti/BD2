package bd2.nonotion.service;

import bd2.nonotion.model.BlockEntity;
import bd2.nonotion.model.UserEntity;
import bd2.nonotion.model.request.UserCreationRequest;
import bd2.nonotion.repository.BlockRepository;
import bd2.nonotion.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    private final BlockRepository blockRepository;

    public UserEntity createUser(UserCreationRequest request) {

        UserEntity user = UserEntity.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .pages(new ArrayList<>())
                .build();

        return userRepository.save(user);
    }

    public List<BlockEntity> getBlocks(String id) {
        return blockRepository.findByOwnerId(id);
    }
}
