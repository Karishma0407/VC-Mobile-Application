package com.example.courseproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class CourseFragment extends Fragment {

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
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

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

    }

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
//        View v = super.onCreateView(inflater, container, savedInstanceState);

        View v = inflater.inflate(R.layout.fragment_course, container, false);

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

        return v;
    }
}
