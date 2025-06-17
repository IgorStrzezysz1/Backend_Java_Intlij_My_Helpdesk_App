package pl.igor.backend.myhelpdeskbackend.service; // Pakiet – klasa znajduje się w folderze 'service', czyli warstwie logiki biznesowej.

import io.jsonwebtoken.Jwts; // Klasa z biblioteki jjwt – służy do tworzenia i odczytywania tokenów JWT.
import io.jsonwebtoken.SignatureAlgorithm; // Enum – określa algorytm szyfrowania tokenu (np. HMAC-SHA256).
import io.jsonwebtoken.security.Keys; // Klasa do generowania bezpiecznych kluczy.
import org.springframework.stereotype.Service; // Adnotacja – mówi Springowi, że to komponent serwisowy (bean).

import java.security.Key; // Klasa reprezentująca klucz szyfrujący JWT.
import java.util.Date; // Klasa do operowania na datach – potrzebna do ustawiania czasu ważności tokenu.

@Service // Adnotacja – mówi Springowi, że to serwis, który może być wstrzykiwany (np. do LoginService).
public class JwtService { // Klasa odpowiedzialna za generowanie i sprawdzanie tokenów JWT.

    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    // Klucz używany do podpisywania i weryfikowania tokenów JWT.
    // Wygenerowany automatycznie – bezpieczny, ale tylko tymczasowy (przy każdym uruchomieniu inny!).

    public String generateToken(String email) {
        // Metoda generująca nowy token JWT na podstawie adresu e-mail użytkownika.
        return Jwts.builder() // Tworzenie nowego tokenu JWT.
                .setSubject(email) // Ustawienie danych użytkownika (email) jako "sub" (subject).
                .setIssuedAt(new Date(System.currentTimeMillis())) // Data wygenerowania tokenu.
                .setExpiration(new Date(System.currentTimeMillis() + 900_000)) // Token ważny przez 15 minut (900 000 ms).
                .signWith(key) // Podpisanie tokenu za pomocą wcześniej wygenerowanego klucza.
                .compact(); // Zbudowanie i zwrócenie tokenu jako String.
    }

    public boolean checkToken(String token) {
        // Metoda sprawdzająca, czy token JWT jest poprawny (czy nie został sfałszowany lub wygasł).
        try {
            Jwts.parserBuilder() // Buduje parser tokenów.
                    .setSigningKey(key) // Ustawia klucz do weryfikacji podpisu.
                    .build()
                    .parseClaimsJws(token); // Próbuje sparsować i zweryfikować token.
            return true; // Jeśli się udało – token jest ważny.
        } catch (Exception e) {
            return false; // Jeśli wystąpi wyjątek – token jest nieprawidłowy lub wygasł.
        }
    }
}
