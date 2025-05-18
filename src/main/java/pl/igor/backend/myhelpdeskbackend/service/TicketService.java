package pl.igor.backend.myhelpdeskbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.igor.backend.myhelpdeskbackend.api.dto.TicketAddDto;
import pl.igor.backend.myhelpdeskbackend.api.dto.TicketDto;
import pl.igor.backend.myhelpdeskbackend.api.mapper.TicketMapper;
import pl.igor.backend.myhelpdeskbackend.model.TicketEntity;
import pl.igor.backend.myhelpdeskbackend.repository.TicketRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TicketService {
    private TicketRepository ticketRepository;
    private TicketMapper ticketMapper;
    public TicketService(TicketRepository ticketRepository, TicketMapper ticketMapper) {
        this.ticketRepository = ticketRepository;
        this.ticketMapper= ticketMapper;

    }

    public TicketDto createTicket(TicketAddDto ticketAddDto){
    TicketEntity ticketEntity = new TicketEntity(null, ticketAddDto.getTitle(), ticketAddDto.getDescription(), ticketAddDto.getContact(), "active");
    ticketEntity = ticketRepository.save(ticketEntity);
    return ticketMapper.mapToTicketDto(ticketEntity);
    }

    public List<TicketDto> getAllTicketsActive(){
        List<TicketEntity>activeTickets = ticketRepository.findAllActive();
        return activeTickets.stream()
                .map(ticketMapper::mapToTicketDto).toList();

    }

    public List<TicketDto> getAllTicketsArchival(){
        List<TicketEntity>activeTickets = ticketRepository.findAllActive();
        return activeTickets.stream()
                .map((ticketMapper::mapToTicketDto))
                .collect(Collectors.toList());

    }
}
