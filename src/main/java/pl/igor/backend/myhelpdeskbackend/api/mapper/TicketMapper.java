package pl.igor.backend.myhelpdeskbackend.api.mapper;

import pl.igor.backend.myhelpdeskbackend.api.dto.TicketDto;
import pl.igor.backend.myhelpdeskbackend.model.TicketEntity;

public class TicketMapper {
    //metoda zmieniająca ticketEntity na TicketDto
    public TicketDto mapToTicketDto(TicketEntity ticketEntity) {
        TicketDto ticketDto = new TicketDto(
                ticketEntity.getId(),
                ticketEntity.getTitle(),
                ticketEntity.getDescription(),
                ticketEntity.getStatus(),
                ticketEntity.getContact());
        return ticketDto;
    }
}
