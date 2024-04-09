package com.example.salonproject;

public class Appointment {
    private String id;
    private String userName;
    private String service;
    private String date;
    private String time;

    public Appointment() {
        // Default constructor required for Firebase
    }

    public Appointment(String id, String userName, String service, String date, String time) {
        this.id = id;
        this.userName = userName;
        this.service = service;
        this.date = date;
        this.time = time;
    }

    // Getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
