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

    @PostMapping("/parent")
    public ResponseEntity<Parent> createParent(@RequestBody Parent parent) {
        Parent createdparent = parentService.createParent(parent);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdparent);
    }

    @GetMapping("/parent/{parentId}")
    public ResponseEntity<Parent> getParentById(@PathVariable Integer parentId) {
        Parent parent = parentService.getParentById(parentId);
        if (parent != null) {
            return ResponseEntity.status(HttpStatus.OK).body(parent);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PatchMapping("/parent/{parentId}")
    public ResponseEntity<Parent> updateParent(
            @PathVariable Integer parentId,
            @RequestBody Map<String, Object> updatedFields) {
        Parent updatedParent = parentService.updateParent(parentId, updatedFields);
        return ResponseEntity.status(HttpStatus.OK).body(updatedParent);
    }
}
