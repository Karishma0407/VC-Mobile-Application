package com.example.facultymobileproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class FacultyActivity extends AppCompatActivity {

    private Button updateFacultyButton;
    private EditText facultyIdEditText;
    private EditText facultyFNameEditText;
    private EditText facultyLNameEditText;
    private EditText facultySalaryEditText;
    private EditText facultyRateBonusEditText;
    private int facultyIdRetrieve;
    private String facultyFNameRetrieve;
    private String facultyLNameRetrieve;
    private double facultySalaryRetrieve;
    private double facultyRateBonusRetrieve;

    private static final String EXTRA_FACULTY_ID = "com.example.facultymobileproject.faculty_id";
    private static final String EXTRA_FACULTY_FNAME = "com.example.facultymobileproject.faculty_fName";
    private static final String EXTRA_FACULTY_LNAME = "com.example.facultymobileproject.faculty_fLame";
    private static final String EXTRA_FACULTY_SALARY = "com.example.facultymobileproject.faculty_salary";
    private static final String EXTRA_FACULTY_RATE_BONUS = "com.example.facultymobileproject.faculty_rate_bonus";


    public static Intent newIntent(Context packageContext, int facultyId,
                                   String facultyFName, String facultyLName, double facultySalary, double facultyRateBonus)
    {
        Intent intent = new Intent(packageContext, FacultyActivity.class);
        intent.putExtra(EXTRA_FACULTY_ID, facultyId);
        intent.putExtra(EXTRA_FACULTY_FNAME, facultyFName);
        intent.putExtra(EXTRA_FACULTY_LNAME, facultyLName);
        intent.putExtra(EXTRA_FACULTY_SALARY, facultySalary);
        intent.putExtra(EXTRA_FACULTY_RATE_BONUS, facultyRateBonus);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty);

        facultyIdRetrieve = getIntent().getIntExtra(EXTRA_FACULTY_ID,0);
        facultyFNameRetrieve = getIntent().getStringExtra(EXTRA_FACULTY_FNAME);
        facultyLNameRetrieve = getIntent().getStringExtra(EXTRA_FACULTY_LNAME);
        facultySalaryRetrieve = getIntent().getDoubleExtra(EXTRA_FACULTY_SALARY,0.0);
        facultyRateBonusRetrieve = getIntent().getDoubleExtra(EXTRA_FACULTY_RATE_BONUS,0.0);

        //Get the view of faculty_id_edit_text
        facultyIdEditText = (EditText) findViewById(R.id.faculty_id_edit_text);
        facultyIdEditText.setText(facultyIdRetrieve+"");

        //Get the view of faculty_fName_edit_text
        facultyFNameEditText = (EditText) findViewById(R.id.faculty_fName_edit_text);
        facultyFNameEditText.setText(facultyFNameRetrieve);

        //Get the view of faculty_lName_edit_text
        facultyLNameEditText = (EditText) findViewById(R.id.faculty_lName_edit_text);
        facultyLNameEditText.setText(facultyLNameRetrieve);

        //Get the view of faculty_salary_edit_text
        facultySalaryEditText = (EditText) findViewById(R.id.faculty_salary_edit_text);
        facultySalaryEditText.setText(facultySalaryRetrieve+"");

        //Get the view of faculty_rate_bonus_edit_text
        facultyRateBonusEditText = (EditText) findViewById(R.id.faculty_rate_bonus_edit_text);
        facultyRateBonusEditText.setText(facultyRateBonusRetrieve+"");

    }
}