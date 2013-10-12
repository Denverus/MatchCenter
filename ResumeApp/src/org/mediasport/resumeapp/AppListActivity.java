package org.mediasport.resumeapp;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class AppListActivity extends Activity {
	
	ListView appList;
	List<AppListItem> appListItems = new ArrayList<AppListItem>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.applistactivity);
		
		appList = (ListView) findViewById(R.id.lvAppList);
		appList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				
				
			}
		});
		
		appListItems = getAppListItem();
		
		AppListAdapter mainMenuListAdapter = new AppListAdapter(this, appListItems, R.layout.app_item);
		
		appList.setAdapter(mainMenuListAdapter);
	}
	
	private List<AppListItem> getAppListItem() {
		List<AppListItem> appListItems = new ArrayList<AppListItem>();

		final PackageManager pm = getPackageManager();
		List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);

		for (ApplicationInfo packageInfo : packages) {
			if ((packageInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 1)
		    {
		        //This is System application
				continue;
		    }
		    else
		    {
		       //This app is installed by user
		    }			
			
			
			final PackageManager pm2 = getApplicationContext().getPackageManager();
			ApplicationInfo ai;
			try {
			    ai = pm2.getApplicationInfo( packageInfo.packageName, 0);
			} catch (final NameNotFoundException e) {
			    ai = null;
			}
			
			if (ai != null) {
				String applicationName = (String) (ai != null ? pm2.getApplicationLabel(ai) : "(unknown)");			
			
				appListItems.add(new AppListItem(ai, applicationName));
			}
		}
		
		return appListItems;
	}
}
