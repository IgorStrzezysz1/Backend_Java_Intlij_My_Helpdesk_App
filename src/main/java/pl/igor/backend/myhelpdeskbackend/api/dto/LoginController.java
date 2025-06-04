package pl.igor.backend.myhelpdeskbackend.api.dto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.igor.backend.myhelpdeskbackend.service.RegisterService;

@RestController
@RequestMapping("/api/v1")
public class LoginController {

    private final RegisterService registerService;

    public LoginController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestHeader("X-Auth-Email") String email,
                                            @RequestHeader("X-Auth-Password") String password) {
        boolean isValid = registerService.validateUser(email, password);
        if (isValid) {
            return ResponseEntity.ok("Zalogowano pomyślnie");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Nieprawidłowe dane logowania");
        }
    }
}
