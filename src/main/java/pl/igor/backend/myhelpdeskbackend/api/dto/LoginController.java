package pl.igor.backend.myhelpdeskbackend.api.dto;  // Pakiet – mylący! Tu powinien być api (a nie dto) – kontroler to nie DTO.

import org.springframework.http.ResponseEntity; // Klasa Springa służąca do budowania odpowiedzi HTTP (np. 200 OK).
import org.springframework.web.bind.annotation.*;// Importuje adnotacje REST, np. @RestController, @PostMapping itd.
import pl.igor.backend.myhelpdeskbackend.api.dto.LoginDto;// Klasa DTO – będzie zwracana po zalogowaniu.
import pl.igor.backend.myhelpdeskbackend.service.LoginService;// Serwis z logiką logowania użytkownika

@RestController// Mówi Springowi: „to jest kontroler REST – odpowiada na żądania HTTP”.
@RequestMapping("/api/v1")// Bazowy adres URL – wszystkie ścieżki zaczynają się od /api/v1.
public class LoginController { // Główna klasa kontrolera logowania.

    private final LoginService loginService; // Pole prywatne – przechowuje obiekt LoginService (logika biznesowa).

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }// Konstruktor – Spring wstrzykuje LoginService automatycznie.

    @PostMapping("/login")
    public ResponseEntity<LoginDto> loginUser(@RequestHeader("X-Auth-Email") String email, // Adnotacja – obsługuje żądanie POST na adres /api/v1/login.
                                              @RequestHeader("X-Auth-Password") String password) {  // Metoda odpowiadająca na logowanie. Zwraca obiekt HTTP z LoginDto.
        return ResponseEntity.ok(loginService.loginUser(email, password));// Tworzy odpowiedź HTTP 200 OK i przekazuje do niej wynik działania logiki logowania (LoginDto).
    }
}
