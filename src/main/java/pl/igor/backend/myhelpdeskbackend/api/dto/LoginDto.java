package pl.igor.backend.myhelpdeskbackend.api.dto;  // Pakiet – określa lokalizację klasy w projekcie (do importu i struktury).

public class LoginDto {                                // Klasa DTO – służy do przesyłania tokenu JWT po zalogowaniu.

    private String accessToken;  // Pole: token JWT, który użytkownik otrzymuje po logowaniu.

    public LoginDto(String accessToken) { // Konstruktor – pozwala utworzyć obiekt DTO z podanym tokenem.
        this.accessToken = accessToken;

    }
    public LoginDto() { // Pusty konstruktor – wymagany przez biblioteki np. Jackson do serializacji JSON.

    }

    public String getAccessToken() {   // Getter – umożliwia pobranie wartości tokenu JWT.
        return accessToken;
    }

    public void setAccessToken(String accessToken) {  // Setter – pozwala ustawić token (np. przy odczycie JSON).
        this.accessToken = accessToken;
    }
}
