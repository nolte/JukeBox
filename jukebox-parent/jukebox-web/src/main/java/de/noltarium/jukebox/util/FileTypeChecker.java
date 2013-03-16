package de.noltarium.jukebox.util;

import java.io.File;

public interface FileTypeChecker {

	/**
	 * Get the Mime Type from any File.
	 * 
	 * @param file
	 *            the Source File.
	 * @return The MimeType As String
	 */
	String getMimeTypeFromeFile(File file);

}
