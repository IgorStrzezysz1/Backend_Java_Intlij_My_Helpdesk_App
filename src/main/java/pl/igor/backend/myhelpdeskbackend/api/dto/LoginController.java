package pl.igor.backend.myhelpdeskbackend.api.dto;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.igor.backend.myhelpdeskbackend.api.dto.LoginDto;
import pl.igor.backend.myhelpdeskbackend.service.LoginService;

@RestController
@RequestMapping("/api/v1")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginDto> loginUser(@RequestHeader("X-Auth-Email") String email,
                                              @RequestHeader("X-Auth-Password") String password) {
        return ResponseEntity.ok(loginService.loginUser(email, password));
    }
}
