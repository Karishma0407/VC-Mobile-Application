package com.example.salonproject;

public class Feedback {
    private String id;
    private String userName;
    private String email;
    private float rating;
    private String comments;

    public Feedback() {
        // Default constructor required for Firebase
    }

    public Feedback(String userName, String email, float rating, String comments) {
        this.userName = userName;
        this.email = email;
        this.rating = rating;
        this.comments = comments;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}

