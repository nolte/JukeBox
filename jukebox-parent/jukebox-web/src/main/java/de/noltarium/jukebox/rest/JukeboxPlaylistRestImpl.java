package de.noltarium.jukebox.rest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sun.jersey.multipart.FormDataParam;

import de.noltarium.jukebox.playlist.PlayListManager;
import de.noltarium.jukebox.util.FileTypeChecker;
import de.noltarium.jukebox.util.PlayListMapperImpl;

@Component
@Path("/playlist")
public class JukeboxPlaylistRestImpl {
	private static final String AUDIO_MPEG = "audio/mpeg";

	private static final Logger LOGGER = LoggerFactory.getLogger(JukeboxPlaylistRestImpl.class);

	@Autowired
	FileTypeChecker fileTypeChecker;

	@Autowired
	PlayListManager playListManager;

	@Autowired
	PlayListMapperImpl playListMapper;

	/**
	 * Upload some File.
	 * 
	 * @param stream
	 *            The uploading file as {@link InputStream}
	 * @return If all right the Methode return HTTP Status Code 200.
	 * 
	 * @throws Exception
	 */
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response handleUpload(@FormDataParam("file") InputStream stream) throws Exception {
		LOGGER.trace("handleUpload start");
		File file = new File("/tmp/" + System.nanoTime() + ".mp3");

		FileOutputStream out = new FileOutputStream(file);
		IOUtils.copy(stream, out);
		stream.close();

		String mimeType = fileTypeChecker.getMimeTypeFromeFile(file);
		if (AUDIO_MPEG.equals(mimeType)) {
			LOGGER.debug("it is a media file");
			playListManager.importMediaFile(file);
		}

		return Response.ok("ok").build();
	}

	/**
	 * Get the Current Playlist.
	 * 
	 * @return The Current playlist as {@link PlayListDTO}
	 */
	@GET
	public Response getPlayList() {
		LOGGER.trace("getPlayList start");
		PlayListDTO mappedPlayList;
		mappedPlayList = playListMapper.mapPlaylist(playListManager.getPlayList());

		return Response.ok(mappedPlayList).build();
	}

	/**
	 * Remove all Tracks from the Playlist.
	 * 
	 * @return
	 */
	@DELETE
	public Response cleanPlayList() {
		playListManager.cleanPlayList();

		return Response.ok().build();
	}

}
