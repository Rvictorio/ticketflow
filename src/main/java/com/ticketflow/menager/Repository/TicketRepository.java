package com.ticketflow.menager.Repository;

import com.ticketflow.menager.Entity.Ticket;
import com.ticketflow.menager.enums.Priority;
import com.ticketflow.menager.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByStatus(Status status);
    List<Ticket> findByPriority(Priority priority);

}
