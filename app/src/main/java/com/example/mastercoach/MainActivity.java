package com.example.mastercoach;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity
{
    public static Game a;
    public static int createdGame = 0;

    public static DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DB = new DBHelper(this);

        Cursor cursor = DB.getUserData();

        if(cursor.getCount() > 0)
        {
            Toast t = Toast.makeText(MainActivity.this, "at least one created profile", Toast.LENGTH_LONG);
            t.show();
            Log.d("db", "at least one user profile");
        }

        else
        {
            Toast t = Toast.makeText(MainActivity.this, "0 created profile", Toast.LENGTH_LONG);
            t.show();
            Log.d("db", "no user profiles");
        }

        if(createdGame == 0)
        {
            a = new Game();
            a.createGame();
            createdGame = 1;
        }

        final Button button = findViewById(R.id.startGame);
        button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent i = new Intent(com.example.mastercoach.MainActivity.this, Game.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onRestart()
    {
        super.onRestart();
    }

    protected void onStart(Bundle savedInstanceState)
    {
        super.onStart();
    }
}