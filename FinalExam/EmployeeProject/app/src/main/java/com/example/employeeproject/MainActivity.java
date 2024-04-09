package com.example.employeeproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView emplIdText_view;
    private TextView emplNameText_view;
    private TextView emplSalaryText_view;
    private TextView displayEmplTaxText_view;
    private Button prev_button;
    private Button next_button;
    private Button emplTax_Button;

    private int currentIndex = 0;
    public static  String TAG = "Employee Project";
    public static String KEY_INDEX = "index";

    public Employee[] all_employeeRecords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Employee employee1 = new Employee(111, "Karishma", 50000.00);
        Employee employee2 = new Employee(112, "John", 5000.00);
        Employee employee3 = new Employee(113, "Dev", 66000.00);
        Employee employee4 = new Employee(114, "Mitesh", 90000.00);
        Employee employee5 = new Employee(115, "Jay", 45000.00);

        //Data Structure: Array of objects
        all_employeeRecords = new Employee[]{employee1, employee2, employee3, employee4, employee5};

        //Get the view of all Text View
        emplIdText_view = (TextView) findViewById(R.id.empl_id_text_view);
        emplIdText_view.setText(String.valueOf(all_employeeRecords[currentIndex].getEmpl_id()));

        emplNameText_view = (TextView) findViewById(R.id.empl_name_text_view);
        emplNameText_view.setText(String.valueOf(all_employeeRecords[currentIndex].getEmpl_name()));

        emplSalaryText_view = (TextView) findViewById(R.id.empl_salary_text_view);
        emplSalaryText_view.setText(String.valueOf(all_employeeRecords[currentIndex].getEmpl_salary()));

        emplTax_Button = (Button) findViewById(R.id.empl_tax_button);
        emplTax_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Get the view of display_empl_tax_text_view
                displayEmplTaxText_view = (TextView) findViewById(R.id.display_empl_tax_text_view);
                displayEmplTaxText_view.setText("Total Employee Tax: " +
                        String.format("%.2f", all_employeeRecords[currentIndex].calculateTotalTax()));

                Toast.makeText(MainActivity.this, "Employee: " + all_employeeRecords[currentIndex].getEmpl_name() +
                        " Total tax is: " + String.format("%.2f", all_employeeRecords[currentIndex].calculateTotalTax()),
                        Toast.LENGTH_SHORT).show();
            }
        });

        //Get the view of next_button
        next_button = (Button) findViewById(R.id.next_empl_button);
        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentIndex = (currentIndex + 1) % all_employeeRecords.length;
                emplIdText_view.setText(String.valueOf(all_employeeRecords[currentIndex].getEmpl_id()));
                emplNameText_view.setText(String.valueOf(all_employeeRecords[currentIndex].getEmpl_name()));
                emplSalaryText_view.setText(String.valueOf(all_employeeRecords[currentIndex].getEmpl_salary()));
            }
        });

        //Get the view of prev_button
        prev_button = (Button) findViewById(R.id.prev_empl_button);
        prev_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentIndex = (currentIndex + 2) % all_employeeRecords.length;
                emplIdText_view.setText(String.valueOf(all_employeeRecords[currentIndex].getEmpl_id()));
                emplNameText_view.setText(String.valueOf(all_employeeRecords[currentIndex].getEmpl_name()));
                emplSalaryText_view.setText(String.valueOf(all_employeeRecords[currentIndex].getEmpl_salary()));
            }
        });
    }

    @Override
    public void onStart(){
        super.onStart();
        Log.d(TAG,"onStart"); //Debug purpose
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d(TAG,"onResume"); //Debug purpose
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.d(TAG,"onPause"); //Debug purpose
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
        Log.d(TAG, "onSaveInstanceState is called"); //Debug purpose
        savedInstanceState.putInt(KEY_INDEX, currentIndex); //store transient data currentIndex
    }
}