package org.mediasport.animals;

import java.util.ArrayList;

public class KnowledgeBase {
	ArrayList<KnowledgeItem> knowItemList = new ArrayList<KnowledgeItem>();
	KnowledgeItem firstPosition = null; 
	KnowledgeItem currentPosition = null; 
	
	public KnowledgeBase() {
		KnowledgeItem man = new KnowledgeItem(false, "слон", null, null);
		KnowledgeItem bird = new KnowledgeItem(false, "голубь", null, null);
		KnowledgeItem canFlight = new KnowledgeItem(true, "умеет летать", bird, null);
		firstPosition = new KnowledgeItem(true, "млекопетающее", man, canFlight);
		
		knowItemList.add(firstPosition);
		knowItemList.add(canFlight);
		knowItemList.add(bird);
		knowItemList.add(man);
	}
	
	public void runNewGame() {
		currentPosition = firstPosition;
	}
	
	public String getQuestion() {
		if (currentPosition.isFeature)
			return "Это животное "+ currentPosition.text + "?";
		else
			return "Это "+ currentPosition.text + "?";
	}
	
	public EngineAnswer doResumeGame(boolean userAnswer) {
		if (currentPosition.isFeature) {
			if (userAnswer) {
				currentPosition = currentPosition.itemIfYes;
				return new EngineAnswer(getQuestion(), AnswerType.NextQuestion, true);
			}
			else 
			{
				if (currentPosition.itemIfNo == null)
					return new EngineAnswer("Я сдаюсь. Кто это?", AnswerType.IDefeat, false);
				else {
					currentPosition = currentPosition.itemIfNo;
					return new EngineAnswer(getQuestion(), AnswerType.NextQuestion, true);
				}
			}
		}
		else 
		{
			if (userAnswer) {
				return new EngineAnswer("Я отгадал! Ура!", AnswerType.IWin, false);
			}
			else 
			{
				return new EngineAnswer("Я сдаюсь. Кто это?", AnswerType.IDefeat, false);
			}
		}
	}
	
	public void addNewKnowledge(String feature, String animal) {
		
	}
}
