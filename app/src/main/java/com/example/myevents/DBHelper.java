package com.example.myevents;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

/**
 * DBHelper class defines the database handler to insert data into database
 */
public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "list.db";
    public static final String TABLE_NAME = "list_data";
    public static final String COL1 = "ID";
    public static final String COL2 = "NAME";
    public static final String COL3 = "DATE";
    public static final String COL4 = "TIME";


    public DBHelper(@Nullable Context context, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, version);
    }

    /**
     * creates SQL table when created
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + " NAME TEXT, DATE TEXT, TIME TEXT)";
        db.execSQL(createTable);
    }

    /**
     * deletes specified table and any data associated with it if exists
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    /**
     * adds data to table
     * @return returns true if data insertion is successful, false otherwise
     */
    public boolean addData(String name, String date, String time){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, name);
        contentValues.put(COL3, date);
        contentValues.put(COL4, time);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1) {
            return false;
        }
        return true;
    }

    /**
     * reads from database
     * @return data
     */
    public Cursor getListContents(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return data;
    }
}