import {Component, EventEmitter, OnInit, ViewChild} from '@angular/core';
import {EcommerceService} from '../services/EcommerceService';
import {EcommerceComponent} from '../ecommerce.component';
import { faCoffee, faUser, faShoppingCart, faLaptop, faTicketAlt} from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
    collapsed = true;
    orderFinished = false;

    @ViewChild('navbar')
    navbar: EcommerceComponent;

    faShoppingCart = faShoppingCart;
    faUser = faUser;
    faTicketAlt = faTicketAlt;
    hideCart = false;
    constructor(private ecommerceService: EcommerceService) {
    }

  ngOnInit(): void {
        this.receiveValuefromService();
  }
    toggleCollapsed(): void {
        this.collapsed = !this.collapsed;
    }

    finishOrder(orderFinished: boolean) {
        this.orderFinished = orderFinished;
    }
    private receiveValuefromService() {
        this.ecommerceService.valueSource.subscribe(value => {
            this.hideCart = value;
        });
    }
}
