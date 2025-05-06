package SDstudios.app.controller;

import SDstudios.app.model.Child;
import SDstudios.app.service.ChildService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class ChildController {
    private final ChildService childService;

    public ChildController(ChildService childService){
        this.childService = childService;
    }

    @PostMapping("/child")
    public ResponseEntity<Child> createChild(@RequestBody Child child){
        Child createdChild = childService.createChild(child);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdChild);
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
}
