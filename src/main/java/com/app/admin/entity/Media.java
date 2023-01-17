package com.app.admin.entity;

import java.io.Serializable;

public class Media implements Serializable {

	private String mediaId;
	private String url;
	private int width;
	private int height;
	
	public Media() {
		super();
	}

	public Media(String mediaId, String url, int width, int height) {
		super();
		this.mediaId = mediaId;
		this.url = url;
		this.width = width;
		this.height = height;
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	@Override
	public String toString() {
		return "Media [mediaId=" + mediaId + ", url=" + url + ", width=" + width + ", height=" + height + "]";
	}
	
}
