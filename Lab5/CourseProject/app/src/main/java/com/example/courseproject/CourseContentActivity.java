package com.example.courseproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.w3c.dom.Text;

import cz.msebera.android.httpclient.Header;

public class CourseContentActivity extends AppCompatActivity {

    private TextView courseContentTextView;
    private Button courseWebServiceButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_content);

        //get the view of courseContent_textView
        courseContentTextView = (TextView) findViewById(R.id.courseContent_textView);

        courseWebServiceButton = (Button) findViewById(R.id.callCourseWebService);
        courseWebServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Add the url of your web service project endpoints
                String url = "http://localhost:8080/customer/find?id=1";

                new AsyncHttpClient().get(url, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        String strResponse = new String(responseBody);
                        courseContentTextView.setText(strResponse);
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        courseContentTextView.setText("Error in calling web service");
                    }
                });
            }
        });

    }
}