package de.noltarium.jukebox.playlist;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileExistsException;
import org.apache.commons.io.FileUtils;
import org.farng.mp3.MP3File;
import org.farng.mp3.TagException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.noltarium.jukebox.model.PlayList;
import de.noltarium.jukebox.model.PlayListItem;

public class PlayListManagerImpl implements PlayListManager {

	private static final String INPUTFOLDER = "/tmp/sorted/";
	private static final Logger LOGGER = LoggerFactory.getLogger(PlayListManagerImpl.class);

	private final PlayList playList;

	public PlayListManagerImpl(PlayList playList) {
		this.playList = playList;
	}

	@Override
	public void importMediaFile(File file) {

		MP3File mp3file;
		PlayListItem item = null;
		File destFile = null;
		try {
			mp3file = new MP3File(file);
			item = new PlayListItem();
			item.setFile(mp3file);

			LOGGER.info("Artist: {}", item.getArtist());
			LOGGER.info("Album:{}", mp3file.getID3v2Tag().getAlbumTitle());
			LOGGER.info("Identifier: {}", mp3file.getID3v2Tag().getIdentifier());
			LOGGER.info("Title: {}", mp3file.getID3v2Tag().getSongTitle());

			destFile = new File(INPUTFOLDER + item.getArtist() + "-" + item.getAlbum() + ".mp3");

			// TODO: Zweifaches hochloden des selben liedes unterbinden ...
			FileUtils.moveFile(file, destFile);
			mp3file = new MP3File(destFile);
			item.setFile(mp3file);

		} catch (IOException e) {
			LOGGER.error("IOException {}", e);

			// if(e.getMessage())
			if (e instanceof FileExistsException) {
				LOGGER.warn("file in system");
				// remove the source file
				file.delete();
			} else {

				throw new RuntimeException(e);
			}
		} catch (TagException e) {
			LOGGER.error("TagException {}", e);
			throw new RuntimeException(e);
		}
		try {
			mp3file = new MP3File(destFile);
			item.setFile(mp3file);
			playList.add(item);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TagException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public PlayList getPlayList() {
		return playList;
	}

}
