import {Ticket} from './ticket.model';

export class TicketOrder {
    ticket: Ticket;
    quantity: number;

    constructor(ticket: Ticket, quantity: number) {
        this.ticket = ticket;
        this.quantity = quantity;
    }

    getTicket() {
        return this.ticket;
    }
}
