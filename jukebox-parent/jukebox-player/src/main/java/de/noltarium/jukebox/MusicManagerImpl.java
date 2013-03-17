package de.noltarium.jukebox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.noltarium.jukebox.model.PlayList;
import de.noltarium.jukebox.model.PlayListItem;

public class MusicManagerImpl extends PlaybackListener implements MusicManager {

	private static final Logger LOGGER = LoggerFactory.getLogger(MusicManagerImpl.class);
	Thread t;
	AdvancedPlayer player;

	PlayList playList;
	private PlayListItem currentTrack;

	@Override
	public void play() {

		if (t == null || !t.isAlive()) {
			playNextTrack();
		} else {
			LOGGER.debug("Thread is playing");
		}
	}

	@Override
	public void stop() {
		LOGGER.debug("stop");
		player.close();

	}

	public PlayList getPlayList() {
		return playList;
	}

	public void setPlayList(PlayList playList) {
		this.playList = playList;
	}

	public void playNextTrack() {
		try {
			currentTrack = playList.getNextTrack();
			playList.remove(currentTrack);
			InputStream stream;
			try {
				stream = new FileInputStream(currentTrack.getPath());

				player = new AdvancedPlayer(stream);

				LOGGER.debug("play n");
				MusicPlayThread musicPlayThread = new MusicPlayThread(player, this);
				t = new Thread(musicPlayThread);
				t.start();
			} catch (FileNotFoundException e) {
				LOGGER.error("FileNotFoundException: {}", e);
				throw new RuntimeException(e);
			} catch (JavaLayerException e) {
				LOGGER.error("JavaLayerException: {}", e);
				throw new RuntimeException(e);
			}
		} catch (NullPointerException e) {
			LOGGER.warn("no tracks in playlist {}", e);
		}

	}

	public void playbackFinished(PlaybackEvent evt) {
		LOGGER.warn("no tracks in playlist {}", evt);
		currentTrack = null;
		playNextTrack();
	}

	@Override
	public PlayListItem getCurrentTrack() {
		// TODO Auto-generated method stub
		return currentTrack;
	}

}
