package com.example.mastercoach;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.Image;
import android.os.Bundle;
import android.text.PrecomputedText;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.LinkedList;
import java.util.List;

public class Market extends MainActivity {
    private static String searchName;
    private static String searchPosition;
    private static int lowerLimitAge, upperLimitAge;
    private static int lowerLimitOvrll, upperLimitOvrll;
    private static double lowerLimitPrice, upperLimitPrice;

    private static List<Team.Player> searchList;

    private void setSearchResults() {
        RadioGroup searchPositionRG = findViewById(R.id.positionSelectRG);
        RadioButton selectedRB = findViewById(searchPositionRG.getCheckedRadioButtonId());

        EditText searchNameET = findViewById(R.id.playerSearchET);

        EditText lowerLimitAgeET = findViewById(R.id.lowerLimitAgeET);
        EditText upperLimitAgeET = findViewById(R.id.upperAgeLimitET);

        EditText lowerLimitPriceET = findViewById(R.id.lowerLimitPriceET);
        EditText upperLimitPriceET = findViewById(R.id.upperPriceLimitET);

        EditText lowerLimitOvrllET = findViewById(R.id.lowerLimitOvrllET);
        EditText upperLimitOvrllET = findViewById(R.id.upperOvrllLimitET);

        searchName = searchNameET.getText().toString();

        searchPosition = selectedRB.getText().toString();

        lowerLimitAge = Integer.parseInt(lowerLimitAgeET.getText().toString());
        upperLimitAge = Integer.parseInt(upperLimitAgeET.getText().toString());

        lowerLimitPrice = Double.parseDouble(lowerLimitPriceET.getText().toString());
        upperLimitPrice = Double.parseDouble(upperLimitPriceET.getText().toString());

        lowerLimitOvrll = Integer.parseInt(lowerLimitOvrllET.getText().toString());
        upperLimitOvrll = Integer.parseInt(upperLimitOvrllET.getText().toString());
    }

