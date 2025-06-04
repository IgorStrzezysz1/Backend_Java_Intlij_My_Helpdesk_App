package pl.igor.backend.myhelpdeskbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.igor.backend.myhelpdeskbackend.model.UserEntity;
import pl.igor.backend.myhelpdeskbackend.repository.UserRepository;

import java.util.Optional;

@Service
public class RegisterService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean registerUser(UserEntity userEntity) {
        if (userRepository.findByEmail(userEntity.getEmail()).isPresent()) {
            return false;
        }

        String encodedPassword = passwordEncoder.encode(userEntity.getPassword());
        userEntity.setPassword(encodedPassword);
        userRepository.save(userEntity);
        return true;
    }

    public boolean validateUser(String email, String rawPassword) {
        Optional<UserEntity> userOpt = userRepository.findByEmail(email);

        if (userOpt.isEmpty()) {
            return false;
        }

        UserEntity user = userOpt.get();

        return passwordEncoder.matches(rawPassword, user.getPassword());
    }
}
