package com.example.billingproject.database;

public class BillingDbSchema {

    public static final class BillingTable
    {
        public static final String NAME = "billing"; //BillingTable.NAME

        public static final class Cols
        {
            public static final String CLIENT_ID = "client_id"; //BillingTable.Cols.BILLING_ID
            public static final String CLIENT_NAME = "client_name";
            public static final String PRODUCT_NAME = "product_name";
            public static final String PRD_PRICE = "prd_price";
            public static final String PRD_QTY = "prd_qty";
        }
    }
}
