package pl.igor.backend.myhelpdeskbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.igor.backend.myhelpdeskbackend.repository.UserRepository;

@Service
public class LoginService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;
    public String loginUser(String email, String password) {
        return userRepository.findByEmail(email)
                .filter(user -> passwordEncoder.matches((password),user.getPassword()))
                .map(user -> jwtService.generateToken(user.getEmail()))
                .orElse("Nieprawidłowy email lub hasło");
    }
}
