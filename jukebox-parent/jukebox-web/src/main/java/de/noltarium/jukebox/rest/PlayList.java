package de.noltarium.jukebox.rest;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PlayList {
	List<PlayListItem> list;

	public List<PlayListItem> getList() {
		return list;
	}

	public void setList(List<PlayListItem> list) {
		this.list = list;
	}

}
