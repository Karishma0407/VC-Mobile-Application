package com.example.billingproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class BillingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billing);

        //Use FragmentManager to add CourseFragment to fragment_container
        //of MainActivity
        FragmentManager fm = getSupportFragmentManager();

        Fragment fragment = fm.findFragmentById(R.id.fragment_containerBilling);
        if(fragment == null)
        {
            fragment = new BillingFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_containerBilling, fragment)
                    .commit();
        }

    }
}