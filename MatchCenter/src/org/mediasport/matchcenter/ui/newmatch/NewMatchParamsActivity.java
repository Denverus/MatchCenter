package org.mediasport.matchcenter.ui.newmatch;

import org.mediasport.matchcenter.R;
import org.mediasport.matchcenter.R.id;
import org.mediasport.matchcenter.R.layout;
import org.mediasport.matchcenter.R.menu;
import org.mediasport.matchcenter.docs.Match;
import org.mediasport.matchcenter.docs.Team;
import org.mediasport.matchcenter.engine.DataStore;
import org.mediasport.matchcenter.engine.Factory;
import org.mediasport.matchcenter.engine.MatchEngine;
import org.mediasport.matchcenter.ui.matchscreen.MatchActivity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class NewMatchParamsActivity extends Activity {
	
	protected NewMatchParamsActivity activity = this; 

	protected Button buttonHomeTeam;	
	
	protected Button buttonAwayTeam;
	
	protected Button buttonBegin;
	
	protected boolean homeTeamChoosed = false;
	protected boolean awayTeamChoosed = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_match_params);

		buttonBegin = (Button) findViewById(R.id.buttonBeginMatch);
		buttonBegin.setEnabled(false);
		buttonBegin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	MatchEngine engine = Factory.getMatchEngine();
            	DataStore data = Factory.getDataStore();
            	
            	Team homeTeam =  data.findTeamByName(buttonHomeTeam.getText().toString());
            	Team awayTeam =  data.findTeamByName(buttonAwayTeam.getText().toString());
            	
            	engine.createMatch(homeTeam, awayTeam);
            	
            	Match match = engine.getMatch();
            	
            	match.setHomePlayerList(data.getPlayerListByTeam(homeTeam));
            	match.setAwayPlayerList(data.getPlayerListByTeam(awayTeam));
            	
            	Intent myIntent = new Intent(activity, MatchActivity.class);
             	startActivity(myIntent);
            }
        });        

		
        buttonHomeTeam = (Button) findViewById(R.id.buttonChooseHomeTeam);
        
        buttonHomeTeam.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
		    	TeamSelectionDialog teamSelectionDlg = new TeamSelectionDialog();
		    	teamSelectionDlg.show(getFragmentManager(), "MyDialog");
		    	teamSelectionDlg.setParamActivity(activity, true);
			}
		});
		
        buttonAwayTeam = (Button) findViewById(R.id.buttonChooseAwayTeam);
        
        buttonAwayTeam.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
		    	TeamSelectionDialog teamSelectionDlg = new TeamSelectionDialog();
		    	teamSelectionDlg.show(getFragmentManager(), "MyDialog");
		    	teamSelectionDlg.setParamActivity(activity, false);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_match_params, menu);
		return true;
	}

	public void setHomeTeam(String selectedTeam) {
		buttonHomeTeam.setText(selectedTeam);
		homeTeamChoosed = true;
		UpdateBeginButtonState();
	}

	public void setAwayTeam(String selectedTeam) {
		buttonAwayTeam.setText(selectedTeam);
		awayTeamChoosed = true;
		UpdateBeginButtonState();
	}

	private void UpdateBeginButtonState() {
		if (homeTeamChoosed && awayTeamChoosed) {
			buttonBegin.setEnabled(true);
		}
	}

}
