package com.ticketflow.menager.Controller;

import com.ticketflow.menager.Dto.TicketDTO;
import com.ticketflow.menager.Entity.Ticket;
import com.ticketflow.menager.Service.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    public ResponseEntity<TicketDTO> createTicket(@RequestBody TicketDTO ticketDTO) {
        TicketDTO newTicket = ticketService.createTicket(ticketDTO);
        return ResponseEntity.ok(newTicket);
    }

    @GetMapping("/creator/{email}")
    public ResponseEntity<List<Ticket>> getTicketsByCreatorEmail(@PathVariable String email) {
        List<Ticket> tickets = ticketService.getTicketsByCreatorEmail(email);
        return ResponseEntity.ok(tickets);
    }

    @GetMapping
    public ResponseEntity<List<TicketDTO>> getAllTickets() {
        List<TicketDTO> tickets = ticketService.getAllTickets();
        return ResponseEntity.ok(tickets);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long id) {
        ticketService.deleteTicket(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TicketDTO> updatedTicket(@PathVariable Long id, @RequestBody TicketDTO ticketDTO) {
        TicketDTO updatedTicket = ticketService.updateTicket(id, ticketDTO);
        return ResponseEntity.ok(updatedTicket);
    }
}
