package com.example.TAASS2022.repository;

import com.example.TAASS2022.model.Ticket;
import com.example.TAASS2022.model.TicketCategories;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TicketRepository extends CrudRepository<Ticket, Long> {
    
    
    @Query("select t from Ticket t where t.category = :category")
    List<Ticket> findByCategory(@Param("category") TicketCategories category);

    @Query("select t from Ticket t where t.price > :p1 AND t.price < :p2")
    List<Ticket> findByRange(@Param("p1") double p1, @Param("p2") double p2);

    @Query("select t from Ticket t where t.price > :p1 AND t.price < :p2 AND t.category = :category")
    List<Ticket> findByCatRange(@Param("p1") double p1, @Param("p2") double p2, @Param("category") TicketCategories category);

    List<Ticket> findByNameContainingIgnoreCase(String name);
}
