package SDstudios.app.service;

import SDstudios.app.exception.ContentNotFoundException;
import SDstudios.app.model.Parent;
import SDstudios.app.repository.ParentRepository;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class ParentService {
    private final ParentRepository parentRepository;

    public ParentService(ParentRepository parentRepository) {this.parentRepository = parentRepository;}

    public Parent createParent(Parent parent) {
        return parentRepository.save(parent);
    }

    public Parent getParentById(Integer parentId){
        Optional<Parent> parentOptional = parentRepository.findById(parentId);
        if (parentOptional.isEmpty()) {
            throw new ContentNotFoundException("Parent not found");
        }
        return parentOptional.get();
    }

    public Parent updateParent(Integer ParentId, Map<String, Object> updatedFields) {
        Optional<Parent> parentOptional = parentRepository.findById(ParentId);
        Parent originalDetails = checkParentExists(parentOptional);

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

        return parentRepository.save(originalDetails);
    }

    private Parent checkParentExists(Optional<Parent> parentOptional) {
        if (parentOptional.isEmpty()) {
            throw new ContentNotFoundException("Parent not found");
        }
        return parentOptional.get();
    }

}
