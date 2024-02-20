package com.example.courseproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

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
    }

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
//        View v = super.onCreateView(inflater, container, savedInstanceState);

        View v = inflater.inflate(R.layout.fragment_course, container, false);
        return v;
    }
}
