package org.mediasport.matchcenter;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

public class MatchScreenFragment extends Fragment {
	 @Override
	 public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
	        if (container == null) {
	            return null;
	        }

	        ScrollView scroller = new ScrollView(getActivity());
	        TextView text = new TextView(getActivity());
	        
	        return inflater.inflate(R.layout.activity_match_screen, container, false);
	    }
}