import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {TicketOrder} from '../models/ticket-order.model';
import {EcommerceService} from '../services/EcommerceService';
import {Subscription} from 'rxjs/internal/Subscription';
import {Ticket} from '../models/ticket.model';
import {faPlus, faMinus} from '@fortawesome/free-solid-svg-icons';

@Component({
    selector: 'app-tickets',
    templateUrl: './tickets.component.html',
    styleUrls: ['./tickets.component.css']
})
export class TicketsComponent implements OnInit {
    ticketOrders: TicketOrder[] = [];
    tickets: Ticket[] = [];
    selectedTicketOrder: TicketOrder;
    sub: Subscription;
    ticketSelected = false;
    listName: string[] = [];
    faMinus = faMinus;
    faPlus = faPlus;


    constructor(public ecommerceService: EcommerceService) {

    }

    ngOnInit() {
        this.ticketOrders = [];
    }
    addToCart(order: TicketOrder) {
        this.ecommerceService.SelectedTicketOrder = order;
        this.selectedTicketOrder = this.ecommerceService.SelectedTicketOrder;
        // rimozione
        this.ticketSelected = true;
    }

    removeFromCart(ticketOrder: TicketOrder) {
        const index = this.getTicketIndex(ticketOrder.ticket);
        if (index > -1) {
            this.ecommerceService.TicketOrders.ticketOrders.splice(
                this.getTicketIndex(ticketOrder.ticket), 1);
        }
        console.log(this.calculateTotal(this.ecommerceService.TicketOrders.ticketOrders));
        this.ecommerceService.changeTotal((this.calculateTotal(this.ecommerceService.TicketOrders.ticketOrders)));
       this.ticketSelected = false;
    }

    getTicketIndex(ticket: Ticket): number {
        return this.ecommerceService.TicketOrders.ticketOrders.findIndex(
            value => value.ticket.id === ticket.id);
    }

    isTicketSelected(ticket: Ticket): boolean {
        return this.getTicketIndex(ticket) > -1;
    }


    loadTickets(i: number) {
        this.ecommerceService.ticketOrders2 = [];
        this.ecommerceService.getCategoryUrl(i)
            .subscribe(
                (tickets: any[]) => {
                    this.tickets = tickets;
                    this.tickets.forEach(ticket => {
                        this.ecommerceService.ticketOrders2.push(new TicketOrder(ticket, 0));
                    });
                },
                (error) => console.log(error)
            );
    }

    private calculateTotal(tickets: TicketOrder[]): number {
        let sum = 0;
        tickets.forEach(value => {
            sum += (value.ticket.price * value.quantity);
        });
        return sum;
    }
    reset() {
        this.ticketOrders = [];
        this.loadTickets(0);
        this.ecommerceService.TicketOrders.ticketOrders = [];
        this.ticketSelected = false;
    }
}
