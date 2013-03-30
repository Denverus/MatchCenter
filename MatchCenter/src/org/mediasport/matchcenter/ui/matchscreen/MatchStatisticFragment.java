package org.mediasport.matchcenter.ui.matchscreen;

import org.mediasport.matchcenter.R;
import org.mediasport.matchcenter.R.layout;
import org.mediasport.matchcenter.engine.DataStore;
import org.mediasport.matchcenter.engine.Factory;
import org.mediasport.matchcenter.engine.MatchEngine;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

public class MatchStatisticFragment extends Fragment {
	
	private DataStore data = Factory.getDataStore();
	private MatchEngine engine = Factory.getMatchEngine();
	
	 @Override
	 public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
	        if (container == null) {
	            return null;
	        }
	        
	        View v = inflater.inflate(R.layout.fragment_match_statistic, null);
	        
	        TextView homeTeamName = (TextView) v.findViewById(R.id.tvMatchStat_HomeTeam);
	        homeTeamName.setText(engine.getMatch().getHomeTeam().getShortName());
	        
	        TextView awayTeamName = (TextView) v.findViewById(R.id.tvMatchStat_AwayTeam);
	        awayTeamName.setText(engine.getMatch().getAwayTeam().getShortName());
	        
	        TextView homeTeamGoals = (TextView) v.findViewById(R.id.tvMatchStat_HomeTeamGoals);
	        homeTeamGoals.setText(engine.getMatch().getHomeGoal()+"");
	        
	        TextView awayTeamGoals = (TextView) v.findViewById(R.id.tvMatchStat_AwayTeamGoals);
	        awayTeamGoals.setText(engine.getMatch().getAwayGoal()+"");
	        
	        
	        return v;
	    }
}