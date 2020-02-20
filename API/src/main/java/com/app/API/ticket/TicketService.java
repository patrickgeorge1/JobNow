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

    public void addTicket(User author, Integer type, Long target, String title, String description){
        Ticket new_ticket = new Ticket(type, author, null, target, title, description, "", 0, new Date(), null);
        this.ticketRepository.save(new_ticket);
    }
}
