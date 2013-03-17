package de.noltarium.jukebox.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sun.jersey.multipart.FormDataParam;

import de.noltarium.jukebox.model.PlayList;
import de.noltarium.jukebox.model.PlayListItem;
import de.noltarium.jukebox.util.PlayListMapperImpl;

@Component
@Path("/playlist/{playListItemId}")
public class JukeboxPlaylistItemRestImpl {

	private static final Logger LOGGER = LoggerFactory.getLogger(JukeboxPlaylistItemRestImpl.class);

	@Autowired
	PlayList playList;

	@Autowired
	PlayListMapperImpl playListMapper;

	@GET
	public Response getPlayListItem(@PathParam("playListItemId") long playListItemId) {
		LOGGER.trace("getPlayListItem start {}", playListItemId);
		PlayListItem item = playList.getItem(playListItemId);

		if (item != null) {

			PlayListItemDTO mappedItem = playListMapper.mapPlayListItem(item);
			return Response.ok(mappedItem).build();
		} else {
			return Response.noContent().build();
		}

	}

	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response vote(@PathParam("playListItemId") long playListItemId, @FormDataParam("points") Integer points) {
		PlayListItem item = playList.getItem(playListItemId);

		if (item != null) {
			item.setVotingPoints(item.getVotingPoints() + points);
			PlayListItemDTO mappedItem = playListMapper.mapPlayListItem(item);
			return Response.ok(mappedItem).build();
		} else {
			return Response.noContent().build();
		}

	}

	@DELETE
	public Response removeTrack(@PathParam("playListItemId") long playListItemId) {
		PlayListItem item = playList.getItem(playListItemId);

		if (item != null) {
			playList.remove(item);
			return Response.ok().build();
		} else {
			return Response.noContent().build();
		}

	}

}
