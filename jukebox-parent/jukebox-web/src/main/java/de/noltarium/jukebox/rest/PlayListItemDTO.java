package de.noltarium.jukebox.rest;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PlayListItemDTO {

	String title;

	String interpret;

	private Integer votingPoints;

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

	public Integer getVotingPoints() {
		return votingPoints;
	}

	public void setVotingPoints(Integer votingPoints) {
		this.votingPoints = votingPoints;
	}

}
