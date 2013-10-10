package org.mediasport.resumeapp;

import java.util.List;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Toast;

public class ResumeService extends Service {

	private Handler handler = new Handler();
	
	private Thread t = new Thread(new Runnable() {
		@Override
		public void run() {
			/*while (true) {
				handler.post(new Runnable() { // This thread runs in the UI
		            @Override
		            public void run() {
		            	Toast.makeText(ResumeService.this,"Сервис работает ("+index+")", Toast.LENGTH_LONG).show();
		            	index++;
		            }
		        });			
				
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
					}
				}*/
			}
		});  
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		Toast.makeText(this,"Сервис запущен.", Toast.LENGTH_LONG).show();
		t.start();
	}
	
	@Override
	public void onDestroy() {
		t.interrupt();
		super.onDestroy();
		Toast.makeText(this, "Сервис остановлен.", Toast.LENGTH_LONG).show();
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		//Toast.makeText(ResumeService.this,"Сервис onStartCommand.", Toast.LENGTH_LONG).show();
		
		Intent runningIntent = new Intent();
		runningIntent.setAction(Intent.ACTION_VIEW);
		runningIntent.setData(Uri.parse("market://search?q=foo"));
	    PackageManager pm = getPackageManager();
	    List<ResolveInfo> list = pm.queryIntentActivities(runningIntent, 0);		
		/*
		Intent runningIntent = new Intent();
		runningIntent.setComponent(new ComponentName("com.example", "com.example.MyExampleActivity"));
		startActivity(runningIntent);*/
		return super.onStartCommand(runningIntent, flags, startId);
	}
}
