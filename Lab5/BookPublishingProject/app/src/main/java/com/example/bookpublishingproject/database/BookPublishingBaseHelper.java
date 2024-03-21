package com.example.bookpublishingproject.database;

import static com.example.bookpublishingproject.database.PublisherDbSchema.PublisherTable;
import static com.example.bookpublishingproject.database.BookDbSchema.BookTable;
import static com.example.bookpublishingproject.database.ChapterDbSchema.ChapterTable;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BookPublishingBaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "BookPublishingBase.db";

    public BookPublishingBaseHelper(@Nullable Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }

    public BookPublishingBaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //Create Publisher Table
        db.execSQL("CREATE TABLE " + PublisherDbSchema.PublisherTable.NAME + "(" +
                PublisherTable.Cols.P_ID + " PRIMARY KEY," +
                PublisherTable.Cols.P_NAME + "," +
                PublisherTable.Cols.P_ADDRESS + ")");

        //Create Book Table
        db.execSQL("CREATE TABLE " + BookDbSchema.BookTable.NAME + "(" +
                BookTable.Cols.B_ID + " PRIMARY KEY," +
                BookTable.Cols.B_AUTHOR + "," +
                BookTable.Cols.B_TITLE + "," +
                BookTable.Cols.B_ISBN + "," +
                BookTable.Cols.B_TYPE + "," +
                BookTable.Cols.B_PRICE + "," +
                BookTable.Cols.P_ID + "," +
                "FOREIGN KEY (" + BookTable.Cols.P_ID + ") REFERENCES " +
                PublisherTable.NAME + "(" + PublisherTable.Cols.P_ID + "))");

        //Create Chapter Table
        db.execSQL("CREATE TABLE " + ChapterTable.NAME + "(" +
                ChapterTable.Cols.B_ID + "," +
                ChapterTable.Cols.C_NO + "," +
                ChapterTable.Cols.C_TITLE + "," +
                ChapterTable.Cols.C_PRICE + "," +
                "PRIMARY KEY (" + ChapterTable.Cols.B_ID + ", " +
                ChapterTable.Cols.C_NO + "), " +
                "FOREIGN KEY (" + ChapterTable.Cols.B_ID + ") REFERENCES " +
                BookTable.NAME + "(" + BookTable.Cols.B_ID + "))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
