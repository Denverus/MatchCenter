package org.mediasport.matchcenter;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MainMenuItemAdapter  extends BaseAdapter {
	Context ctx;
    private LayoutInflater inflater;
    private List<MainMenuItem> objects;
    private int itemLayoutId;

    public MainMenuItemAdapter(Context context, List<MainMenuItem> objects, int itemLayoutId){
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
    
    MainMenuItem getMenuItem(int position) {
      return ((MainMenuItem) getItem(position));
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
    	
        MainMenuItem menuItem = getMenuItem(position);           

        TextView textView = (TextView) view.findViewById(R.id.tvMainMenuItem);
        textView.setText(menuItem.getName());

        return view;
    }
}