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
        facialButton = v.findViewById(R.id.facial_imageButton);

        //Set click listeners
        facialButton.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {

        Fragment fragment;
        if(v.getId() == R.id.facial_imageButton){
            fragment = new FacialFragment();
        } else {
            return;
        }
    /*
        switch (v.getId())
        {
            case R.id.facial_imageButton:
                fragment = new FacialFragment();
                break;
            default:
                return;
        }
    */
        //Replace current fragment with the selected one
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
//                .addToBackStack(null)
                .commit();
    }
}