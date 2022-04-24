package com.example.project.Model;

import java.io.Serializable;

public class Ticket implements Serializable {

    private Long id;
    private String name;
    private TicketCategories category;
    private Double price;
    private String pictureUrl;
    private String description;

    private String place;
    private String date;


    public Ticket(Long id, String name, Double price){
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Ticket(Long id, String name, Double price, TicketCategories category, String pictureUrl, String description, String place, String date) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.pictureUrl = pictureUrl;
        this.category = category;
        this.description = description;
        this.place = place;
        this.date = date;
    }

    public Ticket() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public TicketCategories getCategory() { return category;}

    public void setCategory(TicketCategories category) {this.category = category;}

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public static TicketCategories stringToCategory(String category){
        switch (category){

            case "Sport":
                return TicketCategories.Sport;
            case "Cinema":
                return TicketCategories.Cinema;
            case "Concert":
                return TicketCategories.Concert;
            default:
                return null;

        }

    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", price=" + price +
                ", pictureUrl='" + pictureUrl + '\'' +
                ", description='" + description + '\'' +
                ", place='" + place + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
