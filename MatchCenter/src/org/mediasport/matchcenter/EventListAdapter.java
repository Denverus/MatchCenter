package org.mediasport.matchcenter;

import java.util.ArrayList;
import java.util.List;

import org.mediasport.matchcenter.docs.EEventType;
import org.mediasport.matchcenter.docs.Event;
import org.mediasport.matchcenter.engine.Factory;
import org.mediasport.matchcenter.engine.MatchEngine;

import android.app.ListActivity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class EventListAdapter  extends BaseAdapter {
	Context ctx;
    private LayoutInflater inflater;
    private List<Event> objects;

    public EventListAdapter(Context context, List<Event> objects){
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
    	MatchEngine engine = Factory.getMatchEngine();
    	List<Event> eventList = engine.getEventList();
    	
    	//return eventList.get(position);
    	
        return this.objects.get(position);
    }
    
    Event getEvent(int position) {
      return ((Event) getItem(position));
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
          view = inflater.inflate(R.layout.event_item, parent, false);
        }    	
    	
        Event event = getEvent(position);           

        TextView textViewTime = (TextView) view.findViewById(R.id.tvEventTime);
        textViewTime.setText(event.getStringTime());
        
        TextView textViewDescr = (TextView) view.findViewById(R.id.tvEventDescription);
        textViewDescr.setText(event.getTypeDescription()+" "+event.getTeam().getShortName());

        return view;
    }
}