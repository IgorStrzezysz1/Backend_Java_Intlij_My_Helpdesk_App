package pl.igor.backend.myhelpdeskbackend.api.mapper;  // Pakiet – określa, że ta klasa znajduje się w folderze 'api.mapper'.

import org.springframework.stereotype.Service;    // Import adnotacji @Service – klasa będzie zarządzana przez Springa (do wstrzykiwania zależności).
import pl.igor.backend.myhelpdeskbackend.api.dto.TicketDto;  // Import klasy DTO – reprezentuje dane zgłoszenia przesyłane do frontendu.
import pl.igor.backend.myhelpdeskbackend.model.TicketEntity;  // Import klasy encji – reprezentuje dane zgłoszenia w bazie danych.

@Service    // Adnotacja – informuje Springa, że ta klasa to komponent serwisowy (może być automatycznie wstrzykiwana).
public class TicketMapper {  // Klasa mapująca – służy do konwersji encji JPA na DTO.
    public TicketDto mapToTicketDto(TicketEntity ticketEntity) {   // Metoda mapująca TicketEntity (z bazy danych) na TicketDto (do wysyłki do frontendu).
        TicketDto ticketDto = new TicketDto(   // Tworzy nowy obiekt DTO na podstawie danych z encji.
                ticketEntity.getId(),   // Pobiera ID zgłoszenia z encji.
                ticketEntity.getTitle(),  // Pobiera tytuł zgłoszenia.
                ticketEntity.getDescription(),  // Pobiera opis zgłoszenia.
                ticketEntity.getStatus(),  // Pobiera status zgłoszenia.
                ticketEntity.getContact());   // Pobiera dane kontaktowe zgłaszającego.
        return ticketDto;  // Zwraca gotowy obiekt TicketDto.
    }
}
