package pl.igor.backend.myhelpdeskbackend.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.igor.backend.myhelpdeskbackend.api.dto.TicketDto;
import pl.igor.backend.myhelpdeskbackend.model.TicketEntity;
import pl.igor.backend.myhelpdeskbackend.service.TicketService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tickets")
public class TicketController {
    private TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    public ResponseEntity<TicketDto> create(@RequestBody TicketEntity ticketEntity) {
        TicketEntity savedTicketEntity = ticketService.createTicket(ticketEntity);
        return ResponseEntity.ok(savedTicketEntity);

    }

    @GetMapping("/active")
    public ResponseEntity<List<TicketEntity>> getAllTicketsActive() {
        return ResponseEntity.ok((ticketService.getAllTicketsActive()));
    }

    @GetMapping("/archival")
    public ResponseEntity<List<TicketEntity>> getAllTicketArchival() {
        return ResponseEntity.ok((ticketService.getAllTicketsArchival()));
    }
}
