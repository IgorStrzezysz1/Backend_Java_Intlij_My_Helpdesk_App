package pl.igor.backend.myhelpdeskbackend.service;

import org.aspectj.weaver.patterns.IToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.igor.backend.myhelpdeskbackend.api.dto.LoginDto;
import pl.igor.backend.myhelpdeskbackend.model.UserEntity;
import pl.igor.backend.myhelpdeskbackend.repository.UserRepository;

import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public LoginDto loginUser(String email, String password) {
        return userRepository.findByEmail(email)
                .filter(user -> passwordEncoder.matches(password, user.getPassword()))
                .map(user -> jwtService.generateToken(user.getEmail()))
                .map(token->new LoginDto(token))
                .orElse(null);

    }
    public boolean validateCredentials(String email, String password) {
        Optional<UserEntity> user = userRepository.findByEmail(email);
        return user.isPresent() && passwordEncoder.matches(password, user.get().getPassword());
    }
}
