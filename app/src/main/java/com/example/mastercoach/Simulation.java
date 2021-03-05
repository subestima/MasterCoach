package com.example.mastercoach;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.Random;

import static com.example.mastercoach.MainActivity.a;

public class Simulation extends Activity
{
    int homeTeamScore = 0, visitorTeamScore = 0;

    public LinkedList<String> timeStamps;
    public LinkedList<String> broadcastMessages;
    public LinkedList<String> iterationScores;

    long duration = 3 * 60 * 1000;
    long startTime;
    long endTime = startTime + duration;
    long currTime = 0;

    TextView timeDisplay;
    TextView broadcastDisplay;
    TextView scoreDisplay;

    Handler timeHandler = new Handler();

    Runnable timeRunnable = new Runnable()
    {
        @Override
        public void run()
        {
            int minutes = (int)((90 * currTime) / duration);
            int seconds = (int)((5400 * currTime) / duration) % 60;

            if(minutes == 90)
            {
                seconds = 0;
                timeDisplay.setText(String.format("%d:%02d", minutes, seconds));

                stopSimulation();
            }

            else
            {
                timeDisplay.setText(String.format("%d:%02d", minutes, seconds));

                currTime = System.currentTimeMillis() - startTime;

                int iterationMinute;

                String iterationMsg, iterationScore;

                if(timeStamps != null && !timeStamps.isEmpty())
                {
                    iterationMinute = Integer.parseInt(timeStamps.peek());

                    if(iterationMinute <= minutes)
                    {
                        iterationMsg = broadcastMessages.peek();

                        broadcastDisplay.setText(iterationMsg);

                        iterationScore = iterationScores.peek();

                        if(!iterationScores.isEmpty() && !iterationScore.equals("-1"))
                        {
                            scoreDisplay.setText(iterationScores.peek());
                            Log.d("score peek()", "iteration score peek() = " + iterationScores.peek());
                        }

                        timeStamps.remove();
                        broadcastMessages.remove();
                        iterationScores.remove();
                    }
                }

                timeHandler.postDelayed(this, 100);
            }
        }
    };

    private void stopSimulation()
    {
        timeHandler.removeCallbacks(timeRunnable);
    }

    private void setSimulationLayout()
    {
        setContentView(R.layout.simulation_arena);

        ImageView homeTeamLogo = findViewById(R.id.team1icon);
        ImageView awayTeamLogo = findViewById(R.id.team2icon);

        League myLeague = a.getUserLeague();

        Team myClub = a.getUserClub();

        String myClubName = myClub.getTeamName();

        int indGames = myLeague.getNGamesPlayed();

        String [] cal = myClub.getCalendar();

        String advName = cal[indGames];

        Team advClub = myLeague.getTeamByName(advName);

        setGameScore();

        setTeamLogo(myClubName, homeTeamLogo);
        setTeamLogo(advName, awayTeamLogo);

        setHomeStartingEleven(myClub);
        setVisitorStartingEleven(advClub);
    }

