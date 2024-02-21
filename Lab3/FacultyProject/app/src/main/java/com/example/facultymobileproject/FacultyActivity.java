package com.example.facultymobileproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FacultyActivity extends AppCompatActivity {

    private Button updateFacultyButton;
    private EditText facultyIdEditText;
    private EditText facultyFNameEditText;
    private EditText facultyLNameEditText;
    private EditText facultySalaryEditText;
    private EditText facultyRateBonusEditText;
    private TextView facultyAmountBonusTextView;
    private int facultyIdRetrieve;
    private String facultyFNameRetrieve;
    private String facultyLNameRetrieve;
    private double facultySalaryRetrieve;
    private double facultyRateBonusRetrieve;
    private double facultyAmountBonusRetrieve;

    //Define all KEYS
    private static final String EXTRA_FACULTY_ID = "com.example.facultymobileproject.faculty_id";
    private static final String EXTRA_FACULTY_FNAME = "com.example.facultymobileproject.faculty_fName";
    private static final String EXTRA_FACULTY_LNAME = "com.example.facultymobileproject.faculty_fLame";
    private static final String EXTRA_FACULTY_SALARY = "com.example.facultymobileproject.faculty_salary";
    private static final String EXTRA_FACULTY_RATE_BONUS = "com.example.facultymobileproject.faculty_rate_bonus";
    private static final String EXTRA_FACULTY_AMOUNT_BONUS = "com.example.facultymobileproject.faculty_amount_bonus";

    //Eliminate all coupling between parent and child activities
    //Coding all Extra in the intent object
    public static Intent newIntent(Context packageContext, int facultyId,
                                   String facultyFName, String facultyLName, double facultySalary,
                                   double facultyRateBonus, double facultyAmountBonus)
    {
        Intent intent = new Intent(packageContext, FacultyActivity.class);
        intent.putExtra(EXTRA_FACULTY_ID, facultyId);
        intent.putExtra(EXTRA_FACULTY_FNAME, facultyFName);
        intent.putExtra(EXTRA_FACULTY_LNAME, facultyLName);
        intent.putExtra(EXTRA_FACULTY_SALARY, facultySalary);
        intent.putExtra(EXTRA_FACULTY_RATE_BONUS, facultyRateBonus);
        intent.putExtra(EXTRA_FACULTY_AMOUNT_BONUS, facultyAmountBonus);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty);

        //Decoding the extra data from the intent object
        facultyIdRetrieve = getIntent().getIntExtra(EXTRA_FACULTY_ID,0);
        facultyFNameRetrieve = getIntent().getStringExtra(EXTRA_FACULTY_FNAME);
        facultyLNameRetrieve = getIntent().getStringExtra(EXTRA_FACULTY_LNAME);
        facultySalaryRetrieve = getIntent().getDoubleExtra(EXTRA_FACULTY_SALARY,0.0);
        facultyRateBonusRetrieve = getIntent().getDoubleExtra(EXTRA_FACULTY_RATE_BONUS,0.0);
        facultyAmountBonusRetrieve = getIntent().getDoubleExtra(EXTRA_FACULTY_AMOUNT_BONUS, 0.0);

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

        //Get the view of faculty_amount_bonus_text_view
        facultyAmountBonusTextView = (TextView) findViewById(R.id.faculty_amount_bonus_text_view);
        facultyAmountBonusTextView.setText("Your bonus is: " + facultyAmountBonusRetrieve + "$");

        //Get the view of update_display_button
        updateFacultyButton = (Button) findViewById(R.id.update_display_button);
        updateFacultyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Do the update into database

                //Send updated data Course Info as a result to parent activity MainActivity
                setFacultyUpdateResult(Integer.parseInt(facultyIdEditText.getText().toString()),
                        facultyFNameEditText.getText().toString(),
                        facultyLNameEditText.getText().toString(),
                        Double.parseDouble(facultySalaryEditText.getText().toString()),
                        Double.parseDouble(facultyRateBonusEditText.getText().toString()));
            }
        });
    } //End of onCreate()

    //Coding Extra data Intent from child to parent Activity
    private void setFacultyUpdateResult(int facultyId, String facultyFName, String facultyLName,
                                        double facultySalary, double facultyRateBonus)
    {
        Intent dataIntent = new Intent();
        dataIntent.putExtra(EXTRA_FACULTY_ID, facultyId);
        dataIntent.putExtra(EXTRA_FACULTY_FNAME, facultyFName);
        dataIntent.putExtra(EXTRA_FACULTY_LNAME, facultyLName);
        dataIntent.putExtra(EXTRA_FACULTY_SALARY, facultySalary);
        dataIntent.putExtra(EXTRA_FACULTY_RATE_BONUS, facultyRateBonus);
        setResult(RESULT_OK, dataIntent);
    }

    //Decoding Extra data Intent in ParentActivity
    public static Faculty sendMessageFacultyUpdateResult(Intent resultIntent)
    {
        Faculty facultyUpdateInfo = new Faculty();
        facultyUpdateInfo.setFaculty_id(resultIntent.getIntExtra(EXTRA_FACULTY_ID,0));
        facultyUpdateInfo.setGetFaculty_FName(resultIntent.getStringExtra(EXTRA_FACULTY_FNAME));
        facultyUpdateInfo.setFaculty_LName(resultIntent.getStringExtra(EXTRA_FACULTY_LNAME));
        facultyUpdateInfo.setSalary(resultIntent.getDoubleExtra(EXTRA_FACULTY_SALARY,0.0));
        facultyUpdateInfo.setBonus(resultIntent.getDoubleExtra(EXTRA_FACULTY_RATE_BONUS,0.0));
        return facultyUpdateInfo;
    }
}