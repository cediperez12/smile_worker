package com.smile.worker.Models;

public class Gig {
    private String gigName, gigDesc;
    private double rate;

    public Gig(String gigName, String gigDesc, double rate) {
        this.gigName = gigName;
        this.gigDesc = gigDesc;
        this.rate = rate;
    }

    public Gig(){

    }

    public String getGigName() {
        return gigName;
    }

    public void setGigName(String gigName) {
        this.gigName = gigName;
    }

    public String getGigDesc() {
        return gigDesc;
    }

    public void setGigDesc(String gigDesc) {
        this.gigDesc = gigDesc;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
