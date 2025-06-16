package pl.igor.backend.myhelpdeskbackend.api.dto; // Pakiet – określa lokalizację klasy DTO (do komunikacji z frontendem)


public class TicketDto {   // Klasa DTO – dane pojedynczego zgłoszenia przesyłane z backendu do frontendowej aplikacji.

    private Long id;  // Unikalny identyfikator zgłoszenia.

    private String title; // Tytuł zgłoszenia (np. "Nie działa drukarka").

    private String description;  // Opis problemu lub szczegóły zgłoszenia.

    private String status;  // Status zgłoszenia (np. "Active" oraz "Archived).

    private String contact;   // Kontakt użytkownika, który zgłosił problem (e-mail, telefon itp.).

    public Long getId() {
        return id;
    }      // Getter – zwraca ID zgłoszenia.

    public void setId(Long id) {
        this.id = id;
    }    // Setter – ustawia ID zgłoszenia.

    public String getTitle() {
        return title;
    }      // Getter – zwraca tytuł zgłoszenia.

    public void setTitle(String title) {
        this.title = title;
    }   // Setter – ustawia tytuł zgłoszenia.

    public String getDescription() {
        return description;
    }  // Getter – zwraca opis zgłoszenia.

    public void setDescription(String description) {
        this.description = description;
    }   // Setter – ustawia opis zgłoszenia.

    public String getStatus() {
        return status;
     // Getter – zwraca status zgłoszenia.

    public void setStatus(String status) {
        this.status = status;
    } // Setter – ustawia status zgłoszenia.

    public String getContact() {
        return contact;
    }  // Getter – zwraca dane kontaktowe zgłaszającego.

    public void setContact(String contact) {
        this.contact = contact;
    }     // Setter – ustawia dane kontaktowe zgłaszającego.


    public TicketDto(Long id, String title, String description, String status, String contact) {   // Konstruktor – używany do tworzenia obiektu z pełnym zestawem danych.
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.contact = contact;
    }
}