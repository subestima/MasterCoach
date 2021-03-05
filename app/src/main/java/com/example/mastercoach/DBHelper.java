package com.example.mastercoach;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper
{

    public DBHelper(Context context)
    {
        super(context, "UserData.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB)
    {
        DB.execSQL("create Table UserDetails(name TEXT primary key, club TEXT, age TEXT, gamesPlayed INT)");

        DB.execSQL("create Table CalendarDetails(adversary TEXT primary key, result TEXT)");

        DB.execSQL("create Table AmateurLeagueDetails(club_name TXT primary key, points INT, bank REAL, coach TXT, doctor TEXT, offensive_trainer TEXT, defensive_trainer TEXT)");
        DB.execSQL("create Table SemiProLeagueDetails(club_name TXT primary key, points INT, bank REAL, coach TXT, doctor TEXT, offensive_trainer TEXT, defensive_trainer TEXT)");
        DB.execSQL("create Table ProLeagueDetails(club_name TXT primary key, points INT, bank REAL, coach TXT, doctor TEXT, offensive_trainer TEXT, defensive_trainer TEXT)");

        DB.execSQL("create Table PlayerDetails(name TXT primary key, club TEXT, age INT, position TXT, cb_points INT, mf_points INT, cf_points INT, release_clause REAL)");

        DB.execSQL("create Table DoctorDetails(name TXT primary key, age INT, club TXT, points INT, release_clause REAL)");

        DB.execSQL("create Table DefensiveTrainerDetails(name TXT primary key, age INT, club TXT, points INT, release_clause REAL)");
        DB.execSQL("create Table OffensiveTrainerDetails(name TXT primary key, age INT, club TXT, points INT, release_clause REAL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion)
    {
        DB.execSQL("drop Table if exists UserDetails");
        DB.execSQL("drop Table if exists CalendarDetails");
        DB.execSQL("drop Table if exists AmateurLeagueDetails");
        DB.execSQL("drop Table if exists SemiProLeagueDetails");
        DB.execSQL("drop Table if exists ProLeagueDetails");
        DB.execSQL("drop Table if exists PlayerDetails");
        DB.execSQL("drop Table if exists DoctorDetails");
        DB.execSQL("drop Table if exists DefensiveTrainerDetails");
        DB.execSQL("drop Table if exists OffensiveTrainerDetails");
    }

    // Global Methods
    public void eraseGame()
    {
        SQLiteDatabase DB = this.getWritableDatabase();

        DB.delete("UserDetails", null, null);

        DB.delete("CalendarDetails", null, null);

        DB.delete("AmateurLeagueDetails", null, null);
        DB.delete("SemiProLeagueDetails", null, null);
        DB.delete("ProLeagueDetails", null, null);

        DB.delete("PlayerDetails", null, null);

        DB.delete("DoctorDetails", null, null);

        DB.delete("DefensiveTrainerDetails", null, null);
        DB.delete("OffensiveTrainerDetails", null, null);
    }

    // UserDetails Table Methods
    public Boolean insertUserData(String name, String club, String age, int games_played)
    {
        SQLiteDatabase DB =  this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("name", name);
        contentValues.put("club", club);
        contentValues.put("age", age);
        contentValues.put("gamesPlayed", games_played);

        long result = DB.insert("UserDetails", null, contentValues);

        return (result != 1);
    }

    public Boolean updateUserClub(String name, String club)
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

    public Boolean updateUserGamesPlayed(String name, int gamesPlayed)
    {
        SQLiteDatabase DB =  this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put("gamesPlayed", gamesPlayed);

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

    public String [] getUserDetails()
    {
        String [] user_details = new String[4];

        Cursor cursor = getUserData();

        cursor.moveToFirst();

        user_details[0] = cursor.getString(0);
        user_details[1] = cursor.getString(1);
        user_details[2] = cursor.getString(2);
        user_details[3] = cursor.getString(3);

        return user_details;
    }

    public String getUserName(Cursor cursor)
    {
        return cursor.getString(0);
    }

    public String getUserAge(Cursor cursor)
    {
        return cursor.getString(2);
    }

    public String getUserClubName(Cursor cursor)
    {
        return cursor.getString(1);
    }

    public Cursor getUserData()
    {
        SQLiteDatabase DB = this.getWritableDatabase();

        return DB.rawQuery("Select * from UserDetails", null);
    }
    // Calendar Table Methods

    public Boolean insertNewResult(String result)
    {
        SQLiteDatabase DB =  this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put("result", result);

        long r = DB.insert("CalendarDetails", null, contentValues);

        return (r != 1);
    }

    // Player Table Methods

    public Boolean insertPlayerData(String name, String club, int age, String position, int cb_points, int mf_points, int cf_points, double release_clause)
    {
        SQLiteDatabase DB =  this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put("name", name);
        contentValues.put("club", club);
        contentValues.put("age", age);
        contentValues.put("position", position);
        contentValues.put("cb_points", cb_points);
        contentValues.put("mf_points", mf_points);
        contentValues.put("cf_points", cf_points);
        contentValues.put("release_clause", release_clause);

        long result = DB.insert("PlayerDetails", null, contentValues);

        return (result != 1);
    }

    public Boolean updatePlayerClubData(String name, String new_club)
    {
        SQLiteDatabase DB =  this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put("club", new_club);

        Cursor cursor = DB.rawQuery("Select * from PlayerDetails Where name = ?", new String[] { name });

        if(cursor.getCount() > 0)
        {
            long result = DB.update("PlayerDetails", contentValues, "name=?", new String[] { name });

            return (result != 1);
        }

        cursor.close();

        return false;
    }

    public Boolean deletePlayerData(String name)
    {
        SQLiteDatabase DB =  this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("Select * from PlayerDetails Where name = ?", new String[] { name });

        if(cursor.getCount() > 0)
        {
            long result = DB.delete("PlayerDetails", "name = ?", new String[] { name });

            return (result != 1);
        }

        cursor.close();

        return false;
    }

    public Cursor getPlayerData()
    {
        SQLiteDatabase DB = this.getWritableDatabase();

        return DB.rawQuery("Select * from PlayerDetails", null);
    }

    public String getPlayerClub(Cursor cursor)
    {
        return cursor.getString(cursor.getColumnIndex("club"));
    }

    public String getPlayerName(Cursor cursor)
    {
        return cursor.getString(cursor.getColumnIndex("name"));
    }

    // Doctor Table Methods

    public Boolean insertDoctorData(String name, int age ,String club, int points, double release_clause)
    {
        SQLiteDatabase DB =  this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("name", name);
        contentValues.put("age", age);
        contentValues.put("club", club);
        contentValues.put("points", points);
        contentValues.put("release_clause", release_clause);

        long result = DB.insert("DoctorDetails", null, contentValues);

        return (result != 1);
    }

    public Boolean updateDoctorData(String name, String club)
    {
        SQLiteDatabase DB =  this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("club", club);

        Cursor cursor = DB.rawQuery("Select * from DoctorDetails Where name = ?", new String[] { name });

        if(cursor.getCount() > 0)
        {
            long result = DB.update("DoctorDetails", contentValues, "name=?", new String[] { name });

            return (result != 1);
        }

        cursor.close();

        return false;
    }

    public Boolean deleteDoctorData(String name)
    {
        SQLiteDatabase DB =  this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("Select * from DoctorDetails Where name = ?", new String[] { name });

        if(cursor.getCount() > 0)
        {
            long result = DB.delete("DoctorDetails", "name = ?", new String[] { name });

            return (result != 1);
        }

        cursor.close();

        return false;
    }

    public Cursor getDoctorData()
    {
        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("Select * from DoctorDetails", null);

        return cursor;
    }

    // Defensive Trainer Table Methods

    public Boolean insertDefensiveTrainerData(String name, int age, String club, int points, double release_clause)
    {
        SQLiteDatabase DB =  this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("name", name);
        contentValues.put("club", club);
        contentValues.put("age", age);
        contentValues.put("points", points);
        contentValues.put("release_clause", release_clause);

        long result = DB.insert("DefensiveTrainerDetails", null, contentValues);

        return (result != 1);
    }

    public Boolean updateDefensiveTrainerData(String name, String club)
    {
        SQLiteDatabase DB =  this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("club", club);

        Cursor cursor = DB.rawQuery("Select * from DefensiveTrainerDetails Where name = ?", new String[] { name });

        if(cursor.getCount() > 0)
        {
            long result = DB.update("DefensiveTrainerDetails", contentValues, "name=?", new String[] { name });

            return (result != 1);
        }

        cursor.close();

        return false;
    }

    public Boolean deleteDefensiveTrainerData(String name)
    {
        SQLiteDatabase DB =  this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("Select * from DefensiveTrainerDetails Where name = ?", new String[] { name });

        if(cursor.getCount() > 0)
        {
            long result = DB.delete("DefensiveTrainerDetails", "name = ?", new String[] { name });

            return (result != 1);
        }

        cursor.close();

        return false;
    }

    public Cursor getDefensiveTrainerData()
    {
        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("Select * from DefensiveTrainerDetails", null);

        return cursor;
    }

    // OFFENSIVE TRAINER TABLE METHODS


    public Boolean insertOffensiveTrainerData(String name, int age, String club, int points, double release_clause)
    {
        SQLiteDatabase DB =  this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("name", name);
        contentValues.put("club", club);
        contentValues.put("age", age);
        contentValues.put("points", points);
        contentValues.put("release_clause", release_clause);

        long result = DB.insert("OffensiveTrainerDetails", null, contentValues);

        return (result != 1);
    }

    public Boolean updateOffensiveTrainerData(String name, String club)
    {
        SQLiteDatabase DB =  this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("club", club);

        Cursor cursor = DB.rawQuery("Select * from OffensiveTrainerDetails Where name = ?", new String[] { name });

        if(cursor.getCount() > 0)
        {
            long result = DB.update("OffensiveTrainerDetails", contentValues, "name=?", new String[] { name });

            return (result != 1);
        }

        cursor.close();

        return false;
    }

    public Boolean deleteOffensiveTrainerData(String name)
    {
        SQLiteDatabase DB =  this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("Select * from OffensiveTrainerDetails Where name = ?", new String[] { name });

        if(cursor.getCount() > 0)
        {
            long result = DB.delete("OffensiveTrainerDetails", "name = ?", new String[] { name });

            return (result != 1);
        }

        cursor.close();

        return false;
    }

    public Cursor getOffensiveTrainerData()
    {
        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("Select * from OffensiveTrainerDetails", null);

        return cursor;
    }

    // LEAGUES UNIVERSAL METHODS

    public double getTeamBudget(Cursor cursor)
    {
        return cursor.getDouble(2);
    }

    public String getTeamName(Cursor cursor)
    {
        return cursor.getString(0);
    }

    public int getTeamPoints(Cursor cursor)
    {
        return cursor.getInt(1);
    }



    // AMATEUR LEAGUE TABLE METHODS


    public Boolean insertAmateurLeagueData(String name, int points, double bank, String coach, String doctor, String offensive_trainer, String defensive_trainer)
    {
        SQLiteDatabase DB =  this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        Log.d("Insert = ", name);

        contentValues.put("club_name", name);
        contentValues.put("points", points);
        contentValues.put("bank", bank);
        contentValues.put("coach", coach);
        contentValues.put("doctor", doctor);
        contentValues.put("offensive_trainer", offensive_trainer);
        contentValues.put("defensive_trainer", defensive_trainer);

        long result = DB.insert("AmateurLeagueDetails", null, contentValues);

        return (result != 1);
    }

    public Boolean setAmateurTeamPoints(String name, int points)
    {
        SQLiteDatabase DB =  this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put("points", points);

        Cursor cursor = DB.rawQuery("Select * from AmateurLeagueDetails Where name = ?", new String[] { name });

        if(cursor.getCount() > 0)
        {
            long result = DB.update("AmateurLeagueDetails", contentValues, "name=?", new String[] { name });

            return (result != 1);
        }

        cursor.close();

        return false;
    }

    public Boolean updateAmateurLeagueData(String name, int points, double bank, String coach, String doctor, String offensive_trainer, String defensive_trainer)
    {
        SQLiteDatabase DB =  this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put("club_name", name);
        contentValues.put("points", points);
        contentValues.put("bank", bank);
        contentValues.put("coach", coach);
        contentValues.put("doctor", doctor);
        contentValues.put("offensive_trainer", offensive_trainer);
        contentValues.put("defensive_trainer", defensive_trainer);

        Cursor cursor = DB.rawQuery("Select * from AmateurLeagueDetails Where name = ?", new String[] { name });

        if(cursor.getCount() > 0)
        {
            long result = DB.update("AmateurLeagueDetails", contentValues, "name=?", new String[] { name });

            return (result != 1);
        }

        cursor.close();

        return false;
    }

    public Boolean deleteAmateurLeagueData(String name)
    {
        SQLiteDatabase DB =  this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("Select * from AmateurLeagueDetails Where name = ?", new String[] { name });

        if(cursor.getCount() > 0)
        {
            long result = DB.delete("AmateurLeagueDetails", "name = ?", new String[] { name });

            return (result != 1);
        }

        cursor.close();

        return false;
    }

    public Cursor getAmateurLeagueData()
    {
        SQLiteDatabase DB = this.getWritableDatabase();

        return DB.rawQuery("Select * from AmateurLeagueDetails", null);
    }

    // SEMI PRO LEAGUE TABLE METHODS


    public Boolean insertSemiProLeagueData(String name, int points, double bank, String coach, String doctor, String offensive_trainer, String defensive_trainer)
    {
        SQLiteDatabase DB =  this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put("club_name", name);
        contentValues.put("points", points);
        contentValues.put("bank", bank);
        contentValues.put("coach", coach);
        contentValues.put("doctor", doctor);
        contentValues.put("offensive_trainer", offensive_trainer);
        contentValues.put("defensive_trainer", defensive_trainer);

        long result = DB.insert("SemiProLeagueDetails", null, contentValues);

        return (result != 1);
    }

    public Boolean updateSemiProLeagueData(String name, int points, double bank, String coach, String doctor, String offensive_trainer, String defensive_trainer)
    {
        SQLiteDatabase DB =  this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put("club_name", name);
        contentValues.put("points", points);
        contentValues.put("bank", bank);
        contentValues.put("coach", coach);
        contentValues.put("doctor", doctor);
        contentValues.put("offensive_trainer", offensive_trainer);
        contentValues.put("defensive_trainer", defensive_trainer);

        Cursor cursor = DB.rawQuery("Select * from SemiProLeagueDetails Where name = ?", new String[] { name });

        if(cursor.getCount() > 0)
        {
            long result = DB.update("SemiProLeagueDetails", contentValues, "name=?", new String[] { name });

            return (result != 1);
        }

        cursor.close();

        return false;
    }

    public Boolean setSemiProTeamPoints(String name, int points)
    {
        SQLiteDatabase DB =  this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put("points", points);

        Cursor cursor = DB.rawQuery("Select * from SemiProLeagueDetails Where name = ?", new String[] { name });

        if(cursor.getCount() > 0)
        {
            long result = DB.update("SemiProLeagueDetails", contentValues, "name=?", new String[] { name });

            return (result != 1);
        }

        cursor.close();

        return false;
    }

    public Boolean deleteSemiProLeagueData(String name)
    {
        SQLiteDatabase DB =  this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("Select * from SemiProLeagueDetails Where name = ?", new String[] { name });

        if(cursor.getCount() > 0)
        {
            long result = DB.delete("SemiProLeagueDetails", "name = ?", new String[] { name });

            return (result != 1);
        }

        cursor.close();

        return false;
    }

    public Cursor getSemiProLeagueData()
    {
        SQLiteDatabase DB = this.getWritableDatabase();

        return DB.rawQuery("Select * from SemiProLeagueDetails", null);
    }

    // PRO LEAGUE TABLE METHODS


    public Boolean insertProLeagueData(String name, int points, double bank, String coach, String doctor, String offensive_trainer, String defensive_trainer)
    {
        SQLiteDatabase DB =  this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put("club_name", name);
        contentValues.put("points", points);
        contentValues.put("bank", bank);
        contentValues.put("coach", coach);
        contentValues.put("doctor", doctor);
        contentValues.put("offensive_trainer", offensive_trainer);
        contentValues.put("defensive_trainer", defensive_trainer);

        long result = DB.insert("ProLeagueDetails", null, contentValues);

        return (result != 1);
    }

    public Boolean updateProLeagueData(String name, int points, double bank, String coach, String doctor, String offensive_trainer, String defensive_trainer)
    {
        SQLiteDatabase DB =  this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put("club_name", name);
        contentValues.put("points", points);
        contentValues.put("bank", bank);
        contentValues.put("coach", coach);
        contentValues.put("doctor", doctor);
        contentValues.put("offensive_trainer", offensive_trainer);
        contentValues.put("defensive_trainer", defensive_trainer);

        Cursor cursor = DB.rawQuery("Select * from ProLeagueDetails Where name = ?", new String[] { name });

        if(cursor.getCount() > 0)
        {
            long result = DB.update("ProLeagueDetails", contentValues, "name=?", new String[] { name });

            return (result != 1);
        }

        cursor.close();

        return false;
    }

    public Boolean setProTeamPoints(String name, int points)
    {
        SQLiteDatabase DB =  this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put("points", points);

        Cursor cursor = DB.rawQuery("Select * from ProLeagueDetails Where name = ?", new String[] { name });

        if(cursor.getCount() > 0)
        {
            long result = DB.update("ProLeagueDetails", contentValues, "name=?", new String[] { name });

            return (result != 1);
        }

        cursor.close();

        return false;
    }

    public Boolean deleteProLeagueData(String name)
    {
        SQLiteDatabase DB =  this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("Select * from ProLeagueDetails Where name = ?", new String[] { name });

        if(cursor.getCount() > 0)
        {
            long result = DB.delete("ProLeagueDetails", "name = ?", new String[] { name });

            return (result != 1);
        }

        cursor.close();

        return false;
    }

    public Cursor getProLeagueData()
    {
        SQLiteDatabase DB = this.getWritableDatabase();

        return DB.rawQuery("Select * from ProLeagueDetails", null);
    }
}
