package com.example.salonproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class SalonLocationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salon_location);

        TextView locationTextView = findViewById(R.id.locationTextView);
        TextView hoursTextView = findViewById(R.id.hoursTextView);

        // Set salon location
        String location = "821 avenue Ste-Croix, \n" +
                "Montréal, QC, \n" +
                "H4L 3X9";
        locationTextView.setText(location);

        // Set salon hours
        String hours = "Monday: Closed\n" +
                "Tuesday - Saturday: 10am - 6pm\n" +
                "Sunday: Closed";
        hoursTextView.setText(hours);
    }
}