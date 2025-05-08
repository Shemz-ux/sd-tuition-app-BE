package SDstudios.app.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Files;

@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping
    public ResponseEntity<String> getApi(){
        try {
            var resource = new ClassPathResource("endpoints.json");
            String json = new String(Files.readAllBytes(resource.getFile().toPath()));
            return ResponseEntity.ok().header("Content-Type", "application/json").body(json);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error loading Api: " + e.getMessage());
        }
    }
}
