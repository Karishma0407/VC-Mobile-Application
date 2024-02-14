package com.example.mathoperationsproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText inputStringEditTextX;
    private EditText inputStringEditTextY;
    private TextView additionTextView;
    private TextView subtractionTextView;
    private TextView multiplyTextView;
    private TextView divisionTextView;
    private Button calculateButton;
    private Double inputStringX;
    private Double inputStringY;
    private String additionString;
    private String subtractionString;
    private String multiplyString;
    private String divisionString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get the view resources calculate_button
        calculateButton = (Button) findViewById(R.id.calculate_button);
        //Call button listener
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Implemented algorithm
                //Read the input values of X and Y
                //Do the operations

                //Get the view resource inputStringEditTextX and inputStringEditTextY
                inputStringEditTextX = (EditText) findViewById(R.id.input_string_edit_text_x);
                inputStringX = Double.parseDouble(inputStringEditTextX.getText().toString());
                inputStringEditTextY = (EditText) findViewById(R.id.input_string_edit_text_y);
                inputStringY = Double.parseDouble(inputStringEditTextY.getText().toString());

                //Addition Operation
                additionString = "Addition of X and Y is: " + (inputStringX + inputStringY) ;
                //Get the view resource addition_text_view
                additionTextView = (TextView) findViewById(R.id.addition_text_view);
                additionTextView.setText(additionString);

                //Subtraction Operation
                subtractionString = "Subtraction Y from X is: " + (inputStringY - inputStringX) ;
                //Get the view resource subtraction_text_view
                subtractionTextView = (TextView) findViewById(R.id.subtraction_text_view);
                subtractionTextView.setText(subtractionString);

                //Multiply Operation
                multiplyString = "Product of X and Y is: " + (inputStringX * inputStringY) ;
                //Get the view resource multiply_text_view
                multiplyTextView = (TextView) findViewById(R.id.multiply_text_view);
                multiplyTextView.setText(multiplyString);

                //Division Operation
                divisionString = "Divide X/Y is: " + (inputStringX / inputStringY) ;
                //Get the view resource division_text_view
                divisionTextView = (TextView) findViewById(R.id.division_text_view);
                divisionTextView.setText(divisionString);
            }
        });
    }
}