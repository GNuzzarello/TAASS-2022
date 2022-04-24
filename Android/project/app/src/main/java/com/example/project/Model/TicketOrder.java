package com.example.project.Model;

public class TicketOrder {

    Ticket ticket;
    int quantity;

    public TicketOrder(Ticket ticket, int num) {
        this.ticket = ticket;
        this.quantity = num;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public int getNum() {
        return quantity;
    }

    public void setNum(int num) {
        this.quantity = num;
    }
}
