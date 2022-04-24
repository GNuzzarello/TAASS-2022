package com.example.TAASS2022.controller;

import com.example.TAASS2022.model.Ticket;
import com.example.TAASS2022.model.TicketCategories;
import com.example.TAASS2022.service.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class TicketController {
    private TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/tickets")
    public @NotNull Iterable<Ticket> getTickets() {
        return ticketService.getAllTickets();
    }


    @GetMapping("/test")
    public @NotNull String getProva() {
        return "this is a test";
    }
    @PostMapping("/tickets")
    public @NotNull Ticket putTicket(@RequestBody Ticket p) {
        return ticketService.save(p);
    }

    @GetMapping("/tickets/{category}")
    public @NotNull Iterable<Ticket> getTicketsByCategory(@PathVariable("category") TicketCategories category) {
        return ticketService.findByCategory(category);
    }
    @GetMapping("/tickets/{p1}/-/{p2}")
    public @NotNull Iterable<Ticket> getTicketsByRange(@PathVariable("p1")double p1,@PathVariable("p2")double  p2) {
        return ticketService.findByRange(p1, p2);
    }

    @GetMapping("/tickets/{category}/{p1}/{p2}")
    public @NotNull Iterable<Ticket> getTicketByCatRange(@PathVariable("p1")double p1,@PathVariable("p2")double  p2,@PathVariable("category")TicketCategories category ) {
        return ticketService.findByCatRange(p1, p2, category);
    }

    @GetMapping("/tickets/id/{id}")
    public @NotNull Ticket getTicketsById( @PathVariable("id") long id){
        return ticketService.getTicket(id);
    }

    @GetMapping("/tickets/search={name}")
    public @NotNull Iterable<Ticket> findByName(@PathVariable("name") String name) {
        return ticketService.findByNameContainingIgnoreCase(name);
    }

    @DeleteMapping("/tickets/{id}")
    public ResponseEntity<String> deleteTicket(@PathVariable("id") long id) {
        System.out.println("Delete Ticket with ID = " + id + "...");

        ticketService.deleteTicketByID(id);

        return new ResponseEntity<>("Ticket has been deleted!", HttpStatus.OK);
    }

    @DeleteMapping("/tickets/delete")
    public ResponseEntity<String> deleteAllTickets() {
        System.out.println("Delete All Tickets...");

        ticketService.deleteAllTickets();

        return new ResponseEntity<>("All Tickets have been deleted!", HttpStatus.OK);
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<Ticket> updateTicketByID(@PathVariable("id") long id, @RequestBody Ticket ticket) {
        System.out.println("Update Ticket with ID = " + id + "...");

        Optional<Ticket> ticketData = Optional.ofNullable(ticketService.getTicket(id));

        if (ticketData.isPresent()) {
            Ticket _ticket = ticketData.get();
            _ticket.setName(ticket.getName());
            _ticket.setPrice(ticket.getPrice());
            _ticket.setPictureUrl(ticket.getPictureUrl());
            _ticket.setCategory(ticket.getCategory());
            _ticket.setPlace(ticket.getPlace());
            _ticket.setDate(ticket.getDate());
            return new ResponseEntity<>(ticketService.save(_ticket), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
