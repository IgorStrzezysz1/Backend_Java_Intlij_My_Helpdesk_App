package pl.igor.backend.myhelpdeskbackend.service;

import org.springframework.stereotype.Service;
import pl.igor.backend.myhelpdeskbackend.api.dto.TicketAddDto;
import pl.igor.backend.myhelpdeskbackend.api.dto.TicketDto;
import pl.igor.backend.myhelpdeskbackend.api.mapper.TicketMapper;
import pl.igor.backend.myhelpdeskbackend.model.TicketEntity;
import pl.igor.backend.myhelpdeskbackend.repository.TicketRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;

    public TicketService(TicketRepository ticketRepository, TicketMapper ticketMapper) {
        this.ticketRepository = ticketRepository;
        this.ticketMapper = ticketMapper;
    }

    public TicketDto createTicket(TicketAddDto ticketAddDto){
        TicketEntity ticketEntity = new TicketEntity(null, ticketAddDto.getTitle(), "active", ticketAddDto.getDescription(), ticketAddDto.getContact());
        ticketEntity = ticketRepository.save(ticketEntity);
        return ticketMapper.mapToTicketDto(ticketEntity);
    }

    public List<TicketDto> getAllTicketsActive(){
        List<TicketEntity> activeTickets = ticketRepository.findAllActive();
        return activeTickets.stream()
                .map(ticketMapper::mapToTicketDto)
                .collect(Collectors.toList());
    }

    public List<TicketDto> getAllTicketsArchival(){
        List<TicketEntity> archivalTickets = ticketRepository.findAllArchival();
        return archivalTickets.stream()
                .map(ticketMapper::mapToTicketDto)
                .collect(Collectors.toList());
    }

    public TicketDto updateTicket(Long id, TicketDto dto) {
        TicketEntity ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));
        if (dto.getTitle() != null) {
            ticket.setTitle(dto.getTitle());
        }
        if (dto.getDescription() != null) {
            ticket.setDescription(dto.getDescription());
        }

        ticketRepository.save(ticket);
        return ticketMapper.mapToTicketDto(ticket);
    }

    }

