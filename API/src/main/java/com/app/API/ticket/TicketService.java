package com.app.API.ticket;

import com.app.API.user.User;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TicketService {
    private TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public void addTicket(User from, User to) {
        Ticket ticket = new Ticket(1, from, to, 3, "Titlu adaugat", "Descriere", "Raspuns", 2, new Date(), new Date());
        this.ticketRepository.save(ticket);
    }
}
