package de.noltarium.jukebox;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

public class MusicPlayThread implements Runnable {

	private AdvancedPlayer mediaPlayer;

	MusicPlayThread(AdvancedPlayer mediaPlayer) {
		this.mediaPlayer = mediaPlayer;

	}

	@Override
	public void run() {
		try {
			mediaPlayer.play();
		} catch (JavaLayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
