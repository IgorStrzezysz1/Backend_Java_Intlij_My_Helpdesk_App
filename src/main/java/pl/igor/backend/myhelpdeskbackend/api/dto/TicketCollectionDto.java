package pl.igor.backend.myhelpdeskbackend.api.dto;   // Pakiet – określa miejsce klasy w strukturze projektu (dto = Data Transfer Object).

import java.util.List; // Import klasy List – do przechowywania listy obiektów TicketDto.


public class TicketCollectionDto {// Klasa DTO – opakowuje listę wielu zgłoszeń (TicketDto).

    private List<TicketDto> tickets;// Lista zgłoszeń – każdy element to osobne zgłoszenie typu TicketDto.

    public TicketCollectionDto(List<TicketDto> tickets) {
        this.tickets = tickets;
    }  // Konstruktor – ustawia listę zgłoszeń.

    public List<TicketDto> getTickets() {
        return tickets;
    }// Getter – pozwala odczytać całą listę zgłoszeń.


    public void setTickets(List<TicketDto> tickets) {
        this.tickets = tickets;
    }// Setter – pozwala ustawić listę zgłoszeń (np. w testach lub deserializacji).


}