package pl.igor.backend.myhelpdeskbackend.repository; // Pakiet – repozytoria (czyli dostęp do bazy danych) trzymane są w folderze 'repository'.

import org.springframework.data.jpa.repository.JpaRepository; // Interfejs JPA – zapewnia gotowe metody do operacji na bazie (CRUD).
import org.springframework.data.jpa.repository.Query; // Umożliwia pisanie własnych zapytań (JPQL).
import pl.igor.backend.myhelpdeskbackend.model.TicketEntity; // Import klasy encji – reprezentuje zgłoszenie (ticket) w bazie danych.
import java.util.stream.Collectors; // (Nieużywane – można usunąć, ale nie powoduje błędu).
import java.util.List; // Lista wyników – potrzebna do zwracania wielu zgłoszeń.

public interface TicketRepository extends JpaRepository<TicketEntity, Long> {
    // Interfejs repozytorium – dziedziczy po JpaRepository i zapewnia gotowe metody jak: findAll(), findById(), save(), deleteById() itd.
    // JpaRepository<TicketEntity, Long> – mówi, że repozytorium działa na encji TicketEntity, a ID jest typu Long.

    @Query("select t from TicketEntity t where t.status = 'active'")
        // Własne zapytanie JPQL – znajduje wszystkie zgłoszenia, których status to 'active'.
    List<TicketEntity> findAllActive(); // Metoda zwracająca tylko aktywne zgłoszenia.

    @Query("select t from TicketEntity t where t.status = 'archived'")
        // Drugie zapytanie – znajduje wszystkie zgłoszenia o statusie 'archived'.
    List<TicketEntity> findAllArchival(); // Metoda zwracająca tylko archiwalne zgłoszenia.
}
