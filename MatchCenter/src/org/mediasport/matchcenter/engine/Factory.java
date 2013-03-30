package org.mediasport.matchcenter.engine;

public class Factory {
	static DataStore dataStore = null;
	static MatchEngine matchEngine = null;
	
	public static DataStore getDataStore() {
		if (dataStore == null)
			dataStore = new DataStore();
		
		return dataStore;
	}
	
	public static MatchEngine getMatchEngine() {
		if (matchEngine == null)
			matchEngine = new MatchEngine();
		
		return matchEngine;
	}
}
