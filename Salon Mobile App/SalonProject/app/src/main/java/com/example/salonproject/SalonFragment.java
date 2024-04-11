package com.example.salonproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SalonFragment extends Fragment implements View.OnClickListener {

    private ImageButton facialButton;
    private ImageButton threadingButton;
    private ImageButton waxingButton;
    private ImageButton haircutButton;
    private Button appointBook;

    private FirebaseAuth auth;

    Context context;

    public SalonFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Fragment will receive callback from FragmentManager
        // to fire onCreateOptionMenu
        setHasOptionsMenu(true);

        //Instantiate auth reference object
        auth = FirebaseAuth.getInstance();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_salon, container, false);

        // Get the view of salonToolbar
        Toolbar salonToolbar = (Toolbar) v.findViewById(R.id.salonToolbar);

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(salonToolbar);

        //Initialize ImageButtons
        facialButton = v.findViewById(R.id.facial_image_button);
        threadingButton = v.findViewById(R.id.threading_image_button);
        waxingButton = v.findViewById(R.id.waxing_image_button);
        haircutButton = v.findViewById(R.id.haircut_image_button);

        //Set click listeners
        facialButton.setOnClickListener(this);
        threadingButton.setOnClickListener(this);
        waxingButton.setOnClickListener(this);
        haircutButton.setOnClickListener(this);

        //Get the view of appointment_book_button
        appointBook = (Button) v.findViewById(R.id.appointment_book_button);
        appointBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Go to new Activity
                Intent intent = new Intent(getActivity(), AppointmentBookingActivity.class);
                startActivity(intent);

            }
        });

        //Get the view of userName_textView
        TextView userNameTextView = (TextView) v.findViewById(R.id.userName_textView);

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
                    Toast.makeText(getActivity(), "Failed to retrieve user information", Toast.LENGTH_SHORT).show();
                }
            });
        }
        return v;
    }

    //-------------------- Replace with selected fragments --------------------
    @Override
    public void onClick(View v) {

        Fragment fragment;
        if(v.getId() == R.id.facial_image_button){
            fragment = new FacialFragment();
        } else if(v.getId() == R.id.threading_image_button){
            fragment = new ThreadingFragment();
        } else if(v.getId() == R.id.waxing_image_button){
            fragment = new WaxingFragment();
        } else if(v.getId() == R.id.haircut_image_button){
            fragment = new HaircutFragment();
        }
        else {
            return;
        }

        //Replace current fragment with the selected one
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }

    //-------------------- Display Menu bar --------------------
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

//        Inflate the menu defined in menu resource
        inflater.inflate(R.menu.menu_salon, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        
        int id = item.getItemId();
        Intent intent;
        
        if(id == R.id.salonoptionitem1)
        {
            FirebaseAuth.getInstance().signOut();
            Toast.makeText(getActivity(), "Logout Successful", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getActivity(), LoginActivity.class));
            //End the current activity
            requireActivity().finish();
            return true;
        } else if (id == R.id.salonoptionitem2) {
            startActivity(new Intent(getActivity(), FeedbackActivity.class));
        } else if (id == R.id.salonoptionitem3) {
            startActivity(new Intent(getActivity(), SalonLocationActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }


}