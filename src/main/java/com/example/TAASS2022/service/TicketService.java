package com.example.TAASS2022.service;

import com.example.TAASS2022.model.Ticket;
import com.example.TAASS2022.model.TicketCategories;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Validated
public interface TicketService {

    @NotNull Iterable<Ticket> getAllTickets();

    void deleteAllTickets();
    void deleteTicketByID(long id);
    void initialBoot();

    Iterable<Ticket> findByCategory(TicketCategories category);

    Iterable<Ticket> findByRange(double p1, double p2);

    Iterable<Ticket> findByCatRange(double p1, double p2, TicketCategories category);

    Iterable<Ticket> findByNameContainingIgnoreCase(String name);


    Ticket getTicket(@Min(value = 1L, message = "Invalid Ticket ID.") long id);

    Ticket save(Ticket Ticket);



}
