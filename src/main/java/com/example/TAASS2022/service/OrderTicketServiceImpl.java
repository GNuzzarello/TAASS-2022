package com.example.TAASS2022.service;

import com.example.TAASS2022.model.OrderTicket;
import com.example.TAASS2022.repository.OrderTicketRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderTicketServiceImpl implements OrderTicketService {

    private OrderTicketRepository orderTicketRepository;

    public OrderTicketServiceImpl(OrderTicketRepository orderTicketRepository) {
        this.orderTicketRepository = orderTicketRepository;
    }

    @Override
    public OrderTicket create(OrderTicket orderTicket) {
        return this.orderTicketRepository.save(orderTicket);
    }
}
