package de.noltarium.jukebox.playlist;

import java.io.File;

import de.noltarium.jukebox.model.PlayList;
import de.noltarium.jukebox.model.PlayListItem;

public interface PlayListManager {

	void importMediaFile(File file);

	PlayList getPlayList();

	void cleanPlayList();

	void voteForTrack(String username, PlayListItem item, Integer points);

}
