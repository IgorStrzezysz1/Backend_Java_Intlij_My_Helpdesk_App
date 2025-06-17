package pl.igor.backend.myhelpdeskbackend.model; // Pakiet – encje (czyli klasy reprezentujące tabele w bazie danych) znajdują się w folderze 'model'.

import jakarta.persistence.*; // Import adnotacji JPA, m.in. @Entity, @Id, @Table, @GeneratedValue.

@Entity // Adnotacja – informuje, że klasa to encja JPA (czyli odpowiada tabeli w bazie danych).
@Table(name = "tickets") // Adnotacja – mówi, że ta encja odwzorowuje się na tabelę "tickets" w bazie.
public class TicketEntity { // Klasa reprezentująca zgłoszenie (ticket), zapisywane w bazie danych.

    @Id // Adnotacja – oznacza pole jako klucz główny (PRIMARY KEY).
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID będzie automatycznie generowane przez bazę (np. auto-increment).
    private Long id; // Unikalny identyfikator zgłoszenia.

    private String title; // Tytuł zgłoszenia (np. "Awaria drukarki").
    private String description; // Szczegółowy opis problemu.
    private String status; // Status zgłoszenia (np. NEW, IN_PROGRESS, DONE).
    private String contact; // Dane kontaktowe osoby zgłaszającej (np. e-mail lub telefon).

    public TicketEntity() { } // Pusty konstruktor – wymagany przez JPA.

    public TicketEntity(Long id, String title, String status, String contact, String description) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.contact = contact;
        this.description = description;
    }

    public Long getId() { return id; } // Getter – zwraca ID.
    public void setId(Long id) { this.id = id; } // Setter – ustawia ID.

    public String getTitle() { return title; } // Getter – zwraca tytuł.
    public void setTitle(String title) { this.title = title; } // Setter – ustawia tytuł.

    public String getDescription() { return description; } // Getter – zwraca opis.
    public void setDescription(String description) { this.description = description; } // Setter – ustawia opis.

    public String getStatus() { return status; } // Getter – zwraca status zgłoszenia.
    public void setStatus(String status) { this.status = status; } // Setter – ustawia status zgłoszenia.

    public String getContact() { return contact; } // Getter – zwraca dane kontaktowe.
    public void setContact(String contact) { this.contact = contact; } // Setter – ustawia dane kontaktowe.
}
