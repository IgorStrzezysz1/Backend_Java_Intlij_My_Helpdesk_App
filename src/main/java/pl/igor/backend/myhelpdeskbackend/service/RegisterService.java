package pl.igor.backend.myhelpdeskbackend.service; // Pakiet – klasa znajduje się w folderze 'service', czyli w logice aplikacji.

import org.springframework.beans.factory.annotation.Autowired; // Adnotacja – pozwala na automatyczne wstrzykiwanie zależności.
import org.springframework.security.crypto.password.PasswordEncoder; // Interfejs – do bezpiecznego haszowania haseł (np. BCrypt).
import org.springframework.stereotype.Service; // Adnotacja – informuje Springa, że to komponent serwisowy.
import pl.igor.backend.myhelpdeskbackend.model.UserEntity; // Import encji – reprezentuje użytkownika w bazie danych.
import pl.igor.backend.myhelpdeskbackend.repository.UserRepository; // Import repozytorium – do operacji na tabeli users.

import java.util.Optional; // Klasa Optional – używana do bezpiecznego sprawdzania, czy użytkownik istnieje.

@Service // Adnotacja – Spring rozpoznaje tę klasę jako serwis (logika aplikacji), i może ją automatycznie wstrzykiwać.
public class RegisterService { // Klasa odpowiedzialna za rejestrację i walidację użytkowników.

    @Autowired
    private UserRepository userRepository; // Repozytorium użytkowników – umożliwia dostęp do bazy danych.

    @Autowired
    private PasswordEncoder passwordEncoder; // Komponent do szyfrowania haseł – np. przy użyciu BCrypta.

    public boolean registerUser(UserEntity userEntity) {
        // Metoda do rejestracji użytkownika – zwraca true jeśli się udało, false jeśli e-mail już istnieje.

        if (userRepository.findByEmail(userEntity.getEmail()).isPresent()) {
            // Sprawdza, czy użytkownik z podanym e-mailem już istnieje.
            return false; // Jeśli tak – rejestracja się nie powiedzie.
        }

        String encodedPassword = passwordEncoder.encode(userEntity.getPassword());
        // Szyfruje (haszuje) hasło użytkownika za pomocą BCrypta.

        userEntity.setPassword(encodedPassword);
        // Ustawia zakodowane hasło w obiekcie użytkownika.

        userRepository.save(userEntity);
        // Zapisuje nowego użytkownika do bazy danych.

        return true; // Rejestracja zakończona sukcesem.
    }

    public boolean validateUser(String email, String rawPassword) {
        // Metoda sprawdza, czy podany e-mail i hasło są poprawne (używana np. w logowaniu).

        Optional<UserEntity> userOpt = userRepository.findByEmail(email);
        // Szuka użytkownika po e-mailu.

        if (userOpt.isEmpty()) {
            return false; // Jeśli nie znaleziono – zwraca false.
        }

        UserEntity user = userOpt.get();
        // Jeśli znaleziono – pobiera obiekt użytkownika.

        return passwordEncoder.matches(rawPassword, user.getPassword());
        // Sprawdza, czy podane hasło (raw) zgadza się z zakodowanym hasłem w bazie.
    }
}
