package com.example.mastercoach;

import android.os.Bundle;

import java.util.Random;


public class Team extends MainActivity
{
    // Class data

    double overAllPoints, teamBudget;

    String teamName, teamCoach,leagueName;

    Player [] lineUp;
    Player [] startLineUp;

    Coach coach;
    PhysicalStaff doc;
    Trainer defTrainer, atkTrainer;

    String [] calendar;
    String [] results;


    // Getters

    public String getTeamName()
    {
        return this.teamName;
    }

    public double getOverAllPoints()
    {
        return this.overAllPoints;
    }

    public double getTeamBudget()
    {
        return this.teamBudget;
    }

    public String [] getResults()
    {
        return this.results;
    }

    public String getTeamLeagueName()
    {
        return this.leagueName;
    }

    public void createTeamResults(String [] res)
    {
        this.results = res;
    }

    public int getNumberOfPlayers()
    {
        return this.lineUp.length;
    }

    public Player getPlayerByName(String reqPlayer)
    {
        for(Player aux: lineUp)
        {
            String auxName = aux.getPlayerName();

            if(auxName.equals(reqPlayer))
            {
                return aux;
            }
        }

        return null;
    }

    public Coach getTeamCoachObj()
    {
        if(this.coach != null)
        {
            return this.coach;
        }

        return null;
    }

    public Trainer getAtkTeamTrainerObj()
    {
        if(this.atkTrainer != null)
        {
            return this.atkTrainer;
        }

        return null;
    }

    public Trainer getDefTeamTrainerObj()
    {
        if(this.defTrainer != null)
        {
            return this.defTrainer;
        }

        return null;
    }

    public PhysicalStaff getDoc()
    {
        if(this.doc != null)
        {
            return this.doc;
        }

        return null;
    }

    public Player searchInTeamForPlayer(String name)
    {
        for(Player aux: lineUp)
        {
            if(aux.getPlayerName().equals(name))
            {
                return aux;
            }
        }

        return null;
    }

    public Player [] getStartLineUp()
    {
        if(this.startLineUp != null)
        {
            return this.startLineUp;
        }

        return null;
    }

    public String [] getCalendar()
    {
        if(this.calendar != null)
        {
            return this.calendar;
        }

        return null;
    }

    public double getCenterFowardPoints()
    {
        double cfPts = 0;

        int nPlayers = 0;

        for(Player aux : startLineUp)
        {
            String playerPos = aux.getPlayerPos();

            if(playerPos.equals("CF"))
            {
                cfPts += aux.getOverAllPoints();

                nPlayers++;
            }
        }

        return cfPts/nPlayers;
    }

    public String getRandomTackler()
    {
        Random rand = new Random();

        int chsn = rand.nextInt(4);

        return startLineUp[chsn].getPlayerName();
    }

    public double getAccumulativeDeffensivePoints()
    {
        double accDefPts = 0;

        int nPlayers = 0;

        for(Player aux: startLineUp)
        {
            String Playerpos = aux.getPlayerPos();

            if(Playerpos.equals("MF") || Playerpos.equals("CB"))
            {
                nPlayers++;

                accDefPts += aux.getOverAllPoints();
            }
        }

        return accDefPts/nPlayers;
    }

    public double getAccumulativeAttackPoints()
    {
        double accAttkPts = 0;

        int nPlayers = 0;

        for(Player aux: startLineUp)
        {
            String Playerpos = aux.getPlayerPos();

            if(Playerpos.equals("MF") || Playerpos.equals("CF"))
            {
                nPlayers++;

                accAttkPts += aux.getOverAllPoints();
            }
        }

        return accAttkPts/nPlayers;
    }

    public double getMFPoints()
    {
        double mfPts = 0;

        int nPlayers = 0;

        for(Player aux: startLineUp)
        {
            String playerPos = aux.getPlayerPos();

            if(playerPos.equals("MF"))
            {
                mfPts += aux.getOverAllPoints();

                nPlayers++;
            }
        }

        return mfPts/nPlayers;
    }

