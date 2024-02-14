package com.example.studentgradeproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private TextView studentName_text_view;
    private TextView studentGrade_text_view;
    private Button displayGrade_button;
    private Button nextStudent_button;
    private Button prevStudent_button;

    private int currentIndex = 0;
    public static String TAG = "Student Grade Project";
    public static  String KEY_INDEX = "index";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //It should be retrieved from the database
        Grade studentRecord1 = new Grade(1,"Graham", "Bill", 69,70,98,80,90);
        Grade studentRecord2 = new Grade(2,"Sanchez", "Jim", 88,72,90,83,93);
        Grade studentRecord3 = new Grade(3,"White", "Peter", 85,80,45,93,70);
        Grade studentRecord4 = new Grade(4,"Phelp", "David", 70,60,60,90,70);
        Grade studentRecord5 = new Grade(5,"Lewis", "Sheila", 50,76,87,59,72);
        Grade studentRecord6 = new Grade(6,"James", "Thomas", 89,99,97,98,99);

        //Data structure: Array of Objects
        Grade[] all_studentRecords = new Grade[]{studentRecord1, studentRecord2, studentRecord3, studentRecord4, studentRecord5, studentRecord6};

        if(savedInstanceState != null){
            currentIndex = savedInstanceState.getInt(KEY_INDEX);
        }

        //Get the view of student_name_text_view
        studentName_text_view = (TextView) findViewById(R.id.student_name_text_view);
        studentName_text_view.setText("Student: " + all_studentRecords[currentIndex].getStudent_lLame() + " "
                + all_studentRecords[currentIndex].getStudent_fName());

        //Get the view of display_grade_button
        displayGrade_button = (Button) findViewById(R.id.display_grade_button);
        displayGrade_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Get the view of student_grade_text_view
                studentGrade_text_view = (TextView) findViewById(R.id.student_grade_text_view);
                studentGrade_text_view.setText("Grade Average is: " +
                        String.format("%.2f",all_studentRecords[currentIndex].Calculate_GradeAverage()) + "%");

                Toast.makeText(MainActivity.this, "Grade Average is: " +
                        String.format("%.2f",all_studentRecords[currentIndex].Calculate_GradeAverage()) + "%",
                        Toast.LENGTH_SHORT).show();

            }
        });

        //Get the view of next_button
        nextStudent_button = (Button) findViewById(R.id.next_button);
        nextStudent_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentIndex = (currentIndex == (all_studentRecords.length - 1)) ? 0 : currentIndex + 1;
                studentName_text_view.setText("Student: " + all_studentRecords[currentIndex].getStudent_lLame() + " "
                        + all_studentRecords[currentIndex].getStudent_fName());
            }
        });

        //Get the view of prev_button
        prevStudent_button = (Button) findViewById(R.id.prev_button);
        prevStudent_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentIndex = (currentIndex == 0) ? (all_studentRecords.length-1) : currentIndex - 1;
                studentName_text_view.setText("Student: " + all_studentRecords[currentIndex].getStudent_lLame() + " "
                        + all_studentRecords[currentIndex].getStudent_fName());
            }
        });
    }//end of onCreate()

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