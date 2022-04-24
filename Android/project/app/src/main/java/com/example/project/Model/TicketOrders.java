package com.example.project.Model;

import java.util.ArrayList;

public class TicketOrders {
    ArrayList<TicketOrder> ticketOrders;

    public TicketOrders(ArrayList<TicketOrder> ticketOrders) {
        this.ticketOrders = ticketOrders;
    }

    public ArrayList<TicketOrder> getTicketOrders() {
        return ticketOrders;
    }

    public void setTicketOrders(ArrayList<TicketOrder> ticketOrders) {
        this.ticketOrders = ticketOrders;
    }

}
