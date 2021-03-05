// App Package Import
package com.example.mastercoach;

// Android Imports
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
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

// Java imports
import java.util.List;

public class HomeMenus extends MainActivity
{

    public void showHomeInfo()
    {
        showNextGames();

        TextView aux1 = findViewById(R.id.UserInfoHeader);
        String userClub = a.getUserClub().getTeamName();
        String coachName = a.getUserName();

        aux1.setText(coachName);
        aux1.setTextColor(Color.BLACK);

        TextView aux2 = findViewById(R.id.UserInfoHeader1);
        String aux2Text = userClub+" Manager";
        aux2.setText(aux2Text);

        ImageView myClubIcon = findViewById(R.id.myClubIcon);
        if(userClub.equals("Válega")) {myClubIcon.setImageResource(R.mipmap.valegaicon_foreground);}
        if(userClub.equals("Ovarense")) {myClubIcon.setImageResource(R.mipmap.ovarenseicon_foreground);}
        if(userClub.equals("Espinho")) {myClubIcon.setImageResource(R.mipmap.espinhoicon_foreground);}
        if(userClub.equals("Pardilhó")) {myClubIcon.setImageResource(R.mipmap.pardilhoicon_foreground);}
        if(userClub.equals("Esmoriz")) {myClubIcon.setImageResource(R.mipmap.esmorizicon_foreground);}
        if(userClub.equals("Paramos")) {myClubIcon.setImageResource(R.mipmap.paramosicon_foreground);}
        if(userClub.equals("Torreira")) {myClubIcon.setImageResource(R.mipmap.torreiraicon_foreground);}
        if(userClub.equals("Feirense")) {myClubIcon.setImageResource(R.mipmap.feirenseicon_foreground);}
        if(userClub.equals("São Joanense")) {myClubIcon.setImageResource(R.mipmap.saojoanenseicon_foreground);}
        if(userClub.equals("Seixo")) {myClubIcon.setImageResource(R.mipmap.seixoicon_foreground);}
        if(userClub.equals("Candal")) {myClubIcon.setImageResource(R.mipmap.candalicon_foreground);}
        if(userClub.equals("Murtosa")) {myClubIcon.setImageResource(R.mipmap.murtosaicon_foreground);}
        if(userClub.equals("Rio Tinto")) {myClubIcon.setImageResource(R.mipmap.riotintoicon_foreground);}
        if(userClub.equals("Avanca")) {myClubIcon.setImageResource(R.mipmap.avancaicon_foreground);}
        if(userClub.equals("Souto")) {myClubIcon.setImageResource(R.mipmap.soutoicon_foreground);}
        if(userClub.equals("Pasteleira")) {myClubIcon.setImageResource(R.mipmap.pasteleiraicon_foreground);}
        if(userClub.equals("Lordelo")) {myClubIcon.setImageResource(R.mipmap.lordeloicon_foreground);}
        if(userClub.equals("Estarreja")) {myClubIcon.setImageResource(R.mipmap.estarrejaicon_foreground);}

        myClubIcon.setLayoutParams(new RelativeLayout.LayoutParams(400, 600));
    }

