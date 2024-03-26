package com.example.salonproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {


    private EditText emailEditText;
    private EditText pwdEditText;
    private TextView forgotPwdTextView;
    private TextView signUpTextView;
    private Button signInButton;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);

        //Instantiate auth reference object
        auth = FirebaseAuth.getInstance();

        //Get the view resources
        emailEditText = findViewById(R.id.email_edit_text);
        pwdEditText = findViewById(R.id.pwd_edit_text);

        //Get the view of signin_button
        signInButton = (Button) findViewById(R.id.playVideo_button);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Checking email/pass from Firebase database
                String inputEmail = emailEditText.getText().toString();
                String inputPwd = pwdEditText.getText().toString();

                login(inputEmail, inputPwd);
            }
        });

        //Get the view of signup_text_view
        signUpTextView = (TextView) findViewById(R.id.signup_text_view);
        signUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Go to new Activity
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }

    private void login(String email, String password) {

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>(){
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }
                else {
                    Toast.makeText(LoginActivity.this, "Check Your Credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}