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
import org.mediasport.matchcenter.ui.matchscreen.EventEditorDialog;
import org.mediasport.matchcenter.ui.matchscreen.MatchPlayerListAdapter;
import org.mediasport.matchcenter.ui.registers.EditItemDialog.EItemType;

import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class PlayerFragment extends Fragment {
	int playerIndex = 1;
	
	private ListView listViewPlayers;
	private List<Player> playerList = new ArrayList<Player>();
	
	private PlayerRegisterListAdapter playerAdapter;
	
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

	     listViewPlayers = (ListView) v.findViewById(R.id.listViewTeams);
	     
	     addButton = (Button) v.findViewById(R.id.buttonAdd);
	     
	     addButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				EditItemDialog newEditItemDlg = new EditItemDialog();

				Bundle args = new Bundle();
				args.putSerializable(EditItemDialog.ITEM_TYPE, EItemType.Player);
				newEditItemDlg.setArguments(args);	    
			    //newEventEditorDlg.setInitialEvent(event);
			    
				newEditItemDlg.show(getFragmentManager(), "dialog");
				
		    	String name = "Имя1"+playerIndex;
		    	String lastName = "Фамилия1"+playerIndex;
		    	
		    	Player newPlayer = new Player(name, name);
		    	
		    	playerList.add(newPlayer);
		    	data.addPlayer(newPlayer, null);
		    	playerIndex++;

		    	playerAdapter.notifyDataSetChanged();    			}
		});

	     playerAdapter  = new PlayerRegisterListAdapter(getActivity(), playerList, R.layout.fragment_register_player);
	     playerList.clear();
	     for (Player player : data.getPlayerList()) {
	    	 playerList.add(player);
		}
	     
	     listViewPlayers.setAdapter(playerAdapter);
	     
	     playerAdapter.notifyDataSetChanged();
        
		return v;
	 }
}