package org.mediasport.matchcenter.ui.mainmenu;

public class MainMenuItem {

	private String name;
	private int imageID;
	
	public MainMenuItem() {
	}	
	
	public MainMenuItem(String name, int imageID) {
		super();
		this.name = name;
		this.imageID = imageID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getImageID() {
		return imageID;
	}
	public void setImageID(int imageID) {
		this.imageID = imageID;
	}
}
