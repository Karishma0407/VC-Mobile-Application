package com.example.courseproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class CourseOperationActivity extends AppCompatActivity {

    private EditText courseNo_editText;
    private EditText courseName_editText;
    private EditText courseMaxEnrl_editText;
    private EditText courseCredit_editText;
    private TextView courseListTextView;
    private Button addCourse_button;
    private Button searchCourse_button;
    DatabaseReference databaseRef;
    Map<String, Object> coursehm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_operation);

        //Lab 6:
        //Initialize Firebase database
        databaseRef = FirebaseDatabase.getInstance().getReference();

        //Get reference to UI elements
        courseNo_editText = findViewById(R.id.course_no_edit_text);
        courseName_editText = findViewById(R.id.course_name_edit_text);
        courseMaxEnrl_editText = findViewById(R.id.course_max_enrl_edit_text);
        courseCredit_editText = findViewById(R.id.course_credits_edit_text);
        courseListTextView = (TextView) findViewById(R.id.courseList_text_view);


        //Add/Read course to Firebase database
        //Get the view of add_course_button
        addCourse_button = (Button) findViewById(R.id.add_course_button);
        addCourse_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Read data from Firebase
                readDataFromFirebase();

                //Check if data is entered in EditText fields, if yes, add data to Firebase database
                // Get the values from EditText fields
                String courseNo = courseNo_editText.getText().toString();
                String courseName = courseName_editText.getText().toString();
                String courseMaxEnrlString = courseMaxEnrl_editText.getText().toString();
                if(!courseNo.isEmpty() && !courseName.isEmpty() && !courseMaxEnrlString.isEmpty()) {
                    int courseMaxEnrl = Integer.parseInt(courseMaxEnrlString);

                    //Create a course object
//                Course course = new Course(courseNo, courseName, courseMaxEnrl);

                    //Create a HashMap to store course data
                    coursehm = new HashMap<>();
                    coursehm.put("course_no", courseNo);
                    coursehm.put("course_name", courseName);
                    coursehm.put("course_maxEnrl", courseMaxEnrl);

                    //Push the Course object to Firebase database under "ExtraCourses"
                    databaseRef.child("ExtraCourses").child(courseNo).setValue(coursehm);

                    Toast.makeText(CourseOperationActivity.this, "Course added to Firebase", Toast.LENGTH_SHORT).show();
                }
            }
        }); //end of addCourse onCreate

        //Search course
        //Get the view of search_course_button
        searchCourse_button = (Button) findViewById(R.id.search_course_button);
        searchCourse_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Read the course ID from editText
                String searchCourseId = courseNo_editText.getText().toString();

                //Search the course in Firebase
                searchCourseInFirebase(searchCourseId);

            }

        });
    }

    private void readDataFromFirebase()
    {
        //Read data from Firebase under "ExtraCourses"
        databaseRef.child("ExtraCourses").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                StringBuilder courses = new StringBuilder();
                for(DataSnapshot courseSnapshot : dataSnapshot.getChildren())
                {
                    String courseNo = courseSnapshot.getKey();
                    String courseName = courseSnapshot.child("course_name").getValue(String.class);
                    int courseMaxEnrl = courseSnapshot.child("course_maxEnrl").getValue(Integer.class);

                    courses.append("Course No: ").append(courseNo).append(" ")
                            .append("Course Name: ").append(courseName).append(" ")
                            .append("Max Enrl: ").append(courseMaxEnrl).append("\n");
                }

                //Set the fetched courses to the courseListTextView
                courseListTextView.setText(courses.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle database error
                Toast.makeText(CourseOperationActivity.this, "Failed to read data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void searchCourseInFirebase(final String searchCourseId)
    {
        // Search for the course in Firebase under "ExtraCourses"
        databaseRef.child("ExtraCourses").child(searchCourseId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists())
                {
                    // Course found, display its details in the respective EditText fields
                    String courseName = snapshot.child("course_name").getValue(String.class);
                    int courseMaxEnrl = snapshot.child("course_maxEnrl").getValue(Integer.class);

                    courseName_editText.setText(courseName);
                    courseMaxEnrl_editText.setText(String.valueOf(courseMaxEnrl));

                    Toast.makeText(CourseOperationActivity.this, "Course found in Firebase", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(CourseOperationActivity.this, "Course not found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}