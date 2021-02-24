package com.example.mastercoach;

import android.os.Bundle;

import com.example.mastercoach.MainActivity;

import java.util.Random;

/*
* WIP:
*       => METODOS PARA MELHORAR AS ESTATISTICAS DE JOGADORES, MOVIMENTAÇÕES DE UM EQUIPA PARA A OUTRA, CASO SEJA PAGO A CLAUSULA É ADEQUIRIDO O JOGADOR/PS/TRAINENER,
*       => LESOES ? EM PRIMEIRO PLANO NAO , MAS FALTA ADICIONAR OUTRAS CARACTERISTICAS AO JOGADORES COMO FORMA E MOTIVAÇÃO
*       => acrescentar Numero de constituintes para tornar codigo mais dinamico
* */


public class Team extends com.example.mastercoach.MainActivity {
    double overAllPoints, teamBudget;
    String teamName, teamCoach,leagueName;
    Player [] lineUp;
    Player [] startLineUp;
    Coach coach;
    PhysicalStaff doc;
    Trainer defTrainer, atkTrainer;
    String [] calendar;
    String [] results;

    public String getTeamName() {return this.teamName;}
    public double getOverAllPoints() {return this.overAllPoints;}
    public double getTeamBudget() {return this.teamBudget;}

    public void createTeamResults(String [] res) {
        this.results = res;
    }

    public void setTeamResult(int nJorney, String res) {
        this.results[nJorney] = res;
    }

    public String [] getResults() {
        if(this.results != null)
            return this.results;

        return null;
    }

    public void changeLeagueName(String newLeagueName) {
        this.leagueName = newLeagueName;
    }

    public String getTeamLeagueName() {
        return this.leagueName;
    }

    public int getNumberOfPlayers() {
        return this.lineUp.length;
    }

    public boolean checkIfStartingTeam(String player) {
        for(Player aux: startLineUp) {
            if(aux.getPlayerName().equals(player))
                return true;
        }

        return false;
    }

    public void changeTeamCoach(String newCoachName, Coach newCoach) {
        this.teamCoach = newCoachName;
        this.coach = newCoach;
    }

    public void changeStartingLineUp(Player in, Player out) {
        String outPlayer = out.getPlayerName();
        Player [] aux = new Player [startLineUp.length];
        for(int i = 0; i < 7; i++) {
            String auxPlayerName = this.startLineUp[i].getPlayerName();

            if(auxPlayerName.equals(outPlayer)) {
                aux[i] = in;
            }
            else {
                aux[i] = this.startLineUp[i];
            }
        }

        this.startLineUp = aux;
    }

    public Player getPlayerByName(String reqPlayer) {
        for(Player aux: lineUp) {
            String auxName = aux.getPlayerName();
            if(auxName.equals(reqPlayer))
                return aux;
        }

        return null;
    }

    public void increaseTeamBudget(double val) {
        this.teamBudget += val;
    }

    public Coach getTeamCoachObj() {
        if(this.coach != null)
            return this.coach;

        return null;
    }

    public Trainer getAtkTeamTrainerObj() {
        if(this.atkTrainer != null)
            return this.atkTrainer;

        return null;
    }

    public Trainer getDefTeamTrainerObj() {
        if(this.defTrainer != null)
            return this.defTrainer;

        return null;
    }

    public PhysicalStaff getDoc() {
        if(this.doc != null)
            return this.doc;

        return null;
    }

    public void decreaseTeamBudget(double val) {
        this.teamBudget -= val;
    }

    public void transferPlayer(Player p1) {
        int nPlayers = this.lineUp.length;
        Player [] auxLnp = new Player [nPlayers + 1];

        for(int i = 0; i < nPlayers; i++) {
            auxLnp[i] = this.lineUp[i];
        }

        auxLnp[nPlayers] = p1;
        this.lineUp = auxLnp;
    }

