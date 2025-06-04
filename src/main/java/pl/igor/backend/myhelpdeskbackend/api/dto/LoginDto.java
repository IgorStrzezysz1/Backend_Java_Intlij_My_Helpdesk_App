package pl.igor.backend.myhelpdeskbackend.api.dto;

public class LoginDto {
    private String accessToken;
    public LoginDto(String accessToken) {
        this.accessToken = accessToken;

    }
    public LoginDto() {

    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
