package com.example.mydiary.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DBManager {

    private Context context;
    private DBHelper dbHelper;
    private SQLiteDatabase db;

    public DBManager(Context context) {
        this.context = context;
        dbHelper = new DBHelper(context);
    }

    public void openDB() {
        db = dbHelper.getWritableDatabase();
    }

    public void insert(String title, String discription, String ddMMyy) {

        ContentValues cv = new ContentValues();
        cv.put(MyConstants.TITLE, title);
        cv.put(MyConstants.DISCRIPTION, discription);
        cv.put(MyConstants.ISCOMPLETED, 0);
        cv.put(MyConstants.DAY, ddMMyy);

        db.insert(MyConstants.TABLE_NAME, null, cv);
    }

    public void complete(int id) {
        ContentValues cv = new ContentValues();
        cv.put(MyConstants.ISCOMPLETED, 1);
        db.update(MyConstants.TABLE_NAME, cv, "_id = ?", new String[]{String.valueOf(id)});
    }

    public void decomplete(int id) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MyConstants.ISCOMPLETED, 0);
        db.update(MyConstants.TABLE_NAME, contentValues, "_id = ?", new String[] {String.valueOf(id)});
    }

    public void update(String title, String discription, int id) {

        ContentValues contentValues = new ContentValues();

        contentValues.put(MyConstants.TITLE, title);
        contentValues.put(MyConstants.DISCRIPTION, discription);

        db.update(MyConstants.TABLE_NAME, contentValues, "_id = ?", new String[] {String.valueOf(id)});
    }

    public void delete(int id) {

        db.delete(MyConstants.TABLE_NAME, "_id = ?", new String[] {String.valueOf(id)});
    }

    public List<Integer> getListOfId(String ddMMyy) {

        List<Integer> IDList = new ArrayList<>();

        String[] selectionArgs = new String[] {ddMMyy};

        Cursor cursor = db.query(MyConstants.TABLE_NAME, null, MyConstants.DAY + " =?",selectionArgs,null,null,null);

        while (cursor.moveToNext()) {

            @SuppressLint("Range") Integer id = cursor.getInt(cursor.getColumnIndex(MyConstants._ID));
            IDList.add(id);
        }
        cursor.close();
        return IDList;
    }

    public List<Integer> getIsCompletedFromDB(String ddMMyy) {

        List<Integer> IsCompletedList = new ArrayList<>();

        String[] selectionArgs = new String[] {ddMMyy};

        Cursor cursor = db.query(MyConstants.TABLE_NAME, null, MyConstants.DAY+" =?",selectionArgs,null,null,null);

        while (cursor.moveToNext()) {
            @SuppressLint("Range") Integer iscompleted = cursor.getInt(cursor.getColumnIndex(MyConstants.ISCOMPLETED));
            IsCompletedList.add(iscompleted);
        }
        cursor.close();
        return IsCompletedList;
    }

    public List<String> getTitleFromDB(String ddMMyy) {

        List<String> TitleList = new ArrayList<>();

        String[] selectionArgs = new String[] {ddMMyy};


        Cursor cursor = db.query(MyConstants.TABLE_NAME, null, MyConstants.DAY + " =?",selectionArgs,null,null,null);


        while (cursor.moveToNext()) {
            @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex(MyConstants.TITLE));
            TitleList.add(title);
        }
        cursor.close();
        return TitleList;
    }

    public List<String> getDiscFromDB(String ddMMyy) {

        List<String> DiscList = new ArrayList<>();

        String[] selectionArgs = new String[] {ddMMyy};


        Cursor cursor = db.query(MyConstants.TABLE_NAME, null, MyConstants.DAY+ " =?",selectionArgs,null,null,null);


        while (cursor.moveToNext()) {
            @SuppressLint("Range") String disc = cursor.getString(cursor.getColumnIndex(MyConstants.DISCRIPTION));
            DiscList.add(disc);
        }
        cursor.close();
        return DiscList;
    }

    public void closeDB() {
        dbHelper.close();
    }
}
