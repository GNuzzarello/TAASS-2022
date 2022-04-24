package com.example.TAASS2022.dto;

import com.example.TAASS2022.model.Ticket;

public class OrderTicketDto {
    private Ticket ticket;
    private Integer quantity;

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
