package com.example.temperatureproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText inputStringEditText;
    private TextView outputStringTextView;
    private Button convertTempButton;
    private Double inputString;
    private Double outputValue;
    private String outputString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get the view resources convert_temp_button
        convertTempButton = (Button) findViewById(R.id.convert_temp_button);
        //Call button listener
        convertTempButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Implemented Algorithm
                //Read the input string
                //Convert the temperature using the formula

                //Get the view resource inputStringEditText
                inputStringEditText = (EditText) findViewById(R.id.input_string_edit_text);
                inputString = Double.parseDouble(inputStringEditText.getText().toString());
                outputValue = (inputString - 32) * 5/9;
                outputString = "Temperature in Degree is: " + String.format("%.2f",outputValue);

                //Get the view resource outputStringTextView
                outputStringTextView = (TextView) findViewById(R.id.output_string_text_view);
                outputStringTextView.setText(outputString);

            }
        });
    }
}