package com.example.mastercoach;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class MyClub extends MainActivity
{
    private static Team.Player InPlayer, OutPlayer;

    private TextView InPlayerTV, OutPlayerTV;

    public void userRequestToChangeTeam()
    {
        Team myClub = a.getUserClub();

        Toast warningMissingPlayer = Toast.makeText(getApplicationContext(), "Missing selected player", Toast.LENGTH_SHORT);

        Toast warningNotSamePos = Toast.makeText(getApplicationContext(), "Only same position for viable sub", Toast.LENGTH_SHORT);

        if (InPlayer != null && OutPlayer != null)
        {
            String InPlayerPos = InPlayer.getPlayerPos();
            String OutPlayerPos = OutPlayer.getPlayerPos();

            if(!InPlayerPos.equals(OutPlayerPos))
            {
                warningNotSamePos.show();
            }

            else
            {
                myClub.changeStartingLineUp(InPlayer, OutPlayer);

                String InPlayerName = InPlayer.getPlayerName();
                String InPlayerOA = String.valueOf((int)InPlayer.getOverAllPoints());

                String tvInPlayerNameTXT = parsePlayerName(InPlayerName) + " " + InPlayerOA + "''";

                String OutPlayerName = OutPlayer.getPlayerName();
                String OutPlayerOA = String.valueOf((int)OutPlayer.getOverAllPoints());

                String tvOutPlayerNameTXT = parsePlayerName(OutPlayerName) + " " + OutPlayerOA + "''";

                InPlayerTV.setText(tvOutPlayerNameTXT);
                InPlayerTV = null;

                OutPlayerTV.setText(tvInPlayerNameTXT);
                OutPlayerTV = null;

                InPlayer = null;
                OutPlayer = null;
            }
        }

        else
        {
            warningMissingPlayer.show();
        }

    }

    public void showTeam()
    {
        showStartingEleven();
        showBench();
    }

    public String parsePlayerName(String fullName)
    {
        StringBuilder displayName = new StringBuilder();

        int start = 0;

        while(fullName.charAt(start) == ' ')
        {
            ++start;
        }

        for(int i = start; i < fullName.length(); i++)
        {
            if(fullName.charAt(i) == ' ')
            {
                break;
            }

            displayName.append(fullName.charAt(i));
        }

        return displayName.toString();
    }

    public void showPlayerOnStartingTeam(Team.Player player, TextView tv, RelativeLayout rl)
    {
        String PlayerName = player.getPlayerName();
        String PlayerOA = String.valueOf((int)player.getOverAllPoints());

        String tvPlayerNameTXT = parsePlayerName(PlayerName) + " " + PlayerOA + "''";

        tv.setText(tvPlayerNameTXT);

        rl.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                OutPlayer = player;
                OutPlayerTV = tv;
            }
        });
    }

    public void showPlayerOnBench(Team.Player Player)
    {
        LinearLayout ll = findViewById(R.id.BenchLL);

        RelativeLayout rl = new RelativeLayout(this);

        TableLayout tl = new TableLayout(this);

        TableRow trIcon = new TableRow(this);
        TableRow trPlayerName = new TableRow(this);
        TableRow trPlayerPos = new TableRow(this);

        ImageView PlayerIcon = new ImageView(this);

        PlayerIcon.setImageResource(R.mipmap.field_template_foreground);
        PlayerIcon.setLayoutParams(new TableRow.LayoutParams(100, 100));

        TextView PlayerNameTV = new TextView(this);
        TextView PlayerPosTV = new TextView(this);

        String PlayerFullName = Player.getPlayerName();
        String PlayerOA = String.valueOf((int)Player.getOverAllPoints());

        String PlayerPosTXT = Player.getPlayerPos();
        String PlayerNameTXT = parsePlayerName(PlayerFullName) + " " + PlayerOA + "''";

        TableLayout.LayoutParams TableParams =
                new TableLayout.LayoutParams
                        (TableLayout.LayoutParams.WRAP_CONTENT ,TableLayout.LayoutParams.WRAP_CONTENT);

        TableParams.setMargins(20,10,0,1);

        rl.setLayoutParams(TableParams);

        tl.addView(trIcon);
        tl.addView(trPlayerName);
        tl.addView(trPlayerPos);

        PlayerNameTV.setText(PlayerNameTXT);
        PlayerNameTV.setTextColor(Color.WHITE);
        PlayerNameTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        PlayerNameTV.setBackgroundColor(Color.BLACK);
        PlayerNameTV.setGravity(Gravity.CENTER);

        PlayerPosTV.setText(PlayerPosTXT);
        PlayerPosTV.setTextColor(Color.RED);
        PlayerPosTV.setTypeface(null, Typeface.BOLD);
        PlayerPosTV.setGravity(Gravity.CENTER);
        PlayerPosTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);

        trIcon.addView(PlayerIcon);
        trPlayerName.addView(PlayerNameTV);
        trPlayerPos.addView(PlayerPosTV);

        rl.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                InPlayer = Player;
                InPlayerTV = PlayerNameTV;
            }
        });

        rl.addView(tl);
        ll.addView(rl);
    }

    public void showStartingEleven()
    {
        Team myClub = a.getUserClub();
        Team.Player [] startingLineUp = myClub.getStartLineUp();

        showPlayerOnStartingTeam(startingLineUp[0], findViewById(R.id.gkTV), findViewById(R.id.gkRL));
        showPlayerOnStartingTeam(startingLineUp[1], findViewById(R.id.cb1TV), findViewById(R.id.cb1RL));
        showPlayerOnStartingTeam(startingLineUp[2], findViewById(R.id.cb2TV), findViewById(R.id.cb2RL));
        showPlayerOnStartingTeam(startingLineUp[3], findViewById(R.id.cb3TV), findViewById(R.id.cb3RL));
        showPlayerOnStartingTeam(startingLineUp[4], findViewById(R.id.mf1TV), findViewById(R.id.mf1RL));
        showPlayerOnStartingTeam(startingLineUp[5], findViewById(R.id.mf2TV), findViewById(R.id.mf2RL));
        showPlayerOnStartingTeam(startingLineUp[6], findViewById(R.id.cfTV), findViewById(R.id.cfRL));
    }

    public void showBench()
    {
        Team myClub = a.getUserClub();
        Team.Player [] LineUp = myClub.getPlayers();

        for(Team.Player Player : LineUp)
        {
            String PlayerName = Player.getPlayerName();
            if(!myClub.checkIfStartingTeam(PlayerName))
            {
                showPlayerOnBench(Player);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.my_club);

        showTeam();

        final ImageView doSub = findViewById(R.id.subsIcon);
        doSub.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                userRequestToChangeTeam();
            }
        });

        final Button goHome = findViewById(R.id.homeButton);
        goHome.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent i = new Intent(MyClub.this, HomeMenus.class);
                startActivity(i);
            }
        });
    }
}
