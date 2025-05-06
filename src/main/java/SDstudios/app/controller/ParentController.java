package SDstudios.app.controller;

import SDstudios.app.model.Parent;
import SDstudios.app.service.ParentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class ParentController {
    private final ParentService parentService;

    public ParentController(ParentService parentService){
        this.parentService = parentService;
    }

    @PostMapping("/parents")
    public ResponseEntity<Parent> createParent(@RequestBody Parent parent) {
        Parent createdparent = parentService.createParent(parent);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdparent);
    }

    @GetMapping("/parents/{parentId}")
    public ResponseEntity<Parent> getParentById(@PathVariable Integer parentId) {
        Parent parent = parentService.getParentById(parentId);
        if (parent != null) {
            return ResponseEntity.status(HttpStatus.OK).body(parent);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PatchMapping("/parents/{parentId}")
    public ResponseEntity<Parent> updateParent(
            @PathVariable Integer parentId,
            @RequestBody Map<String, Object> updatedFields) {
        Parent updatedParent = parentService.updateParent(parentId, updatedFields);
        return ResponseEntity.status(HttpStatus.OK).body(updatedParent);
    }

    @DeleteMapping("/parents/{parentId}")
    public ResponseEntity<String> deleteParent(@PathVariable Integer parentId) {
        boolean isParentDeleted = parentService.deleteParentById(parentId);
        if (isParentDeleted) {
            return ResponseEntity.status(HttpStatus.OK).body("Parent profile deleted!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parent profile not found!");
        }
    }
}
