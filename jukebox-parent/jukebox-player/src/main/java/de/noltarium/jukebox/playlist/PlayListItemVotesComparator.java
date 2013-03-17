package de.noltarium.jukebox.playlist;

import java.util.Comparator;

import de.noltarium.jukebox.model.PlayListItem;

public class PlayListItemVotesComparator implements Comparator<PlayListItem> {
	/**
	 * Compare two {@link PlayListItem} by existing votes.
	 */
	@Override
	public int compare(PlayListItem base, PlayListItem compareObject) {
		if (base.getVotingPoints() < compareObject.getVotingPoints()) {
			return 1;
		} else if (base.getVotingPoints() == compareObject.getVotingPoints()) {

			if (base.getPlayListIdentifier() < compareObject.getPlayListIdentifier()) {
				return -1;
			} else {
				return 1;
			}

		} else {
			return -1;
		}
	}

}
