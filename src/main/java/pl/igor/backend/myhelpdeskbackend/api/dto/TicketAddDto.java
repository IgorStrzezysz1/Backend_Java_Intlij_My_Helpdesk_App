package pl.igor.backend.myhelpdeskbackend.api.dto;  // Pakiet – wskazuje lokalizację klasy DTO w strukturze projektu.

public class TicketAddDto {  // Klasa DTO – służy do przesyłania danych przy tworzeniu zgłoszenia.
    private String title;  // Tytuł zgłoszenia (np. „Nie działa drukarka”).
    private String description; // Szczegółowy opis problemu.
    private String contact; // Dane kontaktowe zgłaszającego (np. e-mail, telefon).

    public TicketAddDto() {   // Pusty konstruktor – potrzebny do odczytu danych z JSON-a (np. z aplikacji mobilnej).
    }

    public TicketAddDto(String title, String contact, String description) {  // Konstruktor z parametrami – do szybkiego tworzenia obiektu DTO.
        this.title = title;
        this.contact = contact;
        this.description = description;
    }

    public String getTitle() {
        return title;
    } // Getter – umożliwia pobranie tytułu zgłoszenia.

    public void setTitle(String title) {
        this.title = title;
    } // Setter – pozwala ustawić tytuł (np. z danych przesłanych z frontendu).

    public String getDescription() {
        return description;
    } // Getter – pozwala pobrać opis zgłoszenia

    public void setDescription(String description) {
        this.description = description;
    } // Setter – ustawia opis zgłoszenia.

    public String getContact() {
        return contact;
    }    // Getter – zwraca dane kontaktowe.

    public void setContact(String contact) {
        this.contact = contact;
    }   // Setter – ustawia dane kontaktowe.
}