    public void showInbox()
    {
        List<InboxMessage> Inbox = a.getInbox();

        TableLayout tl = findViewById(R.id.InboxTable);
        TableRow tr = new TableRow(this);
        TextView tv = new TextView(this);

        tv.setBackgroundColor(Color.BLACK);
        tv.setWidth(findViewById(R.id.MessagesBoxLayout).getMeasuredWidth());
        tv.setHeight(1);
        tv.setWidth(60000);
        tr.addView(tv);
        tl.addView(tr);

        for(InboxMessage msg: Inbox)
        {

            TableRow tr1 = new TableRow(this);
            TableRow tr2 = new TableRow(this);
            TableRow tr3 = new TableRow(this);

            TextView tv1 = new TextView(this);
            TextView tv2 = new TextView(this);
            TextView tv3 = new TextView(this);

            String msgAuthor = msg.getAuthor();
            String msgSubject = msg.getSubject();

            tv1.setText(msgAuthor);
            tv1.setTextColor(Color.BLACK);
            tv1.setGravity(Gravity.LEFT);
            tr1.addView(tv1);

            tv2.setText(msgSubject);
            tv2.setTextColor(Color.BLACK);
            tv2.setTypeface(tv2.getTypeface(), Typeface.BOLD);
            tv2.setGravity(Gravity.LEFT);
            tr2.addView(tv2);

            tv3.setBackgroundColor(Color.BLACK);
            tv3.setWidth(findViewById(R.id.MessagesBoxLayout).getMeasuredWidth());
            tv3.setHeight(1);
            tv3.setWidth(60000);
            tr3.addView(tv3);

            tl.addView(tr2);
            tl.addView(tr1);
            tl.addView(tr3);

            tr2.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View v)
                {
                    if(msg.getSubject().equals("Job Offer"))
                        openProposal(msg);
                    else
                        openMail(msg);
                }
            });
        }
    }

    public void openMail(InboxMessage msg)
    {
        setContentView(R.layout.mail_viewer);

        String author = msg.getAuthor();
        String subject = msg.getSubject();
        String body = msg.getBody();

        TextView authorTV = findViewById(R.id.msgAuthorTV);
        TextView subjectTV = findViewById(R.id.msgSubjectTV);
        TextView bodyTV = findViewById(R.id.msgBodyTV);

        authorTV.setText(author);
        subjectTV.setText(subject);
        bodyTV.setText(body);

        final Button bttn = findViewById(R.id.goBackFromMailBttn);
        bttn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(HomeMenus.this, HomeMenus.class);
                startActivity(i);
            }
        });
    }

    public void openProposal(InboxMessage msg)
    {
        setContentView(R.layout.mail_viewer);

        String author = msg.getAuthor();
        String subject = msg.getSubject();

        TextView authorTV = findViewById(R.id.msgAuthorTV);
        TextView subjectTV = findViewById(R.id.msgSubjectTV);

        authorTV.setText(author);
        subjectTV.setText(subject);

        showInterestedClubs(msg);

        final Button bttn = findViewById(R.id.goBackFromMailBttn);
        bttn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(HomeMenus.this, HomeMenus.class);
                startActivity(i);
            }
        });


        final Button accept = findViewById(R.id.acceptProposalBttn);
        accept.setVisibility(View.VISIBLE);
        accept.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                confirmClubProposal(msg);
            }
        });

        final Button decline = findViewById(R.id.declineProposalBttn);
        decline.setVisibility(View.VISIBLE);
        decline.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                denyClubProposal(msg);
            }
        });

    }

    public void showHeaderInfo(Team trg)
    {
        LinearLayout prpLL = findViewById(R.id.msgBodyLL);
        String myClubName = trg.getTeamName();
        ImageView myClubIcon = new ImageView(this);

        if(myClubName.equals("Válega")) {myClubIcon.setImageResource(R.mipmap.valegaicon_foreground);}
        if(myClubName.equals("Ovarense")) {myClubIcon.setImageResource(R.mipmap.ovarenseicon_foreground);}
        if(myClubName.equals("Espinho")) {myClubIcon.setImageResource(R.mipmap.espinhoicon_foreground);}
        if(myClubName.equals("Pardilhó")) {myClubIcon.setImageResource(R.mipmap.pardilhoicon_foreground);}
        if(myClubName.equals("Esmoriz")) {myClubIcon.setImageResource(R.mipmap.esmorizicon_foreground);}
        if(myClubName.equals("Paramos")) {myClubIcon.setImageResource(R.mipmap.paramosicon_foreground);}
        if(myClubName.equals("Torreira")) {myClubIcon.setImageResource(R.mipmap.torreiraicon_foreground);}
        if(myClubName.equals("Feirense")) {myClubIcon.setImageResource(R.mipmap.feirenseicon_foreground);}
        if(myClubName.equals("São Joanense")) {myClubIcon.setImageResource(R.mipmap.saojoanenseicon_foreground);}
        if(myClubName.equals("Seixo")) {myClubIcon.setImageResource(R.mipmap.seixoicon_foreground);}
        if(myClubName.equals("Candal")) {myClubIcon.setImageResource(R.mipmap.candalicon_foreground);}
        if(myClubName.equals("Murtosa")) {myClubIcon.setImageResource(R.mipmap.murtosaicon_foreground);}
        if(myClubName.equals("Rio Tinto")) {myClubIcon.setImageResource(R.mipmap.riotintoicon_foreground);}
        if(myClubName.equals("Avanca")) {myClubIcon.setImageResource(R.mipmap.avancaicon_foreground);}
        if(myClubName.equals("Souto")) {myClubIcon.setImageResource(R.mipmap.soutoicon_foreground);}
        if(myClubName.equals("Pasteleira")) {myClubIcon.setImageResource(R.mipmap.pasteleiraicon_foreground);}
        if(myClubName.equals("Lordelo")) {myClubIcon.setImageResource(R.mipmap.lordeloicon_foreground);}
        if(myClubName.equals("Estarreja")) {myClubIcon.setImageResource(R.mipmap.estarrejaicon_foreground);}

        prpLL.addView(myClubIcon);

        TextView iconSep = new TextView(this);
        String sep2 = ""+System.getProperty("line.separator")+""+System.getProperty("line.separator");
        iconSep.setText(sep2);
        prpLL.addView(iconSep);

        TableRow hSubstitutesLU = new TableRow(this);
        TextView tv1 = new TextView(this);
        tv1.setText("Staff");
        tv1.setTextColor(Color.WHITE);
        hSubstitutesLU.addView(tv1);
        hSubstitutesLU.setBackgroundColor(Color.RED);
        prpLL.addView(hSubstitutesLU);

        TableRow trATrainerName = new TableRow(this);
        TextView tvATN = new TextView(this);
        String atkTrainerName = "Attack Trainer : "+trg.getAtkTeamTrainerObj().getTrainerName();
        tvATN.setText(atkTrainerName);
        tvATN.setTextColor(Color.BLACK);
        tvATN.setTextSize(12);
        trATrainerName.addView(tvATN);
        prpLL.addView(trATrainerName);

        TableRow trDTrainerName = new TableRow(this);
        TextView tvDTN = new TextView(this);
        String defTrainerName = "Deffensive Trainer : "+trg.getDefTeamTrainerObj().getTrainerName();
        tvDTN.setText(defTrainerName);
        tvDTN.setTextColor(Color.BLACK);
        tvDTN.setTextSize(12);
        trDTrainerName.addView(tvDTN);
        prpLL.addView(trDTrainerName);

        TableRow trDoctorName = new TableRow(this);
        TextView tvDN = new TextView(this);
        String doctorName = "Doctor Name : "+trg.getDoc().getDocName();
        tvDN.setText(doctorName);
        tvDN.setTextColor(Color.BLACK);
        tvDN.setTextSize(12);
        trDoctorName.addView(tvDN);
        prpLL.addView(trDoctorName);

        TableRow trLeagueName = new TableRow(this);
        TextView tvLeagueName = new TextView(this);
        String LeagueName = "League Name : "+trg.getTeamLeagueName();
        tvLeagueName.setText(LeagueName);
        tvLeagueName.setTextColor(Color.BLACK);
        tvLeagueName.setTextSize(12);
        trLeagueName.addView(tvLeagueName);
        prpLL.addView(trLeagueName);


    }

    public void showInterestedClubInfo(Team trg, InboxMessage msg) {
        showHeaderInfo(trg);

        Team.Player[] allPlayers = trg.getPlayers();
        LinearLayout mainLL = findViewById(R.id.msgBodyLL);

        TextView roosterSeparator = new TextView(this);
        String sep = ""+System.getProperty("line.separator")+""+System.getProperty("line.separator");
        roosterSeparator.setText(sep);
        mainLL.addView(roosterSeparator);

        TableLayout substitutesLineUpTable = new TableLayout(this);
        TableRow hSubstitutesLU = new TableRow(this);
        TextView tv1 = new TextView(this);
        tv1.setText("Rooster");
        tv1.setTextColor(Color.WHITE);
        hSubstitutesLU.addView(tv1);
        hSubstitutesLU.setBackgroundColor(Color.RED);
        substitutesLineUpTable.addView(hSubstitutesLU);


        for(Team.Player aux: allPlayers)
        {
            String p1Name = aux.getPlayerName();

            int OA_pts = (int)aux.getOverAllPoints();

            String p2Name = "";

            String body = aux.getPlayerPos() + " " + p1Name + " " + OA_pts + "'";

            TableRow tr = new TableRow(this);

            TextView tvPlayer = new TextView(this);
            tvPlayer.setText(body);
            tvPlayer.setTextColor(Color.BLACK);
            tvPlayer.setTextSize(14);
            tr.addView(tvPlayer);
            substitutesLineUpTable.addView(tr);
        }

        mainLL.addView(substitutesLineUpTable);

        String bottomSeperator = ""+System.getProperty("line.separator")+""+System.getProperty("line.separator");
        TextView sep1 = new TextView(this);
        sep1.setText(bottomSeperator);
        mainLL.addView(sep1);

        TableLayout.LayoutParams tableRowParams=
                new TableLayout.LayoutParams
                        (TableLayout.LayoutParams.MATCH_PARENT ,TableLayout.LayoutParams.WRAP_CONTENT);


        tableRowParams.setMargins(10,10,10,10);
    }

    public void confirmClubProposal(InboxMessage msg)
    {
        a.assignUserNewTeam(msg.getInterestedClub());
        a.removeMailAtIndex(msg.getMailIndex());
        a.resetInboxToNewClub();

        Intent i = new Intent(this, HomeMenus.class);
        startActivity(i);
    }

    public void denyClubProposal(InboxMessage msg)
    {
        a.removeMailAtIndex(msg.getMailIndex());
        a.currentMailIndex--;

        Intent i = new Intent(this, HomeMenus.class);
        startActivity(i);
    }

    public void showInterestedClubs(InboxMessage msg)
    {
        Team trg = msg.getInterestedClub();

        if(trg != null)
        {
            LinearLayout proposalL = findViewById(R.id.msgBodyLL);

            String body = "";

            if(a.getUserLeague().getLeagueName().equals(a.getAmateurLeague().getLeagueName()))
            {
                body = "Hello " + a.getUserName() + "!" + System.getProperty("line.separator") + "" + System.getProperty("line.separator");
                body += "Im the president of " + trg.getTeamName() + ", and i want to show my interest in your services. ";
                body += "some may say your results are not enough proff but i believe in your potencial as a coach. Hope to ear a anwser from you soon!" + System.getProperty("line.separator");
                body += "You have all the info. you need about the club bellow." + System.getProperty("line.separator") + "" + System.getProperty("line.separator") + "" + System.getProperty("line.separator");
                body += "Best regards," + System.getProperty("line.separator");
                body += "President of " + trg.getTeamName() + "." + "" + System.getProperty("line.separator") + "" + System.getProperty("line.separator");
            }

            if(a.getUserLeague().getLeagueName().equals(a.getSemiProLeague().getLeagueName()) || a.getUserLeague().getLeagueName().equals(a.getProLeague().getLeagueName()))
            {
                body = "Hello " + a.getUserName() + "!" + System.getProperty("line.separator") + "" + System.getProperty("line.separator");
                body += "Im the president of " + trg.getTeamName() + ", and i want to show my interest in your services. ";
                body += "The fact that you managed to get this far in such short time is really amazing, all the great clubs talk about your capacities as a coach and as a president of this club i allways want the best. Hope to ear a anwser from you soon!" + System.getProperty("line.separator");
                body += "You have all the info. you need about the club bellow." + System.getProperty("line.separator") + "" + System.getProperty("line.separator") + "" + System.getProperty("line.separator");
                body += "Best regards," + System.getProperty("line.separator");
                body += "President of " + trg.getTeamName() + "." + "" + System.getProperty("line.separator") + "" + System.getProperty("line.separator");
            }


            TextView tvProposal = new TextView(this);
            tvProposal.setText(body);
            tvProposal.setTextColor(Color.BLACK);
            proposalL.addView(tvProposal);

            showInterestedClubInfo(trg, msg);
        }
    }

    public void showNextGames()
    {
        Team myClub = a.getUserClub();

        String [] calend = myClub.getCalendar();

        String [] res = a.getMyClubResults();

        LinearLayout mainLinearLayout = findViewById(R.id.showNextGamesLayout);

        int  ind_games = 0;
        for(String adv: calend)
        {
            ImageView advIcon = new ImageView(this);

            LinearLayout nextGamesLL = new LinearLayout(this);
            RelativeLayout nextGamesRL = new RelativeLayout(this);


            if(adv.equals("Válega")) {advIcon.setImageResource(R.mipmap.valegaicon_foreground);}
            if(adv.equals("Ovarense")) {advIcon.setImageResource(R.mipmap.ovarenseicon_foreground);}
            if(adv.equals("Espinho")) {advIcon.setImageResource(R.mipmap.espinhoicon_foreground);}
            if(adv.equals("Pardilhó")) {advIcon.setImageResource(R.mipmap.pardilhoicon_foreground);}
            if(adv.equals("Esmoriz")) {advIcon.setImageResource(R.mipmap.esmorizicon_foreground);}
            if(adv.equals("Paramos")) {advIcon.setImageResource(R.mipmap.paramosicon_foreground);}
            if(adv.equals("Torreira")) {advIcon.setImageResource(R.mipmap.torreiraicon_foreground);}
            if(adv.equals("Feirense")) {advIcon.setImageResource(R.mipmap.feirenseicon_foreground);}
            if(adv.equals("São Joanense")) {advIcon.setImageResource(R.mipmap.saojoanenseicon_foreground);}
            if(adv.equals("Seixo")) {advIcon.setImageResource(R.mipmap.seixoicon_foreground);}
            if(adv.equals("Candal")) {advIcon.setImageResource(R.mipmap.candalicon_foreground);}
            if(adv.equals("Murtosa")) {advIcon.setImageResource(R.mipmap.murtosaicon_foreground);}
            if(adv.equals("Rio Tinto")) {advIcon.setImageResource(R.mipmap.riotintoicon_foreground);}
            if(adv.equals("Avanca")) {advIcon.setImageResource(R.mipmap.avancaicon_foreground);}
            if(adv.equals("Souto")) {advIcon.setImageResource(R.mipmap.soutoicon_foreground);}
            if(adv.equals("Pasteleira")) {advIcon.setImageResource(R.mipmap.pasteleiraicon_foreground);}
            if(adv.equals("Lordelo")) {advIcon.setImageResource(R.mipmap.lordeloicon_foreground);}
            if(adv.equals("Estarreja")) {advIcon.setImageResource(R.mipmap.estarrejaicon_foreground);}

            TableLayout tl = new TableLayout(this);
            TableRow tr1 = new TableRow(this);
            TableRow tr2 = new TableRow(this);

            TableLayout.LayoutParams tableRowParams=
                    new TableLayout.LayoutParams
                            (TableLayout.LayoutParams.FILL_PARENT ,TableLayout.LayoutParams.WRAP_CONTENT);

            tableRowParams.setMargins(10,6,10,6);

            tr1.setLayoutParams(tableRowParams);
            tr2.setLayoutParams(tableRowParams);

            tl.addView(tr1, tableRowParams);
            tl.addView(tr2, tableRowParams);

            advIcon.setBackgroundColor(Color.TRANSPARENT);
            tr1.addView(advIcon);

            TextView tv1 = new TextView(this);

            String iRes = res[ind_games];
            String tv1Text = iRes+" "+adv;
            ind_games++;

            tv1.setText(tv1Text);
            tv1.setTextColor(Color.BLACK);
            tv1.setGravity(Gravity.CENTER);
            tr2.addView(tv1);

            nextGamesRL.addView(tl);
            //nextGamesRL.addView(tr2);


            nextGamesLL.addView(nextGamesRL);
            mainLinearLayout.addView(nextGamesLL);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.home_window);

        showHomeInfo();
        showInbox();

        final Button goLBoard = findViewById(R.id.goRankings);
        goLBoard.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent i1 = new Intent(HomeMenus.this, LeaderBoard.class);
                startActivity(i1);
            }
        });


        final Button goTMTeam = findViewById(R.id.goToMyTeam);
        goTMTeam.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent i3 = new Intent(HomeMenus.this, MyClub.class);
                startActivity(i3);
            }
        });


        final Button goTMarket = findViewById(R.id.goToMarket);
        goTMarket.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent i2 = new Intent(HomeMenus.this, Market.class);
                startActivity(i2);
            }
        });


        final Button playGame = findViewById(R.id.goToNextGame);
        playGame.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent i4 = new Intent(HomeMenus.this, Game.class);
                startActivity(i4);
            }
        });
    }
}