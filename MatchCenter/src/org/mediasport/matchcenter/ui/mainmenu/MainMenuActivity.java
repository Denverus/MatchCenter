package org.mediasport.matchcenter.ui.mainmenu;

import java.util.ArrayList;
import java.util.List;

import org.mediasport.matchcenter.R;
import org.mediasport.matchcenter.R.drawable;
import org.mediasport.matchcenter.R.id;
import org.mediasport.matchcenter.R.layout;
import org.mediasport.matchcenter.R.menu;
import org.mediasport.matchcenter.R.string;
import org.mediasport.matchcenter.docs.Team;
import org.mediasport.matchcenter.docs.Util;
import org.mediasport.matchcenter.engine.DataStore;
import org.mediasport.matchcenter.engine.Factory;
import org.mediasport.matchcenter.engine.MatchEngine;
import org.mediasport.matchcenter.ui.matchscreen.MatchActivity;
import org.mediasport.matchcenter.ui.newmatch.NewMatchParamsActivity;
import org.mediasport.matchcenter.ui.registers.RegisterFragmentActivity;
import org.mediasport.matchcenter.ui.settings.SettingsActivity;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.ActionProvider;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

public class MainMenuActivity extends Activity {

	final MainMenuActivity mainMenuActivity = this;
	
	final Context context = this;
	
	private ListView mainMenuListView = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);
		
		mainMenuListView = (ListView) findViewById(R.id.mainMenuList);
		
		List<MainMenuItem> mainMenuList = new ArrayList<MainMenuItem>();
		
		Resources res = getResources();
		
		mainMenuList.add(new MainMenuItem(res.getString(R.string.Continue), R.drawable.player));
		mainMenuList.add(new MainMenuItem(res.getString(R.string.newmatch), R.drawable.clock));
		mainMenuList.add(new MainMenuItem(res.getString(R.string.register), R.drawable.player));
		mainMenuList.add(new MainMenuItem(res.getString(R.string.statistic), R.drawable.clock));
		mainMenuList.add(new MainMenuItem(res.getString(R.string.settings), R.drawable.player));
		mainMenuList.add(new MainMenuItem(res.getString(R.string.exit), R.drawable.clock));
		
		MainMenuItemAdapter mainMenuListAdapter = new MainMenuItemAdapter(this, mainMenuList, R.layout.main_menu_listview);
		
		mainMenuListView.setAdapter(mainMenuListAdapter);
		
		mainMenuListView.setOnItemClickListener(new OnItemClickListener() {
		      public void onItemClick(AdapterView<?> parent, View view,
		          int position, long id) {
		    	  if (position == 0) {
						Util.createTestMatch();
//		            	Intent myIntent = new Intent(mainMenuActivity, MatchScreenActivity.class);
		            	Intent myIntent = new Intent(mainMenuActivity, MatchActivity.class);
		             	startActivity(myIntent);
		    	  }
		    	  if (position == 1) {
		            	Intent myIntent = new Intent(mainMenuActivity, NewMatchParamsActivity.class);
		             	startActivity(myIntent);
		    	  }	
		    	  // Register
		    	  if (position == 2) {
		            	Intent myIntent = new Intent(mainMenuActivity, RegisterFragmentActivity.class);
		             	startActivity(myIntent);
		    	  }			  
		    	  if (position == 3) {
		            	//Intent myIntent = new Intent(mainMenuActivity, MatchScreenActivitySwipe.class);
		             	//startActivity(myIntent);
		    	  }			  
		    	  if (position == 4) {
		            	Intent myIntent = new Intent(mainMenuActivity, SettingsActivity.class);
		             	startActivity(myIntent);
		    	  }
		    	  if (position == 5) {
		    		  AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
		      				context);
		       
		      			// set title
		      			alertDialogBuilder.setTitle(R.string.exit_dlg_title);
		       
		      			// set dialog message
		      			alertDialogBuilder
		      				.setMessage(R.string.exit_dlg_question)
		      				.setCancelable(false)
		      				.setPositiveButton(R.string.exit_dlg_yes,new DialogInterface.OnClickListener() {
		      					public void onClick(DialogInterface dialog,int id) {
		      						// if this button is clicked, close
		      						// current activity
		      						MainMenuActivity.this.finish();
		      					}
		      				  })
		      				.setNegativeButton(R.string.exit_dlg_no,new DialogInterface.OnClickListener() {
		      					public void onClick(DialogInterface dialog,int id) {
		      						// if this button is clicked, just close
		      						// the dialog box and do nothing
		      						dialog.cancel();
		      					}
		      				});
		       
		      				// create alert dialog
		      				AlertDialog alertDialog = alertDialogBuilder.create();
		       
		      				// show it
		      				alertDialog.show();		    		  
		    	  }							    	  
		      }
		    });		
/*		
        final Button buttonContinue = (Button) findViewById(R.id.buttonContinueMatch);
        buttonContinue.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
            	MatchEngine engine = Factory.getMatchEngine();
            	DataStore data = Factory.getDataStore();
            	
            	Team homeTeam =  data.findTeamByName(data.getChelseaName());
            	Team awayTeam =  data.findTeamByName(data.getMUName());
            	
            	engine.createMatch(homeTeam, awayTeam);
				
            	Intent myIntent = new Intent(mainMenuActivity, MatchScreenActivity.class);
             	startActivity(myIntent);
			}
		});
		
        final Button buttonNewMatch = (Button) findViewById(R.id.buttonNewMatch);
        
        buttonNewMatch.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
            	Intent myIntent = new Intent(mainMenuActivity, NewMatchParamsActivity.class);
             	startActivity(myIntent);
			}
		});
        
        final Button buttonAllMatches = (Button) findViewById(R.id.buttonAllMatches);
        buttonAllMatches.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
            	Intent myIntent = new Intent(mainMenuActivity, MatchScreenActivitySwipe.class);
             	startActivity(myIntent);
			}
		});        
		
        final Button buttonOpenTeams = (Button) findViewById(R.id.buttonTeams);
        
        buttonOpenTeams.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
            	Intent myIntent = new Intent(mainMenuActivity, TeamsActivity.class);
             	startActivity(myIntent);
			}
		});
        
        final Button buttonSettings = (Button) findViewById(R.id.buttonSettings);
        
        buttonSettings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent myIntent = new Intent(mainMenuActivity, SettingsActivity.class);
             	startActivity(myIntent);
            }
        });
        
        final Button buttonExit = (Button) findViewById(R.id.buttonExit);
        
        buttonExit.setOnClickListener(new View.OnClickListener() {
    		@Override
    		public void onClick(View arg0) {
     
    			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
    				context);
     
    			// set title
    			alertDialogBuilder.setTitle(R.string.exit_dlg_title);
     
    			// set dialog message
    			alertDialogBuilder
    				.setMessage(R.string.exit_dlg_question)
    				.setCancelable(false)
    				.setPositiveButton(R.string.exit_dlg_yes,new DialogInterface.OnClickListener() {
    					public void onClick(DialogInterface dialog,int id) {
    						// if this button is clicked, close
    						// current activity
    						MainMenuActivity.this.finish();
    					}
    				  })
    				.setNegativeButton(R.string.exit_dlg_no,new DialogInterface.OnClickListener() {
    					public void onClick(DialogInterface dialog,int id) {
    						// if this button is clicked, just close
    						// the dialog box and do nothing
    						dialog.cancel();
    					}
    				});
     
    				// create alert dialog
    				AlertDialog alertDialog = alertDialogBuilder.create();
     
    				// show it
    				alertDialog.show();
    			}
		});
		*/
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}

}
