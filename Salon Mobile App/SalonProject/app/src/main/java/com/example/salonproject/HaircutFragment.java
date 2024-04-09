package com.example.salonproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.firebase.auth.FirebaseAuth;

public class HaircutFragment extends Fragment {

    private VideoView videoView;
    private Button startService_button;
    private Button stopService_button;
    Context context;

    public HaircutFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Fragment will receive callback from FragmentManager
        // to fire onCreateOptionMenu
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_haircut, container, false);

        // Get the view of salonToolbar
        Toolbar salonToolbar = (Toolbar) v.findViewById(R.id.salonToolbar);

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(salonToolbar);


        videoView = v.findViewById(R.id.videoView);
        videoView.setVideoPath("android.resource://" +  getContext().getPackageName() + "/" + R.raw.haircutvideo);
        MediaController mediaController = new MediaController(getContext());
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);

        //Get the view of start_service_button
        startService_button = (Button) v.findViewById(R.id.start_service_button);
        startService_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                videoView.start();
            }
        });

        //Get the view of stop_service_button
        stopService_button = (Button) v.findViewById(R.id.stop_service_button);
        stopService_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                videoView.pause();
            }
        });

        return v;
    }

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
        }


        return super.onOptionsItemSelected(item);
    }
}