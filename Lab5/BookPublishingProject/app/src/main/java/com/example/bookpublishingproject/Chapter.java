package com.example.bookpublishingproject;

public class Chapter {

    private int b_id;   // Composite primary key referencing Book
    private int c_no;   // Composite primary key
    private String c_title;
    private double c_price;

    public Chapter() {
        this.b_id = 0;
        this.c_no = 0;
        this.c_title = "";
        this.c_price = 0.0;
    }

    public Chapter(int b_id, int c_no, String c_title, double c_price) {
        this.b_id = b_id;
        this.c_no = c_no;
        this.c_title = c_title;
        this.c_price = c_price;
    }

    public int getB_id() {
        return b_id;
    }

    public void setB_id(int b_id) {
        this.b_id = b_id;
    }

    public int getC_no() {
        return c_no;
    }

    public void setC_no(int c_no) {
        this.c_no = c_no;
    }

    public String getC_title() {
        return c_title;
    }

    public void setC_title(String c_title) {
        this.c_title = c_title;
    }

    public double getC_price() {
        return c_price;
    }

    public void setC_price(double c_price) {
        this.c_price = c_price;
    }
}
