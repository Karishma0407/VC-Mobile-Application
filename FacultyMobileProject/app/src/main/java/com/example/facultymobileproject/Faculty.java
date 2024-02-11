package com.example.facultymobileproject;

public class Faculty {
    private int faculty_id;
    private String faculty_LName;
    private String getFaculty_FName;
    private int salary;
    private int bonus;

    public Faculty(int faculty_id, String faculty_LName, String getFaculty_FName, int salary, int bonus) {
        this.faculty_id = faculty_id;
        this.faculty_LName = faculty_LName;
        this.getFaculty_FName = getFaculty_FName;
        this.salary = salary;
        this.bonus = bonus;
    }

    public int getFaculty_id() {
        return faculty_id;
    }

    public void setFaculty_id(int faculty_id) {
        this.faculty_id = faculty_id;
    }

    public String getFaculty_LName() {
        return faculty_LName;
    }

    public void setFaculty_LName(String faculty_LName) {
        this.faculty_LName = faculty_LName;
    }

    public String getFaculty_FName() {
        return getFaculty_FName;
    }

    public void setGetFaculty_FName(String getFaculty_FName) {
        this.getFaculty_FName = getFaculty_FName;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    //concrete method
    public double calculate_Bonus(){ return salary * bonus/100;}

    @Override
    public String toString() {
        return "Faculty{" +
                "faculty_id=" + faculty_id +
                ", faculty_LName='" + faculty_LName + '\'' +
                ", getFaculty_FName='" + getFaculty_FName + '\'' +
                ", salary=" + salary +
                ", bonus=" + bonus +
                '}';
    }
}
