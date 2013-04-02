package org.mediasport.matchcenter.docs;

import java.io.Serializable;

public class Event implements Serializable {
	private long time = 0;
	private Player playerAuthor;
	private Player playerAssist;
	private Team team;
	private EEventType eventType;
	
	public Event() {
	}	
	public Event(long time, Player playerAuthor, Player playerAssist,
			Team team, EEventType eventType) {
		super();
		this.time = time;
		this.playerAuthor = playerAuthor;
		this.playerAssist = playerAssist;
		this.team = team;
		this.eventType = eventType;
	}
	
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public Player getPlayerAuthor() {
		return playerAuthor;
	}
	public void setPlayerAuthor(Player playerAuthor) {
		this.playerAuthor = playerAuthor;
	}
	public Player getPlayerAssist() {
		return playerAssist;
	}
	public void setPlayerAssist(Player playerAssist) {
		this.playerAssist = playerAssist;
	}
	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
	}
	public EEventType getEventType() {
		return eventType;
	}
	public void setEventType(EEventType eventType) {
		this.eventType = eventType;
	}
	
	public String getStringTime() {
		return Util.getStringTime(time);
	}
	
	public String getTypeDescription() {
		if (eventType != null)
		{
			switch (eventType) {
			case GOAL:
				return "Гол";
			case FREEKICK_GOAL:
				return "Гол со штрафного удара";
			case PENALTY:
				return "Гол с пенальти";
			case PENALTY_NOGOAL_KEEPER:
				return "Гол с пенальти не забит (вратарь)";
			case PENALTY_NOGOAL_OUT:
				return "Гол с пенальти не забит (удар мимо ворот)";
			case YELLOW:
				return "Желтая карточка";
			case OWNGOAL:
				return "Гол в свои ворота";
			case RED:
				return "Красная карточка";
			case SHOOTIN:
				return "Удар в створ";
			case SHOOTOUT:
				return "Удар мимо ворот";
			case CORNER:
				return "Угловой удар";
			case FOUL:
				return "Фол";
			case COMMENT:
				return "Комментарий";
			default:
				break;
			}
		}
		return "Unknown event type";
	}
	
	@Override
	public String toString() {
		String typeDescription = (!getTypeDescription().equals("") ? getTypeDescription() : "" );
		String teamName = (!team.getShortName().equals("") ? team.getShortName() : "" );
		
		return Util.getStringTime(time) + " " + typeDescription + " "+teamName;
	}
}
