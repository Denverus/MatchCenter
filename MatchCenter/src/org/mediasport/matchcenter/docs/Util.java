package org.mediasport.matchcenter.docs;

import org.mediasport.matchcenter.engine.DataStore;
import org.mediasport.matchcenter.engine.Factory;
import org.mediasport.matchcenter.engine.MatchEngine;

public class Util {
	private static String convertToDoubleChar(long num) {
		if (num > 9)
			return ""+num;
		else
			return "0"+num;
	}	
	
	public static String getStringTime(long time) {
		long allSeconds = (long)time / 1000;
		long minutes = (long) allSeconds/60;
		long seconds = (long) allSeconds%60;
		String min = "";
		String sec = "";
		
		min = convertToDoubleChar(minutes);
		sec = convertToDoubleChar(seconds);

		return min+":"+sec;
	}		
	
	public static void createTestMatch() {
    	MatchEngine engine = Factory.getMatchEngine();
    	DataStore data = Factory.getDataStore();
    	
    	Team homeTeam =  data.findTeamByName(data.getChelseaName());
    	Team awayTeam =  data.findTeamByName(data.getMUName());
    	
    	engine.createMatch(homeTeam, awayTeam);
//		engine.addHomeEvent(null, null, EEventType.GOAL);
//		engine.addAwayEvent(null, null, EEventType.GOAL);
//		engine.addAwayEvent(null, null, EEventType.GOAL);
	}
}
