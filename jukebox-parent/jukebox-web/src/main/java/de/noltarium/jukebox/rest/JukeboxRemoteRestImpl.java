package de.noltarium.jukebox.rest;

import javax.annotation.PostConstruct;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import de.noltarium.jukebox.MusicManager;
import de.noltarium.jukebox.MusicManagerImpl;

@Component
@Path("/controll")
public class JukeboxRemoteRestImpl {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(JukeboxRemoteRestImpl.class);

	@Autowired
	MusicManager musicManager;

	@GET
	@Path("/play")
	public Response play() {

		System.out.println("play");

		musicManager.play();

		return Response.status(200).entity("hallo").build();

	}

	@GET
	@Path("/stop")
	public Response stop() {

		System.out.println("stop");

		musicManager.stop();

		return Response.status(200).entity("stop").build();

	}
	
	
	
}
