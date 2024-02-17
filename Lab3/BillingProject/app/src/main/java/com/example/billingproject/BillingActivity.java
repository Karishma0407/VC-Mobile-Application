package com.example.billingproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class BillingActivity extends AppCompatActivity {

    private EditText clientIdEditText;
    private EditText clientNameEditText;
    private EditText prdNameEditText;
    private EditText prdPriceEditText;
    private EditText prdQtyEditText;
    private Button billingUpdateButton;

    //Define all KEYS
    private static final String EXTRA_CLIENT_ID = "com.example.billingproject.client_id";
    private static final String EXTRA_CLIENT_NAME = "com.example.billingproject.client_name";
    private static final String EXTRA_PRODUCT_NAME = "com.example.billingproject.product_name";
    private static final String EXTRA_PRODUCT_PRICE = "com.example.billingproject.product_price";
    private static final String EXTRA_PRODUCT_QTY = "com.example.billingproject.product_qty";

    //Eliminate all coupling between parent and child activities
    //coding all Extra in the intent object
    public static Intent newIntent(Context packageContext, int client_id, String client_name,
                                   String prd_name, double prd_price, int prd_qty)
    {
        Intent intent = new Intent(packageContext, BillingActivity.class);
        intent.putExtra(EXTRA_CLIENT_ID, client_id);
        intent.putExtra(EXTRA_CLIENT_NAME, client_name);
        intent.putExtra(EXTRA_PRODUCT_NAME, prd_name);
        intent.putExtra(EXTRA_PRODUCT_PRICE, prd_price);
        intent.putExtra(EXTRA_PRODUCT_QTY, prd_qty);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billing);

        //Decoding the extra data from the intent object
        int clientIdRetrieved = getIntent().getIntExtra(EXTRA_CLIENT_ID,0);
        String clientNameRetrieved = getIntent().getStringExtra(EXTRA_CLIENT_NAME);
        String prdNameRetrieved = getIntent().getStringExtra(EXTRA_PRODUCT_NAME);
        double prdPriceRetrieved = getIntent().getDoubleExtra(EXTRA_PRODUCT_PRICE,0.0);
        int prdQtyRetrieved = getIntent().getIntExtra(EXTRA_PRODUCT_QTY, 0);

        //Get the view of client_id_edit_view_billingA
        clientIdEditText = (EditText) findViewById(R.id.client_id_edit_view_billingA);
        clientIdEditText.setText(clientIdRetrieved);

        //Get the view of client_name_edit_view_billingA
        clientNameEditText = (EditText) findViewById(R.id.client_name_edit_view_billingA);
        clientNameEditText.setText(clientNameRetrieved);

        //Get the view of product_name_edit_view_billingA
        prdNameEditText = (EditText) findViewById(R.id.product_name_edit_view_billingA);
        prdNameEditText.setText(prdNameRetrieved);

        //Get the view of product_price_edit_view_billingA
        prdPriceEditText = (EditText) findViewById(R.id.product_price_edit_view_billingA);
        prdPriceEditText.setText(String.valueOf(prdPriceRetrieved));

        //Get the view of product_qty_edit_view_billingA
        prdQtyEditText = (EditText) findViewById(R.id.product_qty_edit_view_billingA);
        prdQtyEditText.setText(prdQtyRetrieved);

        //Get the view of billing_update_button
        billingUpdateButton = (Button) findViewById(R.id.billing_update_button);
        billingUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Update Database

                //Coding updated Billing info as extra parameter
                setBillingUpdateCodeResult(Integer.parseInt(clientIdEditText.getText().toString()),
                        clientNameEditText.getText().toString(),
                        prdNameEditText.getText().toString(),
                        Double.parseDouble(prdPriceEditText.getText().toString()),
                        Integer.parseInt(prdQtyEditText.getText().toString()));
            }
        });
    } //end of onCreate()

    //coding Extra data Intent from child to parent Activity
    private void setBillingUpdateCodeResult(int client_id, String client_name,
                                            String prd_name, double prd_price, int prd_qty)
    {
        Intent dataIntent = new Intent();
        dataIntent.putExtra(EXTRA_CLIENT_ID, client_id);
        dataIntent.putExtra(EXTRA_CLIENT_NAME, client_name);
        dataIntent.putExtra(EXTRA_PRODUCT_NAME, prd_name);
        dataIntent.putExtra(EXTRA_PRODUCT_PRICE, prd_price);
        dataIntent.putExtra(EXTRA_PRODUCT_QTY, prd_qty);
        setResult(RESULT_OK, dataIntent);
    }

    //Decoding Extra data Intent in ParentActivity
    public static Billing sendMessageBillingUpdateResult(Intent resultIntent)
    {
        Billing billingUpdateInfo = new Billing();
        billingUpdateInfo.setClient_id(resultIntent.getIntExtra(EXTRA_CLIENT_ID,0));
        billingUpdateInfo.setClient_name(resultIntent.getStringExtra(EXTRA_CLIENT_NAME));
        billingUpdateInfo.setProduct_name(resultIntent.getStringExtra(EXTRA_PRODUCT_NAME));
        billingUpdateInfo.setPrd_price(resultIntent.getDoubleExtra(EXTRA_PRODUCT_PRICE, 0.0));
        billingUpdateInfo.setPrd_qty(resultIntent.getIntExtra(EXTRA_PRODUCT_QTY,0));
        return billingUpdateInfo;
    }

}