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
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
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

	/**
	 * Get Request to get the playlist Item.
	 * 
	 * @param playListItemId
	 *            The Item id to get.
	 * @return Return the {@link PlayListDTO} as Response (HTTP Status 200)<br>
	 *         if the Playlist item not found the methode return HTTP Status 202
	 *         (no content)
	 */
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

	/**
	 * Post Request to set some voting poings.
	 * 
	 * @param playListItemId
	 *            The PLaylist item to vote-
	 * @param points
	 *            added points.
	 * @return
	 */
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Secured("ROLE_USER")
	public Response vote(@PathParam("playListItemId") long playListItemId, @FormDataParam("points") Integer points) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		LOGGER.debug("User: {} try to vote for track {}", user.getUsername(), playListItemId);

		PlayListItem item = playList.getItem(playListItemId);

		if (item != null) {
			item.setVotingPoints(item.getVotingPoints() + points);
			PlayListItemDTO mappedItem = playListMapper.mapPlayListItem(item);
			return Response.ok(mappedItem).build();
		} else {
			return Response.noContent().build();
		}

	}

	/**
	 * Delete the item from the Playlist.
	 * 
	 * @param playListItemId
	 *            the Playlist item id to remove.
	 * @return
	 */
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
