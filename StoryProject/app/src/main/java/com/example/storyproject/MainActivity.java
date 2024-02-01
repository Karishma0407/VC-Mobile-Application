package com.example.storyproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText inputStringName;
    private EditText inputStringAge;
    private EditText inputStringCity;
    private EditText inputStringCollegeName;
    private EditText inputStringProfession;
    private EditText inputStringAnimalType;
    private EditText inputStringPetName;
    private TextView outputStringStory;
    private Button buttonDisplayStory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get the resource of button_display_story
        buttonDisplayStory = (Button) findViewById(R.id.button_display_story);
        buttonDisplayStory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Get the resource of inputStrings
                inputStringName = (EditText) findViewById(R.id.input_string_name);
                inputStringAge = (EditText) findViewById(R.id.input_string_age);
                inputStringCity = (EditText) findViewById(R.id.input_string_city);
                inputStringCollegeName = (EditText) findViewById(R.id.input_string_college_name);
                inputStringProfession = (EditText) findViewById(R.id.input_string_profession);
                inputStringAnimalType = (EditText) findViewById(R.id.input_string_animal_type);
                inputStringPetName = (EditText) findViewById(R.id.input_string_pet_name);

                //Get the resource of outputStringStory
                outputStringStory = (TextView) findViewById(R.id.output_string_story);
                outputStringStory.setText("There once was a person named " + inputStringName.getText().toString()
                                            + " who lived in " + inputStringCity.getText().toString()
                                            + ". At the age of " + inputStringAge.getText().toString()
                                            + ", " + inputStringName.getText().toString()
                                            + " went to college at " + inputStringCollegeName.getText().toString()
                                            + ". " + inputStringName.getText().toString()
                                            + " graduated and went to work as a " + inputStringProfession.getText().toString()
                                            + ". Then, " + inputStringName.getText().toString()
                                            + " adopted a " + inputStringAnimalType.getText().toString()
                                            + " named " + inputStringPetName.getText().toString()
                                            + ". They both lived happily ever after!");

            }
        });
    }
}