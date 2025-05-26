package pl.igor.backend.myhelpdeskbackend.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.igor.backend.myhelpdeskbackend.model.UserEntity;
import pl.igor.backend.myhelpdeskbackend.service.RegisterService;

@RestController
@RequestMapping("/api/v1")
public class RegisterController {

    private final RegisterService registerService;

    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserEntity userEntity) {
        boolean success = registerService.registerUser(userEntity);
        if (success) {
            return ResponseEntity.ok("Rejestracja zakończona sukcesem");
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Użytkownik już istnieje!");
        }
    }
}