    public double getDeffensePoints()
    {
        double defPts = 0;

        int nPlayers = 0;

        for(Player aux: startLineUp)
        {
            defPts += aux.getOverAllPoints();

            nPlayers++;
        }

        return defPts/nPlayers;
    }

    public double getKeeperPoints()
    {
        return startLineUp[0].getOverAllPoints();
    }

    public String getAdvOnJourney(int journey)
    {
        if(this.calendar != null)
        {
            return this.calendar[journey];
        }

        return null;
    }

    public Player[] getPlayers()
    {
        if(this.lineUp != null)
        {
            return this.lineUp;
        }

        return null;
    }

    public Player getObjectPlayerIndex(int index)
    {
        if(this.lineUp != null)
        {
            return this.lineUp[index];
        }

        return null;
    }

    // Setters

    public void setTeamResult(int nJorney, String res)
    {
        this.results[nJorney] = res;
    }

    public void changeLeagueName(String newLeagueName)
    {
        this.leagueName = newLeagueName;
    }

    public void changeTeamCoach(String newCoachName, Coach newCoach)
    {
        this.teamCoach = newCoachName;
        this.coach = newCoach;
    }

    public void changeStartingLineUp(Player in, Player out)
    {
        String outPlayer = out.getPlayerName();

        Player [] aux = new Player [startLineUp.length];

        for(int i = 0; i < 7; i++)
        {
            String auxPlayerName = this.startLineUp[i].getPlayerName();

            if(auxPlayerName.equals(outPlayer))
            {
                aux[i] = in;
            }

            else
            {
                aux[i] = this.startLineUp[i];
            }
        }

        this.startLineUp = aux;
    }

    public void increaseTeamBudget(double val)
    {
        this.teamBudget += val;
    }

    public void decreaseTeamBudget(double val)
    {
        this.teamBudget -= val;
    }

    public void transferPlayer(Player p1)
    {
        int nPlayers = this.lineUp.length;

        Player [] auxLnp = new Player [nPlayers + 1];

        System.arraycopy(this.lineUp, 0, auxLnp, 0, nPlayers);

        auxLnp[nPlayers] = p1;

        this.lineUp = auxLnp;
    }

    public void removePlayerFromTeam(String playerName)
    {
        if(this.lineUp.length <= 7)
        {
            return;
        }

        for(Player target: this.lineUp)
        {
            if(target.getPlayerName().equals(playerName))
            {
                return;
            }
        }
    }

    public void getMoneyFromAmateurMatch(String result)
    {
        if(result.equals("win"))
        {
            this.teamBudget += 500;
        }

        if(result.equals("draw"))
        {
            this.teamBudget += 200;
        }
    }

    public void getMoneyFromSemiProMatch(String result)
    {
        if(result.equals("win"))
        {
            this.teamBudget += 1000;
        }

        if(result.equals("draw"))
        {
            this.teamBudget += 400;
        }
    }

    public void getMoneyFromProMatch(String result)
    {
        if(result.equals("win"))
        {
            this.teamBudget += 2000;
        }

        if(result.equals("draw"))
        {
            this.teamBudget += 800;
        }
    }

    public void getMoneyToAmateurChampions()
    {
        this.teamBudget += 3000;
    }

    public void getMoneyToSemiProChampions()
    {
        this.teamBudget += 6000;
    }

    public void getMoneyToProChampions()
    {
        this.teamBudget += 8000;
    }

    public void createTeamCalendar(String [] c)
    {
        System.arraycopy(c, 0, this.calendar, 0, c.length);
    }

    // Checkers

    public boolean checkIfStartingTeam(String player)
    {
        for(Player aux: startLineUp)
        {
            if(aux.getPlayerName().equals(player))
            {
                return true;
            }
        }

        return false;
    }

    // Calculus

    public double calcTeamOApts(Player [] rooster)
    {
        double oaPts = 0;

        for(int i = 0; i < 11; i++)
        {
            Player aux = rooster[i];
            oaPts =+ aux.overAllPoints;
        }

        oaPts = oaPts / 11;

        return oaPts;
    }

    public static class Coach
    {
        String name;

        int age;

        String club;

        // Constructor

        public void createCoach(String name, int age, String club)
        {
            this.name = name;
            this.age = age;
            this.club = club;
        }

