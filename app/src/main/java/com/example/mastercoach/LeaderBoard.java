package com.example.mastercoach;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class LeaderBoard extends MainActivity {
    public void showAmateurLeaderBoard() {
        TextView hdr = findViewById(R.id.hdrAmateurLB);
        hdr.setText("AMATEUR LEAGUE");
        hdr.setTextColor(Color.WHITE);
        hdr.setBackgroundColor(Color.BLACK);
        hdr.setTextSize(10);
        hdr.setGravity(View.TEXT_ALIGNMENT_CENTER);

        int nLeagueTeams = a.getnLeagueTeams();

        LinearLayout mainLayout = findViewById(R.id.showAmateurLBLayout);

        League auxL = a.getAmateurLeague();

        for(int i = 0; i < nLeagueTeams; i++) {

            String auxTeamName = auxL.getTeamByPos(i);
            String auxTeamPts = auxL.getPtsByPos(i);

            ImageView auxTeamLogo = new ImageView(this);

            LinearLayout auxTeamLL = new LinearLayout(this);
            RelativeLayout auxTeamRL = new RelativeLayout(this);

            if(auxTeamName.equals("Válega")) {auxTeamLogo.setImageResource(R.mipmap.valegaicon_foreground);}
            if(auxTeamName.equals("Ovarense")) {auxTeamLogo.setImageResource(R.mipmap.ovarenseicon_foreground);}
            if(auxTeamName.equals("Espinho")) {auxTeamLogo.setImageResource(R.mipmap.espinhoicon_foreground);}
            if(auxTeamName.equals("Pardilhó")) {auxTeamLogo.setImageResource(R.mipmap.pardilhoicon_foreground);}
            if(auxTeamName.equals("Esmoriz")) {auxTeamLogo.setImageResource(R.mipmap.esmorizicon_foreground);}
            if(auxTeamName.equals("Paramos")) {auxTeamLogo.setImageResource(R.mipmap.paramosicon_foreground);}
            if(auxTeamName.equals("Torreira")) {auxTeamLogo.setImageResource(R.mipmap.torreiraicon_foreground);}
            if(auxTeamName.equals("Feirense")) {auxTeamLogo.setImageResource(R.mipmap.feirenseicon_foreground);}
            if(auxTeamName.equals("São Joanense")) {auxTeamLogo.setImageResource(R.mipmap.saojoanenseicon_foreground);}
            if(auxTeamName.equals("Seixo")) {auxTeamLogo.setImageResource(R.mipmap.seixoicon_foreground);}
            if(auxTeamName.equals("Candal")) {auxTeamLogo.setImageResource(R.mipmap.candalicon_foreground);}
            if(auxTeamName.equals("Murtosa")) {auxTeamLogo.setImageResource(R.mipmap.murtosaicon_foreground);}
            if(auxTeamName.equals("Rio Tinto")) {auxTeamLogo.setImageResource(R.mipmap.riotintoicon_foreground);}
            if(auxTeamName.equals("Avanca")) {auxTeamLogo.setImageResource(R.mipmap.avancaicon_foreground);}
            if(auxTeamName.equals("Souto")) {auxTeamLogo.setImageResource(R.mipmap.soutoicon_foreground);}
            if(auxTeamName.equals("Pasteleira")) {auxTeamLogo.setImageResource(R.mipmap.pasteleiraicon_foreground);}
            if(auxTeamName.equals("Lordelo")) {auxTeamLogo.setImageResource(R.mipmap.lordeloicon_foreground);}
            if(auxTeamName.equals("Estarreja")) {auxTeamLogo.setImageResource(R.mipmap.estarrejaicon_foreground);}

            TableLayout tl = new TableLayout(this);
            TableRow tr1 = new TableRow(this);
            TableRow tr2 = new TableRow(this);

            TableLayout.LayoutParams tableRowParams=
                    new TableLayout.LayoutParams
                            (TableLayout.LayoutParams.FILL_PARENT ,TableLayout.LayoutParams.WRAP_CONTENT);

            tableRowParams.setMargins(10,1,10,1);

            tr1.setLayoutParams(tableRowParams);
            tr2.setLayoutParams(tableRowParams);

            tl.addView(tr1, tableRowParams);
            tl.addView(tr2, tableRowParams);

            auxTeamLogo.setBackgroundColor(Color.TRANSPARENT);
            tr1.addView(auxTeamLogo);

            TextView tv1 = new TextView(this);
            String tv1Text = auxTeamName+" "+auxTeamPts+" Pts";
            tv1.setText(tv1Text);
            tv1.setTextColor(Color.BLACK);
            tv1.setGravity(Gravity.CENTER);
            tr2.addView(tv1);

            auxTeamRL.addView(tl);
            auxTeamLL.addView(auxTeamRL);
            mainLayout.addView(auxTeamLL);
        }
    }

    public void showSemiProLeaderBoard() {
        int nLeagueTeams = a.getnLeagueTeams();


        TextView hdr = findViewById(R.id.hdrSemiProLB);
        hdr.setText("SEMI PRO LEAGUE");
        hdr.setTextColor(Color.WHITE);
        hdr.setTextSize(10);
        hdr.setBackgroundColor(Color.BLACK);
        hdr.setGravity(View.TEXT_ALIGNMENT_CENTER);

        LinearLayout mainLayout = findViewById(R.id.showSemiProLBLayout);

        League auxL = a.getSemiProLeague();

        for(int i = 0; i < nLeagueTeams; i++) {

            String auxTeamName = auxL.getTeamByPos(i);
            String auxTeamPts = auxL.getPtsByPos(i);

            ImageView auxTeamLogo = new ImageView(this);

            LinearLayout auxTeamLL = new LinearLayout(this);
            RelativeLayout auxTeamRL = new RelativeLayout(this);

            if(auxTeamName.equals("Válega")) {auxTeamLogo.setImageResource(R.mipmap.valegaicon_foreground);}
            if(auxTeamName.equals("Ovarense")) {auxTeamLogo.setImageResource(R.mipmap.ovarenseicon_foreground);}
            if(auxTeamName.equals("Espinho")) {auxTeamLogo.setImageResource(R.mipmap.espinhoicon_foreground);}
            if(auxTeamName.equals("Pardilhó")) {auxTeamLogo.setImageResource(R.mipmap.pardilhoicon_foreground);}
            if(auxTeamName.equals("Esmoriz")) {auxTeamLogo.setImageResource(R.mipmap.esmorizicon_foreground);}
            if(auxTeamName.equals("Paramos")) {auxTeamLogo.setImageResource(R.mipmap.paramosicon_foreground);}
            if(auxTeamName.equals("Torreira")) {auxTeamLogo.setImageResource(R.mipmap.torreiraicon_foreground);}
            if(auxTeamName.equals("Feirense")) {auxTeamLogo.setImageResource(R.mipmap.feirenseicon_foreground);}
            if(auxTeamName.equals("São Joanense")) {auxTeamLogo.setImageResource(R.mipmap.saojoanenseicon_foreground);}
            if(auxTeamName.equals("Seixo")) {auxTeamLogo.setImageResource(R.mipmap.seixoicon_foreground);}
            if(auxTeamName.equals("Candal")) {auxTeamLogo.setImageResource(R.mipmap.candalicon_foreground);}
            if(auxTeamName.equals("Murtosa")) {auxTeamLogo.setImageResource(R.mipmap.murtosaicon_foreground);}
            if(auxTeamName.equals("Rio Tinto")) {auxTeamLogo.setImageResource(R.mipmap.riotintoicon_foreground);}
            if(auxTeamName.equals("Avanca")) {auxTeamLogo.setImageResource(R.mipmap.avancaicon_foreground);}
            if(auxTeamName.equals("Souto")) {auxTeamLogo.setImageResource(R.mipmap.soutoicon_foreground);}
            if(auxTeamName.equals("Pasteleira")) {auxTeamLogo.setImageResource(R.mipmap.pasteleiraicon_foreground);}
            if(auxTeamName.equals("Lordelo")) {auxTeamLogo.setImageResource(R.mipmap.lordeloicon_foreground);}
            if(auxTeamName.equals("Estarreja")) {auxTeamLogo.setImageResource(R.mipmap.estarrejaicon_foreground);}

            TableLayout tl = new TableLayout(this);
            TableRow tr1 = new TableRow(this);
            TableRow tr2 = new TableRow(this);

            TableLayout.LayoutParams tableRowParams=
                    new TableLayout.LayoutParams
                            (TableLayout.LayoutParams.FILL_PARENT ,TableLayout.LayoutParams.WRAP_CONTENT);

            tableRowParams.setMargins(10,1,10,1);

            tr1.setLayoutParams(tableRowParams);
            tr2.setLayoutParams(tableRowParams);

            tl.addView(tr1, tableRowParams);
            tl.addView(tr2, tableRowParams);

            auxTeamLogo.setBackgroundColor(Color.TRANSPARENT);
            tr1.addView(auxTeamLogo);

            TextView tv1 = new TextView(this);
            String tv1Text = auxTeamName+" "+auxTeamPts+" Pts";
            tv1.setText(tv1Text);
            tv1.setTextColor(Color.BLACK);
            tv1.setGravity(Gravity.CENTER);
            tr2.addView(tv1);

            auxTeamRL.addView(tl);
            auxTeamLL.addView(auxTeamRL);
            mainLayout.addView(auxTeamLL);
        }
    }

    public void showProLeaderBoard() {
        int nLeagueTeams = a.getnLeagueTeams();


        TextView hdr = findViewById(R.id.hdrProLB);
        hdr.setText("PRO LEAGUE");
        hdr.setTextColor(Color.WHITE);
        hdr.setBackgroundColor(Color.BLACK);
        hdr.setTextSize(10);
        hdr.setGravity(View.TEXT_ALIGNMENT_CENTER);

        LinearLayout mainLayout = findViewById(R.id.showProLBLayout);

        League auxL = a.getProLeague();

        for(int i = 0; i < nLeagueTeams; i++) {

            String auxTeamName = auxL.getTeamByPos(i);
            String auxTeamPts = auxL.getPtsByPos(i);

            ImageView auxTeamLogo = new ImageView(this);

            LinearLayout auxTeamLL = new LinearLayout(this);
            RelativeLayout auxTeamRL = new RelativeLayout(this);

            if(auxTeamName.equals("Válega")) {auxTeamLogo.setImageResource(R.mipmap.valegaicon_foreground);}
            if(auxTeamName.equals("Ovarense")) {auxTeamLogo.setImageResource(R.mipmap.ovarenseicon_foreground);}
            if(auxTeamName.equals("Espinho")) {auxTeamLogo.setImageResource(R.mipmap.espinhoicon_foreground);}
            if(auxTeamName.equals("Pardilhó")) {auxTeamLogo.setImageResource(R.mipmap.pardilhoicon_foreground);}
            if(auxTeamName.equals("Esmoriz")) {auxTeamLogo.setImageResource(R.mipmap.esmorizicon_foreground);}
            if(auxTeamName.equals("Paramos")) {auxTeamLogo.setImageResource(R.mipmap.paramosicon_foreground);}
            if(auxTeamName.equals("Torreira")) {auxTeamLogo.setImageResource(R.mipmap.torreiraicon_foreground);}
            if(auxTeamName.equals("Feirense")) {auxTeamLogo.setImageResource(R.mipmap.feirenseicon_foreground);}
            if(auxTeamName.equals("São Joanense")) {auxTeamLogo.setImageResource(R.mipmap.saojoanenseicon_foreground);}
            if(auxTeamName.equals("Seixo")) {auxTeamLogo.setImageResource(R.mipmap.seixoicon_foreground);}
            if(auxTeamName.equals("Candal")) {auxTeamLogo.setImageResource(R.mipmap.candalicon_foreground);}
            if(auxTeamName.equals("Murtosa")) {auxTeamLogo.setImageResource(R.mipmap.murtosaicon_foreground);}
            if(auxTeamName.equals("Rio Tinto")) {auxTeamLogo.setImageResource(R.mipmap.riotintoicon_foreground);}
            if(auxTeamName.equals("Avanca")) {auxTeamLogo.setImageResource(R.mipmap.avancaicon_foreground);}
            if(auxTeamName.equals("Souto")) {auxTeamLogo.setImageResource(R.mipmap.soutoicon_foreground);}
            if(auxTeamName.equals("Pasteleira")) {auxTeamLogo.setImageResource(R.mipmap.pasteleiraicon_foreground);}
            if(auxTeamName.equals("Lordelo")) {auxTeamLogo.setImageResource(R.mipmap.lordeloicon_foreground);}
            if(auxTeamName.equals("Estarreja")) {auxTeamLogo.setImageResource(R.mipmap.estarrejaicon_foreground);}

            TableLayout tl = new TableLayout(this);
            TableRow tr1 = new TableRow(this);
            TableRow tr2 = new TableRow(this);

            TableLayout.LayoutParams tableRowParams=
                    new TableLayout.LayoutParams
                            (TableLayout.LayoutParams.FILL_PARENT ,TableLayout.LayoutParams.WRAP_CONTENT);

            tableRowParams.setMargins(10,1,10,1);

            tr1.setLayoutParams(tableRowParams);
            tr2.setLayoutParams(tableRowParams);

            tl.addView(tr1, tableRowParams);
            tl.addView(tr2, tableRowParams);

            auxTeamLogo.setBackgroundColor(Color.TRANSPARENT);
            tr1.addView(auxTeamLogo);

            TextView tv1 = new TextView(this);
            String tv1Text = auxTeamName+" "+auxTeamPts+" Pts";
            tv1.setText(tv1Text);
            tv1.setTextColor(Color.BLACK);
            tv1.setGravity(Gravity.CENTER);
            tr2.addView(tv1);

            auxTeamRL.addView(tl);
            auxTeamLL.addView(auxTeamRL);
            mainLayout.addView(auxTeamLL);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lboard_menus);

        showAmateurLeaderBoard();
        showSemiProLeaderBoard();
        showProLeaderBoard();
    }
}
