package com.example.stefanie.runnable;

/**
 * Created by stilkin in november 2016.
 */
public class Run {
    private String title;
    private String date;
    private String location;
    private String eventUrl;
    private float price;
    private int distance;
    private String organization;
    private boolean joined = false;

    public Run() {
    }

    public Run(String title, String date, String location, String eventUrl, float price, int distance, String organization) {
        this.title = title;
        this.date = date;
        this.location = location;
        this.eventUrl = eventUrl;
        this.price = price;
        this.distance = distance;
        this.organization = organization;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEventUrl() {
        return eventUrl;
    }

    public void setEventUrl(String eventUrl) {
        this.eventUrl = eventUrl;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public boolean isJoined() {
        return joined;
    }

    public void setJoined(boolean joined) {
        this.joined = joined;
    }

    @Override
    public String toString() {
        return getTitle();
    }
}
