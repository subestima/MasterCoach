package com.example.mastercoach;

import android.os.Bundle;

public class League extends Team {
    final public int nTeams = 18;
    final public int nLeagueTeams = 6;

    public int gamesPlayed;
    public Team[] league;
    public String[][] Score;
    public String LeagueName;

    public int getNTeams() {return this.nTeams;}
    public int getnLeagueTeams() {return this.nLeagueTeams;}
    public int getNGamesPlayed() {return this.gamesPlayed;}
    public String getLeagueName() {return this.LeagueName;}

    public void resetLeague() {
        this.gamesPlayed = 0;

        for (int x1 = 0; x1 < nLeagueTeams; x1++) {
            Score[x1][0] = league[x1].getTeamName();
            Score[x1][1] = "0";
        }

        int [][] MatchesCfg = {
                {1, 2, 3, 4, 5},
                {0, 4, 5, 3, 2},
                {3, 0, 4, 5, 1},
                {2, 5, 0, 1, 4},
                {5, 1, 2, 0, 3},
                {4, 3, 1, 2, 0}
        };

        for(int i = 0; i < nLeagueTeams; i++) {

            String [] calend = new String[nLeagueTeams-1];
            String [] results = new String[nLeagueTeams-1];
            Team t1 = league[i];

            for(int j = 0; j < nLeagueTeams-1; j++) {
                int iAdv = MatchesCfg[i][j];
                String adv = league[iAdv].getTeamName();
                calend[j] = adv;
                results[j] = "TBD";
            }
            t1.createTeamResults(results);
            t1.createTeamCalendar(calend);
        }
    }

    public void changePtsToTeam(String t1, int pts) {
        for(int i = 0 ; i < nLeagueTeams; i++) {
            String aux = this.Score[i][0];
            if(aux.equals(t1)) {
                int res = Integer.parseInt(this.Score[i][1]) + pts;
                this.Score[i][1] = String.valueOf(res);
            }
        }
    }

    public void insertTeamInLeague(int pos, Team newTeam) {
        this.league[pos] = newTeam;
        newTeam.changeLeagueName(this.leagueName);
    }

    public void switchTeamsClass(int t1, int t2) {
        String t1Name = getTeamByPos(t1);
        String t1Pts = getPtsByPos(t1);

        this.Score[t1][0] = this.Score[t2][0];
        this.Score[t1][1] = this.Score[t2][1];
        this.Score[t2][0] = t1Name;
        this.Score[t2][1] = t1Pts;
    }

    public void incGamesPlayed() {
        this.gamesPlayed++;
    }

    public String[][] getScore() {
        if(this.Score != null)
            return this.Score;
        else
            return null;
    }

    public String getPtsByPos(int index) {
        if(this.Score != null && index < this.Score.length)
            return this.Score[index][1];
        else
            return null;
    }

    public String getTeamByPos(int index) {
        if(Score != null)
            return this.Score[index][0];
        else
            return null;
    }


    public Team getTeamByName(String teamName) {
        for(int i = 0; i < this.nLeagueTeams; i++) {
            Team aux = this.league[i];
            String auxName = aux.getTeamName();
            if (auxName.compareTo(teamName) == 0)
                return aux;
        }

        return null;
    }

    public Team [] getAllLeagueTeams() {
        if(this.lineUp != null)
            return this.league;

        return null;
    }

    public Team searchForTeamInLeague(String team_name) {
        for(Team aux: league) {
            if(aux.getTeamName().equals(team_name))
                return aux;
        }

        return null;
    }

    public Player searchForPlayerInLeague(String player_name) {
        for(Team aux: league) {
            Player auxP = aux.searchInTeamForPlayer(player_name);
            if(auxP != null)
                return auxP;
        }

        return null;
    }

    public Team getObjectTeamIndex(int index) {
        if(this.league[index] != null) {return this.league[index];}

        return null;
    }

    public int getIndOfTeam(Team t1) {
        if(t1 != null) {
            String indTeamName = t1.getTeamName();
            for (int i = 0; i < nLeagueTeams; i++) {
                String auxTeamName = getObjectTeamIndex(i).getTeamName();

                if (indTeamName.equals(auxTeamName))
                    return i;
            }
        }
        return -1;
    }

