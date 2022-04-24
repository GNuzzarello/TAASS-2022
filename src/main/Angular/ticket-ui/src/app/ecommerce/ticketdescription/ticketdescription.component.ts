import {Component, OnInit, ViewChild} from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import {Ticket} from '../models/ticket.model';
import {EcommerceService} from '../services/EcommerceService';
import {TicketOrder} from '../models/ticket-order.model';
import {TicketOrders} from '../models/ticket-orders.model';
import {Subscription} from 'rxjs/internal/Subscription';


@Component({
  selector: 'app-ticketdescription',
  templateUrl: './ticketdescription.component.html',
  styleUrls: ['./ticketdescription.component.css'],
})
export class TicketdescriptionComponent implements OnInit {
    id: number;
    inItems: Ticket;
    ticketOrder: TicketOrder;
    orderFinished = false;
    ticketSelected = false;

    sub: Subscription;
    selectedTicketOrder: TicketOrder;
    constructor(private activeRoute: ActivatedRoute, private ecommerceService: EcommerceService) { }
     ngOnInit() {

        this.activeRoute.params.subscribe(
            (params: Params) => {
                this.id = params.id;
            }
        );
         this.loadItem(this.id);
    }

    isTicketSelected(ticket: Ticket): boolean {
        return this.getTicketIndex(ticket) > -1;
    }
    getTicketIndex(ticket: Ticket): number {
        return this.ecommerceService.TicketOrders.ticketOrders.findIndex(
            value => value.ticket.id === ticket.id);
    }
    addToCart(order: TicketOrder) {
        this.ecommerceService.SelectedTicketOrder = order;
        this.selectedTicketOrder = this.ecommerceService.SelectedTicketOrder;
        // tslint:disable-next-line:comment-format
        //rimozione
        this.ticketSelected = true;
    }
    removeFromCart(ticketOrder: TicketOrder) {
        const index = this.getTicketIndex(this.inItems);
        if (index > -1) {
            this.ecommerceService.TicketOrders.ticketOrders.splice(
                this.getTicketIndex(ticketOrder.ticket), 1);
        }
        this.ecommerceService.changeTotal(this.calculateTotal(this.ecommerceService.TicketOrders.ticketOrders));
        this.ticketSelected = false;
    }

    private calculateTotal(tickets: TicketOrder[]): number {
        let sum = 0;
        tickets.forEach(value => {
            sum += (value.ticket.price * value.quantity);
        });
        return sum;
    }
    loadItem(id: number) {
           this.ecommerceService.getTicketbyID(this.id).subscribe((data) => {
               console.log(data);
               this.id = data['id'];
              this.inItems = new Ticket(data['id'], data['name'], data['price'], data['pictureUrl'], data['category'], data['description'], data['place'], data['date']);
              this.ticketOrder = new TicketOrder(this.inItems, 0);
         } );
     }

}
