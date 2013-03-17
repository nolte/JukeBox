package de.noltarium.jukebox.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.noltarium.jukebox.MusicManager;
import de.noltarium.jukebox.model.PlayListItem;
import de.noltarium.jukebox.util.PlayListMapperImpl;

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
	public Response play() {

		System.out.println("play");

		musicManager.play();

		return Response.status(200).entity("hallo").build();

	}

	/**
	 * 
	 * @return
	 */
	@GET
	@Path("/stop")
	public Response stop() {

		System.out.println("stop");

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

		PlayListItem track = musicManager.getCurrentTrack();

		if (track == null) {
			return Response.noContent().build();
		} else {
			PlayListItemDTO mappedTrack = playListMapper.mapPlayListItem(track);
			return Response.ok(mappedTrack).build();
		}

	}
}