    public void createAmateurLeague() {
        String [][] scr = new String[nLeagueTeams][2];
        Team [] lnp = new Team [nLeagueTeams];

        String [] TeamNames = {"Válega", "Ovarense", "Espinho", "Pardilhó", "Esmoriz", "Paramos"};

        String [] coachNames = {"Abel Xavier", "Alberto Cabral", "Alexandre Goulart", "Álvaro Monteiro Magalhães", "Malta da Silva", "Geo Trapatoni"};

        String [] team1Players = {"Abel Braga", "Roberto Caveanha","Cicinho", "Medici Machado", "Dos Santos", "José Gouveia", "Brant", "Germano Silveira", "José de Lira", "Cidreira Sampaio", "Henrique Casemiro"};
        String [] team2Players = {"Abelardo Lamare", "Baiano", "Arlindo Cruz", "Gabiru Imperador", "Aldo Silva", "Aldemar Costa", "Aldair Nascimento", "Cambalhota Campos", "Ariel Augusto", "Adolpho Milman", "Alex Sandro"};
        String [] team3Players = {"Acácio Cunha", "Baltazar Maria", "Afonso Neves", "Jair Mcnuget", "Andreas Pereira", "Angélico Paulino", "Dentinho", "Careca", "Cantareli", "David Cortes", "Dadá Maravilha"};
        String [] team4Players = {"Adão Dornelles", "Paulo Batista", "David Braz", "Caetano Izzo", "Amoroso", "Cleber Baenay" ,"Barbuy Zento", "Ismael Alavarizo","Conte Alma", "Brito", "Benjamim Zola"};
        String [] team5Players = {"Ademir Menezes", "Bauer", "Oliver Khan", "Marcus Neaur", "Philip Lham", "Cannavaro", "Nesta", "Pirlo", "Di Setafano", "John Vardy", "Nicolas Gaitan"};
        String [] team6Players = {"Ademir Roque" ,"Bebeto", "Di Maria", "Ter Seteggen", "Quaresma", "Lucho Gonzales", "Marcano", "Vitor Baia", "Nani", "Cristiano Ronaldo", "Djalminha"};

        String team1PhyStaff = "Dino Sani";
        String team2PhyStaff = "Mateus Pereira";
        String team3PhyStaff = "Jose Guimaraes";
        String team4PhyStaff = "Almeida Rego";
        String team5PhyStaff = "Luiz Paulo";
        String team6PhyStaff = "Militão";

        String [] team1Trainers = {"Dirceu Gonzales", "Deiveson Coelho"};
        String [] team2Trainers = {"Henrique Tavares", "Dias Junior"};
        String [] team3Trainers = {"Geraldo Pereira" ,"Amilcar Xandar"};
        String [] team4Trainers = {"Pádua Manga", "Edu Maragon"};
        String [] team5Trainers = {"Antunes Coimbra", "Edu Bala"};
        String [] team6Trainers = {"Ilsinho", "Humberto do Araujo"};

        String [][] Score = new String[nLeagueTeams][2];
        Team [] league = new Team[nLeagueTeams];

        //public void createTeam(String teamName, String coachName, String [] players, String PhyStaff, String [] trainers, Double TeamBudget)
        Team t1 = new Team();
        t1.createTeam(TeamNames[0], coachNames[0], team1Players, team1PhyStaff, team1Trainers, 1, 3000);
        lnp[0] = t1;

        Team t2 = new Team();
        t2.createTeam(TeamNames[1], coachNames[1], team2Players, team2PhyStaff, team2Trainers, 1, 3000);
        lnp[1] = t2;

        Team t3 = new Team();
        t3.createTeam(TeamNames[2], coachNames[2], team3Players, team3PhyStaff, team3Trainers, 1, 3000);
        lnp[2] = t3;

        Team t4 = new Team();
        t4.createTeam(TeamNames[3], coachNames[3], team4Players, team4PhyStaff, team4Trainers,1 , 3000);
        lnp[3] = t4;

        Team t5 = new Team();
        t5.createTeam(TeamNames[4], coachNames[4], team5Players, team5PhyStaff, team5Trainers, 1, 3000);
        lnp[4] = t5;

        Team t6 = new Team();
        t6.createTeam(TeamNames[5], coachNames[5], team6Players, team6PhyStaff, team6Trainers,1, 3000);
        lnp[5] = t6;

        for(int i = 0; i < nLeagueTeams; i++) {
            for(int j = 0; j < 2; j++) {
                scr[i][0] = TeamNames[i];
                scr[i][1] = "0";
            }
        }

        this.gamesPlayed = 0;
        this.Score = scr;
        this.league = lnp;
        this.LeagueName = "Amateur League";
    }

