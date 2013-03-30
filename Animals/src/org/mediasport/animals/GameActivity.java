package org.mediasport.animals;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class GameActivity extends Activity {
	KnowledgeBase base = null;
	TextView questionView = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		
		base = EngineFactory.getKnowledgeBase();
		
		questionView = (TextView) findViewById(R.id.textViewQuastion);
		
		if (base != null)
			questionView.setText(base.getQuestion());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_game, menu);
		return true;
	}

    public void buttonYes_click(View v)
    {
    	EngineAnswer answer = base.doResumeGame(true);
    	questionView.setText(answer.text);
    	
    	switch (answer.answerType) {
		case NextQuestion:
			break;
		case IWin:
			break;
		case IDefeat:
			break;
		default:
			break;
		}
    }
    
    public void buttonNo_click(View v)
    {
    	EngineAnswer answer = base.doResumeGame(false);
    	questionView.setText(answer.text);
    	
    	switch (answer.answerType) {
		case NextQuestion:
			break;
		case IWin:
			break;
		case IDefeat:
			break;
		default:
			break;
		}
    } 	
}
