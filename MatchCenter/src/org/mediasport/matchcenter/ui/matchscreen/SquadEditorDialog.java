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
import org.mediasport.matchcenter.ui.matchscreen.SquadsFrament.onUpdateEventListener;

import android.app.Activity;
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
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class SquadEditorDialog extends DialogFragment  {
	 	
	 	public static final String ARG_TEAM = "team";

		private SquadEditorDialog dialog = this;
	 	
	 	private Button okButton = null;
	 	private Button cancelButton = null;

	 	private ListView squadListView = null;

	 	private Team team = null;
	 	
	 	MatchEngine match = Factory.getMatchEngine();
	 	DataStore dataStore = Factory.getDataStore();
	 	
		onUpdateEventListener updateEventListener;
	 	
	 	public SquadEditorDialog() {
	 		
	 	}
	 	
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	    }

	    @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
	        team = (Team) getArguments().getSerializable(ARG_TEAM);
	    	
	        View v = inflater.inflate(R.layout.squad_editor_dialog, container, false);

	        getDialog().setTitle(R.string.squad_editor_dlg_title);
	        
	        okButton = (Button) v.findViewById(R.id.btSquadEditorDialogOK);
	        okButton.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
	            	updateEventListener.updateSquad();
					dialog.dismiss();
				}	        
			});
	        cancelButton = (Button) v.findViewById(R.id.btSquadEditorDialogCancel);
	        cancelButton.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
	            	updateEventListener.updateSquad();
					dialog.dismiss();
					
				}
			});
	        
	        squadListView = (ListView) v.findViewById(R.id.lvSquadEditorDialogList);
	        
	        List<Player> playerList = dataStore.getPlayerListByTeam(team);
	        
		 	SquadEditorListAdapter squadAdapter = new SquadEditorListAdapter(getActivity(), playerList);
		 	
		 	squadListView.setAdapter(squadAdapter);
	        
	        return v;
	    }

		public void setTeam(Team team) {
			this.team = team;
		}
		
		@Override
		public void onViewCreated(View view, Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onViewCreated(view, savedInstanceState);
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
