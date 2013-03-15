package de.noltarium.jukebox.rest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.farng.mp3.MP3File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sun.jersey.multipart.FormDataParam;

import de.noltarium.jukebox.MusicManager;

@Component
@Path("/playlist")
public class JukeboxPlaylistRestImpl {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(JukeboxPlaylistRestImpl.class);

	@Autowired
	MusicManager musicManager;

	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Path("/add")
	public Response handleUpload(@FormDataParam("file") InputStream stream)
			throws Exception {
		LOGGER.trace("handleUpload start");
		File file = new File("/tmp/" + System.nanoTime() + ".mp3");
		FileOutputStream out = new FileOutputStream(file);
		IOUtils.copy(stream, out);
		MP3File mp3file = new MP3File(file);
		LOGGER.info("Artist: {}", mp3file.getID3v2Tag().getLeadArtist());
		LOGGER.info("Album:{}", mp3file.getID3v2Tag().getAlbumTitle());
		LOGGER.info("Identifier: {}", mp3file.getID3v2Tag().getIdentifier());
		LOGGER.info("Title: {}", mp3file.getID3v2Tag().getSongTitle());
		File destFile = new File("/tmp/sorted/"
				+ mp3file.getID3v2Tag().getLeadArtist() + "-"
				+ mp3file.getID3v2Tag().getAlbumTitle() + ".mp3");
		FileUtils.moveFile(file, destFile);
		return Response.ok("ok").build();
	}

	@GET
	public Response getPlayList() {
		List<PlayListItem> list = new ArrayList<PlayListItem>();
		PlayList liste = new PlayList();
		
		PlayListItem item = new PlayListItem();
		item.setInterpret("test1");

		PlayListItem item2 = new PlayListItem();
		item2.setInterpret("test2");

		list.add(item2);
		list.add(item);
		liste.setList(list);
		return Response.ok(liste).build();
	}

}
