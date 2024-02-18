package com.example.facultymobileproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
    private Button display_bonus_button;
    private Button next_button;

    private Button prev_button;

    private int currentIndex = 0;
    public static String TAG = "Faculty Project";
    public static String KEY_INDEX = "index";

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
        Faculty[] all_records = new Faculty[]{facultyRecord1, facultyRecord2, facultyRecord3,
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

        //Get the view of display_bonus_button
        display_bonus_button = (Button) findViewById(R.id.display_bonus_button);
        display_bonus_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Get the view of display_bonus_text_view
                display_bonus_Text_View = (TextView) findViewById(R.id.display_bonus_text_view);
                display_bonus_Text_View.setText("Faculty Amount Bonus: " +
                        all_records[currentIndex].calculate_Bonus());

                Toast.makeText(MainActivity.this, "Total Bonus: " +
                        all_records[currentIndex].calculate_Bonus(), Toast.LENGTH_SHORT).show();
            }
        });

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
            }
        });
    } //end of onCreate()
}