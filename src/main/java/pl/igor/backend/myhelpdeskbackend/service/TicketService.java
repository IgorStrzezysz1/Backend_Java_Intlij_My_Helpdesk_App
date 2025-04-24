package pl.igor.backend.myhelpdeskbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.igor.backend.myhelpdeskbackend.api.dto.TicketAddDto;
import pl.igor.backend.myhelpdeskbackend.api.dto.TicketDto;
import pl.igor.backend.myhelpdeskbackend.model.TicketEntity;
import pl.igor.backend.myhelpdeskbackend.repository.TicketRepository;

import java.util.List;
@Service
public class TicketService {
    private TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public TicketDto createTicket(TicketAddDto ticketAddDto){
    TicketEntity ticketEntity = new TicketEntity(null, ticketAddDto.getTitle(), ticketAddDto.getDescription(), ticketAddDto.getContact(), "active");
    ticketEntity = ticketRepository.save(ticketEntity);

    }

    public List<TicketEntity> getAllTicketsActive(){
        return ticketRepository.findAllActive();
    }

    public List<TicketEntity> getAllTicketsArchival(){
        return ticketRepository.findAllArchival();
    }
}
