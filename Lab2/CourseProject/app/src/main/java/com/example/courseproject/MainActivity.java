package com.example.courseproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView courseText_View;
    private TextView courseTotalFeesText_View;
    private Button courseTotalFees_Button;
    private Button courseNext_Button;

    private int currentIndex = 0;
    public static String TAG="Course Project";
    public static String KEY_INDEX="index";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Should be retrieved from the database
        Course courseRecord1 = new Course("MIS 101", "Intro to Info System", 140);
        Course courseRecord2 = new Course("MIS 301", "System Analysis", 35);
        Course courseRecord3 = new Course("MIS 441", "Database Management", 12);
        Course courseRecord4 = new Course("CS 155", "Programming in C++", 90);
        Course courseRecord5 = new Course("MIS 451", "Web-Based Systems", 30);
        Course courseRecord6 = new Course("MIS 551", "Advanced Web", 30);
        Course courseRecord7 = new Course("MIS 651", "Advanced Java", 30);

        //Data structure: Array of Objects
        Course[] all_courses = new Course[]{courseRecord1, courseRecord2, courseRecord3, courseRecord4,
                courseRecord5, courseRecord6, courseRecord7} ;

        if(savedInstanceState != null)
        {
            currentIndex = savedInstanceState.getInt(KEY_INDEX);
        }

        //Get the view of courseText_View
        courseText_View = (TextView) findViewById(R.id.course_text_view);
        courseText_View.setText("Course: " + all_courses[currentIndex].getCourse_no() +
                        " " + all_courses[currentIndex].getCourse_name());

        //Get the view of courseTotalFees_Button
        courseTotalFees_Button = (Button) findViewById(R.id.courseTotalFees_button);
        courseTotalFees_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Get the view of courseTotalFeesText_View
                courseTotalFeesText_View = (TextView) findViewById(R.id.courseTotalFees_text_view);
                courseTotalFeesText_View.setText("Total Course Fees: " +
                        all_courses[currentIndex].calculateTotalFees());

                Toast.makeText(MainActivity.this, "Total Course Fees: " +
                        all_courses[currentIndex].calculateTotalFees(), Toast.LENGTH_SHORT).show();
            }
        });

        //Get the view of courseNext_Button
        courseNext_Button = (Button) findViewById(R.id.courseNext_button);
        courseNext_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentIndex = (currentIndex + 1)%all_courses.length;
                courseText_View.setText("Course: " + all_courses[currentIndex].getCourse_no() +
                        " " + all_courses[currentIndex].getCourse_name());
            }
        });
    }   //end of onCreate()

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