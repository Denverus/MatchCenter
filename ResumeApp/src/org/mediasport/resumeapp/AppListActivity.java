package org.mediasport.resumeapp;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.widget.ListView;

public class AppListActivity extends Activity {
	
	ListView appList;
	List<AppListItem> appListItems = new ArrayList<AppListItem>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.applistactivity);
		
		appList = (ListView) findViewById(R.id.lvAppList);
		
		appListItems = getAppListItem();
		
		AppListAdapter mainMenuListAdapter = new AppListAdapter(this, appListItems, R.layout.app_item);
		
		appList.setAdapter(mainMenuListAdapter);
	}
	
	private List<AppListItem> getAppListItem() {
		List<AppListItem> appListItems = new ArrayList<AppListItem>();

		final PackageManager pm = getPackageManager();
		List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);

		for (ApplicationInfo packageInfo : packages) {
			AppListItem appItem = new AppListItem();
			
			appItem.setName(packageInfo.toString());
			
			appListItems.add(appItem);
		}
		
		return appListItems;
	}
}
