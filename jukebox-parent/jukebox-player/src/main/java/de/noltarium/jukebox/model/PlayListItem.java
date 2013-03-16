package de.noltarium.jukebox.model;

import org.farng.mp3.MP3File;

public class PlayListItem {

	public MP3File file;

	public MP3File getFile() {
		return file;
	}

	public void setFile(MP3File file) {
		this.file = file;
	}

	public String getArtist() {
		return file.getID3v2Tag().getLeadArtist();
	}

	public String getAlbum() {
		return file.getID3v2Tag().getAlbumTitle();
	}

	public String getTitle() {
		return file.getID3v2Tag().getSongTitle();
	}

}
