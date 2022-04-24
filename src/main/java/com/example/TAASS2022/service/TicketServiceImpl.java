package com.example.TAASS2022.service;

import com.example.TAASS2022.exception.ResourceNotFoundException;
import com.example.TAASS2022.model.Ticket;
import com.example.TAASS2022.model.TicketCategories;
import com.example.TAASS2022.repository.TicketRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Transactional
public class TicketServiceImpl implements TicketService {

    private TicketRepository ticketRepository;

    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public Iterable<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public Iterable<Ticket> findByNameContainingIgnoreCase(String name) {
        return ticketRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public void deleteAllTickets(){ ticketRepository.deleteAll(); }

    @Override
    public void deleteTicketByID(long id) { ticketRepository.deleteById(id);}

    @Override
    public Iterable<Ticket> findByCategory(TicketCategories category) {  return ticketRepository.findByCategory(category);}

    @Override
    public Iterable<Ticket> findByRange(double p1, double p2) {  return ticketRepository.findByRange(p1,p2);}

    @Override
    public Iterable<Ticket> findByCatRange(double p1, double p2, TicketCategories category) {  return ticketRepository.findByCatRange(p1,p2, category);}

    @Override
    public Ticket getTicket(long id) {
        return ticketRepository
          .findById(id)
          .orElseThrow(() -> new ResourceNotFoundException("Ticket not found"));
    }

    @Override
    public Ticket save(Ticket ticket) {
        return ticketRepository.save(ticket);
    }


    @Override
    public void initialBoot(){
        ticketRepository.save(new Ticket(1L, "ProvaS", 99.99, TicketCategories.Sport,  "https://i.postimg.cc/nzL7M8Qj/prova.jpg", "descrizione di prova1", "place_prova1", "16-04-2022"));
        ticketRepository.save(new Ticket(2L, "ProvaCI", 199.99, TicketCategories.Cinema,  "https://i.postimg.cc/nzL7M8Qj/prova.jpg", "descrizione di prova2", "place_prova2", "17-04-2022"));
        ticketRepository.save(new Ticket(3L, "ProvaCO", 299.99, TicketCategories.Concert,  "https://i.postimg.cc/nzL7M8Qj/prova.jpg", "descrizione di prova3", "place_prova3", "18-04-2022"));
        ticketRepository.save(new Ticket(4L, "ProvaCOMMIT", 299.99, TicketCategories.Concert,  "https://i.postimg.cc/nzL7M8Qj/prova.jpg", "descrizione di prova3", "place_prova3", "18-04-2022"));
    }

}
