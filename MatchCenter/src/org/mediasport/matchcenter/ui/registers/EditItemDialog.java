package org.mediasport.matchcenter.ui.registers;

import org.mediasport.matchcenter.R;
import org.mediasport.matchcenter.docs.Event;
import org.mediasport.matchcenter.docs.Player;
import org.mediasport.matchcenter.engine.Factory;
import org.mediasport.matchcenter.engine.MatchEngine;
import org.mediasport.matchcenter.ui.matchscreen.EventEditorDialog;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class EditItemDialog extends DialogFragment{
	public static final String ITEM_TYPE = "item_type";
	public static final String ITEM_PLAYER = "player";

	public enum EItemType {Player, Team, Match};
	
 	private EditItemDialog dialog = this;
	
 	private Button okButton = null;
 	private Button cancelButton = null;
	
 	private Player player = null;
 	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
		  
		EItemType itemType = (EItemType) getArguments().getSerializable(ITEM_TYPE);
		player = (Player) getArguments().getSerializable(ITEM_PLAYER);
	  
        View v = null;
        
        switch (itemType) {
		case Player:
			v = inflater.inflate(R.layout.register_item_editor_player, container, false);
			break;

		default:
			v = inflater.inflate(R.layout.register_item_editor_player, container, false);
			break;
		}
        
        okButton = (Button) v.findViewById(R.id.btEventEditorDialogOK);
        okButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
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
	 	
        return v;
	}
}
