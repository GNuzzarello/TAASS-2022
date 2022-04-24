import {Component, EventEmitter, OnInit, Output, ViewChild} from '@angular/core';
import {TicketsComponent} from '../tickets/tickets.component';
import {ShoppingCartComponent} from '../shopping-cart/shopping-cart.component';
import {OrdersComponent} from '../orders/orders.component';
import {SlideshowComponent} from '../slideshow/slideshow.component';
import {EcommerceService} from '../services/EcommerceService';
import {RangebarComponent} from '../rangebar/rangebar.component';

@Component({
  selector: 'app-cards',
  templateUrl: './cards.component.html',
  styleUrls: ['./cards.component.css']
})
export class CardsComponent implements OnInit {
    orderFinished = false;
    category: string;
    @ViewChild('ticketsC')
    ticketsC: TicketsComponent;

    @ViewChild('rangebar')
    rangebar: RangebarComponent;

    @ViewChild('slideC')
    slideC: SlideshowComponent;

    @ViewChild('shoppingCartC')
    shoppingCartC: ShoppingCartComponent;

    @ViewChild('ordersC')
    ordersC: OrdersComponent;
    public loadComponent = false;
    isCardclicked = true;
    ngOnInit() {}
    clickedCard(i: number) {
        this.ecommerceService.category = i;
        this.isCardclicked = false;
        this.ticketsC.loadTickets(i);
        this.ecommerceService.changeFoo(true);
    }

    constructor(private ecommerceService: EcommerceService) {
    }

    finishOrder(orderFinished: boolean) {
        this.orderFinished = orderFinished;
    }
    homereset() {
        this.isCardclicked = true;
        this.ecommerceService.changeFoo(false);
    }
    reset() {
        this.orderFinished = false;
        this.ticketsC.reset();
        this.shoppingCartC.reset();
        this.ordersC.paid = false;
        this.homereset();
    }
}