    public Player removePlayerFromTeam(String playerName) {
        if(this.lineUp.length <= 7)
            return null;

        for(Player target: this.lineUp) {
            if(target.getPlayerName().equals(playerName)) {return target;}
        }

        return null;
    }

    public Player searchInTeamForPlayer(String name) {
        for(Player aux: lineUp) {
            if(aux.getPlayerName().equals(name))
                return aux;
        }

        return null;
    }

    public void getMoneyFromAmateurMatch(String result) {
            if(result.equals("win"))
                this.teamBudget += 500;
            if(result.equals("draw"))
                this.teamBudget += 200;
    }

    public void getMoneyFromSemiProMatch(String result) {
        if(result.equals("win"))
            this.teamBudget += 1000;
        if(result.equals("draw"))
            this.teamBudget += 400;
    }

    public void getMoneyFromProMatch(String result) {
        if(result.equals("win"))
            this.teamBudget += 2000;
        if(result.equals("draw"))
            this.teamBudget += 800;
    }

    public void getMoneyToAmateurChampions() {
        this.teamBudget += 3000;
    }

    public void getMoneyToSemiProChampions() {
        this.teamBudget += 6000;
    }

    public void getMoneyToProChampions() {
        this.teamBudget += 8000;
    }



    public Player [] getStartLineUp() {
        if(this.startLineUp != null)
            return this.startLineUp;

        return null;
    }

    public String [] getCalendar() {
        if(this.calendar != null)
            return this.calendar;

        return null;
    }

    public double getCenterFowardPoints() {
        double cfPts = 0;
        int nPlayers = 0;
        for(Player aux : startLineUp) {
            String playerPos = aux.getPlayerPos();

            if(playerPos.equals("CF")) {
                cfPts += aux.getOverAllPoints();
                nPlayers++;
            }
        }

        return cfPts/nPlayers;
    }

    public String getRandomTackler() {
        Random rand = new Random();
        int chsn = rand.nextInt(4);

        return startLineUp[chsn].getPlayerName();
    }

    public double getAccumulativeDeffensivePoints() {
        double accDefPts = 0;
        int nPlayers = 0;

        for(Player aux: startLineUp) {
            String Playerpos = aux.getPlayerPos();
            if(Playerpos.equals("MF") || Playerpos.equals("CB")){
                nPlayers++;
                accDefPts += aux.getOverAllPoints();
            }
        }

        return accDefPts/nPlayers;
    }

    public double getAccumulativeAttackPoints() {
        double accAttkPts = 0;
        int nPlayers = 0;

        for(Player aux: startLineUp) {
            String Playerpos = aux.getPlayerPos();
            if(Playerpos.equals("MF") || Playerpos.equals("CF")){
                nPlayers++;
                accAttkPts += aux.getOverAllPoints();
            }
        }

        return accAttkPts/nPlayers;
    }

    public double getMFPoints() {
        double mfPts = 0;
        int nPlayers = 0;

        for(Player aux: startLineUp) {
            String playerPos = aux.getPlayerPos();

            if(playerPos.equals("MF")) {
                mfPts += aux.getOverAllPoints();
                nPlayers++;
            }
        }

        return mfPts/nPlayers;
    }

    public double getDeffensePoints() {
        double defPts = 0;
        int nPlayers = 0;

        for(Player aux: startLineUp) {
            defPts += aux.getOverAllPoints();
            nPlayers++;
        }

        return defPts/nPlayers;
    }

    public double getKeeperPoints() {
        return startLineUp[0].getOverAllPoints();
    }

    public String getAdvOnJourney(int journey) {
        if(this.calendar != null) {
            return this.calendar[journey];
        }

        return null;
    }

    public void createTeamCalendar(String [] c) {
        System.arraycopy(c, 0, this.calendar, 0, c.length);
    }

    public Player[] getPlayers() {
        if(this.lineUp != null) {return this.lineUp;}

        return null;
    }

    public Player getObjectPlayerIndex(int index) {
        if(this.lineUp != null) {return this.lineUp[index];}

        return null;
    }


