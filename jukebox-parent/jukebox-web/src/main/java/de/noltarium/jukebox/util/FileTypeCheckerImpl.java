package de.noltarium.jukebox.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.HttpHeaders;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.metadata.TikaMetadataKeys;
import org.apache.tika.mime.MediaType;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import de.noltarium.jukebox.rest.JukeboxRemoteRestImpl;

public class FileTypeCheckerImpl implements FileTypeChecker {

	private static final Logger LOGGER = LoggerFactory.getLogger(JukeboxRemoteRestImpl.class);

	@Override
	public String getMimeTypeFromeFile(File file) {

		AutoDetectParser parser = new AutoDetectParser();
		Map<MediaType, Parser> arg0 = new HashMap<MediaType, Parser>();
		parser.setParsers(arg0);

		Metadata metadata = new Metadata();
		metadata.add(TikaMetadataKeys.RESOURCE_NAME_KEY, file.getName());

		InputStream stream2 = null;
		try {
			stream2 = new FileInputStream(file);
			parser.parse(stream2, new DefaultHandler(), metadata, new ParseContext());
			String mimeType = metadata.get(HttpHeaders.CONTENT_TYPE);
			return mimeType;
		} catch (FileNotFoundException e) {
			LOGGER.error("FileNotFound: {}", e);
			throw new RuntimeException(e);
		} catch (IOException e) {
			LOGGER.error("IOException: {}", e);
			throw new RuntimeException(e);
		} catch (SAXException e) {
			LOGGER.error("SAXException: {}", e);
			throw new RuntimeException(e);
		} catch (TikaException e) {
			LOGGER.error("TikaException: {}", e);
			throw new RuntimeException(e);
		} finally {
			try {
				stream2.close();
			} catch (IOException e) {
				LOGGER.error("IOException e: {}", e);
				throw new RuntimeException(e);
			}
		}

	}
}