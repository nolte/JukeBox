package de.noltarium.jukebox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MusicManagerImpl implements MusicManager {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(MusicManagerImpl.class);
	Thread t;
	AdvancedPlayer player;

	public MusicManagerImpl() throws FileNotFoundException, JavaLayerException {

	}

	@Override
	public void play() {

		InputStream stream;
		try {
			stream = new FileInputStream(
					"/home/nolte/workspace/start/jukebox-server/src/main/resources/test.mp3");

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
	}

	@Override
	public void stop() {
		LOGGER.debug("stop");
		player.close();

	}

}
