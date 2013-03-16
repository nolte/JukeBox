package de.noltarium.jukebox.rest;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PlayListDTO {
	List<PlayListItemDTO> items;

	public List<PlayListItemDTO> getItems() {
		return items;
	}

	public void setItems(List<PlayListItemDTO> items) {
		this.items = items;
	}

}
