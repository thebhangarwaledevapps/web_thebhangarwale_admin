package com.app.admin.entity;

import java.util.ArrayList;

public class BhangarwaleFacebookFeed {
	
	private String nextPageToken;
	private ArrayList<BhangarwaleFacebookPosts> bhangarwaleFacebookFeedPosts;

	public String getNextPageToken() {
		return nextPageToken;
	}

	public void setNextPageToken(String nextPageToken) {
		this.nextPageToken = nextPageToken;
	}

	public ArrayList<BhangarwaleFacebookPosts> getBhangarwaleFacebookFeedPosts() {
		return bhangarwaleFacebookFeedPosts;
	}

	public void setBhangarwaleFacebookFeedPosts(ArrayList<BhangarwaleFacebookPosts> bhangarwaleFacebookFeedPosts) {
		this.bhangarwaleFacebookFeedPosts = bhangarwaleFacebookFeedPosts;
	}

	@Override
	public String toString() {
		return "BhangarwaleFacebookFeed [nextPageToken=" + nextPageToken + ", bhangarwaleFacebookFeedPosts="
				+ bhangarwaleFacebookFeedPosts + "]";
	}
	
}
