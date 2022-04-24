import {TicketOrder} from '../models/ticket-order.model';
import {Subject} from 'rxjs/internal/Subject';
import {TicketOrders} from '../models/ticket-orders.model';
import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';


@Injectable()
export class EcommerceService {
    private ticketsUrl = '/api/tickets';
    private ordersUrl = '/api/orders';
    Urls = ['/api/tickets', '/api/tickets/Sport', '/api/tickets/Cinema', '/api/tickets/Concert'];

    private ticketOrder: TicketOrder;
    private orders: TicketOrders = new TicketOrders();

    private ticketOrderSubject = new Subject();
    private ordersSubject = new Subject();
    private totalSubject = new Subject();
    private _category = 1;
    private _ticketOrders2: TicketOrder[] = [];
    total: number;
    ss = false;
    valueSource: Subject<boolean> = new Subject();
    TicketOrderChanged = this.ticketOrderSubject.asObservable();
    OrdersChanged = this.ordersSubject.asObservable();
    TotalChanged = this.totalSubject.asObservable();
    public isLoggedin = false;

    constructor(private http: HttpClient) {
    }

    async getAllTickets() {
        return this.http.get(this.ticketsUrl);
    }
    getURLIndex(id: number) {
        console.log(id);
        let str = '';
        return str += this.ticketsUrl + '/id/' + String(id);
    }
    getTicketbyID(id: number) {
        return this.http.get(this.getURLIndex(id));
    }
    getCategoryUrl(i: number) {
        return this.http.get(this.Urls[i]);
    }
    saveOrder(order: TicketOrders) {
        return this.http.post(this.ordersUrl, order);
    }

    set SelectedTicketOrder(value: TicketOrder) {
        this.ticketOrder = value;
        this.ticketOrderSubject.next();
    }

    get SelectedTicketOrder() {
        return this.ticketOrder;
    }

    set TicketOrders(value: TicketOrders) {
        this.orders = value;
        this.ordersSubject.next();
    }

    get TicketOrders() {
        return this.orders;
    }

    get Total() {
        if (this.total > 0) {
            return Math.round(this.total * 100) / 100;
        } else {return 0; }
    }

    set Total(value: number) {
        this.total = value;
        this.totalSubject.next();
    }
    changeTotal(value: number) {
        this.total = value;
        this.totalSubject.next();
    }

    changeFoo(ss: boolean) {
        this.valueSource.next(ss);
    }
    getTicketByRange(min: number, max: number, category: number) {
        if (category === 0) {
            return this.http.get(this.Urls[category] + '/' + min + '/-/' + max);
        } else {
            return this.http.get(this.Urls[category] + '/' + min + '/' + max);
        }
    }
    get category(): number {
        return this._category;
    }

    set category(value: number) {
        this._category = value;
    }
    get ticketOrders2(): TicketOrder[] {
        return this._ticketOrders2;
    }

    set ticketOrders2(value: TicketOrder[]) {
        this._ticketOrders2 = value;
    }
    isLogged() {
        return this.isLoggedin;
    }
}
