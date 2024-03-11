package com.example.billingproject.database;

import static com.example.billingproject.database.BillingDbSchema.BillingTable;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.billingproject.Billing;

import java.util.ArrayList;

public class BillingBaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "billingBase.db";

    public BillingBaseHelper(@Nullable Context context)
    {
        super(context, DATABASE_NAME, null, VERSION);
    }

    public BillingBaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + BillingTable.NAME + "(" +
                BillingTable.Cols.CLIENT_ID + "," +
                BillingTable.Cols.CLIENT_NAME + "," +
                BillingTable.Cols.PRODUCT_NAME + "," +
                BillingTable.Cols.PRD_PRICE + "," +
                BillingTable.Cols.PRD_QTY + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public static ContentValues getContentValues (Billing billing)
    {
        ContentValues values = new ContentValues();
        values.put(BillingTable.Cols.CLIENT_ID, billing.getClient_id());
        values.put(BillingTable.Cols.CLIENT_NAME, billing.getClient_name());
        values.put(BillingTable.Cols.PRODUCT_NAME, billing.getProduct_name());
        values.put(BillingTable.Cols.PRD_PRICE, billing.getPrd_price());
        values.put(BillingTable.Cols.PRD_QTY, billing.getPrd_qty());
        return values;
    }

    //Insert data
    public void addNewBillingDetails(Billing billing)
    {
        //Writing data into database
        SQLiteDatabase db = this.getWritableDatabase();

        //Creating values from ContentValues
        ContentValues values = getContentValues(billing);

        //Insert values into table row
        db.insert(BillingTable.NAME, null, values);

        //Close database
        db.close();
    }

    //Read/Search
    public ArrayList<Billing> readBillingDetails()
    {
        //Reading data from database
        SQLiteDatabase db = this.getReadableDatabase();

        //Use cursor as a Data Structure
        Cursor cursorBilling = db.rawQuery("SELECT * FROM " + BillingTable.NAME, null);

        //Create ArrayList billingModelArrayList
        ArrayList<Billing> billingModelArrayList = new ArrayList<>();

        //Moving cursor to first position
        if(cursorBilling.moveToFirst())
        {
            do{
                //Populate ArrayList billingModelArrayList
                billingModelArrayList.add(new Billing(cursorBilling.getInt(0),
                        cursorBilling.getString(1), cursorBilling.getString(2),
                        cursorBilling.getDouble(3), cursorBilling.getInt(4)));
            }
            while(cursorBilling.moveToNext());
        }
        //Close cursor
        cursorBilling.close();
        return billingModelArrayList;
    }

    //Update
    public void updateBilling (Billing billing)
    {
        String client_idString = String.valueOf(billing.getClient_id());
        //Creating values from ContentValues
        ContentValues values = getContentValues(billing);
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(BillingTable.NAME, values, BillingTable.Cols.CLIENT_ID + "=?",
                new String[] {client_idString});
    }

    //Delete
    public void deleteBilling (Billing billing)
    {
        String client_idString = String.valueOf(billing.getClient_id());
        //Creating values from ContentValues
        ContentValues values = getContentValues(billing);
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(BillingTable.NAME,BillingTable.Cols.CLIENT_ID + "=?",
                new String[] {client_idString});
    }

}