    public void createSemiProLeague() {
        String [][] scr = new String[nLeagueTeams][2];
        Team [] lnp = new Team [nLeagueTeams];

        String [] TeamNames = {"Torreira", "Feirense", "São Joanense", "Seixo", "Candal", "Murtosa"};

        String [] coachNames = {"Lucas Tonielle", "Martin Chievers", "John Well", "Harry Gibs", "Steve Hodge", "Jesse Lingard"};

        String [] team1Players = {"Mortensen", "McDemmot", "Jordan Pickford", "Darius Vessel", "Sam Hardy", "Cliff Bastin", "Dennis Wiseh", "Paul Mersion", "Lee Dixon", "Paul Walker", "Tony Dorigo"};
        String [] team2Players = {"Ze Nando", "Manuel Cajuda", "Marco Silva", "Mario Wilson", "Fernando Cabiato", "Emilio Peixe", "Manuel Goualarde", "Luis Norton", "Jolbert Cabrera", "Eder Renteria", "Yammid Haad"};
        String [] team3Players = {"Julio Teheran", "Quintana", "Emirio Frieri", "Donovan Soldado", "Herrera", "Pizzi", "Simao", "Carlos Martins", "Feher", "Ramirez", "De Rossi"};
        String [] team4Players = {"Luis Escobar", "Cosme Damião", "Bruno Lage", "Carlos Queiroz", "Domingos Paciencia", "Antonio Folha", "Vitor Perreira", "Sá Pinto", "Sheu Han", "Sergio Conceicao", "Petit"};
        String [] team5Players = {"Dal Ignana", "Gabriel Jessus", "Zidane", "Platini", "Matiudi", "Ribery", "Roberto Pires", "Malouda", "Makelele", "Emanuel Sagna", "Varane"};
        String [] team6Players = {"Henri Michel", "Enric Abidal", "Samir Nasri", "NGolo Kante", "Piatonni", "Adil Rami", "Jeremy Toulalan", "Bruno Martini", "Lucas Digne", "Jean Nicolas", "Rene Ferrier"};

        String team1PhyStaff = "Antonio Cunha";
        String team2PhyStaff = "Ines Cunha";
        String team3PhyStaff = "Fekir";
        String team4PhyStaff = "Fertir Noava";
        String team5PhyStaff = "Revielleri";
        String team6PhyStaff = "Cuortano";

        String [] team1Trainers = {"Armando Borja", "Mehdi Alghert"};
        String [] team2Trainers = {"Bouzava", "Yebda"};
        String [] team3Trainers = {"Sergio Aguero" ,"Angeleri"};
        String [] team4Trainers = {"Henriq Mitrihan", "Ahamad Elmerich"};
        String [] team5Trainers = {"Richard Garcia", "John Fillan"};
        String [] team6Trainers = {"Chris Herd", "Richardson C"};

        String [][] Score = new String[nLeagueTeams][2];
        Team [] league = new Team[nLeagueTeams];

        //public void createTeam(String teamName, String coachName, String [] players, String PhyStaff, String [] trainers)
        Team t1 = new Team();
        t1.createTeam(TeamNames[0], coachNames[0], team1Players, team1PhyStaff, team1Trainers,2, 3000);
        lnp[0] = t1;

        Team t2 = new Team();
        t2.createTeam(TeamNames[1], coachNames[1], team2Players, team2PhyStaff, team2Trainers,2, 3000);
        lnp[1] = t2;

        Team t3 = new Team();
        t3.createTeam(TeamNames[2], coachNames[2], team3Players, team3PhyStaff, team3Trainers,2, 3000);
        lnp[2] = t3;

        Team t4 = new Team();
        t4.createTeam(TeamNames[3], coachNames[3], team4Players, team4PhyStaff, team4Trainers,2, 3000);
        lnp[3] = t4;

        Team t5 = new Team();
        t5.createTeam(TeamNames[4], coachNames[4], team5Players, team5PhyStaff, team5Trainers,2, 3000);
        lnp[4] = t5;

        Team t6 = new Team();
        t6.createTeam(TeamNames[5], coachNames[5], team6Players, team6PhyStaff, team6Trainers,2, 3000);
        lnp[5] = t6;

        for(int i = 0; i < nLeagueTeams; i++) {
            for(int j = 0; j < 2; j++) {
                scr[i][0] = TeamNames[i];
                scr[i][1] = "0";
            }
        }

        this.gamesPlayed = 0;
        this.Score = scr;
        this.league = lnp;
        this.LeagueName = "Semi Pro League";
    }

