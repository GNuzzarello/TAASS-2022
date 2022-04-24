package com.example.TAASS2022.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull(message = "Ticket name is required.")
    @Basic(optional = false)
    @Column(name = "name")
    private String name;

    @Column(name = "category")
    private TicketCategories category;

    @Column(name = "price")
    private Double price;

    @Column(name = "picture_url")
    private String pictureUrl;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "place", columnDefinition = "text")
    private String place;

    @Column(name = "date", columnDefinition = "text")
    private String date;



    public Ticket(Long id, @NotNull(message = "Ticket name is required.") String name, Double price, TicketCategories category, String pictureUrl, String description, String place, String date ) {
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

    public TicketCategories getCategory() {
        return category;
    }

    public void setCategory(TicketCategories category) {
        this.category = category;
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

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

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



}
