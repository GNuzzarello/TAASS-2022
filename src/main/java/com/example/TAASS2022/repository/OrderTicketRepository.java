package com.example.TAASS2022.repository;

import com.example.TAASS2022.model.OrderTicket;
import com.example.TAASS2022.model.OrderTicketPK;
import org.springframework.data.repository.CrudRepository;

public interface OrderTicketRepository extends CrudRepository<OrderTicket, OrderTicketPK> {
}
