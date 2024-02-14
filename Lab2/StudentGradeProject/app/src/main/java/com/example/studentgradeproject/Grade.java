package com.example.studentgradeproject;

public class Grade {

    private int student_id;
    private String student_lLame;
    private String student_fName;
    private int s_grade_Assignment1;
    private int s_grade_Assignment2;
    private int s_grade_Assignment3;
    private int s_grade_Mid_Term;
    private int s_grade_Final_Term;

    public Grade() {
        this.student_id = 0;
        this.student_lLame = "";
        this.student_fName = "";
        this.s_grade_Assignment1 = 0;
        this.s_grade_Assignment2 = 0;
        this.s_grade_Assignment3 = 0;
        this.s_grade_Mid_Term = 0;
        this.s_grade_Final_Term = 0;
    }
    public Grade(int student_id, String student_lLame, String student_fName, int s_grade_Assignment1, int s_grade_Assignment2, int s_grade_Assignment3, int s_grade_Mid_Term, int s_grade_Final_Term) {
        this.student_id = student_id;
        this.student_lLame = student_lLame;
        this.student_fName = student_fName;
        this.s_grade_Assignment1 = s_grade_Assignment1;
        this.s_grade_Assignment2 = s_grade_Assignment2;
        this.s_grade_Assignment3 = s_grade_Assignment3;
        this.s_grade_Mid_Term = s_grade_Mid_Term;
        this.s_grade_Final_Term = s_grade_Final_Term;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public String getStudent_lLame() {
        return student_lLame;
    }

    public void setStudent_lLame(String student_lLame) {
        this.student_lLame = student_lLame;
    }

    public String getStudent_fName() {
        return student_fName;
    }

    public void setStudent_fName(String student_fName) {
        this.student_fName = student_fName;
    }

    public int getS_grade_Assignment1() {
        return s_grade_Assignment1;
    }

    public void setS_grade_Assignment1(int s_grade_Assignment1) {
        this.s_grade_Assignment1 = s_grade_Assignment1;
    }

    public int getS_grade_Assignment2() {
        return s_grade_Assignment2;
    }

    public void setS_grade_Assignment2(int s_grade_Assignment2) {
        this.s_grade_Assignment2 = s_grade_Assignment2;
    }

    public int getS_grade_Assignment3() {
        return s_grade_Assignment3;
    }

    public void setS_grade_Assignment3(int s_grade_Assignment3) {
        this.s_grade_Assignment3 = s_grade_Assignment3;
    }

    public int getS_grade_Mid_Term() {
        return s_grade_Mid_Term;
    }

    public void setS_grade_Mid_Term(int s_grade_Mid_Term) {
        this.s_grade_Mid_Term = s_grade_Mid_Term;
    }

    public int getS_grade_Final_Term() {
        return s_grade_Final_Term;
    }

    public void setS_grade_Final_Term(int s_grade_Final_Term) {
        this.s_grade_Final_Term = s_grade_Final_Term;
    }

    //Concrete Methods
    /* calculates and returns the average score for each student according to the following mark distribution:
        1) 40 % for all assignments (s_grade_Ass1 for Assignment 1), (s_grade_Ass2 for
            Assignment 2), (s_grade_Ass3 for Assignment 3)
        2) 30 % for Mid Term Exam (s_grade_Mt)
        3) 30 % for Final Exam (s_grade_Ft)
    */
    public double Calculate_GradeAverage(){
        double assignmentAverage = ((s_grade_Assignment1 + s_grade_Assignment2 + s_grade_Assignment3) / 3.0) * 0.4;
        double midTermWeight = s_grade_Mid_Term * 0.3;
        double finalWeight = s_grade_Final_Term * 0.3;
        return assignmentAverage + midTermWeight + finalWeight;
    }
}
