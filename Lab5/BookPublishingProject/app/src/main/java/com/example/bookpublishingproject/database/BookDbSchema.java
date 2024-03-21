package com.example.bookpublishingproject.database;

public class BookDbSchema {

        public static final class BookTable
        {
            public static final String NAME = "book";

            public static final class Cols
            {
                public static final String B_ID = "b_id";
                public static final String B_AUTHOR = "b_author";
                public static final String B_TITLE = "b_title";
                public static final String B_ISBN = "b_isbn";
                public static final String B_TYPE = "b_type";
                public static final String B_PRICE = "b_price";
                public static final String P_ID = "p_id";

            }

        }

}
