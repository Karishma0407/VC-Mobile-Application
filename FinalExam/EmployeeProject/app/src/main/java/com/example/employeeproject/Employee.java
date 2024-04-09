package com.example.employeeproject;

public class Employee {

    private int empl_id;
    private String empl_name;
    private Double empl_salary;

    public Employee() {
        this.empl_id = 0;
        this.empl_name = "";
        this.empl_salary = 0.0;
    }

    public Employee(int empl_id, String empl_name, Double empl_salary) {
        this.empl_id = empl_id;
        this.empl_name = empl_name;
        this.empl_salary = empl_salary;
    }

    public int getEmpl_id() {
        return empl_id;
    }

    public void setEmpl_id(int empl_id) {
        this.empl_id = empl_id;
    }

    public String getEmpl_name() {
        return empl_name;
    }

    public void setEmpl_name(String empl_name) {
        this.empl_name = empl_name;
    }

    public Double getEmpl_salary() {
        return empl_salary;
    }

    public void setEmpl_salary(Double empl_salary) {
        this.empl_salary = empl_salary;
    }

    //Concrete method
    public double calculateTotalTax()
    {
        // Provincial tax (Prv_Tax): 9% of emp_salary
        double prvTax = 0.09 * empl_salary;

        // Federal tax (Fed_Tax): 7% of emp_salary
        double fedTax = 0.07 * empl_salary;

        // Total tax
        double totalTax = prvTax + fedTax;

        return totalTax;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empl_id=" + empl_id +
                ", empl_name='" + empl_name + '\'' +
                ", empl_salary=" + empl_salary +
                '}';
    }


}
