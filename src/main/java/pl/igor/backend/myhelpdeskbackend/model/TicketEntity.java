package pl.igor.backend.myhelpdeskbackend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tickets")
public class TicketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String status;
    private String contact;

    public TicketEntity() {
    }

    public TicketEntity(Long id, String title, String status, String contact, String description) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.contact = contact;
        this.description = description;
    }

    // gettery i settery

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
