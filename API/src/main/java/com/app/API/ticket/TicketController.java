package com.app.API.ticket;

import com.app.API.user.User;
import com.app.API.user.UserRepository;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class TicketController {
    private TicketService ticketService;
    private TicketRepository ticketRepository;
    private UserRepository userRepository;

    public TicketController(TicketService ticketService, TicketRepository ticketRepository, UserRepository userRepository) {
        this.ticketService = ticketService;
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
    }


    @RequestMapping(path = "api/public/ticket", method = RequestMethod.GET)
    public @ResponseBody List<Ticket> getAll() {
        return new ArrayList<>(ticketRepository.findByAuthorId(userRepository.findByUsername((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId()));
    }

    @RequestMapping(path = "api/public/admin/ticket", method = RequestMethod.GET)
    public @ResponseBody List<Ticket> getAllAdmin() {
        return new ArrayList<>(ticketRepository.findAll());
    }

    @RequestMapping(path = "api/public/ticket", method = RequestMethod.POST)
    public @ResponseBody String postTicket(@RequestBody JsonNode json) {
        try {
            ticketService.addTicket(userRepository.findByUsername((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal()), json.get("type").asInt(), json.get("target").asLong(), json.get("title").asText(), json.get("description").asText());
            return "Success";
        } catch (Exception e) {
            return "Failed";
        }
    }
}
