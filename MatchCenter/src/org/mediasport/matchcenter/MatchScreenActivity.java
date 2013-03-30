package org.mediasport.matchcenter;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.mediasport.matchcenter.docs.Match;
import org.mediasport.matchcenter.docs.Player;
import org.mediasport.matchcenter.engine.DataStore;
import org.mediasport.matchcenter.engine.Factory;
import org.mediasport.matchcenter.engine.MatchEngine;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

public class MatchScreenActivity extends Activity {

	private TextView homeTeamTextVeiw = null;
    private TextView awayTeamTextVeiw = null;
    private TextView scoreTextVeiw = null;
    private ListView homePlayersListView = null;
    private ListView awayPlayersListView = null;
    private ImageButton startClockButton = null;
	private TextView clockTextView;
	
	private boolean isTimerOn = false;

	private long timerStep = 0; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_match_screen);
		
        homeTeamTextVeiw = (TextView) findViewById(R.id.textViewHomeTeamName);
        awayTeamTextVeiw = (TextView) findViewById(R.id.textViewAwayTeamName);
        scoreTextVeiw = (TextView) findViewById(R.id.textViewScore);
        
        homePlayersListView = (ListView) findViewById(R.id.listViewHomePlayers);
        awayPlayersListView = (ListView) findViewById(R.id.listViewAwayPlayers);
		
        clockTextView = (TextView) findViewById(R.id.textViewClock);
        startClockButton = (ImageButton) findViewById(R.id.imageButtonClock);
        
		final Timer myTimer = new Timer(); 
		final Handler uiHandler = new Handler();

		myTimer.schedule(new TimerTask() { 
		    @Override
		    public void run() {
            	final MatchEngine engine = Factory.getMatchEngine();
            	engine.addTime(timerStep);
		    	
		        uiHandler.post(new Runnable() {
		            @Override
		            public void run() {
		            	clockTextView.setText(engine.getStringTime());
		            }
		        });
		    };
		}, 0L, 1000);		
		
        startClockButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (isTimerOn) {
					timerStep = 1000;
				} else {
					timerStep = 0;
				}
				
				isTimerOn = !isTimerOn;
			}
		});
        
		loadData();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.match_screen, menu);
		return true;
	}

	
	protected void loadData() {
    	DataStore data = Factory.getDataStore();
    	MatchEngine engine = Factory.getMatchEngine();
    	
    	Match match = engine.getMatch();
    	String homeTeam = match.getHomeTeam().getShortName();
    	String awayTeam = match.getAwayTeam().getShortName();
		
		setTitle("");
		
		homeTeamTextVeiw.setText(homeTeam);
		awayTeamTextVeiw.setText(awayTeam);
		
		List<Player> homePlayerList = data.getPlayerListByTeam(match.getHomeTeam());
		List<Player> awayPlayerList = data.getPlayerListByTeam(match.getAwayTeam());
		
		ArrayAdapter<Player> homePlayersAdapter = new ArrayAdapter<Player>(this,
				  android.R.layout.simple_list_item_1, android.R.id.text1, homePlayerList);
		ArrayAdapter<Player> awayPlayersAdapter = new ArrayAdapter<Player>(this,
				  android.R.layout.simple_list_item_1, android.R.id.text1, awayPlayerList);
		
		MatchPlayerListAdapter homePlayerListAdapter = new MatchPlayerListAdapter(this, homePlayerList, R.layout.players_home_in_match_listview);
		MatchPlayerListAdapter awayPlayerListAdapter = new MatchPlayerListAdapter(this, awayPlayerList, R.layout.players_away_in_match_listview);

		homePlayersListView.setAdapter(homePlayerListAdapter);
		awayPlayersListView.setAdapter(awayPlayerListAdapter);
	}
}
