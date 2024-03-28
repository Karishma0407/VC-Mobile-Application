package com.example.courseproject;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.courseproject.database.CourseBaseHelper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CourseFragment extends Fragment {

    private TextView courseText_View;
    private TextView courseTotalFeesText_View;
    private Button courseTotalFees_Button;
    private Button courseNext_Button;
    private Button courseDetailButton;
    private TextView courseListTextView;
    private Button startService_button;
    private Button stopService_button;
    private Button courseLink_button;
    //Lab 6
    private Button logoutButton;
    private Button writeToFirebaseButton;
    private Button readFromFirebaseButton;

    DatabaseReference databaseRef;

    private int currentIndex = 0;
    public static String TAG="Course Project";
    public static String KEY_INDEX="index";

    public Course[] all_courses;
    Map<String, Object> coursehm;

    Context context;
    ArrayList<Course> courseModelArrayList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        Course.credits=3;

        //Fragment will receive callback from FragmentManager if
        setHasOptionsMenu(true);

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

        //Lab 6:
        databaseRef = FirebaseDatabase.getInstance().getReference();

        coursehm = new HashMap<>();
        coursehm.put(courseRecord1.getCourse_no(), courseRecord1);
        coursehm.put(courseRecord2.getCourse_no(), courseRecord2);
        coursehm.put(courseRecord3.getCourse_no(), courseRecord3);
        coursehm.put(courseRecord4.getCourse_no(), courseRecord4);
        coursehm.put(courseRecord5.getCourse_no(), courseRecord5);
        coursehm.put(courseRecord6.getCourse_no(), courseRecord6);
        coursehm.put(courseRecord7.getCourse_no(), courseRecord7);

        context = getContext().getApplicationContext();
        CourseBaseHelper courseBaseHelper = new CourseBaseHelper(context);
        courseBaseHelper.addNewCourse(courseRecord1);
        courseBaseHelper.addNewCourse(courseRecord2);
        courseBaseHelper.addNewCourse(courseRecord3);
        courseBaseHelper.addNewCourse(courseRecord4);
        courseBaseHelper.addNewCourse(courseRecord5);
        courseBaseHelper.addNewCourse(courseRecord6);
        courseBaseHelper.addNewCourse(courseRecord7);

        //Update record
        courseRecord3.setCourse_name("Android Database System");
        courseBaseHelper.updateCourse(courseRecord3);

        //Delete record
        courseBaseHelper.deleteCourse(courseRecord1);

    }

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
//        View v = super.onCreateView(inflater, container, savedInstanceState);

        View v = inflater.inflate(R.layout.fragment_course, container, false);

        //Get the view of coursetoolbar
        Toolbar courseToolbar = (Toolbar) v.findViewById(R.id.coursetoolbar);

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(courseToolbar);


        //Get the view of courseText_View
        courseText_View = (TextView) v.findViewById(R.id.course_text_view);
        courseText_View.setText("Course: " + all_courses[currentIndex].getCourse_no() +
                " " + all_courses[currentIndex].getCourse_name());

        //Get the view of courseTotalFees_Button
        courseTotalFees_Button = (Button) v.findViewById(R.id.courseTotalFees_button);
        courseTotalFees_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Get the view of courseTotalFeesText_View
                courseTotalFeesText_View = (TextView) v.findViewById(R.id.courseTotalFees_text_view);
                courseTotalFeesText_View.setText("Total Course Fees: " +
                        all_courses[currentIndex].calculateTotalFees());

                Toast.makeText(getActivity(), "Total Course Fees: " +
                        all_courses[currentIndex].calculateTotalFees(), Toast.LENGTH_SHORT).show();
            }
        });

        //Get the view of courseNext_Button
        courseNext_Button = (Button) v.findViewById(R.id.courseNext_button);
        courseNext_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentIndex = (currentIndex + 1)%all_courses.length;
                courseText_View.setText("Course: " + all_courses[currentIndex].getCourse_no() +
                        " " + all_courses[currentIndex].getCourse_name());
            }
        });

        //Get the view of courseDetailButton
        courseDetailButton = (Button) v.findViewById(R.id.course_detail_button);
        courseDetailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Read table rows calling readCourse
                courseModelArrayList = new CourseBaseHelper(context).readCourse();
                //Get the view of courseList_text_view
                courseListTextView = (TextView) v.findViewById(R.id.courseList_text_view);

                String allCourses = "";
                //Read all content of courseModelArrayList
                for (Course course:courseModelArrayList)
                {
                    allCourses += course.toString();
                }

                //Set the content of allCourses into text attribute of courseListTextView
                courseListTextView.setText(allCourses);
            }
        });

        //Get the view of start_service_button
        startService_button = (Button) v.findViewById(R.id.start_service_button);
        startService_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Start Android Service Media Player
                getActivity().startService(new Intent(getActivity(), CourseService.class));
            }
        });

        //Get the view of stop_service_button
        stopService_button = (Button) v.findViewById(R.id.stop_service_button);
        stopService_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Stop Android Service Media Player
                getActivity().stopService(new Intent(getActivity(), CourseService.class));
            }
        });

        //Get the view of course_link_button
        courseLink_button = (Button) v.findViewById(R.id.course_link_button);
        courseLink_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Use Implicit Intent
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.vaniercollege.qc.ca/"));
                startActivity(intent);
            }
        });

        //Lab 6: EventListener of logout button
        logoutButton = (Button) v.findViewById(R.id.logout_button);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(getActivity(), "Logout Successful", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(), StartActivity.class));
            }
        });

        writeToFirebaseButton = (Button) v.findViewById(R.id.writeToFirebase_button);
        writeToFirebaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Store the data into Firebase database
