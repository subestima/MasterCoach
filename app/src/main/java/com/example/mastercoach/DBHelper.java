package com.example.mastercoach;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/*
*   wip = create extra tables to club info like : players, current league, money, current pts .
*/

public class DBHelper extends SQLiteOpenHelper
{

    public DBHelper(Context context)
    {
        super(context, "UserData.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB)
    {
        DB.execSQL("create Table UserDetails(name TEXT primary key, club TEXT, age TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion)
    {
        DB.execSQL("drop Table if exists UserDetails");
    }

    public Boolean insertUserData(String name, String club, String age)
    {
        SQLiteDatabase DB =  this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("name", name);
        contentValues.put("club", club);
        contentValues.put("age", age);

        long result = DB.insert("UserDetails", null, contentValues);

        return (result != 1);
    }

    public Boolean updateUserData(String name, String club)
    {
        SQLiteDatabase DB =  this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("club", club);

        Cursor cursor = DB.rawQuery("Select * from UserDetails Where name = ?", new String[] { name });

        if(cursor.getCount() > 0)
        {
            long result = DB.update("UserDetails", contentValues, "name=?", new String[] { name });

            return (result != 1);
        }

        cursor.close();

        return false;
    }

    public Boolean deleteUserData(String name)
    {
        SQLiteDatabase DB =  this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("Select * from UserDetails Where name = ?", new String[] { name });

        if(cursor.getCount() > 0)
        {
            long result = DB.delete("UserDetails", "name = ?", new String[] { name });

            return (result != 1);
        }

        cursor.close();

        return false;
    }

    public Cursor getUserData()
    {
        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("Select * from UserDetails", null);

        return cursor;
    }

}
