package org.mediasport.animals;

public class KnowledgeItem {
	public boolean isFeature = false;
	public String text = ""; 
	public KnowledgeItem itemIfNo = null;
	public KnowledgeItem itemIfYes = null;
	
	public KnowledgeItem(boolean isFeature, String text, KnowledgeItem itemIfYes, KnowledgeItem itemIfNo) {
		super();
		this.text = text;
		this.isFeature = isFeature;
		this.itemIfYes = itemIfYes;
		this.itemIfNo = itemIfNo;
	}
}