    public void createProLeague() {
        String [][] scr = new String[nLeagueTeams][2];
        Team [] lnp = new Team [nLeagueTeams];

        String [] TeamNames = {"Rio Tinto", "Avanca", "Souto", "Pasteleira", "Lordelo", "Estarreja"};

        String [] coachNames = {"Ignazio Abate", "Christian Abbiati", "Francesco Acerbi", "Danielle Adani", "Emmano Aebi", "Aldo Agroppi"};

        String [] team1Players = {"Demetri Albertini", "Enrico Albertosi", "Giuseppe Aliberti", "Luigi Allemandi", "Jose Altafini", "Alessandro Altobelli", "Amadeo Amadei", "Amauri", "Massimo Abrossini", "Marco Amelia", "Carlo Anceloti"};
        String [] team2Players = {"Angelo Anquelini", "Antognoni", "Apollini", "Guido Ara", "Bruno Aracari", "Ardissone", "Davide Astore", "Badini", "Dino Baggio", "Bachini", "Barone"};
        String [] team3Players = {"Basseto", "Bazzani", "Gastone DiParla", "Fulvio Bernadini", "Carlo Biggato", "Alessandro Birindeli", "Arturo Boiochile", "Franco Brienza", "Buffon", "Mattia Caldara", "Umberto Caligaris"};
        String [] team4Players = {"Francesco Totti", "Aldo Campatelli", "Zulo Candreva", "Pierrolino", "Campalone", "Carboni", "Carter", "Capra", "Franco Cordova","Bryan Cristante","De Agostini"};
        String [] team5Players = {"De Napoli", "Sergio Ramos", "Xavi", "Cesc Fabregas", "Santi Cazorla", "Eusebio", "Rui Aguas", "Pele", "Ronaldinho", "Kaka", "Santilana"};
        String [] team6Players = {"Francesco Poertas", "Kobe Bryan", "Michael Jordini", "Alkorta", "Munoz", "Michel Salgado", "Mendieta", "Isco", "Manolo", "Mantorras", "Chendolaka"};

        String team1PhyStaff = "Michael Owen";
        String team2PhyStaff = "Wayne Rooney";
        String team3PhyStaff = "Frank Lampard";
        String team4PhyStaff = "Bobby Charlton";
        String team5PhyStaff = "Steven Gerard";
        String team6PhyStaff = "Rio Ferdinand";

        String [] team1Trainers = {"Ray Wilkins", "Billy Wright"};
        String [] team2Trainers = {"Kenny Sansom", "Stuart Pearce"};
        String [] team3Trainers = {"Gordon Banks" ,"Johny Macarroni"};
        String [] team4Trainers = {"Joe Cole", "Sterling"};
        String [] team5Trainers = {"Henderson", "Phil Neal"};
        String [] team6Trainers = {"Harry Kane", "Colin Bell"};

        String [][] Score = new String[nLeagueTeams][2];
        Team [] league = new Team[nLeagueTeams];

        //public void createTeam(String teamName, String coachName, String [] players, String PhyStaff, String [] trainers)
        Team t1 = new Team();
        t1.createTeam(TeamNames[0], coachNames[0], team1Players, team1PhyStaff, team1Trainers,3, 9000);
        lnp[0] = t1;

        Team t2 = new Team();
        t2.createTeam(TeamNames[1], coachNames[1], team2Players, team2PhyStaff, team2Trainers,3, 9000);
        lnp[1] = t2;

        Team t3 = new Team();
        t3.createTeam(TeamNames[2], coachNames[2], team3Players, team3PhyStaff, team3Trainers,3, 9000);
        lnp[2] = t3;

        Team t4 = new Team();
        t4.createTeam(TeamNames[3], coachNames[3], team4Players, team4PhyStaff, team4Trainers,3, 9000);
        lnp[3] = t4;

        Team t5 = new Team();
        t5.createTeam(TeamNames[4], coachNames[4], team5Players, team5PhyStaff, team5Trainers,3, 9000);
        lnp[4] = t5;

        Team t6 = new Team();
        t6.createTeam(TeamNames[5], coachNames[5], team6Players, team6PhyStaff, team6Trainers,3, 9000);
        lnp[5] = t6;

        for(int i = 0; i < nLeagueTeams; i++) {
            for(int j = 0; j < 2; j++) {
                scr[i][0] = TeamNames[i];
                scr[i][1] = "0";
            }
        }

        this.gamesPlayed = 0;
        this.Score = scr;
        this.league = lnp;
        this.LeagueName = "Pro League";
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.leader_board);
    }
}