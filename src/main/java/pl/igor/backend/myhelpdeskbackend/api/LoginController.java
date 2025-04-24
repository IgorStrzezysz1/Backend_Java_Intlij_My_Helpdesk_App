package pl.igor.backend.myhelpdeskbackend.api;

import org.springframework.web.bind.annotation.*;
import pl.igor.backend.myhelpdeskbackend.service.LoginService;

@RestController
@RequestMapping("/api/v1")
public class LoginController {
    private LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public String loginUser(
            @RequestHeader("X-Auth-Email") String email,
            @RequestHeader("X-Auth-Password") String password) {

        return loginService.loginUser(email, password);
    }
}
