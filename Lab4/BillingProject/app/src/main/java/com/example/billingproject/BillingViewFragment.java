package com.example.billingproject;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.billingproject.database.BillingBaseHelper;

import java.util.ArrayList;

public class BillingViewFragment extends Fragment {

    private TextView billingListTextView;
    Context context;
    ArrayList<Billing> billingModelArrayList;

    public BillingViewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = getContext().getApplicationContext();

        //Read table rows calling readBillingDetails
        billingModelArrayList = new BillingBaseHelper(context).readBillingDetails();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_billing_view, container, false);

        //Get the view of billingList_textView
        billingListTextView = (TextView) v.findViewById(R.id.billingList_textView);

        //Format billing data into a string
        StringBuilder stringBuilder = new StringBuilder();
        for (Billing billing : billingModelArrayList)
        {
            stringBuilder.append("Client Id: ").append(billing.getClient_id()).append("\n");
            stringBuilder.append("Client Name: ").append(billing.getClient_name()).append("\n");
            stringBuilder.append("Product Name: ").append(billing.getProduct_name()).append("\n");
            stringBuilder.append("Product Price: ").append(billing.getPrd_price()).append("\n");
            stringBuilder.append("Product Quantity: ").append(billing.getPrd_qty()).append("\n");
        }
        //Set the formatted string to the TextView
        billingListTextView.setText(stringBuilder.toString());

        /*
        String allBilling = "";
        //Read all content of billingModelArrayList
        for(Billing billing:billingModelArrayList)
        {
            allBilling += billing.toString();
        }

        //Set the content of allBilling into text attribute of billingListTextView
        billingListTextView.setText(allBilling);
        */

        return v;
    }
}