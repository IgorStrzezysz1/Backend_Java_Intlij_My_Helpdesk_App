package pl.igor.backend.myhelpdeskbackend.service; // Pakiet – klasa znajduje się w folderze 'service', razem z logiką aplikacji.

import org.springframework.stereotype.Service; // Adnotacja – pozwala Springowi zarządzać tą klasą jako serwisem (komponentem biznesowym).
import pl.igor.backend.myhelpdeskbackend.api.dto.TicketAddDto; // DTO – dane wejściowe przy tworzeniu zgłoszenia.
import pl.igor.backend.myhelpdeskbackend.api.dto.TicketDto; // DTO – dane wyjściowe (np. do wyświetlenia listy zgłoszeń).
import pl.igor.backend.myhelpdeskbackend.api.mapper.TicketMapper; // Klasa pomocnicza – konwertuje encje do DTO i odwrotnie.
import pl.igor.backend.myhelpdeskbackend.model.TicketEntity; // Encja – odwzorowuje zgłoszenie (ticket) w bazie danych.
import pl.igor.backend.myhelpdeskbackend.repository.TicketRepository; // Repozytorium – dostęp do bazy danych (tickets).

import java.util.List; // Lista – używana do zwracania wielu zgłoszeń.
import java.util.stream.Collectors; // Pomocnicza klasa – używana do konwertowania listy encji na listę DTO.

@Service // Spring automatycznie rozpozna klasę jako serwis i wstrzyknie ją tam, gdzie trzeba.
public class TicketService { // Główna klasa serwisu odpowiedzialna za logikę zgłoszeń (ticketów).

    private final TicketRepository ticketRepository; // Repozytorium – do zapisywania i odczytywania zgłoszeń.
    private final TicketMapper ticketMapper; // Mapper – do konwersji encja <-> DTO.

    public TicketService(TicketRepository ticketRepository, TicketMapper ticketMapper) {
        // Konstruktor – Spring wstrzykuje repozytorium i mapper.
        this.ticketRepository = ticketRepository;
        this.ticketMapper = ticketMapper;
    }

    public TicketDto createTicket(TicketAddDto ticketAddDto){
        // Tworzy nowe zgłoszenie na podstawie danych z DTO.

        TicketEntity ticketEntity = new TicketEntity(
                null, // ID ustawiamy na null – baza sama go wygeneruje.
                ticketAddDto.getTitle(), // Tytuł zgłoszenia.
                "active", // Domyślny status – „active”.
                ticketAddDto.getDescription(), // Opis zgłoszenia.
                ticketAddDto.getContact() // Dane kontaktowe.
        );

        ticketEntity = ticketRepository.save(ticketEntity); // Zapisuje zgłoszenie do bazy danych.

        return ticketMapper.mapToTicketDto(ticketEntity); // Zwraca DTO do wyświetlenia w aplikacji.
    }

    public List<TicketDto> getAllTicketsActive(){
        // Pobiera wszystkie aktywne zgłoszenia.

        List<TicketEntity> activeTickets = ticketRepository.findAllActive(); // Pobranie zgłoszeń ze statusem „active”.

        return activeTickets.stream() // Konwersja listy encji...
                .map(ticketMapper::mapToTicketDto) // ...na listę DTO...
                .collect(Collectors.toList()); // ...i zwrócenie wyniku.
    }

    public List<TicketDto> getAllTicketsArchival(){
        // Pobiera wszystkie archiwalne zgłoszenia.

        List<TicketEntity> archivalTickets = ticketRepository.findAllArchival(); // Zgłoszenia o statusie „archived”.

        return archivalTickets.stream()
                .map(ticketMapper::mapToTicketDto)
                .collect(Collectors.toList());
    }

    public TicketDto updateTicket(Long id, TicketDto dto) {
        // Aktualizuje zgłoszenie o podanym ID na podstawie przesłanych danych.

        TicketEntity ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found")); // Jeśli nie znajdzie zgłoszenia – rzuca wyjątek.

        if (dto.getTitle() != null) {
            ticket.setTitle(dto.getTitle()); // Aktualizacja tytułu, jeśli został podany.
        }
        if (dto.getDescription() != null) {
            ticket.setDescription(dto.getDescription()); // Aktualizacja opisu.
        }
        if(dto.getStatus() != null){
            ticket.setStatus(dto.getStatus()); // Aktualizacja statusu.
        }

        ticketRepository.save(ticket); // Zapisanie zmian do bazy.
        return ticketMapper.mapToTicketDto(ticket); // Zwrócenie zaktualizowanego zgłoszenia jako DTO.
    }
}
