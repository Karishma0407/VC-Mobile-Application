package com.example.salonproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText emailEditText;
    private EditText pwdEditText;
    private TextView forgotPwdTextView;
    private TextView signUpTextView;
    private Button signInButton;

    private final String email = "admin@gmail.com";
    private final String pwd = "admin";

    boolean isValid = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailEditText = findViewById(R.id.email_edit_text);
        pwdEditText = findViewById(R.id.pwd_edit_text);

        //Get the view of signin_button
        signInButton = (Button) findViewById(R.id.signin_button);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String inputEmail = emailEditText.getText().toString();
                String inputPwd = pwdEditText.getText().toString();

                if(inputEmail.isEmpty() || inputPwd.isEmpty())
                {
                    Toast.makeText(MainActivity.this, "Please enter all the details correctly!", Toast.LENGTH_SHORT).show();
                }
                else {
                    isValid = validate(inputEmail, inputPwd);

                    if(!isValid){
                        Toast.makeText(MainActivity.this, "Incorrect credentials entered!", Toast.LENGTH_SHORT).show();
                    }else{
                        //Add the code to go to new activity
                        Intent intent = new Intent(MainActivity.this, HomePageActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });

        //Get the view of signup_text_view
        signUpTextView = (TextView) findViewById(R.id.signup_text_view);
        signUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Go to new Activity
                Intent intent = new Intent(MainActivity.this, RegisterUserActivity.class);
                startActivity(intent);
            }
        });

    }


    private boolean validate(String emailValue, String pwdValue) {

        if(emailValue.equals(email) && pwdValue.equals(pwd)){
            return true;
        }
        return false;
    }
}