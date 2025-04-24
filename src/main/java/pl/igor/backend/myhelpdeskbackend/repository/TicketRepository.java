package pl.igor.backend.myhelpdeskbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.igor.backend.myhelpdeskbackend.model.TicketEntity;

import java.util.List;

public interface TicketRepository extends JpaRepository<TicketEntity, Long> {
    @Query("select t from TicketEntity t where t.status = 'active'")
    List<TicketEntity> findAllActive();

    @Query("select t from TicketEntity t where t.status = 'archival'")
    List<TicketEntity> findAllArchival();
}