package com.app.admin.entity;

public class BhangarwaleFacebookPageDetail {

	private String title;
	private String image;
	
	public BhangarwaleFacebookPageDetail() {}

	public BhangarwaleFacebookPageDetail(String title, String image) {
		super();
		this.title = title;
		this.image = image;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "BhangarwaleFacebookPageDetail [title=" + title + ", image=" + image + "]\n\n";
	}
	
}
