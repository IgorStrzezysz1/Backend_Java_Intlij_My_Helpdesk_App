package pl.igor.backend.myhelpdeskbackend.api;   // Pakiet – lokalizacja klasy kontrolera w strukturze projektu.

import org.springframework.http.HttpStatus;  // Import – potrzebny do ustawiania kodów HTTP (np. 409 Conflict).
import org.springframework.http.ResponseEntity;   // Import – pozwala budować odpowiedzi HTTP (np. 200 OK, 409 Conflict).
import org.springframework.web.bind.annotation.*;   // Import – adnotacje REST: @RestController, @RequestMapping, @PostMapping itd.
import pl.igor.backend.myhelpdeskbackend.model.UserEntity;  // Import – klasa encji użytkownika (dane z formularza rejestracji).
import pl.igor.backend.myhelpdeskbackend.service.RegisterService; // Import – serwis odpowiedzialny za logikę rejestracji

@RestController   // Adnotacja – klasa działa jako REST API (obsługuje żądania HTTP).
@RequestMapping("/api/v1")  // Bazowy URL – wszystkie ścieżki w tym kontrolerze będą zaczynać się od /api/v1.
public class RegisterController {  // Klasa kontrolera – obsługuje rejestrację nowych użytkowników.


    private final RegisterService registerService;  // Pole – przechowuje referencję do serwisu rejestracji.

    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }// Konstruktor – Spring automatycznie wstrzykuje zależność.

    @PostMapping("/register")    // Adnotacja – metoda obsługuje żądania POST na adres /api/v1/register.
    public ResponseEntity<String> registerUser(@RequestBody UserEntity userEntity) {  // Metoda – zwraca odpowiedź HTTP z tekstem (String) oraz @RequestBody – dane użytkownika przychodzące w ciele żądania (np. JSON z aplikacji).
        boolean success = registerService.registerUser(userEntity);  // Wywołanie metody serwisu – rejestruje użytkownika i zwraca true/false.
        if (success) {  // Jeśli rejestracja się udała:

            return ResponseEntity.ok("Rejestracja zakończona sukcesem");  // Zwróć 200 OK + komunikat.
        } else {        // Jeśli użytkownik już istnieje:
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Użytkownik już istnieje!");     // Zwróć 409 Conflict + komunikat.
        }
    }
}
