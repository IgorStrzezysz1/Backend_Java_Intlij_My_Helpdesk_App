package pl.igor.backend.myhelpdeskbackend.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.igor.backend.myhelpdeskbackend.model.UserEntity;
import pl.igor.backend.myhelpdeskbackend.service.RegisterService;

@RestController
@RequestMapping("/api/v1")
public class RegisterController {
    private RegisterService registerService;

    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(
            @RequestHeader("X-Auth-Email") String email,
            @RequestHeader("X-Auth-Name") String firstName,
            @RequestHeader("X-Auth-Surname") String lastName,
            @RequestHeader("X-Auth-Password") String password) {

        UserEntity userEntity = new UserEntity(email, firstName, lastName, password);
        boolean success = registerService.registerUser(userEntity);
        if (success) {
            return ResponseEntity.ok("Rejestracja zakończona sukcesem");
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Użytkownik już istnieje!");
        }
    }
}
