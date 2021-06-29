package com.smile.worker.Models;

public class Certificate {
    private String title, event, year;

    public Certificate(String title, String event, String year) {
        this.title = title;
        this.event = event;
        this.year = year;
    }

    public Certificate() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
