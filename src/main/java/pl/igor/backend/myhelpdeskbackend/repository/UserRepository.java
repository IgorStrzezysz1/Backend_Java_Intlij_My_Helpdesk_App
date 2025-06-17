package pl.igor.backend.myhelpdeskbackend.repository; // Pakiet – repozytoria (czyli klasy do obsługi bazy danych) znajdują się w folderze 'repository'.

import org.springframework.data.jpa.repository.JpaRepository; // JpaRepository – interfejs Springa, który zapewnia gotowe metody do operacji na bazie danych.
import org.springframework.stereotype.Repository; // Adnotacja – informuje Springa, że to komponent typu „repozytorium”.
import pl.igor.backend.myhelpdeskbackend.model.UserEntity; // Import encji – UserEntity reprezentuje użytkownika w bazie danych.

import java.util.Optional; // Optional – typ z Java, który może zawierać wartość lub być pusty (np. jeśli użytkownik nie istnieje).

@Repository // Adnotacja – Spring automatycznie rozpoznaje ten interfejs jako repozytorium i wstrzykuje go do serwisów.
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    // Interfejs – działa na encji UserEntity, identyfikator to Long.
    // Dzięki dziedziczeniu po JpaRepository mamy dostęp do metod: save(), findById(), deleteById(), findAll() itd.

    boolean existsByEmail(String email);
    // Sprawdza, czy w bazie istnieje użytkownik o podanym e-mailu.
    // Spring sam generuje zapytanie SQL na podstawie nazwy metody.

    Optional<UserEntity> findByEmail(String email);
    // Znajduje użytkownika po adresie e-mail. Zwraca Optional – może być pusty, jeśli użytkownika nie ma.
}
