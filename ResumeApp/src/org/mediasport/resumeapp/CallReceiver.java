package org.mediasport.resumeapp;

import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.telephony.TelephonyManager;

public class CallReceiver extends BroadcastReceiver {
	
    public void onReceive(Context context, Intent intent) {
        String phoneNumber = "";
		if (intent.getAction().equals("android.intent.action.NEW_OUTGOING_CALL")) {
            //получаем исходящий номер
            phoneNumber  = intent.getExtras().getString("android.intent.extra.PHONE_NUMBER");
        } else if (intent.getAction().equals("android.intent.action.PHONE_STATE")){
            String phone_state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
            if (phone_state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
                //телефон звонит, получаем входящий номер
                phoneNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
            } else if (phone_state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)){
                //телефон находится в режиме звонка (набор номера / разговор)
            } else if (phone_state.equals(TelephonyManager.EXTRA_STATE_IDLE)){
                //телефон находиться в ждущем режиме. Это событие наступает по окончанию разговора, когда мы уже знаем номер и факт звонка
                Intent i = new Intent();
                i.setClassName("org.mediasport.resumeapp", "org.mediasport.resumeapp.MainActivity");
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);        		
            }
        }
    }
    
    boolean isNamedProcessRunning(Context context, String processName){
    	if (processName == null) return false;

    	ActivityManager manager = 
    	    (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
    	List<RunningAppProcessInfo> processes = manager.getRunningAppProcesses();

    	for (RunningAppProcessInfo process : processes)
    	{
    	    if (processName.equals(process.processName))
    	    {
    	        return true;
    	    }
    	}
    	return false;
    }    
}