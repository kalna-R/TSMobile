package com.example.ts;

import java.util.Date;

public class Ticket {

    private String email;
    private String origin;
    private String destination;
    private double distance;
    private String date;
    private int adults;
    private int children;
    private int seniors;
    private Double total;

    public Ticket(){}

    public Ticket(String ticketId, String origin, String destination, double distance,
                  int noOfAdults, int noOfChildren, int noOfSeniors, double total, String date) {
        this.origin = origin;
        this.destination = destination;
        this.distance = distance;
        this.date = date;
        this.adults = noOfAdults;
        this.children = noOfChildren;
        this.seniors = noOfSeniors;
        this.total = total;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getAdults() {
        return adults;
    }

    public void setAdults(int adults) {
        this.adults = adults;
    }

    public int getChildren() {
        return children;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    public int getSeniors() {
        return seniors;
    }

    public void setSeniors(int seniors) {
        this.seniors = seniors;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

}
