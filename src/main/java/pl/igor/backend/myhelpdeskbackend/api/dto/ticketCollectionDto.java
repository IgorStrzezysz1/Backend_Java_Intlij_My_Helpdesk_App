package pl.igor.backend.myhelpdeskbackend.api.dto;

import java.util.List;

public class ticketCollectionDto {
    private List<TicketDto> tickets;

    public ticketCollectionDto(List<TicketDto> tickets) {
        this.tickets = tickets;
    }

    public List<TicketDto> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketDto> tickets) {
        this.tickets = tickets;
    }
}
