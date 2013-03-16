package de.noltarium.jukebox;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MusicPlayThread implements Runnable {

	private static final Logger LOGGER = LoggerFactory.getLogger(MusicPlayThread.class);

	private AdvancedPlayer mediaPlayer;

	MusicPlayThread(AdvancedPlayer mediaPlayer, PlaybackListener listener) {
		this.mediaPlayer = mediaPlayer;
		mediaPlayer.setPlayBackListener(listener);

	}

	@Override
	public void run() {
		try {
			mediaPlayer.play();
			LOGGER.debug("end");
		} catch (JavaLayerException e) {
			LOGGER.error("JavaLayerException: {}", e);
			throw new RuntimeException(e);
		}
	}

}
