import {Component, EventEmitter, OnDestroy, OnInit, Output, ViewChild} from '@angular/core';
import {TicketOrders} from '../models/ticket-orders.model';
import {TicketOrder} from '../models/ticket-order.model';
import {EcommerceService} from '../services/EcommerceService';
import {Subscription} from 'rxjs/internal/Subscription';
import { faCoffee, faPhone, faShoppingCart} from '@fortawesome/free-solid-svg-icons';
import {Router} from '@angular/router';

@Component({
    selector: 'app-shopping-cart',
    templateUrl: './shopping-cart.component.html',
    styleUrls: ['./shopping-cart.component.css']
})
export class ShoppingCartComponent implements OnInit, OnDestroy {
    faShoppingCart = faShoppingCart;
    orderFinished: boolean;
    orders: TicketOrders;
    total: number = this.ecommerceService.Total;
    sub: Subscription;

    // tslint:disable-next-line:no-output-on-prefix
    @Output() onOrderFinished: EventEmitter<boolean>;
    constructor(public ecommerceService: EcommerceService, private router: Router) {
        this.total = this.ecommerceService.Total;
        this.orderFinished = false;
        this.onOrderFinished = new EventEmitter<boolean>();
    }

    ngOnInit() {
        this.orders = new TicketOrders();
        this.loadCart();
        this.loadTotal();
    }


    private calculateTotal(tickets: TicketOrder[]): number {
        let sum = 0;
        tickets.forEach(value => {
            sum += (value.ticket.price * value.quantity);
        });
        return sum;
    }

    ngOnDestroy() {
        this.sub.unsubscribe();
    }

    finishOrder() {
        this.orderFinished = true;
        this.ecommerceService.Total = this.total;
        this.onOrderFinished.emit(this.orderFinished);
        this.router.navigate([':id/payments']);
    }

    loadTotal() {
        this.sub = this.ecommerceService.OrdersChanged.subscribe(() => {
            this.ecommerceService.Total = this.calculateTotal(this.orders.ticketOrders);
            this.total = this.ecommerceService.Total;
        });
    }

    loadCart() {
        this.sub = this.ecommerceService.TicketOrderChanged.subscribe(() => {
            const ticketOrder = this.ecommerceService.SelectedTicketOrder;
            if (ticketOrder) {
                this.orders.ticketOrders.push(new TicketOrder(
                    ticketOrder.ticket, ticketOrder.quantity));
            }
            this.ecommerceService.TicketOrders = this.orders;
            this.orders = this.ecommerceService.TicketOrders;
            this.ecommerceService.Total = this.calculateTotal(this.orders.ticketOrders);
            this.total = this.ecommerceService.Total;
        });
    }

    reset() {
        this.orderFinished = false;
        this.orders = new TicketOrders();
        this.orders.ticketOrders = [];
        this.loadTotal();
        this.total = 0;
    }
}
