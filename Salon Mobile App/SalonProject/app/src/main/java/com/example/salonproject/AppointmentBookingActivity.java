package com.example.salonproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class AppointmentBookingActivity extends AppCompatActivity {

    private TextView userNameTextView;
    private TextView dateTextview;
    private TextView timeTextview;
    private Spinner serviceSpinner;
    private Button bookNowButton;
    int cyear,cmonth,cday;
    int chour,cminut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_booking);

        userNameTextView = (TextView) findViewById(R.id.userName_textView);
        dateTextview = (TextView) findViewById(R.id.date_textview);
        timeTextview = (TextView) findViewById(R.id.time_textview);
        serviceSpinner = findViewById(R.id.serviceSpinner);
        bookNowButton = findViewById(R.id.book_now_button);

        // Get the current user
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        // Check if the user is logged in
        if(currentUser != null) {
            // User is signed in
            String userId = currentUser.getUid();

            // Get a reference to the Firebase Realtime Database
            DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("Users").child(userId);

            // Retrieve user information from the database
            userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()){
                        // Get user data
                        String firstName = dataSnapshot.child("firstname").getValue(String.class);

                        // Set the first name to the userNameTextView
                        userNameTextView.setText(firstName);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(AppointmentBookingActivity.this, "Failed to retrieve user information", Toast.LENGTH_SHORT).show();
                }
            });
        }

        dateTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Show current date
                final Calendar calendar = Calendar.getInstance();
                cyear = calendar.get(Calendar.YEAR);
                cmonth = calendar.get(Calendar.MONTH);
                cday = calendar.get(Calendar.DAY_OF_MONTH);

                //Launch datepicker Dialog
                DatePickerDialog datePickerDialog = new DatePickerDialog(AppointmentBookingActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        //for textview shown
                        dateTextview.setText(dayOfMonth+"/"+(month + 1)+"/"+year);
                    }
                }, cyear,cmonth,cday);
                datePickerDialog.show();
            }
        });

        timeTextview = (TextView) findViewById(R.id.time_textview);
        timeTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //show current time
                final Calendar calendar = Calendar.getInstance();
                chour = calendar.get(Calendar.HOUR_OF_DAY);
                cminut = calendar.get(Calendar.MINUTE);
                //launch timepicker dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(AppointmentBookingActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        //for time textview shown
                        timeTextview.setText(hourOfDay+":"+minute);
                    }
                },chour,cminut,false);
                timePickerDialog.show();
            }
        });

        //For the services dropdown
        //Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                AppointmentBookingActivity.this,
                R.array.spinner_array_services,
                android.R.layout.simple_spinner_item
        );
        //Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Apply the adapter to the spinner
        serviceSpinner.setAdapter(adapter);

        // Book Now button onClick listener
        bookNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bookAppointment();
            }
        });
    }

    private void bookAppointment() {
        // Get the selected service
        String selectedService = serviceSpinner.getSelectedItem().toString();
        // Get the selected date
        String selectedDate = dateTextview.getText().toString();
        // Get the selected time
        String selectedTime = timeTextview.getText().toString();
        // Get the user's name
        String userName = userNameTextView.getText().toString();

        // Store appointment details in Firebase Realtime Database
        DatabaseReference appointmentsRef = FirebaseDatabase.getInstance().getReference().child("Appointments");
        String appointmentId = appointmentsRef.push().getKey();

        Appointment appointment = new Appointment(appointmentId, userName, selectedService, selectedDate, selectedTime);

        appointmentsRef.child(appointmentId).setValue(appointment)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(AppointmentBookingActivity.this, "Appointment booked successfully!", Toast.LENGTH_SHORT).show();
                        // Clear the form after successful booking
                        dateTextview.setText("");
                        timeTextview.setText("");
                        serviceSpinner.setSelection(0); // Select the first item in the spinner
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AppointmentBookingActivity.this, "Failed to book appointment: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }
}