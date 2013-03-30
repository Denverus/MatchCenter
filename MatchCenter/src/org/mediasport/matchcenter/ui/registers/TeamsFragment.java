package org.mediasport.matchcenter.ui.registers;

import java.util.ArrayList;
import java.util.List;

import org.mediasport.matchcenter.R;
import org.mediasport.matchcenter.R.id;
import org.mediasport.matchcenter.R.layout;
import org.mediasport.matchcenter.R.menu;
import org.mediasport.matchcenter.docs.Match;
import org.mediasport.matchcenter.docs.Player;
import org.mediasport.matchcenter.docs.Team;
import org.mediasport.matchcenter.engine.DataStore;
import org.mediasport.matchcenter.engine.Factory;
import org.mediasport.matchcenter.engine.MatchEngine;
import org.mediasport.matchcenter.ui.matchscreen.MatchPlayerListAdapter;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class TeamsFragment extends Fragment {
	int teamIndex = 1;
	
	private ListView listViewTeams;
	private List<Team> teamsList  = new ArrayList<Team>();
	
	private TeamRegisterListAdapter teamsAdapter;
	
	private DataStore data = Factory.getDataStore();
	private MatchEngine engine = Factory.getMatchEngine();
	
	private Button addButton;

	 @Override
	 public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
        if (container == null) {
            return null;
        }
        
        View v = inflater.inflate(R.layout.activity_teams, null);

	     listViewTeams = (ListView) v.findViewById(R.id.listViewTeams);

	     teamsAdapter  = new TeamRegisterListAdapter(getActivity(), teamsList, R.layout.players_home_in_match_listview);
	     
	     for (Team team : data.getTeamList()) {
	    	 teamsList.add(team);
		}
	     
	     listViewTeams.setAdapter(teamsAdapter);
	     
	     teamsAdapter.notifyDataSetChanged();
        
	     addButton = (Button) v.findViewById(R.id.buttonAdd);
	     
	     addButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
		    	String name = "Команда"+teamIndex;
		    	
		    	Team newTeam = new Team(name, name);
		    	
		    	teamsList.add(newTeam);
		    	data.addTeam(name, name);
		    	teamIndex++;

		    	teamsAdapter.notifyDataSetChanged();   			
			}
		});
	     
		return v;
	 }
}