    public String parsePlayerName(String fullName) {
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

    private void setGameScore()
    {
        TextView scoreDisplay = findViewById(R.id.ScoreDisplay);

        String scoreTXT = homeTeamScore + " - " + visitorTeamScore;

        scoreDisplay.setText(scoreTXT);
    }

    private void setHomeStartingEleven(Team t)
    {
        TextView gkTV = findViewById(R.id.homeTeamGKTV);
        TextView cb1TV = findViewById(R.id.homeTeamCB1TV);
        TextView cb2TV = findViewById(R.id.homeTeamCB2TV);
        TextView cb3TV = findViewById(R.id.homeTeamCB3TV);
        TextView mf1TV = findViewById(R.id.homeTeamMF1TV);
        TextView mf2TV = findViewById(R.id.homeTeamMF2TV);
        TextView cfTV = findViewById(R.id.homeTeamCFTV);

        Team.Player [] startLineUp = t.getStartLineUp();

        String gkTXT = parsePlayerName(startLineUp[0].getPlayerName()) + " " + (int)startLineUp[0].getOverAllPoints() + "''";
        gkTV.setText(gkTXT);

        String cb1TXT = parsePlayerName(startLineUp[1].getPlayerName()) + " " + (int)startLineUp[1].getOverAllPoints() + "''";
        cb1TV.setText(cb1TXT);

        String cb2TXT = parsePlayerName(startLineUp[2].getPlayerName()) + " " + (int)startLineUp[2].getOverAllPoints() + "''";
        cb2TV.setText(cb2TXT);

        String cb3TXT = parsePlayerName(startLineUp[3].getPlayerName()) + " " + (int)startLineUp[3].getOverAllPoints() + "''";
        cb3TV.setText(cb3TXT);

        String mf1TXT = parsePlayerName(startLineUp[4].getPlayerName()) + " " + (int)startLineUp[4].getOverAllPoints() + "''";
        mf1TV.setText(mf1TXT);

        String mf2TXT = parsePlayerName(startLineUp[5].getPlayerName()) + " " + (int)startLineUp[5].getOverAllPoints() + "''";
        mf2TV.setText(mf2TXT);

        String cfTXT = parsePlayerName(startLineUp[6].getPlayerName()) + " " + (int)startLineUp[6].getOverAllPoints() + "''";
        cfTV.setText(cfTXT);
    }

    private void setVisitorStartingEleven(Team t)
    {
        TextView gkTV  =    findViewById(R.id.awayTeamGKTV);
        TextView cb1TV =    findViewById(R.id.awayTeamCB1TV);
        TextView cb2TV =    findViewById(R.id.awayTeamCB2TV);
        TextView cb3TV =    findViewById(R.id.awayTeamCB3TV);
        TextView mf1TV =    findViewById(R.id.awayTeamMF1TV);
        TextView mf2TV =    findViewById(R.id.awayTeamMF2TV);
        TextView cfTV  =    findViewById(R.id.awayTeamCFTV);

        Team.Player [] startLineUp = t.getStartLineUp();

        String gkTXT =  (int)startLineUp[0].getOverAllPoints() + "'' " +  parsePlayerName(startLineUp[0].getPlayerName());
        gkTV.setText(gkTXT);
        gkTV.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.END);

        String cb1TXT = (int)startLineUp[1].getOverAllPoints() + "'' " + parsePlayerName(startLineUp[1].getPlayerName());
        cb1TV.setText(cb1TXT);
        cb1TV.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.END);

