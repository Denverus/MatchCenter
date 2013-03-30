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

public class MatchFragment extends Fragment {
	int matchIndex = 1;
	
	private ListView listViewMatch;
	private List<Match> matchList = new ArrayList<Match>();
	
	private MatchRegisterListAdapter matchAdapter;
	
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

	     listViewMatch = (ListView) v.findViewById(R.id.listViewTeams);

	     matchAdapter  = new MatchRegisterListAdapter(getActivity(), matchList, R.layout.players_home_in_match_listview);
	     
	     for (Match match : data.getMatchList()) {
	    	 matchList.add(match);
		}
	     
	     listViewMatch.setAdapter(matchAdapter);
	     
	     matchAdapter.notifyDataSetChanged();
        
	     addButton = (Button) v.findViewById(R.id.buttonAdd);
	     
	     addButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
		    	Match newMatch = new Match();
		    	
		    	matchList.add(newMatch);
		    	data.addMatch(newMatch);

		    	matchAdapter.notifyDataSetChanged();    			}
		});	     
		return v;
	 }
}