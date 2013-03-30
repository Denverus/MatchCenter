package org.mediasport.matchcenter;

import java.util.List;

import org.mediasport.matchcenter.engine.DataStore;
import org.mediasport.matchcenter.engine.Factory;
import org.mediasport.matchcenter.engine.MatchEngine;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

public class TeamSelectionDialog extends DialogFragment {
	protected String selectedTeam = "Error";
	
	protected boolean isHomeTeamChoose = false;
	
	protected NewMatchParamsActivity parentActivity; 
	
	  public TeamSelectionDialog() {      
		  }
	  
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		DataStore context = Factory.getDataStore();
		
		final List<String> teamList = context.getTeamStringList();
		
		CharSequence[] cs = teamList.toArray(new CharSequence[teamList.size()]);		
		
	    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	    builder.setTitle("Выберите команду:")
	    		.setItems(cs, new DialogInterface.OnClickListener() {
	               public void onClick(DialogInterface dialog, int which) {
	            	   // The 'which' argument contains the index position
	               // 	of the selected item;
	            	   	selectedTeam = teamList.get(which);
	            	   	
	            	   	if (isHomeTeamChoose)
	            	   		parentActivity.setHomeTeam(selectedTeam);
	            	   	else
	            	   		parentActivity.setAwayTeam(selectedTeam);
	           }
	    });
	    return builder.create();
	}
	
	public String getSelectedTeam() {
		return selectedTeam;
	}

	public void setParamActivity(NewMatchParamsActivity parentActivity, boolean isHomeTeamChoose) {
		this.parentActivity = parentActivity; 
		this.isHomeTeamChoose = isHomeTeamChoose;
	}
}
