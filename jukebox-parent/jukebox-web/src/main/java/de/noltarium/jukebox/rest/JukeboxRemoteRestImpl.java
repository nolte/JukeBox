package de.noltarium.jukebox.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import de.noltarium.jukebox.MusicManager;
import de.noltarium.jukebox.model.PlayListItem;
import de.noltarium.jukebox.util.PlayListMapperImpl;

/**
 * Music Player Remote Serice.
 * 
 * @author nolte
 * 
 */
@Component
@Path("/controll")
public class JukeboxRemoteRestImpl {
	private static final Logger LOGGER = LoggerFactory.getLogger(JukeboxRemoteRestImpl.class);

	@Autowired
	MusicManager musicManager;

	@Autowired
	PlayListMapperImpl playListMapper;

	/**
	 * Play the next element from the Playlist.
	 * 
	 * @return
	 */
	@GET
	@Path("/play")
	@Secured("ROLE_ADMIN")
	public Response play() {
		LOGGER.debug("play track");

		musicManager.play();

		return Response.status(200).entity("hallo").build();

	}

	/**
	 * 
	 * @return
	 */
	@GET
	@Path("/stop")
	@Secured("ROLE_ADMIN")
	public Response stop() {
		LOGGER.debug("stop track");

		musicManager.stop();

		return Response.status(200).entity("stop").build();

	}

	/**
	 * Get the current playing track
	 * 
	 * @return
	 */
	@GET
	public Response getCurrentTrack() {
		LOGGER.debug("get the curent track");
		PlayListItem track = musicManager.getCurrentTrack();

		if (track == null) {
			LOGGER.debug("no track are playing");
			return Response.noContent().build();
		} else {
			PlayListItemDTO mappedTrack = playListMapper.mapPlayListItem(track);
			return Response.ok(mappedTrack).build();
		}

	}
}
