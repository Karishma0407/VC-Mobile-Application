package com.example.courseproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class CourseActivity extends AppCompatActivity {

    private EditText courseIdEditText;
    private EditText courseNameEditText;
    private EditText courseMaxEnrlEditText;
    private EditText courseCreditsEditText;


    //Define all KEYS
    private static final String EXTRA_COURSE_NO = "com.example.courseproject.course_no";
    private static final String EXTRA_COURSE_NAME = "com.example.courseproject.course_name";
    private static final String EXTRA_COURSE_MAX_ENRL = "com.example.courseproject.course_max_enrl";
    private static final String EXTRA_COURSE_CREDITS = "com.example.courseproject.course_credits";

    //Eliminate all coupling between parent and child activities
    //Coding all Extra in the Intent Object
    public static Intent newIntent(Context packageContext, String course_id, String course_name,
                                   int course_max_enrl, int course_credits)
    {
        Intent intent = new Intent(packageContext, CourseActivity.class);
        intent.putExtra(EXTRA_COURSE_NO, course_id);
        intent.putExtra(EXTRA_COURSE_NAME, course_name);
        intent.putExtra(EXTRA_COURSE_MAX_ENRL, course_max_enrl);
        intent.putExtra(EXTRA_COURSE_CREDITS, course_credits);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        //Decoding the extra data from the intent object
        String courseIdRetrieve = getIntent().getStringExtra(EXTRA_COURSE_NO);
        String courseNameRetrieve = getIntent().getStringExtra(EXTRA_COURSE_NAME);
        int courseMaxEnrlRetrieve = getIntent().getIntExtra(EXTRA_COURSE_MAX_ENRL,0);
        int courseCreditsRetrieve = getIntent().getIntExtra(EXTRA_COURSE_CREDITS, 0);

        //Get the view of course_no_edit_text
        courseIdEditText = (EditText) findViewById(R.id.course_no_edit_text);
        courseIdEditText.setText(courseIdRetrieve);

        //Get the view of course_name_edit_text
        courseNameEditText = (EditText) findViewById(R.id.course_name_edit_text);
        courseNameEditText.setText(courseNameRetrieve);

        //Get the view of course_max_enrl_edit_text
        courseMaxEnrlEditText = (EditText) findViewById(R.id.course_max_enrl_edit_text);
        courseMaxEnrlEditText.setText(courseMaxEnrlRetrieve+"");

        //Get the view of course_credits_edit_text
        courseCreditsEditText = (EditText) findViewById(R.id.course_credits_edit_text);
        courseCreditsEditText.setText(courseCreditsRetrieve+"");
    }
}