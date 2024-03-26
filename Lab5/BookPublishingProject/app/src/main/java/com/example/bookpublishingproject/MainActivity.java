package com.example.bookpublishingproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView pubIdTextView;
    private TextView pubNameTextView;
    private TextView pubAddTextView;
    private Button prevPubButton;
    private Button nextPubButton;
    private Button pubDetailsButton;
    private Button showBookButton;

    private int currentIndex = 0;

    public Publisher[] all_publishers;
    Context context;
    ArrayList<Publisher> publisherModelArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Publisher publisher1 = new Publisher();
        Publisher publisher2 = new Publisher();
        Publisher publisher3 = new Publisher();
        Publisher publisher4 = new Publisher();
        Publisher publisher5 = new Publisher();

    }
}