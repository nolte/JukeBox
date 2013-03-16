package de.noltarium.jukebox.rest;

public class PlayListItemDTO {

	String title;

	String interpret;

	private Long playListIdentifier;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getInterpret() {
		return interpret;
	}

	public void setInterpret(String interpret) {
		this.interpret = interpret;
	}

	public Long getPlayListIdentifier() {
		return playListIdentifier;
	}

	public void setPlayListIdentifier(Long playListIdentifier) {
		this.playListIdentifier = playListIdentifier;
	}

}
