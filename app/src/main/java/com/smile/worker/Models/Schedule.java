package com.smile.worker.Models;

public class Schedule {
    String customer_id;
    String gig_id;
    String status;
    double rate;
    double fare;
    long date;
    long loc_long;
    long loc_lat;

    public Schedule(String customer_id, String gig_id, String status, double rate, double fare, long date, long loc_long, long loc_lat) {
        this.customer_id = customer_id;
        this.gig_id = gig_id;
        this.status = status;
        this.rate = rate;
        this.fare = fare;
        this.date = date;
        this.loc_long = loc_long;
        this.loc_lat = loc_lat;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getGig_id() {
        return gig_id;
    }

    public void setGig_id(String gig_id) {
        this.gig_id = gig_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getFare() {
        return fare;
    }

    public void setFare(double fare) {
        this.fare = fare;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public long getLoc_long() {
        return loc_long;
    }

    public void setLoc_long(long loc_long) {
        this.loc_long = loc_long;
    }

    public long getLoc_lat() {
        return loc_lat;
    }

    public void setLoc_lat(long loc_lat) {
        this.loc_lat = loc_lat;
    }
}
