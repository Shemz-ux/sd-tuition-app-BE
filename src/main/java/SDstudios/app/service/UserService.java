package SDstudios.app.service;

import SDstudios.app.exception.ContentNotFoundException;
import SDstudios.app.model.User;
import SDstudios.app.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {this.userRepository = userRepository;}

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(Integer userId){
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new ContentNotFoundException("User not found");
        }
        return userOptional.get();
    }

    public User updateUser(Integer userId, Map<String, Object> updatedFields) {
        Optional<User> userOptional = userRepository.findById(userId);
        User originalDetails = checkUserExists(userOptional);

        if (updatedFields.containsKey("firstName")) {
            originalDetails.setFirstName((String) updatedFields.get("firstName"));
        }

        if (updatedFields.containsKey("lastName")) {
            originalDetails.setLastName((String) updatedFields.get("lastName"));
        }

        if (updatedFields.containsKey("email")) {
            originalDetails.setEmail((String) updatedFields.get("email"));
        }

        if (updatedFields.containsKey("mobile")) {
            originalDetails.setMobile((String) updatedFields.get("mobile"));
        }

        return userRepository.save(originalDetails);
    }

    private User checkUserExists(Optional<User> userOptional) {
        if (userOptional.isEmpty()) {
            throw new ContentNotFoundException("User not found");
        }
        return userOptional.get();
    }

}
