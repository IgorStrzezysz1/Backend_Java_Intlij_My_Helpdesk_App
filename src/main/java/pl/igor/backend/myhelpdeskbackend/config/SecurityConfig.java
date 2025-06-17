package pl.igor.backend.myhelpdeskbackend.config; // Pakiet – klasa konfiguracyjna znajduje się w folderze 'config'.

import org.springframework.context.annotation.Bean; // Adnotacja @Bean – pozwala tworzyć komponenty w kontenerze Springa.
import org.springframework.context.annotation.Configuration; // Adnotacja @Configuration – wskazuje, że to klasa z konfiguracją aplikacji.
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity; // Pozwala zabezpieczać metody np. @PreAuthorize.
import org.springframework.security.config.annotation.web.builders.HttpSecurity; // Klasa do konfigurowania zabezpieczeń HTTP.
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity; // Włącza zabezpieczenia WebSecurity (Spring Security).
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; // Klasa do bezpiecznego szyfrowania haseł (algorytm BCrypt).
import org.springframework.security.crypto.password.PasswordEncoder; // Interfejs – ogólna reprezentacja szyfrowania haseł.
import org.springframework.security.web.SecurityFilterChain; // Reprezentuje łańcuch filtrów bezpieczeństwa HTTP.

@EnableGlobalMethodSecurity(prePostEnabled = true) // Pozwala używać @PreAuthorize w kontrolerach/serwisach.
@EnableWebSecurity // Włącza Spring Security dla całej aplikacji.
@Configuration // Mówi Springowi, że to klasa z konfiguracją.
public class SecurityConfig { // Główna klasa konfiguracyjna zabezpieczeń.

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Tworzy bean odpowiedzialny za szyfrowanie haseł algorytmem BCrypt.
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests() // Ustawia reguły autoryzacji HTTP.
                .anyRequest() // Dotyczy każdego zapytania HTTP.
                .permitAll() // Każdy może sięgnąć do każdego endpointu – brak zabezpieczeń.
                .and()
                .formLogin() // Wyłącza domyślny formularz logowania Spring Security.
                .disable()
                .logout() // Wyłącza domyślne wylogowywanie Spring Security.
                .disable();

        http.csrf().disable(); // Wyłącza ochronę CSRF – potrzebne przy pracy z API REST.
        http.headers().frameOptions().disable(); // Pozwala np. na otwieranie konsoli H2 w iframe.

        return http.build(); // Buduje i zwraca łańcuch zabezpieczeń.
    }
}
