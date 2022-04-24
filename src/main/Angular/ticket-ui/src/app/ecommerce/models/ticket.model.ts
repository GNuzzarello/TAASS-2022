export class Ticket {
    id: number;
    name: string;
    category: string;
    price: number;
    pictureUrl: string;
    description: string;
    place: string;
    date: string;


    constructor(id: number, name: string, price: number, pictureUrl: string, category: string, description: string, place: string, date: string) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.pictureUrl = pictureUrl;
        this.category = category;
        this.description = description;
        this.place = place;
        this.date = date;
    }
}
