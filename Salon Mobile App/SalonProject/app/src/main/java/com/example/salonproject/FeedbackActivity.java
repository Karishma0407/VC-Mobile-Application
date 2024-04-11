package com.example.salonproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
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

public class FeedbackActivity extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextEmail;
    private RatingBar ratingBar;
    private EditText editTextComments;
    private Button buttonSubmitFeedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        ratingBar = findViewById(R.id.ratingBar);
        editTextComments = findViewById(R.id.editTextComments);
        buttonSubmitFeedback = findViewById(R.id.buttonSubmitFeedback);

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
                        editTextName.setText(firstName);
                        editTextEmail.setText(currentUser.getEmail());
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(FeedbackActivity.this, "Failed to retrieve user information", Toast.LENGTH_SHORT).show();
                }
            });
        }

        buttonSubmitFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitFeedback();
            }
        });
    }

    private void submitFeedback() {
        String name = editTextName.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        float rating = ratingBar.getRating();
        String comments = editTextComments.getText().toString().trim();

        // Validate input (optional)

        // Create a Feedback object
        Feedback feedback = new Feedback(name, email, rating, comments);

        // Store the feedback in Firebase Realtime Database or Firestore
        DatabaseReference feedbackRef = FirebaseDatabase.getInstance().getReference().child("Feedback");
        String feedbackId = feedbackRef.push().getKey();

        feedbackRef.child(feedbackId).setValue(feedback)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(FeedbackActivity.this, "Feedback submitted successfully!", Toast.LENGTH_SHORT).show();
                        // Optionally, clear the form fields after successful submission
                        clearForm();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(FeedbackActivity.this, "Failed to submit feedback: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void clearForm() {
        editTextName.getText().clear();
        editTextEmail.getText().clear();
        ratingBar.setRating(0);
        editTextComments.getText().clear();
    }
}
