package com.converter;

import com.converter.SiteDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * A {@code Site} that defines fields for a site.
 * @author seungchan
 *
 */
@JsonDeserialize(using = SiteDeserializer.class)
public class Site {
	private String id;
	private String name;
	private int mobile;
	private String keywords;
	private String score;
	
	public Site() {	
	}
	
	public Site(String id, String name, int mobile, String keywords, String score) {
		this.id = id;
		this.name = name;
		this.mobile = mobile;
		this.keywords = keywords;
		this.score = score;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMobile() {
		return mobile;
	}
	public void setMobile(int mobile) {
		this.mobile = mobile;
	}
	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	
	@Override
	public String toString(){
		return "id="+id+",name="+name+",mobile="+ mobile +",score="+score+"\n";
	}
}