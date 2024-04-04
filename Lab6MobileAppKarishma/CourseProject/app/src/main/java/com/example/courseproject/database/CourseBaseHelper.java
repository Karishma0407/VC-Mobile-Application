package com.example.courseproject.database;

import static com.example.courseproject.database.CourseDbSchema.CourseTable;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.courseproject.Course;

import java.util.ArrayList;

public class CourseBaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "courseBase.db";

    public CourseBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    public CourseBaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + CourseDbSchema.CourseTable.NAME + "(" +
                CourseTable.Cols.COURSE_NO + "," +
                CourseTable.Cols.COURSE_NAME + "," +
                CourseTable.Cols.MAX_ENRL + "," +
                CourseTable.Cols.CREDITS + ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    public static ContentValues getContentValues (Course course)
    {
        ContentValues values = new ContentValues();
        values.put(CourseTable.Cols.COURSE_NO, course.getCourse_no());
        values.put(CourseTable.Cols.COURSE_NAME, course.getCourse_name());
        values.put(CourseTable.Cols.MAX_ENRL, course.getMax_enrl());
        values.put(CourseTable.Cols.CREDITS, course.credits);
        return values;
    }

    // Insert
    public void addNewCourse(Course course)
    {
        //Writing data into database
        SQLiteDatabase db =  this.getWritableDatabase();

        //Creating values from ContentValues
        ContentValues values = getContentValues(course);

        //Insert values into table row
        db.insert(CourseTable.NAME, null, values);

        //Close database
        db.close();
    }

    //Read/Search
    public ArrayList<Course> readCourse()
    {
        //Reading data into database
        SQLiteDatabase db =  this.getReadableDatabase();

        //Use cursor as Data Structure
        Cursor cursorCourse = db.rawQuery("SELECT * FROM " + CourseTable.NAME, null);

        //Create ArrayList courseModelArrayList
        ArrayList<Course> courseModelArrayList = new ArrayList<>();

        //Moving cursor to first position
        if(cursorCourse.moveToFirst())
        {
            do{
                //Populate ArrayList courseModelArrayList
                courseModelArrayList.add(new Course (cursorCourse.getString(0),
                        cursorCourse.getString(1), cursorCourse.getInt(2)));
            }
            while (cursorCourse.moveToNext());
        }
        //Close cursor
        cursorCourse.close();

        return courseModelArrayList;
    }

    //Update
    public void updateCourse (Course course)
    {
        String course_noString = course.getCourse_no();
        //Creating values from ContentValues
        ContentValues values = getContentValues(course);
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(CourseTable.NAME, values, CourseTable.Cols.COURSE_NO + "=?",
                new String[] {course_noString});
    }

    //Delete
    public void deleteCourse (Course course)
    {
        String course_noString = course.getCourse_no();
        //Creating values from ContentValues
        ContentValues values = getContentValues(course);
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(CourseTable.NAME, CourseTable.Cols.COURSE_NO + "=?",
                new String[] {course_noString});
    }

}
