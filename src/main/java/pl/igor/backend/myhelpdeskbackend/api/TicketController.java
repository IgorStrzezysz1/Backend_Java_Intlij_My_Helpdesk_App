package pl.igor.backend.myhelpdeskbackend.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.igor.backend.myhelpdeskbackend.model.TicketEntity;
import pl.igor.backend.myhelpdeskbackend.repository.TicketRepository;
import pl.igor.backend.myhelpdeskbackend.service.TicketService;
import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import pl.igor.backend.myhelpdeskbackend.model.UserEntity;


@RestController
@RequestMapping("/api/v1/tickets")
public class TicketController {
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private TicketService ticketService;
    @PostMapping
    public ResponseEntity<TicketEntity> create(@RequestBody TicketEntity ticketEntity) {
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
    @PostMapping
    public ResponseEntity<TicketEntity> createTicket(
            @RequestBody TicketEntity ticketEntity,
            @AuthenticationPrincipal UserEntity user 
    ) {
        ticketEntity.setUser(user);
        TicketEntity savedTicket = ticketService.createTicket(ticketEntity);
        return ResponseEntity.ok(savedTicket);
    }

}
