package pl.igor.backend.myhelpdeskbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.igor.backend.myhelpdeskbackend.model.UserEntity;
import pl.igor.backend.myhelpdeskbackend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

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

        String encodePassword = passwordEncoder.encode(userEntity.getPassword());
        userEntity.setPassword(encodePassword);
        userRepository.save(userEntity);
        return true;
    }
}
