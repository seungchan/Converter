package com.converter;

import java.util.List;

import com.converter.Site;

/**
 * A {@code Sites} that defines the output format of json data.
 * @author seungchan
 *
 */
public class Sites {
	private String collectionId;
	private List<Site> sites;

	public String getCollectionId() {
		return collectionId;
	}

	public void setCollectionId(String collectionId) {
		this.collectionId = collectionId;
	}

	public List<Site> getSites() {
		return sites;
	}

	public void setSites(List<Site> sites) {
		this.sites = sites;
	}
}