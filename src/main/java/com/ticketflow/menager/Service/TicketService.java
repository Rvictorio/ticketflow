package com.ticketflow.menager.Service;

import com.ticketflow.menager.Dto.TicketDTO;
import com.ticketflow.menager.Entity.Ticket;
import com.ticketflow.menager.Entity.Users;
import com.ticketflow.menager.Repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UsersService userService;

    public TicketDTO createTicket(TicketDTO ticketDTO) {
        // Buscar o usuário pelo email
        Users creator = userService.getUserByEmail(ticketDTO.creatorEmail());

        Ticket ticket = new Ticket(
                null,
                ticketDTO.title(),
                ticketDTO.description(),
                ticketDTO.status(),
                ticketDTO.priority(),
                creator,
                ticketDTO.createdAt(),
                ticketDTO.updatedAt()
        );

        Ticket savedTicket = ticketRepository.save(ticket);

        return new TicketDTO(
                savedTicket.getId(),
                savedTicket.getTitle(),
                savedTicket.getDescription(),
                savedTicket.getStatus(),
                savedTicket.getPriority(),
                savedTicket.getCreator().getEmail(),
                savedTicket.getCreatedAt(),
                savedTicket.getUpdatedAt()
        );
    }

    public List<Ticket> getTicketsByCreatorEmail(String email) {
        return ticketRepository.findTicketsByCreatorEmail(email);
    }
}