    public double calcTeamOApts(Player [] rooster) {
        double oaPts = 0;

        for(int i = 0; i < 11; i++) {
            Player aux = rooster[i];
            oaPts =+ aux.overAllPoints;
        }

        oaPts = oaPts / 11;
        return oaPts;
    }

    public class Coach {
        String name;
        int age;
        String club;

        public void createCoach(String name, int age, String club) {
            this.name = name;
            this.age = age;
            this.club = club;
        }

        public Coach generateCoach(String club, String name, int leagueIndex) {

            Coach c1 = new Coach();
            Random rand = new Random();
            int age =  rand.nextInt(60)+30;
            c1.createCoach(name, age, club);

            return c1;
        }

        public String getCoachName() {return this.name;}
        public int getCoachAge() {return this.age;}
        public String getCoachClub() {return this.club;}
    }

    public class PhysicalStaff {
        public String name, club;
        public int age;
        public float healingLVL,contractClause;

        public void createPStaff(String name, int age, String club, float healingLVL, float contractClause) {
            this.name = name;
            this.age = age;
            this.club = club;
            this.healingLVL = healingLVL;
            this.contractClause = contractClause;
        }

        public PhysicalStaff generatePStaff(String club, String name, int leagueIndex) {

            PhysicalStaff ps1 = new PhysicalStaff();

            Random rand = new Random();

            int age = rand.nextInt(60)+25;
            float healingLVL = (float)age*((rand.nextInt(3)+1));
            float contractClause = healingLVL*2000;

            ps1.createPStaff(name, age, club, healingLVL, contractClause);
            return ps1;
        }

        public String getDocName() {return this.name;}
        public int getDocAge() {return this.age;}
        public String getDocClub() {return this.club;}
        public float getHealingLVL() {return this.healingLVL;}
    }


    public class Trainer {

        public String name;
        public int age, type;
        public float LVL,contractClause;
        public String club;

        public void createTrainer(String name, int age, String club, float LVL, float contractClause, int type) {
            this.name = name;
            this.age = age;
            this.club = club;
            this.LVL = LVL;
            this.contractClause = contractClause;
            this.type = type;
        }

        // TYPE = 0 (DEF) , TYPE = 1 (OFFENSVE)
        public Trainer generateTrainer(int type, String club, String name, int leagueIndex) {

            Trainer t1 = new Trainer();
            Random rand1 = new Random();

            int age = rand1.nextInt(50)+20;
            float LVL = rand1.nextFloat()*100;
            float contractClause = LVL*6000;

            t1.createTrainer(name, age, club, LVL, contractClause, type);

            return t1;
        }

        public String getTrainerName() {return this.name;}
        public String getTrainerClub() {return this.club;}
        public float getLVL() {return this.LVL;}
    }

    public class Player {
        // pos:  1 - CENTERBACK , 2-MIDDLEFIELD , 3 -FOWARD
        public int position, age;
        public int [] points;
        public String playerName, club;
        public double overAllPoints, realeaseClause;

