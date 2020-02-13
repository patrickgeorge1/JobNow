package com.app.API.ticket;

import com.app.API.user.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class TicketController {
    private TicketService ticketService;
    private TicketRepository ticketRepository;

    public TicketController(TicketService ticketService, TicketRepository ticketRepository) {
        this.ticketService = ticketService;
        this.ticketRepository = ticketRepository;
    }

    @RequestMapping(path = "api/public/ticket", method = RequestMethod.POST)
    public @ResponseBody String postTIcket(@RequestParam("user1") User user1, @RequestParam("user2") User user2) {
        try {
            this.ticketService.addTicket(user1, user2);
            return "Success";
        } catch (Exception e) {
            return "Faiked";
        }
    }

    @RequestMapping(path = "api/public/ticket", method = RequestMethod.GET)
    public @ResponseBody List<Ticket> getAll() {
        return new ArrayList<>(ticketRepository.findAll());
    }

    @RequestMapping(path = "api/public/admin/ticket", method = RequestMethod.GET)
    public @ResponseBody List<Ticket> getAllAdmin() {
        return new ArrayList<>(ticketRepository.findAll());
    }
}
