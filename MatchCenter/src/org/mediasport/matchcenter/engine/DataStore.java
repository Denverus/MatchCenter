package org.mediasport.matchcenter.engine;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.mediasport.matchcenter.R;
import org.mediasport.matchcenter.docs.Match;
import org.mediasport.matchcenter.docs.Player;
import org.mediasport.matchcenter.docs.Team;

public class DataStore {
	List<Team> teamList = new ArrayList<Team>();
	List<Player> playerList = new ArrayList<Player>();
	List<Match> matchList = new ArrayList<Match>();
	
	public DataStore() {
		loadTestDate();
	}
	
	public void addTeam(Team team) {
		teamList.add(team);
	}
	
	public void addTeam(String name, String shortName) {
		Team team = new Team(name, shortName);
		teamList.add(team);
	}
	
	public List<Team> getTeamList() {
		return teamList;
	}

	public void addPlayer(Player player, Team team) {
		playerList.add(player);
		player.setTeam(team);
	}
	
	public List<String> getTeamStringList() {
		ArrayList<String> teamStringList = new ArrayList<String>();
		
		for (Team team : teamList) {
			teamStringList.add(team.getName());
		}
		return teamStringList;
	}
	
	public Team findTeamByName(String name) {
		for (Team team : teamList) {
			if (team.getName().equals(name))
				return team;
		}
		
		return null;
	}
	
	public List<Player> getPlayerListByTeam(Team team) {
		List<Player> resultList = new ArrayList<Player>();
		for (Player player : playerList) {
			if (player.getTeam() == team)
				resultList.add(player);
		}
		
		return resultList;
	}
	
	public List<Player> getPlayerList() {
		return playerList;
	}

	public List<Match> getMatchList() {
		return matchList;
	}

	public void addMatch(Match newMatch) {
		matchList.add(newMatch);
	}

	public String getMUName() {
		return "Манчестер Юнайтед";
	}

	public String getChelseaName() {
		return "Челси";
	}
	
	protected void loadTestDate() {
		Team team1 = new Team("Манчестер Юнайтед", "Ман.Юн.");
		Team team2 = new Team("Челси", "Челси");
		
		addPlayer(new Player("Рио", "Фердинанд"), team1);
		addPlayer(new Player("Райан", "Гигз"), team1);
		addPlayer(new Player("Уэйн", "Руни"), team1);
		
		addPlayer(new Player("Фрэнк", "Лэмпард"), team2);
		addPlayer(new Player("Петер", "Чех"), team2);
		addPlayer(new Player("Джон", "Терри"), team2);
		
		teamList.add(team1);
		teamList.add(team2);
		
		Match match = new Match();
		match.setHomeTeam(team2);
		match.setAwayTeam(team1);
		match.setDate(new Date());
		matchList.add(match);
	}
}
