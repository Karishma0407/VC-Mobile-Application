package com.example.courseproject;

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

    private TextView courseText_View;
    private TextView courseTotalFeesText_View;
    private Button courseTotalFees_Button;
    private Button courseNext_Button;
    private Button course_detail_Button;

    private int currentIndex = 0;
    public static String TAG="Course Project";
    public static String KEY_INDEX="index";

    public Course[] all_courses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Course.credits=3;

        //Should be retrieved from the database
        Course courseRecord1 = new Course("MIS 101", "Intro to Info System", 140);
        Course courseRecord2 = new Course("MIS 301", "System Analysis", 35);
        Course courseRecord3 = new Course("MIS 441", "Database Management", 12);
        Course courseRecord4 = new Course("CS 155", "Programming in C++", 90);
        Course courseRecord5 = new Course("MIS 451", "Web-Based Systems", 30);
        Course courseRecord6 = new Course("MIS 551", "Advanced Web", 30);
        Course courseRecord7 = new Course("MIS 651", "Advanced Java", 30);

        //Data structure: Array of Objects
        all_courses = new Course[]{courseRecord1, courseRecord2, courseRecord3, courseRecord4,
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

        //Get the view of course_detail_button
        course_detail_Button = (Button) findViewById(R.id.course_detail_button);
        course_detail_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Start new Activity CourseActivity
                //The first approach is to call startActivity as Unidirectional Communication
                //Only use when sending data from parent activity to child activity
                String courseId = all_courses[currentIndex].getCourse_no();
                String courseName = all_courses[currentIndex].getCourse_name();
                int courseMaxEnrl = all_courses[currentIndex].getMax_enrl();
                int courseCredits = Course.credits;
                //calling the coding Extra
                Intent intent = CourseActivity.newIntent (MainActivity.this, courseId, courseName, courseMaxEnrl, courseCredits);
                //startActivity(intent);
                startActivityIntent.launch(intent);

            }
        });

    }   //end of onCreate()

    ActivityResultLauncher<Intent> startActivityIntent = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>(){
                @Override
                public void onActivityResult(ActivityResult result){
                    //Decoding of extra data Intent
                    if(result.getResultCode() != Activity.RESULT_OK)
                    {
                        return;
                    }else
                    {
                        Course courseUpdateinfo = CourseActivity.sendMessageCourseUpdateResult(result.getData());
                        courseText_View.setText("Course: " + courseUpdateinfo.getCourse_no() +
                                " " + courseUpdateinfo.getCourse_name());

                        Toast.makeText(MainActivity.this, "Updated Course: " +
                                "Course: " + courseUpdateinfo.getCourse_no() +
                                " " + courseUpdateinfo.getCourse_name(),
                                Toast.LENGTH_SHORT).show();
                        //Update the array element
                        all_courses[currentIndex].setCourse_no(courseUpdateinfo.getCourse_no());
                        all_courses[currentIndex].setCourse_name(courseUpdateinfo.getCourse_name());
                        all_courses[currentIndex].setMax_enrl(courseUpdateinfo.getMax_enrl());
                        all_courses[currentIndex].credits = courseUpdateinfo.credits;
                    }
                }
            }
    );

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