package org.mediasport.matchcenter.ui.matchscreen;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.mediasport.matchcenter.R;
import org.mediasport.matchcenter.R.id;
import org.mediasport.matchcenter.R.layout;
import org.mediasport.matchcenter.docs.EEventType;
import org.mediasport.matchcenter.docs.Event;
import org.mediasport.matchcenter.docs.Match;
import org.mediasport.matchcenter.docs.Player;
import org.mediasport.matchcenter.docs.Team;
import org.mediasport.matchcenter.engine.DataStore;
import org.mediasport.matchcenter.engine.Factory;
import org.mediasport.matchcenter.engine.MatchEngine;

import android.app.Activity;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

public class SquadsFrament extends Fragment {
	
	public interface onUpdateEventListener {
	    public void updatingEvent();
	    public void showEventEditorDlg();
	    public void showEventEditorDlg(Event event);
	  }
	  
	onUpdateEventListener updateEventListener;
	  
	private TextView homeTeamTextVeiw = null;
    private TextView awayTeamTextVeiw = null;
    private TextView scoreTextVeiw = null;
    private ListView homePlayersListView = null;
    private ListView awayPlayersListView = null;
    private ImageButton startClockButton = null;
	private TextView clockTextView;
	private TextView scoreTextView;
	
	private Button homeGoalButton = null;
	private Button awayGoalButton = null;

	private Button homeEventCreateButton = null;
	private Button awayEventCreateButton = null;
	private Button newEventCreateButton = null;
	
	private boolean isTimerOn = true;

	private long timerStep = 0; 	
	
	 @Override
	 public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
	        if (container == null) {
	            return null;
	        }
	        
	        View v = inflater.inflate(R.layout.activity_match_screen_test, null);
	        
	        homeTeamTextVeiw = (TextView) v.findViewById(R.id.textViewHomeTeamName);
	        awayTeamTextVeiw = (TextView) v.findViewById(R.id.textViewAwayTeamName);
	        scoreTextVeiw = (TextView) v.findViewById(R.id.textViewScore);
	        
	        homePlayersListView = (ListView) v.findViewById(R.id.listViewHomePlayers);
	        awayPlayersListView = (ListView) v.findViewById(R.id.listViewAwayPlayers);
	        
	        homeGoalButton = (Button) v.findViewById(R.id.imageButtonHomeGoal);
	        awayGoalButton = (Button) v.findViewById(R.id.imageButtonAwayGoal);
	        
	        homeEventCreateButton = (Button) v.findViewById(R.id.ibHomeEvent);
	        awayEventCreateButton = (Button) v.findViewById(R.id.ibAwayEvent);
	        newEventCreateButton = (Button) v.findViewById(R.id.ibNewEvent);
			
	        clockTextView = (TextView) v.findViewById(R.id.textViewClock);
	        startClockButton = (ImageButton) v.findViewById(R.id.imageButtonClock);
	        scoreTextView = (TextView) v.findViewById(R.id.textViewScore);
	        
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
			            	
			            	scoreTextVeiw.setText(engine.getMatch().getStringScore());
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
	        
	        homeGoalButton.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
	            	MatchEngine engine = Factory.getMatchEngine();
	            	engine.addHomeEvent(null, null, EEventType.GOAL);
	            	
	            	updateEventListener.updatingEvent();
				}
			});
	        
	        awayGoalButton.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
	            	MatchEngine engine = Factory.getMatchEngine();
	            	engine.addAwayEvent(null, null, EEventType.GOAL);
	            	
	            	updateEventListener.updatingEvent();
				}
			});	  
	        
	        if (homeEventCreateButton != null)
	        homeEventCreateButton.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
	            	MatchEngine engine = Factory.getMatchEngine();
	            	updateEventListener.showEventEditorDlg(engine.createEvent(engine.getMatch().getAwayTeam()));
				}
			});
	        
	        if (awayEventCreateButton != null)
	        awayEventCreateButton.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
	            	MatchEngine engine = Factory.getMatchEngine();
	            	updateEventListener.showEventEditorDlg(engine.createEvent(engine.getMatch().getHomeTeam()));
				}
			});
	        
	        if (newEventCreateButton != null)
	        newEventCreateButton.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
	            	MatchEngine engine = Factory.getMatchEngine();
	            	updateEventListener.showEventEditorDlg(engine.createEvent());
				}
			});
	        
	        homePlayersListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int position, long arg3) {
					Player player = (Player)homePlayersListView.getAdapter().getItem(position);
	            	MatchEngine engine = Factory.getMatchEngine();
	            	updateEventListener.showEventEditorDlg(engine.createEvent(engine.getMatch().getHomeTeam(), player));
				}});
	        
	        awayPlayersListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int position, long arg3) {
					Player player = (Player)awayPlayersListView.getAdapter().getItem(position);
	            	MatchEngine engine = Factory.getMatchEngine();
	            	updateEventListener.showEventEditorDlg(engine.createEvent(engine.getMatch().getAwayTeam(), player));
				}});
	        
	        homeTeamTextVeiw.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
	            	MatchEngine engine = Factory.getMatchEngine();
					
	            	updateEventListener.showEventEditorDlg(engine.createEvent(engine.getMatch().getHomeTeam()));
				}
			});
	        awayTeamTextVeiw.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
	            	MatchEngine engine = Factory.getMatchEngine();
					
	            	updateEventListener.showEventEditorDlg(engine.createEvent(engine.getMatch().getAwayTeam()));
				}
			});
	        
			loadData();	        
	        
	        return v;
	    }
	 
		protected void loadData() {
	    	DataStore data = Factory.getDataStore();
	    	MatchEngine engine = Factory.getMatchEngine();
	    	
	    	Match match = engine.getMatch();
	    	String homeTeam = match.getHomeTeam().getShortName();
	    	String awayTeam = match.getAwayTeam().getShortName();
			
			homeTeamTextVeiw.setText(homeTeam);
			awayTeamTextVeiw.setText(awayTeam);
			
			List<Player> homePlayerList = data.getPlayerListByTeam(match.getHomeTeam());
			List<Player> awayPlayerList = data.getPlayerListByTeam(match.getAwayTeam());
			
			MatchPlayerListAdapter homePlayerListAdapter = new MatchPlayerListAdapter(getActivity(), homePlayerList, R.layout.players_home_in_match_listview);
			MatchPlayerListAdapter awayPlayerListAdapter = new MatchPlayerListAdapter(getActivity(), awayPlayerList, R.layout.players_away_in_match_listview);

			homePlayersListView.setAdapter(homePlayerListAdapter);
			awayPlayersListView.setAdapter(awayPlayerListAdapter);
		}	 
		
	  @Override
	  public void onAttach(Activity activity) {
	    super.onAttach(activity);
	        try {
	        	updateEventListener = (onUpdateEventListener) activity;
	        } catch (ClassCastException e) {
	            throw new ClassCastException(activity.toString() + " must implement onUpdateEventListener");
	        }
	  }	
}
