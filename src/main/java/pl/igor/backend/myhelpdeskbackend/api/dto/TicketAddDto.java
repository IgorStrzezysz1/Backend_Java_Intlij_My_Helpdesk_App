package pl.igor.backend.myhelpdeskbackend.api.dto;

public class TicketAddDto {
    private String title;
    private String description;
    private String contact;

    public TicketAddDto() {
    }

    public TicketAddDto(String title, String contact, String description) {
        this.title = title;
        this.contact = contact;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}