        public void createPlayer(String playerName, int position, int [] points, String club) {
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

        public Player generatePlayer(String club, String name, int leagueIndex, int pos) {

            Player aux = new Player();

            Random rand = new Random();
            int[] pts = new int[3];

            int auxRand;
            for (int x = 0; x <= 2; x++) {
                if (x != pos - 1) {
                    auxRand = rand.nextInt(30);
                    auxRand = auxRand + (20 * leagueIndex);
                    pts[x] = auxRand;
                }

                else{
                    auxRand = rand.nextInt(30);
                    auxRand = auxRand + (30 * leagueIndex);
                    pts[x] = auxRand;
                }
            }

            aux.createPlayer(name, pos, pts, club);
            return aux;
        }

        public double calculateAvPoints(int [] points, int position) {
            double avPoints = 0;
            for(int i = 0; i < 3; i++) {
                if(position == i + 1) {
                    avPoints = avPoints + points[i];
                }
                else {
                    avPoints = avPoints + points[i] + (points[i]*0.10);
                }
            }

            return (avPoints/3);
        }

        public void changePlayerClub(String newClub) {
            this.club = newClub;
        }

        public String getPlayerPos() {
            if(this.position == 0)
                return "GK";
            if(this.position == 1)
                return "CB";
            if(this.position == 2)
                return "MF";

            return "CF";
        }

        public void changeClubForPlayer(String new_club) { this.club = new_club; }
        public double calcPlayerClause(double overAllPoints) {return ((overAllPoints*1000)/((this.age/2)-1));}
        public double getOverAllPoints() {
            return this.overAllPoints;
        }
        public String getPlayerName() {return this.playerName;}
        public int getPlayerAge() {return this.age;}
        public String getPlayerClub() {return this.club;}
        public double getPlayerClause() {return this.realeaseClause;}
    }

    //createTeam(TeamNames[1], coachNames[1], team2Players, team2PhyStaff, team2Trainers)
    //team having a rooster of players {physical staff, offensive and defensive trainers and a coach}
    public void createTeam(String teamName, String coachName, String [] players, String PhyStaff, String [] trainers, int leagueIndex, double teamBgt) {

        this.teamName = teamName;
        this.teamBudget = teamBgt;

        //coach
        Coach c1 = new Coach();
        c1 = c1.generateCoach(teamName, coachName, leagueIndex);
        this.coach = c1;
        this.teamCoach = coachName;

        //Defensive and Offensive trainers
        Trainer defTrainer = new Trainer();
        Trainer offTrainer = new Trainer();

        defTrainer = defTrainer.generateTrainer(0, teamName, trainers[0], leagueIndex);
        this.defTrainer = defTrainer;

        offTrainer = offTrainer.generateTrainer(1, teamName, trainers[1], leagueIndex);
        this.atkTrainer = offTrainer;

        //Physical staff -- Missing generate call for Pstaff
        PhysicalStaff Pstaff = new PhysicalStaff();
        Pstaff = Pstaff.generatePStaff(teamName, PhyStaff, leagueIndex);
        this.doc = Pstaff;

        Player[] lineUp = new Player[11];

        for(int indPlayer = 0; indPlayer <= 10; indPlayer++) {
            Player aux = new Player();
            String name = players[indPlayer];

            if(indPlayer < 1)
                aux = aux.generatePlayer(teamName, name, leagueIndex, 0);
            if(indPlayer >= 1 && indPlayer < 5)
                aux = aux.generatePlayer(teamName, name, leagueIndex, 1);
            if(indPlayer >= 5 && indPlayer < 8)
                aux = aux.generatePlayer(teamName, name, leagueIndex, 2);
            if(indPlayer >= 8)
                aux = aux.generatePlayer(teamName, name, leagueIndex, 3);

            lineUp[indPlayer] = aux;
        }

        Player [] strLU = new Player[7];

        for(int i = 0; i < 4; i++) {
            strLU[i] = lineUp[i];
        }

        for(int i = 4; i < 6; i++) {
            strLU[i] = lineUp[i+1];
        }

        for(int i = 6; i < 7; i++) {
            strLU[i] = lineUp[i+2];
        }

        this.lineUp = lineUp;
        this.overAllPoints = calcTeamOApts(lineUp);

        int nGames = a.getnLeagueTeams();
        this.calendar = new String[nGames-1];
        this.results = new String[nGames-1];
        this.startLineUp = strLU;

        if(leagueIndex == 1) {
            this.leagueName = "Amateur League";
        }

        if(leagueIndex == 2) {
            this.leagueName = "Semi Pro League";
        }

        if(leagueIndex == 3) {
            this.leagueName = "Pro League";
        }

        for(int i = 0; i < nGames-1; i++) {
            this.calendar[i] = "TBA";
            this.results[i] = "TBD";
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}