package org.Java8;

public class DVDInfo {
	String title;
	String genre;
	String leadActor;
	
	public DVDInfo(String title, String genre, String leadActor) {
		super();
		this.title = title;
		this.genre = genre;
		this.leadActor = leadActor;
	}
	
	
	@Override
	public String toString() {
		return "DVDInfo [title=" + title + ", genre=" + genre + ", leadActor=" + leadActor + "]";
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getGenre() {
		return genre;
	}


	public void setGenre(String genre) {
		this.genre = genre;
	}


	public String getLeadActor() {
		return leadActor;
	}


	public void setLeadActor(String leadActor) {
		this.leadActor = leadActor;
	}

	
	
}
