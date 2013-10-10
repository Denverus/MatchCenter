package org.mediasport.resumeapp;

import org.mediasport.resumeapp.R;
import org.mediasport.resumeapp.R.id;
import org.mediasport.resumeapp.R.layout;
import org.mediasport.resumeapp.R.menu;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	@Override //�������������
	protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);
	        Button start = (Button)findViewById(R.id.serviceStartButton);
	        Button stop = (Button)findViewById(R.id.serviceStopButton);
	        Button info = (Button)findViewById(R.id.infoButton);
	        start.setOnClickListener(startListener);
	        stop.setOnClickListener(stopListener);
	        info.setOnClickListener(infoListener);
	   }
    private OnClickListener startListener = new OnClickListener() {
    	public void onClick(View v){
    		//������ �������
    		//Toast.makeText(MainActivity.this,"������ �������...", Toast.LENGTH_LONG).show();
    		startService(new Intent(MainActivity.this,ResumeService.class));
    	}
    };
    private OnClickListener stopListener = new OnClickListener() {
       	public void onClick(View v){
       		//��������� �������
       		stopService(new Intent(MainActivity.this,ResumeService.class));
       	}
    };

    private OnClickListener infoListener = new OnClickListener() {
       	public void onClick(View v){
       		//��������� �������
       		//stopService(new Intent(MainActivity.this,ResumeService.class));
       		
       		
       	}
    };
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}


