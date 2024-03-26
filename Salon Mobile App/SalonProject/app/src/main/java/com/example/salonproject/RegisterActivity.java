package com.example.salonproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private EditText userFNameEditText;
    private EditText userLNameEditText;
    private EditText userPhoneEditText;
    private EditText userEmailEditText;
    private EditText userPwdEditText;
    private Button signupButton;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        //Instantiate auth reference object
        auth = FirebaseAuth.getInstance();

        //Get the view of userfname_edit_text
        userFNameEditText = (EditText) findViewById(R.id.userfname_edit_text);

        //Get the view of userlname_edit_text
        userLNameEditText = (EditText) findViewById(R.id.userlname_edit_text);

        //Get the view of userphone_edit_text
        userPhoneEditText = (EditText) findViewById(R.id.userphone_edit_text);

        //Get the view of useremail_edit_text
        userEmailEditText = (EditText) findViewById(R.id.useremail_edit_text);

        //Get the view of user_pwd_edit_text
        userPwdEditText = (EditText) findViewById(R.id.user_pwd_edit_text);

        //Get the view of signup_button
        signupButton = (Button) findViewById(R.id.signup_button);
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Adding data to Firebase database
                String userFName = userFNameEditText.getText().toString();
                String userLName = userLNameEditText.getText().toString();
                String userPhone = String.valueOf(userPhoneEditText.getText());
                String userEmail = userEmailEditText.getText().toString();
                String userPwd = userPwdEditText.getText().toString();

                if(TextUtils.isEmpty(userFName) || TextUtils.isEmpty(userLName) || TextUtils.isEmpty(userPhone) ||
                        TextUtils.isEmpty(userEmail) || TextUtils.isEmpty(userPwd))
                {
                    Toast.makeText(RegisterActivity.this, "Empty Credential", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(userPwd.length() < 6){
                    Toast.makeText(RegisterActivity.this, "Password too short", Toast.LENGTH_SHORT).show();
                }
                else{
                    register(userEmail, userPwd);
                    Toast.makeText(RegisterActivity.this, "Register Successful " + userFName, Toast.LENGTH_SHORT).show();
                }
            }
        });
    } //End of onCreate

    private void register(String email, String password) {

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(RegisterActivity.this,
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(RegisterActivity.this, "Register is successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        }else
                        {
                            Toast.makeText(RegisterActivity.this, "Register Failure", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}