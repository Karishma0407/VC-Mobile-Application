package com.example.facultymobileproject;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView idText_View;
    private TextView faculty_LName_Text_View;
    private TextView faculty_FName_Text_View;
    private TextView salary_Text_View;
    private TextView bonus_Text_View;
    private TextView display_bonus_Text_View;
    private Button faculty_details_button;
    private Button next_button;
    private Button prev_button;

    private int currentIndex = 0;
    private static final String TAG = "Faculty Project";
    private static final String KEY_INDEX = "index";
    public Faculty[] all_records;

/*
    private static final String EXTRA_FACULTY_ID = "com.example.facultymobileproject.faculty_id";
    private static final String EXTRA_FACULTY_FNAME = "com.example.facultymobileproject.faculty_fName";
    private static final String EXTRA_FACULTY_LNAME = "com.example.facultymobileproject.faculty_fLame";
    private static final String EXTRA_FACULTY_SALARY = "com.example.facultymobileproject.faculty_salary";
    private static final String EXTRA_FACULTY_RATE_BONUS = "com.example.facultymobileproject.faculty_rate_bonus";
    private static final String EXTRA_FACULTY_AMOUNT_BONUS = "com.example.facultymobileproject.faculty_amount_bonus";
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Should be retrieved from the database
        Faculty facultyRecord1 = new Faculty(101, "Robertson", "Myra", 60000.00, 2.50);
        Faculty facultyRecord2 = new Faculty(102, "Smith", "Neal", 40000.00, 3.00);
        Faculty facultyRecord3 = new Faculty(103, "Arlec", "Lisa", 55000.00, 1.50);
        Faculty facultyRecord4 = new Faculty(104, "Fillipo", "Paul", 30000.00, 5.00);
        Faculty facultyRecord5 = new Faculty(105, "Denkan", "Anais", 95000.00, 5.00);

        //Data Structure: Array of Objects
        all_records = new Faculty[]{facultyRecord1, facultyRecord2, facultyRecord3,
                facultyRecord4, facultyRecord5};

        if(savedInstanceState != null)
        {
            currentIndex = savedInstanceState.getInt(KEY_INDEX);
        }

        //Get the view of all Text_view
        idText_View = (TextView) findViewById(R.id.faculty_id_text_view);
        idText_View.setText("Faculty No: " + String.valueOf(all_records[currentIndex].getFaculty_id()));

        faculty_LName_Text_View = (TextView) findViewById(R.id.faculty_LName_text_view);
        faculty_LName_Text_View.setText("Faculty LName: " + all_records[currentIndex].getFaculty_LName());

        faculty_FName_Text_View = (TextView) findViewById(R.id.faculty_FName_text_view);
        faculty_FName_Text_View.setText("Faculty FName: " + all_records[currentIndex].getFaculty_FName());

        salary_Text_View = (TextView) findViewById(R.id.faculty_salary_text_view);
        salary_Text_View.setText(String.valueOf("Faculty Salary: " + all_records[currentIndex].getSalary()));

        bonus_Text_View = (TextView) findViewById(R.id.faculty_bonus_text_view);
        bonus_Text_View.setText(String.valueOf("Faculty Rate Bonus: " + all_records[currentIndex].getBonus()));

        //Get the view of next_button
        next_button = (Button) findViewById(R.id.next_button);
        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentIndex = (currentIndex + 1)%all_records.length;
                idText_View.setText("Faculty No: " + String.valueOf(all_records[currentIndex].getFaculty_id()));
                faculty_LName_Text_View.setText("Faculty LName: " + all_records[currentIndex].getFaculty_LName());
                faculty_FName_Text_View.setText("Faculty FName: " + all_records[currentIndex].getFaculty_FName());
                salary_Text_View.setText(String.valueOf("Faculty Salary: " + all_records[currentIndex].getSalary()));
                bonus_Text_View.setText(String.valueOf("Faculty Rate Bonus: " + all_records[currentIndex].getBonus()));
                displayBonus();
            }
        });

        //Get the view of prev_button
        prev_button = (Button) findViewById(R.id.prev_button);
        prev_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentIndex = (currentIndex == 0) ? (all_records.length -1) : (currentIndex-1);
                idText_View.setText("Faculty No: " + String.valueOf(all_records[currentIndex].getFaculty_id()));
                faculty_LName_Text_View.setText("Faculty LName: " + all_records[currentIndex].getFaculty_LName());
                faculty_FName_Text_View.setText("Faculty FName: " + all_records[currentIndex].getFaculty_FName());
                salary_Text_View.setText(String.valueOf("Faculty Salary: " + all_records[currentIndex].getSalary()));
                bonus_Text_View.setText(String.valueOf("Faculty Rate Bonus: " + all_records[currentIndex].getBonus()));
                displayBonus();
            }
        });

        //Get the view of faculty_details_button
        faculty_details_button = (Button) findViewById(R.id.faculty_details_button);
        faculty_details_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Start new Activity FacultyActivity
/*
                //First Approach
                //Need to create same EXTRA variables in MainActivity and FacultyActivity
                Intent intent = new Intent(MainActivity.this, FacultyActivity.class);
                int facultyId = all_records[currentIndex].getFaculty_id();
                String facultyFName = all_records[currentIndex].getFaculty_FName();
                String facultyLName = all_records[currentIndex].getFaculty_LName();
                double facultySalary = all_records[currentIndex].getSalary();
                double facultyRateBonus = all_records[currentIndex].getBonus();
                double facultyAmountBonus = all_records[currentIndex].calculate_Bonus();
                intent.putExtra(EXTRA_FACULTY_ID, facultyId);
                intent.putExtra(EXTRA_FACULTY_FNAME, facultyFName);
                intent.putExtra(EXTRA_FACULTY_LNAME, facultyLName);
                intent.putExtra(EXTRA_FACULTY_SALARY, facultySalary);
                intent.putExtra(EXTRA_FACULTY_RATE_BONUS, facultyRateBonus);
                intent.putExtra(EXTRA_FACULTY_AMOUNT_BONUS, facultyAmountBonus);
                startActivity(intent);
*/

                //Second Approach
                //Need to create EXTRA variables only in FacultyActivity
                int facultyId = all_records[currentIndex].getFaculty_id();
                String facultyFName = all_records[currentIndex].getFaculty_FName();
                String facultyLName = all_records[currentIndex].getFaculty_LName();
                double facultySalary = all_records[currentIndex].getSalary();
                double facultyRateBonus = all_records[currentIndex].getBonus();
                double facultyAmountBonus = all_records[currentIndex].calculate_Bonus();
                //Calling the coding Extra
                Intent intent = FacultyActivity.newIntent(MainActivity.this,
                        facultyId, facultyFName, facultyLName, facultySalary, facultyRateBonus, facultyAmountBonus);

                //Only use when sending data from parent MainActivity WITHOUT expecting result from Child Activity
                //startActivity(intent);

                //Used when sending data from parent MainActivity WHEN expecting result from Child Activity(FacultyActivity)
                startActivityIntent.launch(intent);

            }
        });

    } //end of onCreate()

        ActivityResultLauncher<Intent> startActivityIntent = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        //Decoding of extra data intent
                        if(result.getResultCode()!= Activity.RESULT_OK)
                        {
                            return;
                        }else
                        {
                            Faculty facultyUpdateInfo = FacultyActivity.sendMessageFacultyUpdateResult(result.getData());
                            idText_View.setText(String.valueOf(facultyUpdateInfo.getFaculty_id()));
                            faculty_LName_Text_View.setText(facultyUpdateInfo.getFaculty_LName());
                            faculty_FName_Text_View.setText(facultyUpdateInfo.getFaculty_FName());
                            salary_Text_View.setText(String.valueOf(facultyUpdateInfo.getSalary()));
                            bonus_Text_View.setText(String.valueOf(facultyUpdateInfo.getBonus()));

                            display_bonus_Text_View = (TextView) findViewById(R.id.display_bonus_text_view);
                            display_bonus_Text_View.setText("Faculty Amount Bonus: " +
                                    facultyUpdateInfo.calculate_Bonus());

                            Toast.makeText(MainActivity.this, "Updated Faculty Bonus: " +
                                    facultyUpdateInfo.calculate_Bonus(), Toast.LENGTH_SHORT).show();

                            //Update array component related to currentIndex
                            all_records[currentIndex].setFaculty_id(facultyUpdateInfo.getFaculty_id());
                            all_records[currentIndex].setGetFaculty_FName(facultyUpdateInfo.getFaculty_FName());
                            all_records[currentIndex].setFaculty_LName(facultyUpdateInfo.getFaculty_LName());
                            all_records[currentIndex].setSalary(facultyUpdateInfo.getSalary());
                            all_records[currentIndex].setBonus(facultyUpdateInfo.getBonus());

                        }
                    }
                }
        );

    //Methods to display total bonus
    public void displayBonus(){
        //Get the view of display_bonus_text_view
        display_bonus_Text_View = (TextView) findViewById(R.id.display_bonus_text_view);
        display_bonus_Text_View.setText("Faculty Amount Bonus: " +
                all_records[currentIndex].calculate_Bonus());

        Toast.makeText(MainActivity.this, "Total Bonus: " +
                all_records[currentIndex].calculate_Bonus(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStart(){

        super.onStart();
        Log.d(TAG,"onStart"); //Debug purpose

    }

    @Override
    public void onPause(){

        super.onPause();
        Log.d(TAG,"onPause"); //Debug purpose

    }

    @Override
    public void onResume(){

        super.onResume();
        Log.d(TAG,"onResume"); //Debug purpose

    }

    @Override
    public void onStop(){

        super.onStop();
        Log.d(TAG,"onStop"); //Debug purpose

    }

    @Override
    public void onDestroy(){

        super.onDestroy();
        Log.d(TAG,"onDestroy"); //Debug purpose

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        Log.d(TAG,"onSaveInstanceState is called"); //Debug purpose
        savedInstanceState.putInt(KEY_INDEX, currentIndex); //store transient data currentIndex
    }
}