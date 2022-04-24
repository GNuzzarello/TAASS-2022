package com.example.TAASS2022.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public class OrderTicket {

    @EmbeddedId
    @JsonIgnore
    private OrderTicketPK pk;

    @Column(nullable = false) private Integer quantity;

    public OrderTicket() {
        super();
    }

    public OrderTicket(Order order, Ticket ticket, Integer quantity) {
        pk = new OrderTicketPK();
        pk.setOrder(order);
        pk.setTicket(ticket);
        this.quantity = quantity;
    }

    @Transient
    public Ticket getTicket() {
        return this.pk.getTicket();
    }

    @Transient
    public Double getTotalPrice() {
        return getTicket().getPrice() * getQuantity();
    }

    public OrderTicketPK getPk() {
        return pk;
    }

    public void setPk(OrderTicketPK pk) {
        this.pk = pk;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((pk == null) ? 0 : pk.hashCode());

        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        OrderTicket other = (OrderTicket) obj;
        if (pk == null) {
            if (other.pk != null) {
                return false;
            }
        } else if (!pk.equals(other.pk)) {
            return false;
        }

        return true;
    }
}
