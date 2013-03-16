package de.noltarium.jukebox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.noltarium.jukebox.model.PlayList;
import de.noltarium.jukebox.model.PlayListItem;

public class MusicManagerImpl implements MusicManager {

	private static final Logger LOGGER = LoggerFactory.getLogger(MusicManagerImpl.class);
	Thread t;
	AdvancedPlayer player;

	PlayList playList;

	@Override
	public void play() {

		if (t == null || !t.isAlive()) {

			PlayListItem nextTrack = playList.getNextTrack();

			InputStream stream;
			try {
				stream = new FileInputStream(nextTrack.getPath());

				player = new AdvancedPlayer(stream);

				LOGGER.debug("play n");
				MusicPlayThread musicPlayThread = new MusicPlayThread(player);
				t = new Thread(musicPlayThread);
				t.start();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JavaLayerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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

}
