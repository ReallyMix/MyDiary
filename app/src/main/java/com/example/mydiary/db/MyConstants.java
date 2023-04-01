package com.example.mydiary.db;

public class MyConstants {

    public static final String TABLE_NAME = "affairs";
    public static final String _ID = "_id";
    public static final String TITLE = "title";
    public static final String DISCRIPTION = "discription";
    public static final String DB_NAME = "affairs_db.db";
    public static final String ISCOMPLETED = "is_completed";
    public static final String DAY = "day";
    public static final int DB_VERSION = 7;


    public static final String DB_STRUCTURE = "CREATE TABLE IF NOT EXISTS " +
            TABLE_NAME + " (" + _ID + " INTEGER PRIMARY KEY," +
            TITLE + " TEXT," + DISCRIPTION + " TEXT," + ISCOMPLETED + " NUMERIC," + DAY + " INTEGER NOT NULL)";

    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

}
