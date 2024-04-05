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
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;

public class SalonFragment extends Fragment implements View.OnClickListener {

    private ImageButton facialButton;
    private ImageButton threadingButton;
    private ImageButton waxingButton;
    private ImageButton haircutButton;

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
//                .addToBackStack(null)
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
            Toast.makeText(context, "Logout Successful", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getActivity(), LoginActivity.class));
            //End the current activity
            requireActivity().finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}