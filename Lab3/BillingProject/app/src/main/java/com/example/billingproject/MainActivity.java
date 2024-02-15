package com.example.billingproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText clientIdEdit_view;
    private EditText clientNameEdit_view;
    private EditText productNameEdit_view;
    private EditText productPriceEdit_view;
    private EditText productQuantityEdit_view;
    private TextView totalBillingText_view;
    private TextView recordBillingText_view;
    private Button totalInputBilling_button;
    private Button totalRecordBilling_button;
    private Button prev_button;
    private Button next_button;

    private int currentIndex = 0;
    public static  String TAG = "Billing Project";
    public static String KEY_INDEX = "index";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Should be retrieved from the database
        Billing billingRecord1 = new Billing(105, "Johnston Jane", "Chair", 99.99, 2);
        Billing billingRecord2 = new Billing(108, "Fikhali Samuel", "Table", 139.99, 1);
        Billing billingRecord3 = new Billing(113, "Samson Amina", "KeyUSB", 14.99, 2);

        //Data Structure: Array of Objects
        Billing[] all_billingRecords = new Billing[]{billingRecord1, billingRecord2, billingRecord3};

        if (savedInstanceState != null) {
            currentIndex = savedInstanceState.getInt(KEY_INDEX);
        }

        //Get the view of all Edit Text and assign values from the array
        clientIdEdit_view = (EditText) findViewById(R.id.client_id_edit_view);
        clientIdEdit_view.setText(String.valueOf(all_billingRecords[currentIndex].getClient_id()));

        clientNameEdit_view = (EditText) findViewById(R.id.client_name_edit_view);
        clientNameEdit_view.setText(all_billingRecords[currentIndex].getClient_name());

        productNameEdit_view = (EditText) findViewById(R.id.product_name_edit_view);
        productNameEdit_view.setText(all_billingRecords[currentIndex].getProduct_name());

        productPriceEdit_view = (EditText) findViewById(R.id.product_price_edit_view);
        productPriceEdit_view.setText(String.valueOf(all_billingRecords[currentIndex].getPrd_price()));

        productQuantityEdit_view = (EditText) findViewById(R.id.product_quantity_edit_view);
        productQuantityEdit_view.setText(String.valueOf(all_billingRecords[currentIndex].getPrd_qty()));

        //Get the view of total_input_billing_button
        totalInputBilling_button = (Button) findViewById(R.id.total_input_billing_button);
        totalInputBilling_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Get the view of total_billing_text_view
                totalBillingText_view = (TextView) findViewById(R.id.total_billing_text_view);
                totalBillingText_view.setText( String.format("Total Billing: %.2f", all_billingRecords[currentIndex].CalculateBilling()) + "$");
            }
        });

        //Get the view of total_record_billing_button
        totalRecordBilling_button = (Button) findViewById(R.id.total_record_billing_button);
        totalRecordBilling_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Get the view of record_billing_text_view
                recordBillingText_view = (TextView) findViewById(R.id.record_billing_text_view);
                recordBillingText_view.setText("Client: " + all_billingRecords[currentIndex].getClient_id() +
                        ", " + all_billingRecords[currentIndex].getClient_name() +
                        ", Product: " + all_billingRecords[currentIndex].getProduct_name() +
                        " is " + String.format("%.2f", all_billingRecords[currentIndex].CalculateBilling()) + "$");

                Toast.makeText(MainActivity.this, "Client: " + all_billingRecords[currentIndex].getClient_id() +
                        ", " + all_billingRecords[currentIndex].getClient_name() +
                        ", Product: " + all_billingRecords[currentIndex].getProduct_name() +
                        " is " + String.format("%.2f", all_billingRecords[currentIndex].CalculateBilling()) + "$",
                        Toast.LENGTH_SHORT).show();
            }
        });

        //Get the view of next_billing_button
        next_button = (Button) findViewById(R.id.next_billing_button);
        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                currentIndex = (currentIndex + 1) % all_billingRecords.length;
                currentIndex = (currentIndex == all_billingRecords.length - 1) ? 0 : currentIndex + 1;
                clientIdEdit_view.setText(String.valueOf(all_billingRecords[currentIndex].getClient_id()));
                clientNameEdit_view.setText(all_billingRecords[currentIndex].getClient_name());
                productNameEdit_view.setText(all_billingRecords[currentIndex].getProduct_name());
                productPriceEdit_view.setText(String.valueOf(all_billingRecords[currentIndex].getPrd_price()));
                productQuantityEdit_view.setText(String.valueOf(all_billingRecords[currentIndex].getPrd_qty()));
            }
        });

        //Get the view of prev_billing_button
        prev_button = (Button) findViewById(R.id.prev_billing_button);
        prev_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                currentIndex = (currentIndex + 2) % all_billingRecords.length;
                currentIndex = (currentIndex == 0) ? all_billingRecords.length - 1 : currentIndex - 1;
                clientIdEdit_view.setText(String.valueOf(all_billingRecords[currentIndex].getClient_id()));
                clientNameEdit_view.setText(all_billingRecords[currentIndex].getClient_name());
                productNameEdit_view.setText(all_billingRecords[currentIndex].getProduct_name());
                productPriceEdit_view.setText(String.valueOf(all_billingRecords[currentIndex].getPrd_price()));
                productQuantityEdit_view.setText(String.valueOf(all_billingRecords[currentIndex].getPrd_qty()));
            }
        });

    }

    @Override
    public void onStart(){
        super.onStart();
        Log.d(TAG,"onStart"); //Debug purpose
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d(TAG,"onResume"); //Debug purpose
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.d(TAG,"onPause"); //Debug purpose
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.d(TAG,"onStop"); //Debug purpose
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG,"onDestroy"); //Debug purpose
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        Log.d(TAG, "onSaveInstanceState is called"); //Debug purpose
        savedInstanceState.putInt(KEY_INDEX, currentIndex); //store transient data currentIndex
    }
}