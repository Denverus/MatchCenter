package org.mediasport.matchcenter.ui.matchscreen;

import java.util.List;

import org.mediasport.matchcenter.R;
import org.mediasport.matchcenter.R.id;
import org.mediasport.matchcenter.R.layout;
import org.mediasport.matchcenter.R.string;
import org.mediasport.matchcenter.docs.EEventType;
import org.mediasport.matchcenter.docs.Event;
import org.mediasport.matchcenter.docs.Player;
import org.mediasport.matchcenter.docs.Team;
import org.mediasport.matchcenter.docs.Util;
import org.mediasport.matchcenter.engine.DataStore;
import org.mediasport.matchcenter.engine.Factory;
import org.mediasport.matchcenter.engine.MatchEngine;

import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class EventEditorDialog extends DialogFragment  {
	 	
	 	private EventEditorDialog dialog = this;
	 	
	 	private Button okButton = null;
	 	private Button cancelButton = null;

	 	private TextView timeTextView = null;
	 	private RadioButton goalRB = null;
	 	private RadioButton ownGoalRB = null;
	 	private RadioButton shootinRB = null;
	 	private RadioButton shootoutRB = null;
	 	private RadioButton yellowRB = null;
	 	private RadioButton redRB = null;
	 	private RadioButton commentRB = null;
	 	private RadioButton homeTeamRB = null;
	 	private RadioButton awayTeamRB = null;
	 	
	 	private RadioGroup eventTypeRadioGroup = null;
	 	private RadioGroup teamsRadioGroup = null;
	 	
	 	private Button playerAuthor = null;
	 	private Button playerAssist = null;
	 	
	 	private Event event = new Event();
	 	
	 	MatchEngine match = Factory.getMatchEngine();

		public static final String ARG_EVENT = "event";
	 	
	 	public EventEditorDialog() {
	 		
	 	}
	 	
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	    }

	    @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
	        View v = inflater.inflate(R.layout.event_editor_dialog, container, false);

	        event = (Event) getArguments().getSerializable(ARG_EVENT);
	        
	        getDialog().setTitle(R.string.event_editor_dlg_title);
	        
	        timeTextView = (TextView) v.findViewById(R.id.twEventEditor_TimeValue);
	        
	        timeTextView.setText(Util.getStringTime(event.getTime()));
	        
	        okButton = (Button) v.findViewById(R.id.btEventEditorDialogOK);
	        okButton.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
	            	MatchEngine engine = Factory.getMatchEngine();
	            	engine.addEvent(event);
					
					dialog.dismiss();
				}	        
			});
	        cancelButton = (Button) v.findViewById(R.id.btEventEditorDialogCancel);
	        cancelButton.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					dialog.dismiss();
					
				}
			});
	        
	        playerAuthor = (Button) v.findViewById(R.id.buttonChoosePlayerAthor);
	        
	        if (event.getPlayerAuthor() != null)
	        	playerAuthor.setText(event.getPlayerAuthor().toString());
	        
	        playerAssist = (Button) v.findViewById(R.id.buttonChoosePlayerAssist);

	        eventTypeRadioGroup = (RadioGroup) v.findViewById(R.id.rgEventType);
	        teamsRadioGroup = (RadioGroup) v.findViewById(R.id.rgTeams);
		 	
		 	eventTypeRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
				
				@Override
				public void onCheckedChanged(RadioGroup group, int checkedId) {
					switch (checkedId) {
	        			case R.id.radioButtonGoal: eventTypeRadioGroup.setBackgroundColor(Color.parseColor("#ff0000"));
	        				break;
	        			case R.id.radioButtonOwnGoal: eventTypeRadioGroup.setBackgroundColor(Color.parseColor("#ffff00"));
	        				break;
	        			case R.id.radioButtonShootIn: eventTypeRadioGroup.setBackgroundColor(Color.parseColor("#ff00ff"));
	        				break;
	        			case R.id.radioButtonShootOut: eventTypeRadioGroup.setBackgroundColor(Color.parseColor("#ff0f00"));
	        				break;
	        			case R.id.radioButtonYelCard: eventTypeRadioGroup.setBackgroundColor(Color.parseColor("#ff0F0f"));
	        				break;
	        			case R.id.radioButtonRedCard: eventTypeRadioGroup.setBackgroundColor(Color.parseColor("#fff0f0"));
	        				break;
        				default:
        					break;
					}
				}
			});		 	
		 	
		 	homeTeamRB = (RadioButton) v.findViewById(R.id.radioButtonHomeTeam);
		 	homeTeamRB.setText(match.getMatch().getHomeTeam().getShortName());
		 	
		 	awayTeamRB = (RadioButton) v.findViewById(R.id.radioButtonAwayTeam);
		 	awayTeamRB.setText(match.getMatch().getAwayTeam().getShortName());
		 	
		 	teamsRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
				
				@Override
				public void onCheckedChanged(RadioGroup group, int checkedId) {
					switch (checkedId) {
	        			case R.id.radioButtonHomeTeam: teamsRadioGroup.setBackgroundColor(Color.parseColor("#ff0000"));
	        				break;
	        			case R.id.radioButtonAwayTeam: teamsRadioGroup.setBackgroundColor(Color.parseColor("#ffff00"));
	        				break;
        				default:
        					break;
					}
				}
			});		
		 	
		 	if (match.teamIsHome(event.getTeam()))
		 		homeTeamRB.setChecked(true);
		 	else
		 		awayTeamRB.setChecked(true);
		 	
	        return v;
	    }

		public void setInitialEvent(Event initialEvent) {
			this.event = initialEvent;
		}
		
		@Override
		public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		}
}
