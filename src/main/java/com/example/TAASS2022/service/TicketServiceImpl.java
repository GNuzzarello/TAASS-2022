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
        ticketRepository.save(new Ticket(1L, "Blanco", 299.99, TicketCategories.Concert,  "https://i.postimg.cc/T1Q3J7WR/Schermata-2022-04-25-alle-16-23-12.png", "Blanco, l’artista rivelazione vincitore del Festival di Sanremo 2022, arriverà con il suo attesissimo Blu Celeste tour nel 2022", "Milano", "18-09-2022"));
        ticketRepository.save(new Ticket(2L, "Vasco", 199.99, TicketCategories.Concert,  "https://i.postimg.cc/cHS3Dz6Y/Schermata-2022-04-25-alle-16-31-52.png", "Dopo il grandissimo successo del precedente tour, Vasco si esibirà in 10 concerti imperdibili tra maggio e giugno 2022", "Firenze", "27-05-2022"));
        ticketRepository.save(new Ticket(3L, "Laura Pausini", 99.99, TicketCategories.Concert,  "https://i.postimg.cc/RF4R3h7j/Schermata-2022-04-25-alle-16-35-50.png", "Diventata la cantante italiana più nota al mondo, Laura Pausini ha collaborato con artisti del calibro di Ennio Morricone, Michael Jackson, Michael Bublè e Madonna", "Verona", "28-08-2022"));
        ticketRepository.save(new Ticket(4L, "Juventus-Lazio", 59.99, TicketCategories.Sport,  "https://i.postimg.cc/TYtg0QrN/Schermata-2022-04-25-alle-16-44-46.png", "La Juventus affronterà la Lazio nella 37 giornata del campionato italiano di calcio.", "Torino", "16-05-2022"));
        ticketRepository.save(new Ticket(5L, "Milan-Atalanta", 199.99, TicketCategories.Sport,  "https://i.postimg.cc/VsBVfQ8S/Schermata-2022-04-25-alle-16-48-08.png", "Il Milan affronterà l'Atalanta nella 37 giornata del campionato italiano di calcio. Incontro decisivo per la vittoria dello scudetto.", "Milano", "15-05-2022"));
        ticketRepository.save(new Ticket(6L, "Sonic 2 - Il FIlm", 9.99, TicketCategories.Cinema,  "https://i.postimg.cc/VsWm3VZg/Schermata-2022-04-25-alle-16-52-04.png", "Cast: James Marsden, Ben Schwartz, Tika Sumpter, Natasha Rothwell, Adam Pally, Jim Carrey", "The Space Cinema - Torino", "29-05-2022"));
        ticketRepository.save(new Ticket(7L, "The Lost City", 89.99, TicketCategories.Cinema,  "https://i.postimg.cc/V63D5BF4/Schermata-2022-04-25-alle-16-55-18.png", "Cast: Sandra Bullock, Channing Tatum, Daniel Radcliffe, Da'Vine Joy Randolph", "Cinema Eliseo - Torino", "30-09-2022"));
    }

}
