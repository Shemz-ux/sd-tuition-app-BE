package SDstudios.app.controller;

import SDstudios.app.model.Child;
import SDstudios.app.service.ChildService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ChildController {
    private final ChildService childService;

    public ChildController(ChildService childService){
        this.childService = childService;
    }

    @PostMapping("/parents/{parentId}/children")
    public ResponseEntity<Child> createChild(
            @PathVariable Integer parentId,
            @RequestBody Child child){
        Child createdChild = childService.createChild(parentId, child);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdChild);
    }

    @GetMapping("/parents/{parentId}/children")
    public ResponseEntity<List<Child>> getAllChildrenByParentId(@PathVariable Integer parentId){
        List<Child> children = childService.getChildrenByParentId(parentId);
        if (children != null) {
            return ResponseEntity.status(HttpStatus.OK).body(children);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/child/{childId}")
    public ResponseEntity<Child> getChildById(@PathVariable Integer childId){
        Child child = childService.getChildById(childId);
        if (child != null) {
            return ResponseEntity.status(HttpStatus.OK).body(child);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PatchMapping("/child/{childId}")
    public ResponseEntity<Child> updateChild(
            @PathVariable Integer childId,
            @RequestBody Map<String, Object> updatedFields) {
        Child updatedChild = childService.updateChild(childId, updatedFields);
        return ResponseEntity.status(HttpStatus.OK).body(updatedChild);
    }

    @DeleteMapping("/child/{childId}")
    public ResponseEntity<String> deleteChild(@PathVariable Integer childId){
        boolean isChildDeleted = childService.deleteChildById(childId);
        if (isChildDeleted) {
            return ResponseEntity.status(HttpStatus.OK).body("Child profile deleted!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Child profile not found!");
        }
    }
}
