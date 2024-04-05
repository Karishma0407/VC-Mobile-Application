package com.example.salonproject.database;

public class UserDbSchema {

    public static final class UserTable
    {
        public static final String NAME = "user";
        public static final class Cols
        {
            public static final String USER_ID = "user_id";
            public static final String USER_FNAME = "user_fName";
            public static final String USER_LNAME = "user_lName";
            public static final String USER_PHONE = "user_phone";
            public static final String USER_EMAIL = "user_email";
            public static final String USER_PWD = "user_pwd";

        }
    }
}
