package de.noltarium.jukebox.model;

public class TrackVoting {

	private String username;

	private Integer points = 0;

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
