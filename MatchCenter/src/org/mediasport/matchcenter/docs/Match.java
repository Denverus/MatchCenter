package org.mediasport.matchcenter.docs;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Match {
	private Date date = null;
	private Team homeTeam = null;
	private Team awayTeam = null;
	private List<Event> eventList = new ArrayList<Event>();
	private List<Player> homePlayerList = new ArrayList<Player>();
	private List<Player> awayPlayerList = new ArrayList<Player>();
	
	public List<Player> getHomePlayerList() {
		return homePlayerList;
	}
	public void setHomePlayerList(List<Player> homePlayerList) {
		this.homePlayerList = homePlayerList;
	}
	public List<Player> getAwayPlayerList() {
		return awayPlayerList;
	}
	public void setAwayPlayerList(List<Player> awayPlayerList) {
		this.awayPlayerList = awayPlayerList;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Team getHomeTeam() {
		return homeTeam;
	}
	public void setHomeTeam(Team homeTeam) {
		this.homeTeam = homeTeam;
	}
	public Team getAwayTeam() {
		return awayTeam;
	}
	public void setAwayTeam(Team awayTeam) {
		this.awayTeam = awayTeam;
	}
	
	public void addEvent(Event event) {
		eventList.add(event);
	}
	
	public List<Event> getEventList() {
		return eventList;
	}	
	
	public int getHomeGoal() {
		int result = 0;
		
		for (Event event : eventList) {
			if ((event.getEventType() == EEventType.GOAL) && (event.getTeam() == homeTeam)) {
				result++;
			}
		}
		
		return result;
	}
	
	public int getAwayGoal() {
		int result = 0;
		
		for (Event event : eventList) {
			if ((event.getEventType() == EEventType.GOAL) && (event.getTeam() == awayTeam)) {
				result++;
			}
		}
		
		return result;
	}
	
	public String getStringScore() {
		return getHomeGoal() + ":"+ getAwayGoal();
	}
	
	public boolean isPlayerInSquad(Player player, Team team) {
		List<Player> currentSquad = homePlayerList;
		
		if (team != homeTeam)
			currentSquad = awayPlayerList;
		
		return currentSquad.contains(player);
	}

	public void movePlayerToSquad(Player player, Team team) {
		List<Player> squadList = homePlayerList;
		if (team == awayTeam)
			squadList = awayPlayerList;
		
		if (!squadList.contains(player))
			squadList.add(player);
	}

	public void removePlayerFromSquad(Player player, Team team) {
		List<Player> squadList = homePlayerList;
		if (team == awayTeam)
			squadList = awayPlayerList;
		
		if (squadList.contains(player))
			squadList.remove(player);
	}
	
	public void setPlayerSquadStatus(Player player, Team team, boolean isInSquad) {
		if (isInSquad)
			movePlayerToSquad(player, team);
		else
			removePlayerFromSquad(player, team);
	}
	
	@Override
	public String toString() {
		String homeTeamName = "";
		String awayTeamName = "";
		
		if (homeTeam != null)
			homeTeamName = homeTeam.getShortName();
		if (awayTeam != null)
			awayTeamName = awayTeam.getShortName();
		
		return homeTeamName + " - " + awayTeamName + " "+getStringScore();
	}
}
