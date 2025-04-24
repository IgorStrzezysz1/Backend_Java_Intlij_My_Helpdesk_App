package pl.igor.backend.myhelpdeskbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.igor.backend.myhelpdeskbackend.model.TicketEntity;
import pl.igor.backend.myhelpdeskbackend.repository.TicketRepository;

import java.util.List;
@Service
public class TicketService {
    private TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public TicketEntity createTicket(TicketEntity ticked){
        return ticketRepository.save(ticked);
    }
    public List<TicketEntity> getAllTicketsActive(){
        return ticketRepository.findAllActive();
    }

    public List<TicketEntity> getAllTicketsArchival(){
        return ticketRepository.findAllArchival();
    }
}
