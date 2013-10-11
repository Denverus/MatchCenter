package org.mediasport.resumeapp;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AppListAdapter extends BaseAdapter {

    private List<AppListItem> objects;
	private int itemLayoutId;
	private LayoutInflater inflater;
	private Context ctx;

	public AppListAdapter(Context context, List<AppListItem> objects, int itemLayoutId){
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
	public Object getItem(int position) {
		return objects.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
    	View view = convertView;
        if (view == null) {
          view = inflater.inflate(itemLayoutId, parent, false);
        }    	
    	
        AppListItem appItem = (AppListItem) getItem(position);           

        TextView textView = (TextView) view.findViewById(R.id.tvAppName);
        textView.setText(appItem.getName());

        return view;	
	}
}