        String cb2TXT = (int)startLineUp[2].getOverAllPoints() + "'' " + parsePlayerName(startLineUp[2].getPlayerName());
        cb2TV.setText(cb2TXT);
        cb2TV.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.END);

        String cb3TXT = (int)startLineUp[3].getOverAllPoints() + "'' " + parsePlayerName(startLineUp[3].getPlayerName());
        cb3TV.setText(cb3TXT);
        cb3TV.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.END);

        String mf1TXT = (int)startLineUp[4].getOverAllPoints() + "'' " + parsePlayerName(startLineUp[4].getPlayerName());
        mf1TV.setText(mf1TXT);
        mf1TV.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.END);

        String mf2TXT = (int)startLineUp[5].getOverAllPoints() + "'' " + parsePlayerName(startLineUp[5].getPlayerName());
        mf2TV.setText(mf2TXT);
        mf2TV.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.END);

        String cfTXT = (int)startLineUp[6].getOverAllPoints() + "'' " + parsePlayerName(startLineUp[6].getPlayerName());
        cfTV.setText(cfTXT);
        cfTV.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.END);
    }

    private void setTeamLogo(String TeamName, ImageView TeamLogo)
    {
        if(TeamName.equals("Válega"))       {TeamLogo.setImageResource(R.mipmap.valegaicon_foreground);}
        if(TeamName.equals("Ovarense"))     {TeamLogo.setImageResource(R.mipmap.ovarenseicon_foreground);}
        if(TeamName.equals("Espinho"))      {TeamLogo.setImageResource(R.mipmap.espinhoicon_foreground);}
        if(TeamName.equals("Pardilhó"))     {TeamLogo.setImageResource(R.mipmap.pardilhoicon_foreground);}
        if(TeamName.equals("Esmoriz"))      {TeamLogo.setImageResource(R.mipmap.esmorizicon_foreground);}
        if(TeamName.equals("Paramos"))      {TeamLogo.setImageResource(R.mipmap.paramosicon_foreground);}
        if(TeamName.equals("Torreira"))     {TeamLogo.setImageResource(R.mipmap.torreiraicon_foreground);}
        if(TeamName.equals("Feirense"))     {TeamLogo.setImageResource(R.mipmap.feirenseicon_foreground);}
        if(TeamName.equals("São Joanense")) {TeamLogo.setImageResource(R.mipmap.saojoanenseicon_foreground);}
        if(TeamName.equals("Seixo"))        {TeamLogo.setImageResource(R.mipmap.seixoicon_foreground);}
        if(TeamName.equals("Candal"))       {TeamLogo.setImageResource(R.mipmap.candalicon_foreground);}
        if(TeamName.equals("Murtosa"))      {TeamLogo.setImageResource(R.mipmap.murtosaicon_foreground);}
        if(TeamName.equals("Rio Tinto"))    {TeamLogo.setImageResource(R.mipmap.riotintoicon_foreground);}
        if(TeamName.equals("Avanca"))       {TeamLogo.setImageResource(R.mipmap.avancaicon_foreground);}
        if(TeamName.equals("Souto"))        {TeamLogo.setImageResource(R.mipmap.soutoicon_foreground);}
        if(TeamName.equals("Pasteleira"))   {TeamLogo.setImageResource(R.mipmap.pasteleiraicon_foreground);}
        if(TeamName.equals("Lordelo"))      {TeamLogo.setImageResource(R.mipmap.lordeloicon_foreground);}
        if(TeamName.equals("Estarreja"))    {TeamLogo.setImageResource(R.mipmap.estarrejaicon_foreground);}
    }

    private static int minute = 1;

    private void calculateGameSimulation()
    {
        Team myClub = a.getUserClub();
        String myClubName = myClub.getTeamName();

        League l = a.getUserLeague();
        int indGames = l.getNGamesPlayed();
        int nJourneys = l.getnLeagueTeams()-1;

        if(indGames < nJourneys)
        {
            int myClubScore = 0, advScore = 0;

            String[] cal = myClub.getCalendar();

            String advName = cal[indGames];

            Team adv = l.getTeamByName(advName);

            Team teamWithBall = myClub;

            Team teamDeffending = adv;

            while(minute <= 90)
            {

                // ( attacking == 0 ) -> no change on team with ball or ( attacking == 1 ) -> team defending recovered ball or ( attacking == 2 ) -> team with ball scored
                int res = atacking(teamWithBall, teamDeffending);

                if(minute >= 90) break;

                if (res == 2)
                {
                    String msg = teamWithBall.getTeamName()+" scored a goal !";

                    timeStamps.add(String.valueOf(minute));

                    broadcastMessages.add(msg);

                    minute += 4;

                    if (teamWithBall.getTeamName().equals(myClubName))
                    {
                        myClubScore++;

                        teamWithBall = adv;

                        teamDeffending = myClub;
                    }

                    else
                    {
                        advScore++;

                        teamWithBall = myClub;

                        teamDeffending = adv;
                    }

                    String score = myClubScore + " - " + advScore;

                    iterationScores.add(score);
                }

                if (res == 1)
                {
                    stoppedAtacked(teamWithBall, teamDeffending);

                    if (teamWithBall.getTeamName().equals(myClubName))
                    {
                        teamDeffending = myClub;

                        teamWithBall = adv;
                    }

                    else
                    {
                        teamDeffending = adv;

                        teamWithBall = myClub;
                    }
                }

                if (res == 3)
                {
                    penaltySaved(teamWithBall, teamDeffending);

                    if (teamWithBall.getTeamName().equals(myClubName))
                    {
                        teamDeffending = myClub;

                        teamWithBall = adv;
                    }

                    else
                    {
                        teamDeffending = adv;

                        teamWithBall = myClub;
                    }
                }
            }

            if (myClubScore > advScore)
            {
                l.changePtsToTeam(myClubName, 3);

                if (a.getUserLeague().getLeagueName().equals("Pro League"))
                {
                    myClub.getMoneyFromProMatch("win");
                }

                if (a.getUserLeague().getLeagueName().equals("Semi Pro League"))
                {
                    myClub.getMoneyFromSemiProMatch("win");
                }

                if (a.getUserLeague().getLeagueName().equals("Amateur League"))
                {
                    myClub.getMoneyFromAmateurMatch("win");
                }
            }

            if (myClubScore < advScore)
            {
                l.changePtsToTeam(advName, 3);
            }

            if (myClubScore == advScore)
            {
                if (a.getUserLeague().getLeagueName().equals("Pro League"))
                {
                    myClub.getMoneyFromProMatch("draw");
                }

                if (a.getUserLeague().getLeagueName().equals("Semi Pro League"))
                {
                    myClub.getMoneyFromSemiProMatch("draw");
                }

                if (a.getUserLeague().getLeagueName().equals("Amateur League"))
                {
                    myClub.getMoneyFromAmateurMatch("draw");
                }

                l.changePtsToTeam(advName, 1);
                l.changePtsToTeam(myClubName, 1);
            }

            String res = myClubScore + " - " + advScore;

            a.setMyTeamResult(res, indGames);
        }
    }

    public int atacking(Team teamWithBall, Team teamDeffending)
    {
        if(minute >= 90) return -3;

        String msg = "" + teamWithBall.getTeamName() + " is in possetion of ball";

        timeStamps.add(String.valueOf(minute));

        broadcastMessages.add(msg);

        iterationScores.add("-1");

        minute += 3;

        Random rand = new Random();

        double team1FieldLuck = rand.nextDouble();
        double team2FieldLuck = rand.nextDouble();

        double teamWithBallForce = teamWithBall.getMFPoints() * team1FieldLuck;
        double teamDeffendingForce = teamDeffending.getMFPoints() * team2FieldLuck;

        if(teamDeffendingForce <= teamWithBallForce)
        {
            return finishingArea(teamWithBall, teamDeffending);
        }

        return 1;
    }

    public int finishingArea(Team teamWithBall, Team teamDeffending)
    {
        if(minute >= 90) return -3;

        String msg = "" + teamWithBall.getTeamName() + " getting close to finishing area";

        timeStamps.add(String.valueOf(minute));

        broadcastMessages.add(msg);

        iterationScores.add("-1");

        minute += 3;

        Random rand = new Random();

        double deffensePoints = teamDeffending.getDeffensePoints();

        double probDeffenseMistakes = (rand.nextDouble()) * deffensePoints;

        if(probDeffenseMistakes <= 18)
        {
            return penalty(teamWithBall, teamDeffending);
        }

        if(probDeffenseMistakes <= 30)
        {
            return corner(teamWithBall, teamDeffending);
        }

        double team1FieldLuck = rand.nextDouble();
        double team2FieldLuck = rand.nextDouble();

        double teamWithBallLuck = team1FieldLuck * teamWithBall.getCenterFowardPoints();
        double teamDeffendingLuck = team2FieldLuck * teamDeffending.getDeffensePoints();

        if(teamDeffendingLuck <= teamWithBallLuck)
        {
            return 2;
        }

        return 1;
    }

    public int penalty(Team teamWithBall, Team teamDeffending)
    {
        if(minute >= 90) return -3;

        String msg = "Penalty for "+teamWithBall.getTeamName()+" !";

        timeStamps.add(String.valueOf(minute));

        broadcastMessages.add(msg);

        iterationScores.add("-1");

        minute += 3;

        Random rand = new Random();

        double team1FieldLuck = rand.nextDouble();
        double team2FieldLuck = rand.nextDouble();

        double StrikerLuck = teamWithBall.getCenterFowardPoints() * team1FieldLuck;
        double KeeperLuck = teamDeffending.getKeeperPoints() * team2FieldLuck;

        if(StrikerLuck >= KeeperLuck)
        {
            return 2;
        }

        return 3;
    }

    public int corner(Team teamWithBall, Team teamDeffending)
    {
        if(minute >= 90) return -3;

        String msg = "Corner for "+teamWithBall.getTeamName()+"";

        timeStamps.add(String.valueOf(minute));

        broadcastMessages.add(msg);

        iterationScores.add("-1");

        minute += 3;

        Random rand = new Random();

        double goodHeaderteam1 = rand.nextDouble() * teamWithBall.getAccumulativeAttackPoints();
        double goodHeaderteam2 = rand.nextDouble() * teamDeffending.getAccumulativeDeffensivePoints();

        if(goodHeaderteam1 >= 60)
        {
            return 2;
        }

        if(goodHeaderteam1 >= goodHeaderteam2)
            return 0;

        return 1;
    }

    public void stoppedAtacked(Team teamWithBall, Team teamDeffending)
    {
        String msg = "Good tackle by "+teamDeffending.getRandomTackler();

        timeStamps.add(String.valueOf(minute));

        broadcastMessages.add(msg);

        iterationScores.add("-1");

        minute += 3;
    }

    public void penaltySaved(Team teamWithBall, Team teamDeffending)
    {
        String msg = "Penalty saved !";

        timeStamps.add(String.valueOf(minute));

        broadcastMessages.add(msg);

        iterationScores.add("-1");

        minute += 3;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setSimulationLayout();

        timeStamps = new LinkedList<String>();

        broadcastMessages = new LinkedList<String>();

        iterationScores = new LinkedList<String>();

        calculateGameSimulation();

        timeDisplay = findViewById(R.id.TimeDisplay);
        broadcastDisplay = findViewById(R.id.broadcastDisplay);
        scoreDisplay = findViewById(R.id.ScoreDisplay);

        startTime = System.currentTimeMillis();

        Button endSimBttn = findViewById(R.id.finishSimulationBttn);

        endSimBttn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                a.iterateGame();

                Intent i =  new Intent(Simulation.this, HomeMenus.class);
                startActivity(i);
            }
        });

        timeHandler.postDelayed(timeRunnable, 0);
    }

    @Override
    public void onPause()
    {
        super.onPause();

        timeHandler.removeCallbacks(timeRunnable);
    }
}
