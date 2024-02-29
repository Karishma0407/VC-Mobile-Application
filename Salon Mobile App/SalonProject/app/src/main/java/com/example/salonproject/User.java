package com.example.salonproject;

public class User {

    private int user_id;
    private String user_fName;
    private String user_lName;
    private int user_phone;
    private String user_email;
    private String user_pwd;

    public User() {
        this.user_id = 0;
        this.user_fName = "";
        this.user_lName = "";
        this.user_phone = 0;
        this.user_email = "";
        this.user_pwd = "";
    }

    public User(int user_id, String user_fName, String user_lName, int user_phone, String user_email, String user_pwd) {
        this.user_id = user_id;
        this.user_fName = user_fName;
        this.user_lName = user_lName;
        this.user_phone = user_phone;
        this.user_email = user_email;
        this.user_pwd = user_pwd;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_fName() {
        return user_fName;
    }

    public void setUser_fName(String user_fName) {
        this.user_fName = user_fName;
    }

    public String getUser_lName() {
        return user_lName;
    }

    public void setUser_lName(String user_lName) {
        this.user_lName = user_lName;
    }

    public int getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(int user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_pwd() {
        return user_pwd;
    }

    public void setUser_pwd(String user_pwd) {
        this.user_pwd = user_pwd;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", user_fName='" + user_fName + '\'' +
                ", user_lName='" + user_lName + '\'' +
                ", user_phone=" + user_phone +
                ", user_email='" + user_email + '\'' +
                ", user_pwd='" + user_pwd + '\'' +
                '}';
    }
}
