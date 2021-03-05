// App package
package com.example.mastercoach;

// Android Imports
import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

// Java Imports
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Game extends League
{
    //Game data
    public int day, month, year;

    public String name, age, club;

    public League amateurLeague, semiProLeague, proLeague;

    public Team userClub;

    public String [] myClubResults;

    public League currentLeague;

    public static int createdGame = 0;

    public boolean isTeamChosen;

    public Team interested_Club;

    public List<InboxMessage> Inbox;

    public int currentMailIndex = 0;

    // Constructors

    public void startGame(String name, String age)
    {
        this.day = 10;
        this.month = 9;
        this.year = 2020;
        this.name = name;
        this.age = age;
        this.currentLeague = this.amateurLeague;

        Inbox = new ArrayList<InboxMessage>();

        String author = this.club+" President";
        String subject = "Welcome to "+this.club;
        String body = "Hello "+this.name+", I want to welcome you to "+this.club+". im sure you are the best candidate to fullfill the club expectations";

        Inbox.add(new InboxMessage(author, subject, body, currentMailIndex, null));

        ++currentMailIndex;
    }

    public void createGame()
    {
        League amateurLeague = new League();
        League semiProLeague = new League();
        League proLeague = new League();

        amateurLeague.createAmateurLeague();
        semiProLeague.createSemiProLeague();
        proLeague.createProLeague();

        this.amateurLeague = amateurLeague;
        this.semiProLeague = semiProLeague;
        this.proLeague = proLeague;
    }

    public void createGameSchedule()
    {
        League auxP = a.getProLeague();
        League auxSP = a.getSemiProLeague();
        League auxA = a.getAmateurLeague();

        int [][] MatchesCfg = {
                {1, 2, 3, 4, 5},
                {0, 4, 5, 3, 2},
                {3, 0, 4, 5, 1},
                {2, 5, 0, 1, 4},
                {5, 1, 2, 0, 3},
                {4, 3, 1, 2, 0}
        };

        for(int i = 0; i < nLeagueTeams; i++)
        {
            String [] calend = new String[nLeagueTeams-1];
            Team t1 = auxP.getObjectTeamIndex(i);

            for(int j = 0; j < nLeagueTeams-1; j++)
            {
                int iAdv = MatchesCfg[i][j];
                String adv = auxP.getObjectTeamIndex(iAdv).getTeamName();
                calend[j] = adv;
            }

            t1.createTeamCalendar(calend);
        }

        for(int i = 0; i < nLeagueTeams; i++)
        {
            String [] calend = new String[nLeagueTeams-1];
            Team t1 = auxSP.getObjectTeamIndex(i);

            for(int j = 0; j < nLeagueTeams-1; j++)
            {
                int iAdv = MatchesCfg[i][j];
                String adv = auxSP.getObjectTeamIndex(iAdv).getTeamName();
                calend[j] = adv;
            }

            t1.createTeamCalendar(calend);
        }

        for(int i = 0; i < nLeagueTeams; i++)
        {
            String [] calend = new String[nLeagueTeams-1];
            Team t1 = auxA.getObjectTeamIndex(i);

            for(int j = 0; j < nLeagueTeams-1; j++)
            {
                int iAdv = MatchesCfg[i][j];
                String adv = auxA.getObjectTeamIndex(iAdv).getTeamName();
                calend[j] = adv;
            }

            t1.createTeamCalendar(calend);
        }

        this.myClubResults = new String [nLeagueTeams-1];
        for(int i = 0; i < nLeagueTeams-1; i++)
        {
            this.myClubResults[i] = "TBD";
        }
    }

    // Getters

    public String getUserAge()
    {
        return this.age;
    }

    public String getUserName()
    {
        return this.name;
    }

    public Team getInterestedClub()
    {
        return this.interested_Club;
    }

    public Team getUserClub()
    {
        return (this.userClub);
    }

    public List<InboxMessage> getInbox()
    {
        return this.Inbox;
    }

    public String [] getMyClubResults()
    {
        if(this.myClubResults != null)
        {
            return this.myClubResults;
        }

        return null;
    }

    public League getUserLeague()
    {
        if(this.currentLeague != null)
        {
            return this.currentLeague;
        }

        return null;
    }

    public League getSemiProLeague()
    {
        return this.semiProLeague;
    }

    public League getAmateurLeague()
    {
        return this.amateurLeague;
    }

    public League getProLeague()
    {
        return this.proLeague;
    }

    public Team searchForTeamInGame(String team_name)
    {
        League al = a.getAmateurLeague();
        Team aux;

        League aA = a.getAmateurLeague();
        aux = aA.searchForTeamInLeague(team_name);

        if(aux != null) { return aux; }

        League aSP = a.getSemiProLeague();
        aux = aSP.searchForTeamInLeague(team_name);

        if(aux != null) { return aux; }

        League aP = a.getProLeague();
        aux = aP.searchForTeamInLeague(team_name);

        return aux;
    }

    // Setters
    public void removeMailAtIndex(int index)
    {
        Inbox.remove(index);
    }

    public void resetInterestedClub()
    {
        this.interested_Club = null;
    }

    public void setMyTeamResult(String res, int i)
    {
        this.myClubResults[i] = res;
    }

    void resetInboxToNewClub()
    {
        List<InboxMessage> newInbox = new ArrayList<InboxMessage>();

        String author = this.club+" President";
        String subject = "Welcome to "+this.club;
        String body = "Hello "+this.name+", I want to welcome you to "+this.club+". im sure you are the best candidate to fullfill the club expectations";

        newInbox.add(new InboxMessage(author, subject, body, currentMailIndex, null));

        for(InboxMessage it : Inbox)
        {
            if(it.getSubject().equals("Job Offer"))
            {
                newInbox.add(it);
            }
        }

        Inbox.clear();
        Inbox = newInbox;
    }

    public void assignUserNewTeam(Team newTeam)
    {
        Team.Coach newTeamOldCoach = newTeam.getTeamCoachObj();

        Team.Coach myCoach = a.getUserClub().getTeamCoachObj();

        newTeam.changeTeamCoach(a.getUserName(), myCoach);

        this.userClub.changeTeamCoach(newTeamOldCoach.getCoachName(),newTeamOldCoach);

        if(newTeam.getTeamLeagueName().equals(a.getAmateurLeague().getLeagueName()))
        {
            this.currentLeague = a.getAmateurLeague();
        }

        if(newTeam.getTeamLeagueName().equals(a.getSemiProLeague().getLeagueName()))
        {
            this.currentLeague = a.getSemiProLeague();
        }

        if(newTeam.getTeamLeagueName().equals(a.getProLeague().getLeagueName()))
        {
            this.currentLeague = a.getProLeague();
        }

        this.myClubResults = newTeam.getResults();
        this.userClub = newTeam;

        DB.updateUserClub(a.getUserName(), this.userClub.getTeamName());
    }

    public void assignUserClub(String club, Team myClub)
    {
        this.club = club;
        this.userClub = myClub;
    }

    // Layout construction

    public void createProfile()
    {
        setContentView(R.layout.new_start);

        isTeamChosen = false;

        final EditText edt1 = findViewById(R.id.birthEdtText);
        final EditText edt2 = findViewById(R.id.userName_id);

        final ImageView chsnTeam1 = findViewById(R.id.team1icon);

        chsnTeam1.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                League aux = a.getAmateurLeague();
                String club = "Válega";
                a.assignUserClub(club ,aux.getTeamByName(club));
                isTeamChosen = true;
            }
        });

        final ImageView chsnTeam2 = findViewById(R.id.team2Icon);
        chsnTeam2.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                League aux = a.getAmateurLeague();
                String club = "Ovarense";
                a.assignUserClub(club ,aux.getTeamByName(club));
                isTeamChosen = true;
            }
        });

        final ImageView chsnTeam3 = findViewById(R.id.team3Icon);
        chsnTeam3.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                League aux = a.getAmateurLeague();
                String club = "Espinho";
                a.assignUserClub(club ,aux.getTeamByName(club));
                isTeamChosen = true;
            }
        });

        final ImageView chsnTeam4 = findViewById(R.id.team4Icon);
        chsnTeam4.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                League aux = a.getAmateurLeague();
                String club = "Pardilhó";
                a.assignUserClub(club ,aux.getTeamByName(club));
                isTeamChosen = true;
            }
        });

        final ImageView chsnTeam5 = findViewById(R.id.team5Icon);
        chsnTeam5.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                League aux = a.getAmateurLeague();
                String club = "Esmoriz";
                a.assignUserClub(club ,aux.getTeamByName(club));
                isTeamChosen = true;
            }
        });

        final ImageView chsnTeam6 = findViewById(R.id.team6Icon);
        chsnTeam6.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                League aux = a.getAmateurLeague();
                String club = "Paramos";
                a.assignUserClub(club ,aux.getTeamByName(club));
                isTeamChosen = true;
            }
        });

        Button cfgDone = findViewById(R.id.cfgButton);
        cfgDone.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                if (edt1 != null && edt2 != null && isTeamChosen)
                {
                    String name = edt2.getText().toString();
                    String age = edt1.getText().toString();

                    String club = a.getUserClub().getTeamName();

                    Boolean validInsert = DB.insertUserData(name, club, age, a.getNGamesPlayed());

                    a.startGame(name, age);
                    a.createGameSchedule();

                    Intent i3 = new Intent(Game.this, HomeMenus.class);
                    startActivity(i3);
                }
            }
        });
    }

    // Iteration deciders

    public String decideWinner(Team t1, Team t2)
    {
        String t1Name = t1.getTeamName();
        String t2Name = t2.getTeamName();

        double t1_oaPts = t1.getOverAllPoints();
        double t2_oaPts = t2.getOverAllPoints();

        Random rand = new Random();

        double probT1 = rand.nextDouble();
        double probT2 = 1 - probT1;

        if(probT1 * t1_oaPts >= probT2 * t2_oaPts)
        {
            return t1Name;
        }

        return t2Name;
    }

    public void iterateNonUserLeague(League l)
    {
        String leagueName = l.getLeagueName();
        String userLeague = a.getUserLeague().getLeagueName();

        int indGames = l.getNGamesPlayed();
        int nJourneys = l.getnLeagueTeams()-1;

        boolean [] teamPlayed = new boolean[nLeagueTeams];

        for(int i = 0; i < nLeagueTeams; i++)
        {
          teamPlayed[i] = false;
        }

        int [][] MatchesCfg = {
                {1, 2, 3, 4, 5},
                {0, 4, 5, 3, 2},
                {3, 0, 4, 5, 1},
                {2, 5, 0, 1, 4},
                {5, 1, 2, 0, 3},
                {4, 3, 1, 2, 0}
        };

        League userL = a.getUserLeague();

        if(leagueName.equals(userLeague))
        {
            for(int i = 0; i < nLeagueTeams; i++)
            {
                Team t1 = l.getObjectTeamIndex(i);

                String t1Name = t1.getTeamName();

                String [] t1Cal = t1.getCalendar();

                String advName = t1Cal[indGames];

                if(t1Name.equals(a.getUserClub().getTeamName()))
                {
                    teamPlayed[i] = true;

                    Team adv = userL.getTeamByName(advName);

                    int indAdv = userL.getIndOfTeam(adv);

                    teamPlayed[indAdv] = true;
                }
            }
        }

        if(indGames < nJourneys)
        {
            for(int i =0; i < nLeagueTeams; i++)
            {
                if(!teamPlayed[i])
                {
                    Team t1 = l.getObjectTeamIndex(i);

                    String [] t1Calend = t1.getCalendar();

                    String t2Name = t1Calend[indGames];

                    Team t2 = l.getTeamByName(t2Name);

                    String winner = decideWinner(t1,t2);

                    l.changePtsToTeam(winner, 3);

                    Team tWinner = l.getTeamByName(winner);

                    String t1res = "", t2res = "";
                    
                    if(tWinner.getTeamName().equals(t1.getTeamName()))
                    {
                        t1res = "1 - 0";
                        t2res = "0 - 1";
                    }

                    if(tWinner.getTeamName().equals(t2.getTeamName()))
                    {
                        t2res = "1 - 0";
                        t1res = "0 - 1";
                    }

                    t1.setTeamResult(indGames, t1res);
                    t2.setTeamResult(indGames, t2res);

                    if(l.getLeagueName().equals("Amateur League"))
                    {
                        tWinner.getMoneyFromAmateurMatch("win");
                    }

                    if(l.getLeagueName().equals("Semi Pro League"))
                    {
                        tWinner.getMoneyFromSemiProMatch("win");
                    }

                    if(l.getLeagueName().equals("Pro League"))
                    {
                        tWinner.getMoneyFromProMatch("win");
                    }

                    teamPlayed[i] = true;

                    int indAdv = l.getIndOfTeam(t2);

                    teamPlayed[indAdv] = true;
                }
            }

            l.incGamesPlayed();
        }
    }

    public void iterateGame()
    {
        League auxP = a.getProLeague();
        League auxSP = a.getSemiProLeague();
        League auxA = a.getAmateurLeague();

        iterateNonUserLeague(auxP);
        iterateNonUserLeague(auxSP);
        iterateNonUserLeague(auxA);

        int nGamesPlayed = auxP.getNGamesPlayed();
        int nGames =  auxP.getnLeagueTeams();

        updateLeagueBoard(auxP);
        updateLeagueBoard(auxA);
        updateLeagueBoard(auxSP);

        if(nGames-1 == nGamesPlayed)
        {
            a.endLeagueUpdate();
        }

        this.interested_Club = checkInNeedForARealCoachTeams();

        if(this.interested_Club != null)
        {
            String author = this.interested_Club.getTeamName() + " President";
            String subject = "Job Offer";
            String body = "";

            Inbox.add(new InboxMessage(author, subject, body, currentMailIndex,this.interested_Club));

            this.interested_Club = null;

            ++currentMailIndex;
        }
    }


    public Team checkInNeedForARealCoachTeams()
    {
        String userLeague = a.getUserLeague().getLeagueName();

        if(userLeague.equals(a.getProLeague().getLeagueName()))
        {
            League aux = a.getProLeague();

            Random rand = new Random();

            int chosenTeam = rand.nextInt(6);

            double prob = rand.nextDouble() * 100;

            boolean isMyTeamLP = aux.getObjectTeamIndex(chosenTeam).getTeamName().equals(a.getUserClub().getTeamName());

            if(prob >= 40 && !isMyTeamLP)
            {
                return aux.getObjectTeamIndex(chosenTeam);
            }
        }

        if(userLeague.equals(a.getSemiProLeague().getLeagueName()))
        {
            League aux1 = a.getProLeague();
            League aux2 = a.getSemiProLeague();

            Random rand = new Random();

            int chosenTeam1 = rand.nextInt(6);
            int chosenTeam2 = rand.nextInt(6);

            double prob = rand.nextDouble() * 100;

            boolean isMyTeamLSP = aux2.getObjectTeamIndex(chosenTeam2).getTeamName().equals(a.getUserClub().getTeamName());
            boolean isMyTeamLP = aux1.getObjectTeamIndex(chosenTeam1).getTeamName().equals(a.getUserClub().getTeamName());

            if (prob > 60 && !isMyTeamLSP)
            {
                return aux2.getObjectTeamIndex(chosenTeam2);
            }

            if (prob > 85 && !isMyTeamLP)
            {
                return aux1.getObjectTeamIndex(chosenTeam1);
            }
        }

        if(userLeague.equals(a.getAmateurLeague().getLeagueName()))
        {
            League aux1 = a.getProLeague();
            League aux2 = a.getSemiProLeague();
            League aux3 = a.getAmateurLeague();

            Random rand = new Random();

            int chosenTeam1 = rand.nextInt(6);
            int chosenTeam2 = rand.nextInt(6);
            int chosenTeam3 = rand.nextInt(6);

            double prob = rand.nextDouble() * 100;

            boolean isMyTeamLSP = aux2.getObjectTeamIndex(chosenTeam2).getTeamName().equals(a.getUserClub().getTeamName());
            boolean isMyTeamLP = aux1.getObjectTeamIndex(chosenTeam1).getTeamName().equals(a.getUserClub().getTeamName());
            boolean isMyTeamLA = aux3.getObjectTeamIndex(chosenTeam3).getTeamName().equals(a.getUserClub().getTeamName());

            if(prob > 97 && !isMyTeamLP)
            {
                return aux1.getObjectTeamIndex(chosenTeam1);
            }

            if(prob > 85 && !isMyTeamLSP)
            {
                return aux2.getObjectTeamIndex(chosenTeam2);
            }

            if(prob > 50 && !isMyTeamLA)
            {
                return aux3.getObjectTeamIndex(chosenTeam3);
            }
        }

        return null;
    }

    public void updateLeagueBoard(League l)
    {
        int nLeagueTeams = l.getnLeagueTeams();

        for(int i = 0; i < nLeagueTeams; i++)
        {
            int indTeamPts = Integer.parseInt(l.getPtsByPos(i));

            int rightPlace = -1;

            int itMax = indTeamPts;

            for(int j = i; j < nLeagueTeams; j++)
            {
                int auxTeamPts = Integer.parseInt(l.getPtsByPos(j));

                if(itMax < auxTeamPts)
                {
                    rightPlace = j;

                    itMax = indTeamPts;
                }
            }

            if(rightPlace > 0)
            {
                l.switchTeamsClass(i,rightPlace);
            }
        }
    }

    public void endLeagueUpdate()
    {
        League auxP = a.getProLeague();
        League auxSP = a.getSemiProLeague();
        League auxA = a.getAmateurLeague();

        int nLeagueTeams = auxP.getnLeagueTeams();

        String AWName = auxA.getTeamByPos(0);
        Team AmateurWinner = auxA.getTeamByName(AWName);
        AmateurWinner.getMoneyToAmateurChampions();
        int AmateurWinnerPos = auxA.getIndOfTeam(AmateurWinner);

        String SPWName = auxSP.getTeamByPos(0);
        Team SemiProWinner = auxSP.getTeamByName(SPWName);
        SemiProWinner.getMoneyToSemiProChampions();
        int SemiProWinnerPos = auxSP.getIndOfTeam(SemiProWinner);

        String SPLName = auxSP.getTeamByPos(nLeagueTeams-1);
        Team SemiProLoser = auxSP.getTeamByName(SPLName);
        int SemiProLoserPos = auxSP.getIndOfTeam(SemiProLoser);

        String PLName = auxP.getTeamByPos(nLeagueTeams-1);
        Team ProLoser = auxP.getTeamByName(PLName);
        int ProLoserPos = auxP.getIndOfTeam(ProLoser);

        String ProChampName = auxP.getTeamByPos(0);
        Team ProWinner = auxP.getTeamByName(ProChampName);
        ProWinner.getMoneyToProChampions();

        auxP.insertTeamInLeague(ProLoserPos, SemiProWinner);
        SemiProWinner.changeLeagueName(a.getProLeague().getLeagueName());

        auxSP.insertTeamInLeague(SemiProWinnerPos, ProLoser);
        ProLoser.changeLeagueName(a.getSemiProLeague().getLeagueName());

        auxSP.insertTeamInLeague(SemiProLoserPos, AmateurWinner);
        AmateurWinner.changeLeagueName(a.getSemiProLeague().getLeagueName());

        auxA.insertTeamInLeague(AmateurWinnerPos, SemiProLoser);
        SemiProLoser.changeLeagueName(a.getAmateurLeague().getLeagueName());

        auxA.resetLeague();
        auxSP.resetLeague();
        auxP.resetLeague();

        if(SemiProWinner.getTeamName().equals(a.getUserClub().getTeamName()))
        {
            a.currentLeague = this.proLeague;
        }

        if(SemiProLoser.getTeamName().equals(a.getUserClub().getTeamName()))
        {
            a.currentLeague = this.amateurLeague;
        }

        if(ProLoser.getTeamName().equals(a.getUserClub().getTeamName()))
        {
            a.currentLeague = this.semiProLeague;
        }

        if(AmateurWinner.getTeamName().equals(a.getUserClub().getTeamName()))
        {
            a.currentLeague = this.semiProLeague;
        }

        Arrays.fill(this.myClubResults, "TBD");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        if(createdGame == 0)
        {
            createProfile();
            createdGame = 1;
        }

        else {
//          a.iterateGame();

            Intent i = new Intent(Game.this, Simulation.class);

            startActivity(i);
        }
    }

    @Override
    protected void onStart(Bundle savedInstanceState)
    {
        super.onStart();
    }
}