    private void showMarketResultsLayout()
    {
        setContentView(R.layout.market_results);

        TextView bankTV = findViewById(R.id.MarketSearchBankTV);

        double teamBudget = a.getUserClub().getTeamBudget();
        double formatDouble = Math.floor(teamBudget * 100) / 100;

        String bankTXT = String.valueOf(formatDouble) + " $";

        bankTV.setText(bankTXT);

        final Button goHome = findViewById(R.id.goBackFromMarketSearch);
        goHome.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent i = new Intent(Market.this, HomeMenus.class);
                startActivity(i);
            }
        });
    }

    private void showSearchList()
    {
        showMarketResultsLayout();

        for(Team.Player p : searchList)
        {
            showSearchResult(p);
        }

        searchList.clear();
    }

    private void showSearchResult(Team.Player Player)
    {
        LinearLayout ll = findViewById(R.id.searchResultsLL);

        LinearLayout rl = new LinearLayout(this);

        TableLayout tl = new TableLayout(this);

        LinearLayout.LayoutParams detailedInfoParams =
                new LinearLayout.LayoutParams
                        (500 ,400);

        detailedInfoParams.setMargins(100,0,0,0);

        LinearLayout.LayoutParams TableParams =
                new TableLayout.LayoutParams
                        (LinearLayout.LayoutParams.MATCH_PARENT ,500);

        TableParams.setMargins(30,30,0,1);

        TableLayout.LayoutParams RowParams =
                new TableLayout.LayoutParams
                        (200 ,400);

        RowParams.setMargins(0,10,0,0);

        TableRow trIcon = new TableRow(this);
        TableRow trPlayerPos = new TableRow(this);
        TableRow trPlayerOvrll = new TableRow(this);

        ImageView PlayerIcon = new ImageView(this);

        PlayerIcon.setImageResource(R.mipmap.field_template_foreground);
        PlayerIcon.setLayoutParams(new TableRow.LayoutParams(150, 150));

        TextView PlayerPosTV = new TextView(this);
        TextView PlayerOvrllTV = new TextView(this);

        String PlayerFullName = " "+ Player.getPlayerName();
        String PlayerOA = String.valueOf((int)Player.getOverAllPoints()) + "''";
        String PlayerPosTXT = Player.getPlayerPos();

        rl.setLayoutParams(TableParams);

        tl.addView(trIcon);
        tl.addView(trPlayerPos);
        tl.addView(trPlayerOvrll);

        PlayerOvrllTV.setText(PlayerOA);
        PlayerOvrllTV.setTextColor(Color.BLACK);
        PlayerOvrllTV.setTypeface(null, Typeface.BOLD);
        PlayerOvrllTV.setGravity(Gravity.CENTER);
        PlayerOvrllTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);

        PlayerPosTV.setText(PlayerPosTXT);
        PlayerPosTV.setTextColor(Color.RED);
        PlayerPosTV.setTypeface(null, Typeface.BOLD);
        PlayerPosTV.setGravity(Gravity.CENTER);
        PlayerPosTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);

        trIcon.addView(PlayerIcon);
        trPlayerPos.addView(PlayerPosTV);
        trPlayerOvrll.addView(PlayerOvrllTV);

        TableLayout playerDetailedInfoTL = new TableLayout(this);

        playerDetailedInfoTL.setLayoutParams(detailedInfoParams);

        TableRow PlayerNameTR = new TableRow(this);

        TextView PlayerNameHdrTV = new TextView(this);

        PlayerNameHdrTV.setText("NAME");
        PlayerNameHdrTV.setTextColor(Color.WHITE);
        PlayerNameHdrTV.setBackgroundColor(Color.BLACK);
        PlayerNameHdrTV.setTypeface(null, Typeface.BOLD);
        PlayerNameHdrTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);

        TextView PlayerFullNameTV = new TextView(this);

        PlayerFullNameTV.setText(PlayerFullName);
        PlayerFullNameTV.setTextColor(Color.BLACK);
        PlayerFullNameTV.setBackgroundColor(Color.WHITE);
        PlayerFullNameTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);

        PlayerNameTR.setLayoutParams(new TableLayout.LayoutParams(500, TableLayout.LayoutParams.WRAP_CONTENT));
        PlayerNameTR.addView(PlayerNameHdrTV);
        PlayerNameTR.addView(PlayerFullNameTV);

        TableRow PlayerAgeTR = new TableRow(this);

        TextView PlayerAgeHdrTV = new TextView(this);

        PlayerAgeHdrTV.setText("AGE");
        PlayerAgeHdrTV.setTextColor(Color.WHITE);
        PlayerAgeHdrTV.setBackgroundColor(Color.BLACK);
        PlayerAgeHdrTV.setTypeface(null, Typeface.BOLD);
        PlayerAgeHdrTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);

        TextView PlayerAgeTV = new TextView(this);

        String playerAgeTXT = " " + String.valueOf(Player.getPlayerAge());
        PlayerAgeTV.setText(playerAgeTXT);
        PlayerAgeTV.setTextColor(Color.BLACK);
        PlayerAgeTV.setBackgroundColor(Color.WHITE);
        PlayerAgeTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);

        PlayerAgeTR.addView(PlayerAgeHdrTV);
        PlayerAgeTR.addView(PlayerAgeTV);

        TableRow PlayerPriceTR = new TableRow(this);

        TextView PlayerPriceHdrTV = new TextView(this);

        PlayerPriceHdrTV.setText("PRICE");
        PlayerPriceHdrTV.setTextColor(Color.WHITE);
        PlayerPriceHdrTV.setBackgroundColor(Color.BLACK);
        PlayerPriceHdrTV.setTypeface(null, Typeface.BOLD);
        PlayerPriceHdrTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);

        TextView PlayerPriceTV = new TextView(this);

        double formatDouble = Math.floor(Player.getPlayerClause() * 100) / 100;
        String playerPriceTXT = " " + String.valueOf(formatDouble) + " $";
        PlayerPriceTV.setText(playerPriceTXT);
        PlayerPriceTV.setTextColor(Color.BLACK);
        PlayerPriceTV.setBackgroundColor(Color.WHITE);
        PlayerPriceTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);

        PlayerPriceTR.addView(PlayerPriceHdrTV);
        PlayerPriceTR.addView(PlayerPriceTV);

        TableRow PlayerClubTR = new TableRow(this);

        TextView PlayerClubHdrTV = new TextView(this);

        PlayerClubHdrTV.setText("CLUB");
        PlayerClubHdrTV.setTextColor(Color.WHITE);
        PlayerClubHdrTV.setBackgroundColor(Color.BLACK);
        PlayerClubHdrTV.setTypeface(null, Typeface.BOLD);
        PlayerClubHdrTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        TextView PlayerClubTV = new TextView(this);

        String playerClubTXT = " " + Player.getPlayerClub();
        PlayerClubTV.setText(playerClubTXT);
        PlayerClubTV.setTextColor(Color.BLACK);
        PlayerClubTV.setBackgroundColor(Color.WHITE);
        PlayerClubTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);

        PlayerClubTR.addView(PlayerClubHdrTV);
        PlayerClubTR.addView(PlayerClubTV);

        playerDetailedInfoTL.addView(PlayerNameTR, RowParams);
        playerDetailedInfoTL.addView(PlayerAgeTR, RowParams);
        playerDetailedInfoTL.addView(PlayerPriceTR, RowParams);
        playerDetailedInfoTL.addView(PlayerClubTR, RowParams);

        ImageView buyIcon = new ImageView(this);

        buyIcon.setImageResource(R.mipmap.buy_icon_foreground);

        rl.addView(tl);
        rl.addView(playerDetailedInfoTL, detailedInfoParams);

        LinearLayout buyLL = new LinearLayout(this);

        buyIcon.setLayoutParams(new LinearLayout.LayoutParams(150,250));

        buyLL.addView(buyIcon);

        buyLL.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                confirmTransaction(Player);
            }
        });

        rl.addView(buyLL);

        ll.addView(rl, TableParams);
    }

    private void confirmTransaction(Team.Player player)
    {
        Team myClub = a.getUserClub();
        Team trgClub = a.searchForTeamInGame(player.getPlayerClub());

        String myClubName = a.getUserClub().getTeamName();
        String trgClubName = player.getPlayerClub();

        double trgPrice = player.getPlayerClause();
        double myBudget = a.getUserClub().getTeamBudget();

        if(trgPrice <= myBudget && !(myClubName.equals(trgClubName)))
        {
            if(trgClub.getNumberOfPlayers() >= 9) {
                myClub.transferPlayer(player);

                trgClub.removePlayerFromTeam(player.getPlayerName());
                trgClub.increaseTeamBudget(player.getPlayerClause());

                player.changeClubForPlayer(myClub.getTeamName());

                myClub.decreaseTeamBudget(player.getPlayerClause());

                Intent i = new Intent(Market.this, HomeMenus.class);
                startActivity(i);
            }
            else
            {
                Toast.makeText(getApplicationContext(), "This Team Cant Sell More Players", Toast.LENGTH_SHORT).show();
            }
        }

        else
        {
            Toast.makeText(getApplicationContext(), "You're short on money!", Toast.LENGTH_SHORT).show();
        }
    }

    private void getCriteriaSearchResults()
    {
        searchList = new LinkedList<Team.Player>();

        League amateurLeague = a.getAmateurLeague();
        League semiProLeague = a.getSemiProLeague();
        League proLeague = a.getProLeague();

        boolean found = false;

        for(int i = 0; i < 5; ++i)
        {
            Team t = amateurLeague.getObjectTeamIndex(i);

            for(Team.Player p : t.getPlayers())
            {
                if(checkSearchCriterea(p))
                {
                    searchList.add(p);

                    found = true;
                }
            }
        }

        for(int i = 0; i < 5; ++i)
        {
            Team t = semiProLeague.getObjectTeamIndex(i);

            for(Team.Player p : t.getPlayers())
            {
                if(checkSearchCriterea(p))
                {
                    searchList.add(p);

                    found = true;
                }
            }
        }

        for(int i = 0; i < 5; ++i)
        {
            Team t = proLeague.getObjectTeamIndex(i);

            for(Team.Player p : t.getPlayers())
            {
                if(checkSearchCriterea(p))
                {
                    searchList.add(p);

                    found = true;
                }
            }
        }

        if(!found)
        {
            Toast.makeText(Market.this, "No player found", Toast.LENGTH_SHORT).show();
        }

        else
        {
            showSearchList();
        }
    }

    private void getNameSearchResults()
    {
        searchList = new LinkedList<Team.Player>();

        League amateurLeague = a.getAmateurLeague();
        League semiProLeague = a.getSemiProLeague();
        League proLeague = a.getProLeague();

        boolean found = false;

        TextView searchNameED = findViewById(R.id.playerSearchET);

        String searchName = searchNameED.getText().toString().toLowerCase();

        for(int i = 0; i < 5; ++i)
        {
            Team t = amateurLeague.getObjectTeamIndex(i);

            for(Team.Player p : t.getPlayers())
            {
                String playerName = p.getPlayerName().toLowerCase();

                if(playerName.contains(searchName))
                {
                    searchList.add(p);

                    found = true;
                }
            }
        }

        for(int i = 0; i < 5; ++i)
        {
            Team t = semiProLeague.getObjectTeamIndex(i);

            for(Team.Player p : t.getPlayers())
            {
                String playerName = p.getPlayerName().toLowerCase();

                if(playerName.contains(searchName))
                {
                    searchList.add(p);

                    found = true;
                }
            }
        }

        for(int i = 0; i < 5; ++i)
        {
            Team t = proLeague.getObjectTeamIndex(i);

            for(Team.Player p : t.getPlayers())
            {
                String playerName = p.getPlayerName().toLowerCase();

                if(playerName.contains(searchName))
                {
                    searchList.add(p);

                    found = true;
                }
            }
        }

        if(!found)
        {
            Toast.makeText(Market.this, "No player found", Toast.LENGTH_SHORT).show();
        }

        else
        {
            showSearchList();
        }
    }

    private boolean checkSearchCriterea(Team.Player player)
    {
        String playerPos = player.getPlayerPos();
        if(!playerPos.equals(searchPosition))  return false;

        int playerAge = player.getPlayerAge();
        if(playerAge < lowerLimitAge || playerAge > upperLimitAge)  return false;

        double playerPrice = player.getPlayerClause();
        if(playerPrice < lowerLimitPrice || playerPrice > upperLimitPrice) return false;

        double playerOvrll = player.getOverAllPoints();
        if(playerOvrll < lowerLimitOvrll || playerOvrll > upperLimitOvrll) return false;

        return true;
    }

    private void setMarketLayout()
    {
        setContentView(R.layout.market);

        TextView bankTV = findViewById(R.id.HeaderMarketTV);

        double teamBudget = a.getUserClub().getTeamBudget();
        double formatDouble = Math.floor(teamBudget * 100) / 100;

        String bankTXT = String.valueOf(formatDouble) + " $";

        bankTV.setText(bankTXT);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setMarketLayout();

        final Button applySearchBttn = findViewById(R.id.applySearchBttn);
        applySearchBttn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                setSearchResults();
                getCriteriaSearchResults();
            }
        });

        final ImageView searchNameBttn = findViewById(R.id.searchNameIV);
        searchNameBttn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                getNameSearchResults();
            }
        });
    }

    @Override
    protected void onRestart()
    {
        super.onRestart();
    }

    @Override
    protected void onStart(Bundle savedInstanceState)
    {
        super.onStart(savedInstanceState);
    }
}