        public Coach generateCoach(String club, String name, int leagueIndex)
        {
            Coach c1 = new Coach();

            Random rand = new Random();

            int age =  rand.nextInt(60) + 30;

            c1.createCoach(name, age, club);

            return c1;
        }

        // Getters

        public String getCoachName()
        {
            return this.name;
        }

        public int getCoachAge()
        {
            return this.age;
        }

        public String getCoachClub()
        {
            return this.club;
        }
    }

    public static class PhysicalStaff
    {
        public String name, club;

        public int age;

        public float healingLVL,contractClause;

        // Constructor

        public void createPStaff(String name, int age, String club, float healingLVL, float contractClause)
        {
            this.name = name;
            this.age = age;
            this.club = club;
            this.healingLVL = healingLVL;
            this.contractClause = contractClause;
        }

        public PhysicalStaff generatePStaff(String club, String name, int leagueIndex)
        {
            PhysicalStaff ps1 = new PhysicalStaff();

            Random rand = new Random();

            int age = rand.nextInt(60) + 25;

            float healingLVL = (float)age * ( rand.nextInt(3) + 1 );

            float contractClause = healingLVL * 2000;

            ps1.createPStaff(name, age, club, healingLVL, contractClause);

            return ps1;
        }

        // Getters

        public String getDocName()
        {
            return this.name;
        }

        public int getDocAge()
        {
            return this.age;
        }

        public String getDocClub()
        {
            return this.club;
        }

        public float getHealingLVL()
        {
            return this.healingLVL;
        }
    }


    public static class Trainer
    {
        public String name;

        public int age, type;

        public float LVL,contractClause;

        public String club;

        // Constructor

        public void createTrainer(String name, int age, String club, float LVL, float contractClause, int type)
        {
            this.name = name;
            this.age = age;
            this.club = club;
            this.LVL = LVL;
            this.contractClause = contractClause;
            this.type = type;
        }

        // ( type == 0 ) --> defensive or ( type == 1 ) --> offensive
        public Trainer generateTrainer(int type, String club, String name, int leagueIndex)
        {
            Trainer t1 = new Trainer();

            Random rand1 = new Random();

            int age = rand1.nextInt(50) + 20;

            float LVL = rand1.nextFloat() * 100;

            float contractClause = LVL * 6000;

            t1.createTrainer(name, age, club, LVL, contractClause, type);

            return t1;
        }

        // Getters

        public String getTrainerName()
        {
            return this.name;
        }

        public String getTrainerClub()
        {
            return this.club;
        }

        public float getLVL()
        {
            return this.LVL;
        }
    }

    public static class Player
    {
        // Class data

        public int position, age;

        public int [] points;

        public String playerName, club;

        public double overAllPoints, realeaseClause;

        // Constructor

        public void createPlayer(String playerName, int position, int [] points, String club)
        {
            this.playerName = playerName;

            this.position = position;

            this.points = new int[3];

            System.arraycopy(points, 0, this.points, 0, 3);

            Random r1 = new Random();

            int age = r1.nextInt(20);

            this.age = age+16;

            this.club = club;
            this.overAllPoints = Math.min(calculateAvPoints(points, position),100);
            this.realeaseClause = calcPlayerClause(overAllPoints);
        }

        // ( position ==  1 ) --> cb or  ( position ==  2 ) --> mf or ( position ==  3 ) --> cf
        public Player generatePlayer(String club, String name, int leagueIndex, int pos)
        {
            Player aux = new Player();

            Random rand = new Random();

            int[] pts = new int[3];

            int auxRand;

            for (int x = 0; x <= 2; x++)
            {
                if (x != pos - 1)
                {
                    auxRand = rand.nextInt(30);

                    auxRand = auxRand + ( 20 * leagueIndex );

                    pts[x] = auxRand;
                }

                else
                {
                    auxRand = rand.nextInt(30);

                    auxRand = auxRand + ( 30 * leagueIndex );

                    pts[x] = auxRand;
                }
            }

            aux.createPlayer(name, pos, pts, club);

            return aux;
        }

        // Getters

