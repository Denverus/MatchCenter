package org.mediasport.matchcenter.ui.matchscreen;

import java.util.ArrayList;
import java.util.List;

import org.mediasport.matchcenter.R;
import org.mediasport.matchcenter.R.id;
import org.mediasport.matchcenter.docs.Match;
import org.mediasport.matchcenter.docs.Player;
import org.mediasport.matchcenter.engine.DataStore;
import org.mediasport.matchcenter.engine.Factory;
import org.mediasport.matchcenter.engine.MatchEngine;

import android.app.ListActivity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;

public class SquadEditorListAdapter  extends BaseAdapter {
	Context ctx;
    private LayoutInflater inflater;
    private List<Player> objects;

 	MatchEngine engine = Factory.getMatchEngine();
 	DataStore dataStore = Factory.getDataStore();
    
    public SquadEditorListAdapter(Context context, List<Player> objects){
        ctx = context;
        this.objects = objects;
        inflater = (LayoutInflater) ctx
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object  getItem(int position) throws IndexOutOfBoundsException{
        return this.objects.get(position);
    }
    
    Player getPlayer(int position) {
      return ((Player) getItem(position));
    }    

    @Override
    public long getItemId(int position) throws IndexOutOfBoundsException{
        if(position < getCount() && position >= 0 ){
            return position;
        }
		return 0;
    }

    public int getViewTypeCount(){
        return 1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
    	View view = convertView;
        if (view == null) {
          view = inflater.inflate(R.layout.sqaud_editor_listview_item, parent, false);
        }    	
    	
     	final Match match = engine.getMatch();
        
        final Player player = getPlayer(position);           

        CheckBox checkBox = (CheckBox) view.findViewById(R.id.chSquadEditorCheckBox);
        checkBox.setText(player.toString());
        checkBox.setChecked(match.isPlayerInSquad(player, player.getTeam()));

        checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				match.setPlayerSquadStatus(player, player.getTeam(), isChecked);
				
			}
		});
        
        return view;
    }
}