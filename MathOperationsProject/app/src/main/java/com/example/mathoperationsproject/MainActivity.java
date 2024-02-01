package com.example.mathoperationsproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText inputStringEditTextX;
    private EditText inputStringEditTextY;
    private TextView additionTextView;
    private TextView subtractionTextView;
    private TextView multiplyTextView;
    private TextView divisionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}