        public String getPlayerPos()
        {
            if(this.position == 0)
            {
                return "GK";
            }

            if(this.position == 1)
            {
                return "CB";
            }

            if(this.position == 2)
            {
                return "MF";
            }

            return "CF";
        }

        public double getOverAllPoints()
        {
            return this.overAllPoints;
        }

        public String getPlayerName()
        {
            return this.playerName;
        }

        public int getPlayerAge()
        {
            return this.age;
        }

        public String getPlayerClub()
        {
            return this.club;
        }

        public double getPlayerClause()
        {
            return this.realeaseClause;
        }

        // Calculus

        public double calculateAvPoints(int [] points, int position)
        {
            double avPoints = 0;

            for(int i = 0; i < 3; i++)
            {
                if(position == i + 1)
                {
                    avPoints = avPoints + points[i];
                }

                else
                {
                    avPoints = avPoints + points[i] + ( points[i] * 0.10 );
                }
            }

            return ( avPoints / 3 );
        }

        public double calcPlayerClause(double overAllPoints)
        {
            return ( (overAllPoints * 1000) / ( (this.age / 2) - 1) );
        }

        // Setters

        public void changePlayerClub(String newClub)
        {
            this.club = newClub;
        }

        public void changeClubForPlayer(String new_club)
        {
            this.club = new_club;
        }
    }

    public void createTeam(String teamName, String coachName, String [] players, String PhyStaff, String [] trainers, int leagueIndex, double teamBgt)
    {
        this.teamName = teamName;

        this.teamBudget = teamBgt;

        // Coach construction

        Coach c1 = new Coach();

        c1 = c1.generateCoach(teamName, coachName, leagueIndex);

        this.coach = c1;

        this.teamCoach = coachName;

        //Defensive and Offensive trainers construction

        Trainer defTrainer = new Trainer();
        Trainer offTrainer = new Trainer();

        defTrainer = defTrainer.generateTrainer(0, teamName, trainers[0], leagueIndex);

        this.defTrainer = defTrainer;

        offTrainer = offTrainer.generateTrainer(1, teamName, trainers[1], leagueIndex);

        this.atkTrainer = offTrainer;

        //Physical staff construction

        PhysicalStaff Pstaff = new PhysicalStaff();

        Pstaff = Pstaff.generatePStaff(teamName, PhyStaff, leagueIndex);

        this.doc = Pstaff;

        // Players construction

        Player[] lineUp = new Player[11];

        for(int indPlayer = 0; indPlayer <= 10; indPlayer++)
        {
            Player aux = new Player();

            String name = players[indPlayer];

            if(indPlayer < 1)
            {
                aux = aux.generatePlayer(teamName, name, leagueIndex, 0);
            }

            if(indPlayer >= 1 && indPlayer < 5)
            {
                aux = aux.generatePlayer(teamName, name, leagueIndex, 1);
            }

            if(indPlayer >= 5 && indPlayer < 8)
            {
                aux = aux.generatePlayer(teamName, name, leagueIndex, 2);
            }

            if(indPlayer >= 8)
            {
                aux = aux.generatePlayer(teamName, name, leagueIndex, 3);
            }

            lineUp[indPlayer] = aux;
        }

        // Starting line-up construction

        Player [] strLU = new Player[7];

        System.arraycopy(lineUp, 0, strLU, 0, 4);

        System.arraycopy(lineUp, 5, strLU, 4, 2);

        System.arraycopy(lineUp, 8, strLU, 6, 1);

        this.lineUp = lineUp;

        this.overAllPoints = calcTeamOApts(lineUp);

        int nGames = a.getnLeagueTeams();

        this.calendar = new String[nGames-1];

        this.results = new String[nGames-1];

        this.startLineUp = strLU;

        // League contruction

        if(leagueIndex == 1)
        {
            this.leagueName = "Amateur League";
        }

        if(leagueIndex == 2)
        {
            this.leagueName = "Semi Pro League";
        }

        if(leagueIndex == 3)
        {
            this.leagueName = "Pro League";
        }

        // Calendar construction

        for(int i = 0; i < nGames-1; i++)
        {
            this.calendar[i] = "TBA";
            this.results[i] = "TBD";
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}