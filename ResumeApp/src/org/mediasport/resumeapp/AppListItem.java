package org.mediasport.resumeapp;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;

public class AppListItem {

	private int image;
	private String name;
	private String description;
	private ApplicationInfo appInfo;
	
	public AppListItem(ApplicationInfo appInfo, String name) {
		this.appInfo = appInfo;
		this.name = name;
	}

	public int getImage() {
		return image;
	}

	public void setImage(int image) {
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
