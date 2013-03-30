package org.mediasport.animals;

public class EngineFactory {
	static KnowledgeBase knowledgeBase = null;
	
	public static KnowledgeBase getKnowledgeBase() {
		if (knowledgeBase == null)
			knowledgeBase = new KnowledgeBase();
		
		return knowledgeBase;
	}
}