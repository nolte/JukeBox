package de.noltarium.jukebox.model;

import java.util.ArrayList;
import java.util.List;

import org.farng.mp3.MP3File;

public class PlayListItem {

	private Long playListIdentifier;

	private String path;

	private List<TrackVoting> votings = new ArrayList<TrackVoting>();

	public MP3File file;

	public PlayListItem(Long playListIdentifier, String path, MP3File file) {
		super();
		this.playListIdentifier = playListIdentifier;
		this.path = path;
		this.file = file;
	}

	public MP3File getFile() {
		return file;
	}

	public void setFile(MP3File file) {
		this.file = file;
	}

	public String getArtist() {
		if (file.hasID3v2Tag()) {
			return file.getID3v2Tag().getLeadArtist();
		} else if (file.hasID3v1Tag()) {
			return file.getID3v1Tag().getArtist();
		} else {
			return "unknown";
		}
	}

	public String getAlbum() {
		if (file.hasID3v2Tag()) {
			return file.getID3v2Tag().getAlbumTitle();
		} else if (file.hasID3v1Tag()) {
			return file.getID3v1Tag().getAlbumTitle();
		} else {
			return "unknown";
		}

	}

	public String getTitle() {
		if (file.hasID3v2Tag()) {
			return file.getID3v2Tag().getSongTitle();
		} else if (file.hasID3v1Tag()) {
			return file.getID3v1Tag().getSongTitle();
		} else {
			return "unknown";
		}
	}

	public Long getPlayListIdentifier() {
		return playListIdentifier;
	}

	public void setPlayListIdentifier(Long playListIdentifier) {
		this.playListIdentifier = playListIdentifier;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void addVoting(TrackVoting voting) {
		votings.add(voting);
	}

	public Integer getVotingPoints() {
		Integer votingPoints = 0;

		for (TrackVoting voting : votings) {
			votingPoints += voting.getPoints();
		}
		return votingPoints;
	}

}
