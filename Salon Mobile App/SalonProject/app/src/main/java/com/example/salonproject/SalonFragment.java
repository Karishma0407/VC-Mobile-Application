package com.example.salonproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class SalonFragment extends Fragment implements View.OnClickListener {

    private ImageButton facialButton;
    private ImageButton threadingButton;
    private ImageButton waxingButton;
    private ImageButton haircutButton;

    public SalonFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_salon, container, false);

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
}