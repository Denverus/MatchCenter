package org.mediasport.matchcenter.ui.matchscreen;

import java.util.List;

import org.mediasport.matchcenter.R;
import org.mediasport.matchcenter.R.id;
import org.mediasport.matchcenter.R.layout;
import org.mediasport.matchcenter.docs.Event;
import org.mediasport.matchcenter.docs.Match;
import org.mediasport.matchcenter.docs.Player;
import org.mediasport.matchcenter.engine.DataStore;
import org.mediasport.matchcenter.engine.Factory;
import org.mediasport.matchcenter.engine.MatchEngine;
import org.mediasport.matchcenter.ui.matchscreen.SquadsFrament.onUpdateEventListener;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

public class EventsFragment extends Fragment {

	private ListView evensListView = null;
	
	private EventListAdapter eventListAdapter = null;
	
	onUpdateEventListener updateEventListener;
	
	 @Override
	 public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
	        if (container == null) {
	            return null;
	        }
	        
	        View v = inflater.inflate(R.layout.event_listview, null);
	        
	        evensListView = (ListView) v.findViewById(R.id.lvMatchEventList);
	        
	    	MatchEngine engine = Factory.getMatchEngine();
	    	
	    	Match match = engine.getMatch();
			
			List<Event> homePlayerList = match.getEventList();
			
			eventListAdapter = new EventListAdapter(getActivity(), homePlayerList);

			evensListView.setAdapter(eventListAdapter);
	        
			evensListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int position, long arg3) {
					Event event = (Event)evensListView.getAdapter().getItem(position);
	            	updateEventListener.showEventEditorDlg(event);
				}});			
			
	        return v;
	 }
	 
	 public void notifyOnAdd() {
		 eventListAdapter.notifyDataSetChanged();
	 }
	 
	 public void notifyOnRemove() {
		 eventListAdapter.notifyDataSetChanged();
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
