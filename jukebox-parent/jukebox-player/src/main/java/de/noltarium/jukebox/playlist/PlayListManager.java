package de.noltarium.jukebox.playlist;

import java.io.File;

import de.noltarium.jukebox.model.PlayList;

public interface PlayListManager {

	void importMediaFile(File file);

	PlayList getPlayList();

}
