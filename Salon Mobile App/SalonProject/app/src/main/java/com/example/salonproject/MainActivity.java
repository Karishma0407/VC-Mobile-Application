package com.example.salonproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageButton facialButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the resources
        facialButton = (ImageButton) findViewById(R.id.facial_imageButton);
        facialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Create a new instance of FacialFragment
                FacialFragment facialFragment = new FacialFragment();

                // Get the FragmentManager and start a transaction
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, facialFragment)
                        .addToBackStack(null) // This line allows the user to navigate back to the previous fragment
                        .commit();
            }
        });
    }
}