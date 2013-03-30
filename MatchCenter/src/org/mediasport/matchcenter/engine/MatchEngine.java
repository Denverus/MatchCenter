package org.mediasport.matchcenter.engine;

import java.util.Date;
import java.util.List;

import org.mediasport.matchcenter.docs.EEventType;
import org.mediasport.matchcenter.docs.Event;
import org.mediasport.matchcenter.docs.Match;
import org.mediasport.matchcenter.docs.Player;
import org.mediasport.matchcenter.docs.Team;
import org.mediasport.matchcenter.docs.Util;

public class MatchEngine {
	Match match = new Match();
	private long time = 0; 
	
	public Match getMatch() {
		return match;
	}
	private void setMatch(Match match) {
		this.match = match;
	}

	public void createMatch(Team homeTeam, Team awayTeam) {
		match = new Match();
		match.setHomeTeam(homeTeam);
		match.setAwayTeam(awayTeam);
		match.setDate(new Date());
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	
	public void addTime(long addedTime) {
		time += addedTime;
	}
	
	public Event createEvent(long time, Team team, Player player) {
		Event newEvent = new Event();
		
		newEvent.setTime(time);
		newEvent.setTeam(team);
		newEvent.setPlayerAuthor(player);
		
		return newEvent;
	}
	
	public Event createEvent(Team team, Player player) {
		Event newEvent = new Event();
		
		newEvent.setTime(this.time);
		newEvent.setTeam(team);
		newEvent.setPlayerAuthor(player);
		
		return newEvent;
	}	

	public Event createEvent() {
		Event newEvent = new Event();
		
		newEvent.setTime(this.time);
		
		return newEvent;
	}		
	public Event createEvent(Player player) {
		Event newEvent = new Event();
		
		newEvent.setTime(this.time);
		newEvent.setPlayerAuthor(player);
		
		return newEvent;
	}	

	public Event createEvent(Team team) {
		Event newEvent = new Event();
		
		newEvent.setTime(this.time);
		newEvent.setTeam(team);
		
		return newEvent;
	}
	
	public void addEvent(Event event) {
		event.setTime(time);
		match.addEvent(event);
	}
	
	public void addHomeEvent(Player author, Player assistent, EEventType eventType) {
		Event event = new Event();
		event.setPlayerAuthor(author);
		event.setPlayerAssist(assistent);
		event.setEventType(eventType);
		event.setTime(time);
		event.setTeam(match.getHomeTeam());
		match.addEvent(event);
	}
	
	public void addAwayEvent(Player author, Player assistent, EEventType eventType) {
		Event event = new Event();
		event.setPlayerAuthor(author);
		event.setPlayerAssist(assistent);
		event.setEventType(eventType);		
		event.setTime(time);
		event.setTeam(match.getAwayTeam());
		match.addEvent(event);
	}
	
	public List<Event> getEventList() {
		return match.getEventList();
	}
	
	public CharSequence getStringTime() {
		return Util.getStringTime(time);
	}
	
	public boolean teamIsHome(Team team) {
		if (match.getHomeTeam() == team)
			return true;
		return false;
	}
}
