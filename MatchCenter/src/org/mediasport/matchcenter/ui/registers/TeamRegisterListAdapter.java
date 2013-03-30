package org.mediasport.matchcenter.ui.registers;

import java.util.ArrayList;
import java.util.List;

import org.mediasport.matchcenter.R;
import org.mediasport.matchcenter.R.id;
import org.mediasport.matchcenter.docs.Player;
import org.mediasport.matchcenter.docs.Team;

import android.app.ListActivity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class TeamRegisterListAdapter  extends BaseAdapter {
	Context ctx;
    private LayoutInflater inflater;
    private List<Team> objects;
    private int itemLayoutId;

    public TeamRegisterListAdapter(Context context, List<Team> objects, int itemLayoutId){
        ctx = context;
        this.objects = objects;
        this.itemLayoutId = itemLayoutId;
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
    
    Team getTeam(int position) {
      return ((Team) getItem(position));
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
          view = inflater.inflate(itemLayoutId, parent, false);
        }    	
    	
        Team team = getTeam(position);           

        TextView textView = (TextView) view.findViewById(R.id.tvPlayerName);
        textView.setText(team.getName());

        return view;
    }
}