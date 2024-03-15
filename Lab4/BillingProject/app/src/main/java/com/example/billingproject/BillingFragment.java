package com.example.billingproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.billingproject.database.BillingBaseHelper;

import java.util.ArrayList;

public class BillingFragment extends Fragment {

    private EditText clientIdEditText;
    private EditText clientNameEditText;
    private EditText prdNameEditText;
    private EditText prdPriceEditText;
    private EditText prdQtyEditText;
    private Button billingUpdateButton;
    private Button billingDeleteButton;
    private Button billingSearchButton;
    private Button billingViewButton;

    ArrayList<Billing> billingModelArrayList;
    Context context;

    int clientIdRetrieved;
    String clientNameRetrieved;
    String prdNameRetrieved;
    double prdPriceRetrieved;
    int prdQtyRetrieved;

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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Decoding the extra data from the intent object
        clientIdRetrieved = getActivity().getIntent().getIntExtra(EXTRA_CLIENT_ID, 0);
        clientNameRetrieved = getActivity().getIntent().getStringExtra(EXTRA_CLIENT_NAME);
        prdNameRetrieved = getActivity().getIntent().getStringExtra(EXTRA_PRODUCT_NAME);
        prdPriceRetrieved = getActivity().getIntent().getDoubleExtra(EXTRA_PRODUCT_PRICE, 0.0);
        prdQtyRetrieved = getActivity().getIntent().getIntExtra(EXTRA_PRODUCT_QTY, 0);


    } //end of onCreate()

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

//        BillingBaseHelper billingBaseHelper = new BillingBaseHelper(context);
        BillingBaseHelper billingBaseHelper = new BillingBaseHelper(getContext());


        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_billing, container, false);

        //Get the view of client_id_edit_view_billingA
        clientIdEditText = (EditText) v.findViewById(R.id.client_id_edit_view_billingA);
        clientIdEditText.setText(clientIdRetrieved + "");

        //Get the view of client_name_edit_view_billingA
        clientNameEditText = (EditText) v.findViewById(R.id.client_name_edit_view_billingA);
        clientNameEditText.setText(clientNameRetrieved);

        //Get the view of product_name_edit_view_billingA
        prdNameEditText = (EditText) v.findViewById(R.id.product_name_edit_view_billingA);
        prdNameEditText.setText(prdNameRetrieved);

        //Get the view of product_price_edit_view_billingA
        prdPriceEditText = (EditText) v.findViewById(R.id.product_price_edit_view_billingA);
        prdPriceEditText.setText(String.valueOf(prdPriceRetrieved + ""));

        //Get the view of product_qty_edit_view_billingA
        prdQtyEditText = (EditText) v.findViewById(R.id.product_qty_edit_view_billingA);
        prdQtyEditText.setText(prdQtyRetrieved + "");

        //Get the view of billing_update_button
        billingUpdateButton = (Button) v.findViewById(R.id.billing_update_button);
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
                Toast.makeText(getActivity(), clientIdRetrieved+ " Updated", Toast.LENGTH_SHORT).show();

            }
        });

        //Get the view of billing_delete_button
        billingDeleteButton = (Button) v.findViewById(R.id.billing_delete_button);
        billingDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Delete from Database
                // You need to implement a method to delete the record using the client ID
                int clientIdToDelete = Integer.parseInt(clientIdEditText.getText().toString());
                billingBaseHelper.deleteBilling(new Billing(clientIdToDelete, null, null, 0.0, 0));
                Toast.makeText(getActivity(), clientIdToDelete + " Deleted", Toast.LENGTH_SHORT).show();
            }
        });

        //Get the view of billing_search_button
        billingSearchButton = (Button) v.findViewById(R.id.billing_search_button);
        billingSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                billingBaseHelper.readBillingDetails();
                Toast.makeText(getActivity(), "Read", Toast.LENGTH_SHORT).show();
            }
        });

        //Get the view of billing_view_button
        billingViewButton = (Button) v.findViewById(R.id.billing_view_button);
        billingViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Replace BillingFragment with BillingViewFragment
                FragmentManager fm = getParentFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.replace(R.id.fragment_containerBilling, new BillingViewFragment());
                transaction.addToBackStack(null); //Allow user to navigate back
                transaction.commit();

            }
        });


        return v;
    }


    //coding Extra data Intent from child to parent Activity
    private void setBillingUpdateCodeResult(int client_id, String client_name,
                                            String prd_name, double prd_price, int prd_qty) {
        Intent dataIntent = new Intent();
        dataIntent.putExtra(EXTRA_CLIENT_ID, client_id);
        dataIntent.putExtra(EXTRA_CLIENT_NAME, client_name);
        dataIntent.putExtra(EXTRA_PRODUCT_NAME, prd_name);
        dataIntent.putExtra(EXTRA_PRODUCT_PRICE, prd_price);
        dataIntent.putExtra(EXTRA_PRODUCT_QTY, prd_qty);
        getActivity().setResult(getActivity().RESULT_OK, dataIntent);
    }

    //Decoding Extra data Intent in ParentActivity
    public static Billing sendMessageBillingUpdateResult(Intent resultIntent) {
        Billing billingUpdateInfo = new Billing();
        billingUpdateInfo.setClient_id(resultIntent.getIntExtra(EXTRA_CLIENT_ID, 0));
        billingUpdateInfo.setClient_name(resultIntent.getStringExtra(EXTRA_CLIENT_NAME));
        billingUpdateInfo.setProduct_name(resultIntent.getStringExtra(EXTRA_PRODUCT_NAME));
        billingUpdateInfo.setPrd_price(resultIntent.getDoubleExtra(EXTRA_PRODUCT_PRICE, 0.0));
        billingUpdateInfo.setPrd_qty(resultIntent.getIntExtra(EXTRA_PRODUCT_QTY, 0));
        return billingUpdateInfo;
    }
}