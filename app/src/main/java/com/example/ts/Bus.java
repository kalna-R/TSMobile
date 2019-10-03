package com.example.ts;

public class Bus {

    private String route;
    private String origin;
    private String destination;
    private String time;

    public Bus(){}

    public Bus(String busRoute, String from, String to, String time) {
        this.route = busRoute;
        this.origin = from;
        this.destination = to;
        this.time = time;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