//                databaseRef.child("Courses").child(all_courses[0].getCourse_no())
//                        .setValue(all_courses[0]);
                databaseRef.child("Courses").updateChildren(coursehm);

                Toast.makeText(getActivity(), "Map added to Firebase", Toast.LENGTH_SHORT).show();
            }
        });

        readFromFirebaseButton = (Button) v.findViewById(R.id.readFromFirebase_button);
        readFromFirebaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Read the data from Firebase database and write to scroll textView
                databaseRef.child("Courses").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String allcourses = "";

                        for (DataSnapshot ss:snapshot.getChildren())
                        {
                            allcourses += ss.getValue(Course.class).toString();
                        }
                        //Get the view of courseList_text_view
                        courseListTextView = (TextView) v.findViewById(R.id.courseList_text_view);
                        courseListTextView.setText(allcourses);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        return v;
    }

    public void onCreateOptionsMenu(@Nullable Menu menu, @Nullable MenuInflater inflater){

        super.onCreateOptionsMenu(menu, inflater);

        //Inflate the menu defined in menu resource
        inflater.inflate(R.menu.menu_course, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        Intent intent;

        if(id == R.id.courseoptionitem1)
        {
            Toast.makeText(context, "Item 1 selected", Toast.LENGTH_SHORT).show();
            //start new Activity CourseMapActivity
            intent = new Intent(getActivity(), CourseMapActivity.class);
            startActivity(intent);
            return true;
        }
        else if(id == R.id.courseoptionitem2)
        {
            Toast.makeText(context, "Item 2 selected", Toast.LENGTH_SHORT).show();
            //start new Activity CourseContentActivity
            intent = new Intent(getActivity(), CourseContentActivity.class);
            startActivity(intent);
            return true;
        }
        else if(id == R.id.courseoptionitem3)
        {
            Toast.makeText(context, "Item 3 selected", Toast.LENGTH_SHORT).show();
            //start new Activity CourseOperationActivity
            intent = new Intent(getActivity(), CourseOperationActivity.class);
            startActivity(intent);
            return true;
        }
        else if(id == R.id.courseoptionitem4)
        {
            Toast.makeText(context, "Item 4 selected", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if(id == R.id.courseoptionitem5)
        {
            Toast.makeText(context, "Item 5 selected", Toast.LENGTH_SHORT).show();
            return true;
        }

        /*
        switch (id)
        {
            case R.id.courseoptionitem1:
                Toast.makeText(context, "Item 1 selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.courseoptionitem2:
                Toast.makeText(context, "Item 2 selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.courseoptionitem3:
                Toast.makeText(context, "Item 3 selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.courseoptionitem4:
                Toast.makeText(context, "Item 4 selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.courseoptionitem5:
                Toast.makeText(context, "Item 5 selected", Toast.LENGTH_SHORT).show();
                return true;
        }*/
        return super.onOptionsItemSelected(item);
    }
}
