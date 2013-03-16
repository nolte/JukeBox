package de.noltarium.jukebox.util;

import org.dozer.Mapper;

import de.noltarium.jukebox.model.PlayList;
import de.noltarium.jukebox.rest.PlayListDTO;

public class PlayListMapperImpl {

	Mapper mapper;

	public PlayListDTO mapPlaylist(PlayList playList) {

		PlayListDTO playListDto = mapper.map(playList, PlayListDTO.class);

		return playListDto;
	}

	public Mapper getMapper() {
		return mapper;
	}

	public void setMapper(Mapper mapper) {
		this.mapper = mapper;
	}

}
