package de.noltarium.jukebox.model;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PlayList {

	private static final Logger LOGGER = LoggerFactory.getLogger(PlayList.class);

	private List<PlayListItem> items = new ArrayList<PlayListItem>();

	public List<PlayListItem> getItems() {
		return items;
	}

	public void setItems(List<PlayListItem> items) {
		this.items = items;
	}

	public void add(PlayListItem item) {
		LOGGER.trace("add");

		item.setPlayListIdentifier(System.nanoTime());
		items.add(item);

	}

	public PlayListItem getNextTrack() throws NullPointerException {
		LOGGER.trace("getNextTrack");
		if (items.size() > 0)
			return items.get(0);
		else
			throw new NullPointerException("no track");
	}

	public PlayListItem getItem(long playListItemId) {
		for (PlayListItem item : items) {
			if (playListItemId == item.getPlayListIdentifier()) {
				return item;
			}
		}

		return null;
	}

	public void remove(PlayListItem nextTrack) {
		items.remove(nextTrack);
	}
}
