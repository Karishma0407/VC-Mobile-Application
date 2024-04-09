package com.example.salonproject.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.salonproject.User;

import java.util.ArrayList;

public class UserBaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "userBase.db";

    public UserBaseHelper(@Nullable Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }

    public UserBaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + UserDbSchema.UserTable.NAME + "(" +
                UserDbSchema.UserTable.Cols.USER_ID + "," +
                UserDbSchema.UserTable.Cols.USER_FNAME + "," +
                UserDbSchema.UserTable.Cols.USER_LNAME + ","  +
                UserDbSchema.UserTable.Cols.USER_PHONE + "," +
                UserDbSchema.UserTable.Cols.USER_EMAIL + "," +
                UserDbSchema.UserTable.Cols.USER_PWD + ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public static ContentValues getContentValues (User user)
    {
        ContentValues values = new ContentValues();
        values.put(UserDbSchema.UserTable.Cols.USER_ID, user.getUser_id());
        values.put(UserDbSchema.UserTable.Cols.USER_FNAME, user.getUser_fName());
        values.put(UserDbSchema.UserTable.Cols.USER_LNAME, user.getUser_lName());
        values.put(UserDbSchema.UserTable.Cols.USER_PHONE, user.getUser_phone());
        values.put(UserDbSchema.UserTable.Cols.USER_EMAIL, user.getUser_email());
        values.put(UserDbSchema.UserTable.Cols.USER_PWD, user.getUser_pwd());
        return values;
    }

    //Insert
    public void addNewUser(User user)
    {
        //Writing data into database
        SQLiteDatabase db =  this.getWritableDatabase();

        //Creating values from ContentValues
        ContentValues values = getContentValues(user);

        //Insert values into table row
        db.insert(UserDbSchema.UserTable.NAME, null, values);

        //Close database
        db.close();
    }

}
