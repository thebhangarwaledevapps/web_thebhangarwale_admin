package com.app.admin.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

public class BhangarwaleFacebookPosts implements Serializable {

	private String postId;
	private String pageTitle;
	private String pageImage;
	private String message;
	private String postType;
	private List<Media> media;
	private String pageUrl;
	private String postDate;
	
	public BhangarwaleFacebookPosts() {
		super();
	}

	public BhangarwaleFacebookPosts(String postId, String pageTitle, String pageImage, String message, String postType,
                                    List<Media> media, String pageUrl, String postDate) {
		super();
		this.postId = postId;
		this.pageTitle = pageTitle;
		this.pageImage = pageImage;
		this.message = message;
		this.postType = postType;
		this.media = media;
		this.pageUrl = pageUrl;
		this.postDate = postDate;
	}

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public String getPageTitle() {
		return pageTitle;
	}

	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}

	public String getPageImage() {
		return pageImage;
	}

	public void setPageImage(String pageImage) {
		this.pageImage = pageImage;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPostType() {
		return postType;
	}

	public void setPostType(String postType) {
		this.postType = postType;
	}

	public List<Media> getMedia() {
		return media;
	}

	public void setMedia(List<Media> media) {
		this.media = media;
	}

	public String getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}

	public String getPostDate() {
		return postDate;
	}

	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}

	@Override
	public String toString() {
		return "BhangarwaleFacebookPosts [postId=" + postId + ", pageTitle=" + pageTitle + ", pageImage=" + pageImage
				+ ", message=" + message + ", postType=" + postType + ", media=" + media + ", pageUrl=" + pageUrl
				+ ", postDate=" + postDate + "]";
	}

}
