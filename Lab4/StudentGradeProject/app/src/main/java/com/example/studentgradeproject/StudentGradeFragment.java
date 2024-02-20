package com.example.studentgradeproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class StudentGradeFragment extends Fragment {

    private TextView studentName_text_view;
    private TextView studentGrade_text_view;
    private Button displayGrade_button;
    private Button nextStudent_button;
    private Button prevStudent_button;

    private int currentIndex = 0;
    public static String TAG = "Student Grade Project";
    public static  String KEY_INDEX = "index";

    public Grade[] all_studentRecords;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        //It should be retrieved from the database
        Grade studentRecord1 = new Grade(1,"Graham", "Bill", 69,70,98,80,90);
        Grade studentRecord2 = new Grade(2,"Sanchez", "Jim", 88,72,90,83,93);
        Grade studentRecord3 = new Grade(3,"White", "Peter", 85,80,45,93,70);
        Grade studentRecord4 = new Grade(4,"Phelp", "David", 70,60,60,90,70);
        Grade studentRecord5 = new Grade(5,"Lewis", "Sheila", 50,76,87,59,72);
        Grade studentRecord6 = new Grade(6,"James", "Thomas", 89,99,97,98,99);

        //Data structure: Array of Objects
        all_studentRecords = new Grade[]{studentRecord1, studentRecord2, studentRecord3, studentRecord4, studentRecord5, studentRecord6};

    }

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){

        View v = inflater.inflate(R.layout.fragment_studentgrade, container, false);

        //Get the view of student_name_text_view
        studentName_text_view = (TextView) v.findViewById(R.id.student_name_text_view);
        studentName_text_view.setText("Student: " + all_studentRecords[currentIndex].getStudent_lLame() + " "
                + all_studentRecords[currentIndex].getStudent_fName());

        //Get the view of display_grade_button
        displayGrade_button = (Button) v.findViewById(R.id.display_grade_button);
        displayGrade_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Get the view of student_grade_text_view
                studentGrade_text_view = (TextView) v.findViewById(R.id.student_grade_text_view);
                studentGrade_text_view.setText("Grade Average is: " +
                        String.format("%.2f",all_studentRecords[currentIndex].Calculate_GradeAverage()) + "%");

                Toast.makeText(getActivity(), "Grade Average is: " +
                                String.format("%.2f",all_studentRecords[currentIndex].Calculate_GradeAverage()) + "%",
                        Toast.LENGTH_SHORT).show();

            }
        });

        //Get the view of next_button
        nextStudent_button = (Button) v.findViewById(R.id.next_button);
        nextStudent_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentIndex = (currentIndex == (all_studentRecords.length - 1)) ? 0 : currentIndex + 1;
                studentName_text_view.setText("Student: " + all_studentRecords[currentIndex].getStudent_lLame() + " "
                        + all_studentRecords[currentIndex].getStudent_fName());
            }
        });

        //Get the view of prev_button
        prevStudent_button = (Button) v.findViewById(R.id.prev_button);
        prevStudent_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentIndex = (currentIndex == 0) ? (all_studentRecords.length-1) : currentIndex - 1;
                studentName_text_view.setText("Student: " + all_studentRecords[currentIndex].getStudent_lLame() + " "
                        + all_studentRecords[currentIndex].getStudent_fName());
            }
        });

        return v;
    }
}
