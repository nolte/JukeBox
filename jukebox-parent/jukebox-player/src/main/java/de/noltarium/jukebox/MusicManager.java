package de.noltarium.jukebox;

import de.noltarium.jukebox.model.PlayListItem;

public interface MusicManager {

	public void play();

	public void stop();

	public PlayListItem getCurrentTrack();

}
