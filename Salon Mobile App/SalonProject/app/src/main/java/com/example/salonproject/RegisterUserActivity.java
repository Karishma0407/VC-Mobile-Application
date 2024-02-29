package com.example.salonproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterUserActivity extends AppCompatActivity {

    private EditText userFNameEditText;
    private EditText userLNameEditText;
    private EditText userPhoneEditText;
    private EditText userEmailEditText;
    private EditText userPwdEditText;
    private Button signupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

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

                String userFName = userFNameEditText.getText().toString();
                String userLName = userLNameEditText.getText().toString();
                int userPhone = Integer.parseInt(userPhoneEditText.getText().toString());
                String UserEmail = userEmailEditText.getText().toString();
                String userPwd = userPwdEditText.getText().toString();

                Toast.makeText(RegisterUserActivity.this, "Register Successful " + userFName, Toast.LENGTH_SHORT).show();
            }
        });
    } //End of onCreate


}