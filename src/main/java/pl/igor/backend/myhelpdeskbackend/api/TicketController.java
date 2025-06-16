package pl.igor.backend.myhelpdeskbackend.api;  // Pakiet – klasa kontrolera znajduje się w folderze 'api'.


import org.springframework.http.ResponseEntity;  // Klasa Springa – pozwala budować odpowiedzi HTTP (np. 200 OK, 404 itd.).
import org.springframework.web.bind.annotation.*;// Importuje adnotacje takie jak: @RestController, @PostMapping, @GetMapping itd
import pl.igor.backend.myhelpdeskbackend.api.dto.TicketAddDto; // DTO używane przy dodawaniu nowego zgłoszenia
import pl.igor.backend.myhelpdeskbackend.api.dto.TicketDto;  // DTO używane przy zwracaniu zgłoszeń.
import pl.igor.backend.myhelpdeskbackend.service.TicketService;// Serwis z logiką działania zgłoszeń (tickets).

import java.util.List;  // Import listy – używana do zwracania wielu zgłoszeń

@RestController  // Adnotacja – klasa działa jako kontroler REST.
@RequestMapping("/api/v1/tickets")   // Wszystkie endpointy zaczynają się od /api/v1/tickets
public class TicketController {   // Klasa kontrolera – obsługuje operacje na zgłoszeniach

    private final TicketService ticketService;  // Serwis z logiką biznesową dotyczącą zgłoszeń.

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;  // Konstruktor – Spring automatycznie wstrzykuje zależność.
    }

    @PostMapping    // Obsługuje żądanie POST /api/v1/tickets
    public ResponseEntity<TicketDto> create(@RequestBody TicketAddDto ticketAddDto) {
        TicketDto ticket = ticketService.createTicket(ticketAddDto);  // Przekazuje dane do serwisu, tworzy nowe zgłoszenie.
        return ResponseEntity.ok(ticket);  // Zwraca nowo utworzone zgłoszenie w odpowiedzi (200 OK).
    }

    @GetMapping("/active")  // Obsługuje żądanie GET /api/v1/tickets/active
    public ResponseEntity<List<TicketDto>> getAllTicketsActive() {
        return ResponseEntity.ok(ticketService.getAllTicketsActive());  // Zwraca listę aktywnych zgłoszeń.
    }

    @GetMapping("/archival")   // Obsługuje żądanie GET /api/v1/tickets/archival
    public ResponseEntity<List<TicketDto>> getAllTicketsArchival() {
        return ResponseEntity.ok(ticketService.getAllTicketsArchival());  // Zwraca listę archiwalnych zgłoszeń.
    }
    @PutMapping("/{id}")  // Obsługuje żądanie PUT /api/v1/tickets/{id}
    public ResponseEntity<TicketDto>  // Aktualizuje zgłoszenie o podanym ID.

    updateTicket(@PathVariable Long id,  // Pobiera ID zgłoszenia z adresu URL.
                 @RequestBody TicketDto ticketDto) {// Dane do aktualizacji – przesyłane w ciele żądania.

        TicketDto updated = ticketService.updateTicket(id, ticketDto);  // Przekazanie do serwisu.
        return ResponseEntity.ok(updated); // Zwraca zaktualizowane zgłoszenie.
    }




}
