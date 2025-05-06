package SDstudios.app.service;

import SDstudios.app.exception.ContentNotFoundException;
import SDstudios.app.model.Child;
import SDstudios.app.repository.ChildRepository;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class ChildService {
    private final ChildRepository childRepository;

    public ChildService(ChildRepository childRepository) {
        this.childRepository = childRepository;
    }

    public Child createChild(Child child) {
        return childRepository.save(child);
    }

    public Child getChildById(Integer childId) {
        Optional<Child> child = childRepository.findById(childId);
        if (child.isEmpty()) {
            throw new ContentNotFoundException("Child not found");
        }
        return child.get();
    }

    public Child updateChild(Integer childId, Map<String, Object> updatedFields) {
        Optional<Child> child = childRepository.findById(childId);
        Child childToUpdate = checkChildExists(child);

        if (updatedFields.containsKey("name")) {
            childToUpdate.setName((String) updatedFields.get("name"));
        }

        if (updatedFields.containsKey("school")) {
            childToUpdate.setSchool((String) updatedFields.get("school"));
        }

        if (updatedFields.containsKey("yearGroup")) {
            childToUpdate.setYearGroup((Integer) updatedFields.get("yearGroup"));
        }

        if (updatedFields.containsKey("currentGrade")) {
            childToUpdate.setCurrentGrade((String) updatedFields.get("currentGrade"));
        }

        if (updatedFields.containsKey("additionalInfo")) {
            childToUpdate.setAdditionalInfo((String) updatedFields.get("additionalInfo"));
        }

        return childRepository.save(childToUpdate);
    }

    private Child checkChildExists(Optional<Child> child) {
        if (child.isEmpty()) {
            throw new ContentNotFoundException("Child not found");
        }
        return child.get();
    }

}
