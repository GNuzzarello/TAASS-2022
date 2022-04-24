package com.example.TAASS2022.service;


import com.example.TAASS2022.model.OrderTicket;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
public interface OrderTicketService {

    OrderTicket create(@NotNull(message = "The products for order cannot be null.") @Valid OrderTicket orderProduct);
}
