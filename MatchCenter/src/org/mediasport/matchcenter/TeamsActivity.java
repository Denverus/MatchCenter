package org.mediasport.matchcenter;

import java.util.ArrayList;

import org.mediasport.matchcenter.docs.Team;
import org.mediasport.matchcenter.engine.DataStore;
import org.mediasport.matchcenter.engine.Factory;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class TeamsActivity extends Activity {

	int teamIndex = 1;
	
	private ListView listViewTeams;
	private ArrayList<String> teamsArray;	
	private ArrayAdapter<String> teamsAdapter;
	
	private DataStore context = null; 
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		context = Factory.getDataStore();
		
		if (context == null) {
			// Error
		}
			
		setContentView(R.layout.activity_teams);
		setUpView();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_teams, menu);
		return true;
	}
	
	private void setUpView() {
     listViewTeams = (ListView)this.findViewById(R.id.listViewTeams);

     DataStore data = Factory.getDataStore();
     
     teamsArray = new ArrayList<String>();
     teamsArray.clear();

     teamsAdapter  = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,teamsArray);
     
     for (Team team : context.getTeamList()) {
    	 teamsArray.add(team.getName());
	}
     
     listViewTeams.setAdapter(teamsAdapter);
     teamsAdapter.notifyDataSetChanged();
	}

    public void buttonAddTeam_click(View v)
    {
    	String name = "Команда"+teamIndex;
    	
    	teamsArray.add(name);
    	context.addTeam(name, name);
    	teamIndex++;

    	teamsAdapter.notifyDataSetChanged();    
    }    	
}
