import { Component, OnInit } from '@angular/core';
import { faCoffee, faPhone, faShoppingCart} from '@fortawesome/free-solid-svg-icons';
@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent implements OnInit {
    faCoffee = faCoffee;
    faPhone = faPhone;
    faShoppingCart = faShoppingCart;
  constructor() { }
    // tslint:disable-next-line:comment-format
    //<fa-icon [icon]="faShoppingCart"></fa-icon>
  ngOnInit(): void {
  }

}
