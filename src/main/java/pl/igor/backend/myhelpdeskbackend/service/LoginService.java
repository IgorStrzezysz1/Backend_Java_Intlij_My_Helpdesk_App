package pl.igor.backend.myhelpdeskbackend.service; // Pakiet – klasa znajduje się w folderze 'service', razem z logiką aplikacji.

import io.jsonwebtoken.Jwts; // Klasa z biblioteki JJWT – służy do tworzenia i czytania tokenów JWT.
import io.jsonwebtoken.SignatureAlgorithm; // Określa algorytm podpisu JWT (np. HS256).
import io.jsonwebtoken.security.Keys; // Klasa pomocnicza – generuje bezpieczne klucze.
import org.springframework.stereotype.Service; // Adnotacja – informuje Springa, że to komponent serwisowy.

import java.security.Key; // Typ klucza szyfrującego – używany do podpisywania i weryfikacji tokenu.
import java.util.Date; // Klasa Java – do obsługi dat (wydania i wygaśnięcia tokenu).

@Service // Adnotacja – ta klasa jest zarządzana przez Springa i może być automatycznie wstrzykiwana jako zależność.
public class JwtService { // Klasa odpowiedzialna za tworzenie i sprawdzanie tokenów JWT (JSON Web Token).

    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    // Prywatny klucz – generowany jednorazowo przy uruchomieniu aplikacji. Używany do podpisywania tokenów.

    public String generateToken(String email) {
        // Metoda – generuje nowy token JWT na podstawie e-maila użytkownika.

        return Jwts.builder() // Tworzy nowy token JWT...
                .setSubject(email) // Ustawia e-mail jako „subject” – główną wartość identyfikującą użytkownika.
                .setIssuedAt(new Date(System.currentTimeMillis())) // Ustawia datę wygenerowania tokenu (obecna chwila).
                .setExpiration(new Date(System.currentTimeMillis() + 900000)) // Ustawia datę wygaśnięcia – po 15 minutach.
                .signWith(key) // Podpisuje token wcześniej wygenerowanym kluczem.
                .compact(); // Zamyka budowanie tokena i zwraca go jako String.
    }

    public boolean checkToken(String token) {
        // Metoda – sprawdza, czy token jest poprawny i został poprawnie podpisany.

        try {
            Jwts.parserBuilder() // Buduje parser JWT...
                    .setSigningKey(key).build() // Ustawia klucz do weryfikacji podpisu tokena.
                    .parseClaimsJws(token); // Próbuje sparsować token – jeśli się uda, to znaczy że jest poprawny.
            return true; // Token jest poprawny.
        } catch (Exception e) {
            return false; // Token jest niepoprawny lub wygasł.
        }
    }
}
