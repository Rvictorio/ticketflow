package com.ticketflow.menager.Service;

import com.ticketflow.menager.Dto.TicketDTO;
import com.ticketflow.menager.Entity.Ticket;
import com.ticketflow.menager.Entity.Users;
import com.ticketflow.menager.Repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UsersService userService;

    public TicketDTO createTicket(TicketDTO ticketDTO) {


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

    public List<TicketDTO> getAllTickets() {
        List<Ticket> ticketList = ticketRepository.findAll();

        return ticketList.stream()
                .map(ticket -> new TicketDTO(
                        ticket.getId(),
                        ticket.getTitle(),
                        ticket.getDescription(),
                        ticket.getStatus(),
                        ticket.getPriority(),
                        ticket.getCreator().getEmail(),
                        ticket.getCreatedAt(),
                        ticket.getUpdatedAt()
                ))
                .collect(Collectors.toList());
    }

    public void deleteTicket(Long id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket não encontrado"));
        ticketRepository.delete(ticket);
    }


    public TicketDTO updateTicket(Long id, TicketDTO ticketDTO) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket não encontrado!"));

        ticket.setTitle(ticketDTO.title());
        ticket.setDescription(ticketDTO.description());
        ticket.setStatus(String.valueOf(ticketDTO.status()));
        ticket.setPriority(String.valueOf(ticketDTO.priority()));
        ticket.setUpdatedAt(ticketDTO.updatedAt());

        if (ticketDTO.creatorEmail() != null && !ticketDTO.creatorEmail().equals(ticket.getCreator().getEmail())) {
            Users creator = userService.getUserByEmail(ticketDTO.creatorEmail());
            ticket.setCreator(creator);
        }

        Ticket updatedTicket = ticketRepository.save(ticket);

        return new TicketDTO(
                updatedTicket.getId(),
                updatedTicket.getTitle(),
                updatedTicket.getDescription(),
                updatedTicket.getStatus(),
                updatedTicket.getPriority(),
                updatedTicket.getCreator().getEmail(),
                updatedTicket.getCreatedAt(),
                updatedTicket.getUpdatedAt()
        );
    }


}
