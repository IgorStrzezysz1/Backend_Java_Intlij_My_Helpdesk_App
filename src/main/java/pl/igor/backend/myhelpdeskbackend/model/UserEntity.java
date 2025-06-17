package pl.igor.backend.myhelpdeskbackend.model; // Pakiet – klasa encji znajduje się w folderze 'model', razem z innymi klasami bazodanowymi.

import jakarta.persistence.*; // Import adnotacji JPA – potrzebnych do mapowania klasy na tabelę w bazie danych.

@Entity // Adnotacja – oznacza, że klasa jest encją JPA (czyli odpowiada tabeli w bazie danych).
@Table(name = "users") // Adnotacja – definiuje, że ta encja jest mapowana na tabelę "users".
public class UserEntity { // Klasa encji – reprezentuje użytkownika systemu.

    @Id // Adnotacja – oznacza pole jako klucz główny tabeli (PRIMARY KEY).
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Strategia – ID generowane automatycznie przez bazę (np. AUTO_INCREMENT).
    private Long id; // Unikalny identyfikator użytkownika.

    private String email; // Adres e-mail użytkownika (używany przy logowaniu).
    private String firstName; // Imię użytkownika.
    private String lastName; // Nazwisko użytkownika.
    private String password; // Hasło użytkownika (powinno być zakodowane, np. BCrypt).

    public UserEntity() { } // Pusty konstruktor – wymagany przez JPA do odczytu danych z bazy.

    public UserEntity(String email, String firstName, String lastName, String password) {
        this.email = email; // Konstruktor – pozwala szybko utworzyć nowego użytkownika z podanymi danymi.
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public Long getId() { return id; } // Getter – zwraca ID użytkownika.
    public void setId(Long id) { this.id = id; } // Setter – ustawia ID użytkownika.

    public String getEmail() { return email; } // Getter – zwraca e-mail.
    public void setEmail(String email) { this.email = email; } // Setter – ustawia e-mail.

    public String getFirstName() { return firstName; } // Getter – zwraca imię.
    public void setFirstName(String firstName) { this.firstName = firstName; } // Setter – ustawia imię.

    public String getLastName() { return lastName; } // Getter – zwraca nazwisko.
    public void setLastName(String lastName) { this.lastName = lastName; } // Setter – ustawia nazwisko.

    public String getPassword() { return password; } // Getter – zwraca hasło (zakodowane).
    public void setPassword(String password) { this.password = password; } // Setter – ustawia hasło.
}
