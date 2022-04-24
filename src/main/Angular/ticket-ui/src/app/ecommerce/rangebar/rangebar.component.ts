import {Component, OnInit, ViewChild} from '@angular/core';
import { Options, LabelType } from '@angular-slider/ngx-slider';
import {EcommerceService} from '../services/EcommerceService';
import {TicketsComponent} from '../tickets/tickets.component';
import {SlideshowComponent} from '../slideshow/slideshow.component';
import {ShoppingCartComponent} from '../shopping-cart/shopping-cart.component';
import {OrdersComponent} from '../orders/orders.component';
import {TicketOrder} from '../models/ticket-order.model';
import {Ticket} from '../models/ticket.model';

@Component({
  selector: 'app-rangebar',
  templateUrl: './rangebar.component.html',
  styleUrls: ['./rangebar.component.css']
})
export class RangebarComponent implements OnInit {
    @ViewChild('ticketsC')
    ticketsC: TicketsComponent;

    @ViewChild('slideC')
    slideC: SlideshowComponent;

    @ViewChild('shoppingCartC')
    shoppingCartC: ShoppingCartComponent;

    @ViewChild('ordersC')
    tickets: Ticket[] = [];
    ordersC: OrdersComponent;
    value = 0;
    highvalue = 1000;
    options: Options = {
        floor: 0,
        ceil: 1000,
        showSelectionBar: true,
        translate: (value: number, label: LabelType): string => {
            switch (label) {
                case LabelType.Low:
                    return '<b>Min price:</b> $' + value;
                case LabelType.High:
                    return '<b>Max price:</b> $' + value;
                default:
                    return '$' + value;
            }
        },
        getPointerColor: (): string => {
            return '#202867';
        }
    };
    loadTicketsByRange(i: number, min: number, max: number) {
        this.ecommerceService.ticketOrders2 = [];
        this.ecommerceService.getTicketByRange(min, max, i)
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
    sliderEvent() {
        this.loadTicketsByRange( this.ecommerceService.category, this.value, this.highvalue);
    }

  constructor(private ecommerceService: EcommerceService) { }

  ngOnInit(): void { }

}
