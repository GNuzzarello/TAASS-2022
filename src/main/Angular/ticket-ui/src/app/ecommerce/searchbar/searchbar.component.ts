import {Component, OnInit, ViewChild} from '@angular/core';
import {FilterpipePipe} from '../../filterpipe.pipe';
import {TicketsComponent} from '../tickets/tickets.component';
import {EcommerceService} from '../services/EcommerceService';
import {Ticket} from '../models/ticket.model';
import {faSearch} from '@fortawesome/free-solid-svg-icons';
import {Router} from '@angular/router';

@Component({
    selector: 'app-searchbar',
    templateUrl: './searchbar.component.html',
    styleUrls: ['./searchbar.component.css']
})

export class SearchbarComponent implements OnInit {
    faSearch = faSearch;
    searchText = '';
    tickets: Ticket[] = [];
    characters = [
        'Ant-Man',
        'Aquaman',
        'Asterix',
        'The Atom',
        'The Avengers',
        'Batgirl',
        'Batman',
        'Batwoman'
    ];
    enterpressed = false;
    filtered = [];
    @ViewChild('ticketsC')
    ticketsC: TicketsComponent;
    listName: [string, number] [] = [];
    constructor(private ecommerceService: EcommerceService, private r: Router) { }

    ngOnInit(): void {
        this.listName = this.loadTicketsName();
    }
    loadTicketsName() {
        this.ecommerceService.getCategoryUrl(0)
            .subscribe(
                (tickets: any[]) => {
                    this.tickets = tickets;
                    this.tickets.forEach(ticket => {
                        this.listName.push([ticket.name, ticket.id]);
                    });
                },
                (error) => console.log(error)
            );
        return this.listName;
    }



    search(searchText: string) {
        this.filtered = new FilterpipePipe().transform(this.listName, searchText);
        this.enterpressed = true;
    }

    setSelectedItem($event: MouseEvent, pr: any) {
        // @ts-ignore
        this.r.navigate(['/' + pr]);
    }

}
