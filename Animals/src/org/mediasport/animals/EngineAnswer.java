package org.mediasport.animals;

public class EngineAnswer {
	public EngineAnswer(String text, AnswerType answerType, boolean resumeGame) {
		super();
		this.text = text;
		this.answerType = answerType;
		this.resumeGame = resumeGame;
	}
	public String text = "";
	public AnswerType answerType = AnswerType.NextQuestion;
	public boolean resumeGame = false;
}
