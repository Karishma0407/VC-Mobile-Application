package com.example.reversestringproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText inputStringEditText;
    private TextView reverseStringTextView;
    private Button reverseStringButton;
    private String inputString;
    private String reverseString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get the view resourse reverse_string_button
        reverseStringButton = (Button) findViewById(R.id.reverse_string_button);
        //Call button listener
        reverseStringButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Implemented Algorithm
                //Read input string referenced by inpustStringEditText
                //Reverse it using loop

                //Get the view resourse inputStringEditText
                inputStringEditText = (EditText) findViewById(R.id.input_string_edit_text);
                inputString = inputStringEditText.getText().toString();
                reverseString = "";
                for(int i=inputString.length()-1; i>=0; i--){
                    reverseString += inputString.charAt(i);
                }

                //Get the view resourse reverseStringTextView
                reverseStringTextView = (TextView) findViewById(R.id.reverse_string_text_view);
                reverseStringTextView.setText(reverseString);

            }
        });
    }
}