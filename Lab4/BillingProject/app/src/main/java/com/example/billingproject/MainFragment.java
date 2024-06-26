package com.example.billingproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.billingproject.database.BillingBaseHelper;

import java.util.ArrayList;

public class MainFragment extends Fragment {

    private EditText clientIdEdit_view;
    private EditText clientNameEdit_view;
    private EditText productNameEdit_view;
    private EditText productPriceEdit_view;
    private EditText productQuantityEdit_view;
    private TextView totalBillingText_view;
    private TextView recordBillingText_view;
    private Button totalInputBilling_button;
    private Button totalRecordBilling_button;
    private Button billingDetails_button;
    private Button prev_button;
    private Button next_button;

    private int currentIndex = 0;
    public static  String TAG = "Billing Project";
    public static String KEY_INDEX = "index";

    public Billing[] all_billingRecords;
    Context context;
    ArrayList<Billing> billingModelArraylist;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Should be retrieved from the database
        Billing billingRecord1 = new Billing(105, "Johnston Jane", "Chair", 99.99, 2);
        Billing billingRecord2 = new Billing(108, "Fikhali Samuel", "Table", 139.99, 1);
        Billing billingRecord3 = new Billing(113, "Samson Amina", "KeyUSB", 14.99, 2);

        //Data Structure: Array of Objects
        all_billingRecords = new Billing[]{billingRecord1, billingRecord2, billingRecord3};

        context = getContext().getApplicationContext();
        BillingBaseHelper billingBaseHelper = new BillingBaseHelper(context);
        billingBaseHelper.addNewBillingDetails(billingRecord1);
        billingBaseHelper.addNewBillingDetails(billingRecord2);
        billingBaseHelper.addNewBillingDetails(billingRecord3);

        //Update record

        //Delete record

    } //End of onCreate

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        //Get the view of all Edit Text and assign values from the array
        clientIdEdit_view = (EditText) v.findViewById(R.id.client_id_edit_view);
        clientIdEdit_view.setText(String.valueOf(all_billingRecords[currentIndex].getClient_id()));

        clientNameEdit_view = (EditText) v.findViewById(R.id.client_name_edit_view);
        clientNameEdit_view.setText(all_billingRecords[currentIndex].getClient_name());

        productNameEdit_view = (EditText) v.findViewById(R.id.product_name_edit_view);
        productNameEdit_view.setText(all_billingRecords[currentIndex].getProduct_name());

        productPriceEdit_view = (EditText) v.findViewById(R.id.product_price_edit_view);
        productPriceEdit_view.setText(String.valueOf(all_billingRecords[currentIndex].getPrd_price()));

        productQuantityEdit_view = (EditText) v.findViewById(R.id.product_quantity_edit_view);
        productQuantityEdit_view.setText(String.valueOf(all_billingRecords[currentIndex].getPrd_qty()));

        //Get the view of total_input_billing_button
        totalInputBilling_button = (Button) v.findViewById(R.id.total_input_billing_button);
        totalInputBilling_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Get the view of total_billing_text_view
                totalBillingText_view = (TextView) v.findViewById(R.id.total_billing_text_view);
                totalBillingText_view.setText( String.format("Total Billing: %.2f", all_billingRecords[currentIndex].CalculateBilling()) + "$");
            }
        });

        //Get the view of total_record_billing_button
        totalRecordBilling_button = (Button) v.findViewById(R.id.total_record_billing_button);
        totalRecordBilling_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Get the view of record_billing_text_view
                recordBillingText_view = (TextView) v.findViewById(R.id.record_billing_text_view);
                recordBillingText_view.setText("Client: " + all_billingRecords[currentIndex].getClient_id() +
                        ", " + all_billingRecords[currentIndex].getClient_name() +
                        ", Product: " + all_billingRecords[currentIndex].getProduct_name() +
                        " is " + String.format("%.2f", all_billingRecords[currentIndex].CalculateBilling()) + "$");

                Toast.makeText(getActivity(), "Client: " + all_billingRecords[currentIndex].getClient_id() +
                                ", " + all_billingRecords[currentIndex].getClient_name() +
                                ", Product: " + all_billingRecords[currentIndex].getProduct_name() +
                                " is " + String.format("%.2f", all_billingRecords[currentIndex].CalculateBilling()) + "$",
                        Toast.LENGTH_SHORT).show();
            }
        });

        //Get the view of next_billing_button
        next_button = (Button) v.findViewById(R.id.next_billing_button);
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
        prev_button = (Button) v.findViewById(R.id.prev_billing_button);
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

        //Get the view of billing_details_button
        billingDetails_button = (Button) v.findViewById(R.id.billing_details_button);
        billingDetails_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Start new Activity BillingActivity
                //The first approach is to call startActivity as Unidirectional Communication
                //Only use when sending data from parent activity to child activity
                int clientId = all_billingRecords[currentIndex].getClient_id();
                String clientName = all_billingRecords[currentIndex].getClient_name();
                String prdName = all_billingRecords[currentIndex].getProduct_name();
                double prdPrice = all_billingRecords[currentIndex].getPrd_price();
                int prdQty = all_billingRecords[currentIndex].getPrd_qty();
                //calling the coding Extra
                Intent intent = BillingFragment.newIntent(getActivity(), clientId, clientName,
                        prdName, prdPrice, prdQty);
                //StartActivity(intent);
                startActivityIntent.launch(intent);

            }
        });
    return v;
    }

    ActivityResultLauncher<Intent> startActivityIntent = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    //Decoding of extra data Intent
                    if (result.getResultCode() != Activity.RESULT_OK)
                    {
                        return;
                    }else
                    {
                        Billing billingUpdateInfo = BillingFragment.sendMessageBillingUpdateResult(result.getData());
                        clientIdEdit_view.setText(String.valueOf(billingUpdateInfo.getClient_id()));
                        clientNameEdit_view.setText(billingUpdateInfo.getClient_name());
                        productNameEdit_view.setText(billingUpdateInfo.getProduct_name());
                        productPriceEdit_view.setText(String.valueOf(billingUpdateInfo.getPrd_price()));
                        productQuantityEdit_view.setText(String.valueOf(billingUpdateInfo.getPrd_qty()));

                        Toast.makeText(getActivity(), "Updated Billing Record: " +
                                        "Client: " + billingUpdateInfo.getClient_id() +
                                        ", " + billingUpdateInfo.getClient_name() +
                                        ", Product: " + billingUpdateInfo.getProduct_name() +
                                        " is " + String.format("%.2f", billingUpdateInfo.CalculateBilling()) + "$",
                                Toast.LENGTH_SHORT).show();

                        //Update the array element
                        all_billingRecords[currentIndex].setClient_id(billingUpdateInfo.getClient_id());
                        all_billingRecords[currentIndex].setClient_name(billingUpdateInfo.getClient_name());
                        all_billingRecords[currentIndex].setProduct_name(billingUpdateInfo.getProduct_name());
                        all_billingRecords[currentIndex].setPrd_price(billingUpdateInfo.getPrd_price());
                        all_billingRecords[currentIndex].setPrd_qty(billingUpdateInfo.getPrd_qty());
                    }

                }
            }
    );
}