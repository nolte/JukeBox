package de.noltarium.jukebox.util;

import org.dozer.Mapper;

import de.noltarium.jukebox.model.PlayList;
import de.noltarium.jukebox.model.PlayListItem;
import de.noltarium.jukebox.rest.PlayListDTO;
import de.noltarium.jukebox.rest.PlayListItemDTO;

public class PlayListMapperImpl {

	Mapper mapper;

	public PlayListDTO mapPlaylist(PlayList playList) {

		PlayListDTO playListDto = mapper.map(playList, PlayListDTO.class);

		return playListDto;
	}

	public PlayListItemDTO mapPlayListItem(PlayListItem item) {

		PlayListItemDTO itemDTO = mapper.map(item, PlayListItemDTO.class);

		return itemDTO;
	}

	public Mapper getMapper() {
		return mapper;
	}

	public void setMapper(Mapper mapper) {
		this.mapper = mapper;
	}

}
