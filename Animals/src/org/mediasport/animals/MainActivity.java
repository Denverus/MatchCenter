package org.mediasport.animals;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
    public void buttonNewGame_click(View v)
    {
    	KnowledgeBase base = EngineFactory.getKnowledgeBase(); 
    	
    	if (base != null)
    		base.runNewGame();
    	
    	Intent myIntent = new Intent(this, GameActivity.class);
     	startActivity(myIntent);
    }    	

}
