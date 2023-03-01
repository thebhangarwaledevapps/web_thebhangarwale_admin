package com.app.admin.util.facebook;

import com.app.admin.entity.BhangarwaleFacebookFeed;
import com.app.admin.entity.BhangarwaleFacebookPosts;
import com.app.admin.entity.Media;
import feign.FeignException;
import feign.Response;
import feign.codec.DecodeException;
import feign.codec.Decoder;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class BhangarwaleFacebookAfterFirstFeedDecoder implements Decoder {

    @Override
    public Object decode(Response response, Type type) throws IOException, DecodeException, FeignException {
        try {
            InputStream inputStream = response.body().asInputStream();
            BufferedReader bR = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            StringBuilder responseStrBuilder = new StringBuilder();
            while ((line = bR.readLine()) != null) {
                responseStrBuilder.append(line);
            }
            inputStream.close();
            JSONObject result = new JSONObject(responseStrBuilder.toString());
            String nextPageToken = null;
            BhangarwaleFacebookFeed bhangarwaleFacebookFeed = new BhangarwaleFacebookFeed();
            if(result.has("paging")) {
                JSONObject pagingJsonObject = result.getJSONObject("paging");
                if(pagingJsonObject.has("cursors")) {
                    JSONObject cursorJsonObject = pagingJsonObject.getJSONObject("cursors");
                    if(pagingJsonObject.has("next")) {
                        if(cursorJsonObject.has("after")) {
                            nextPageToken = cursorJsonObject.getString("after");
                            bhangarwaleFacebookFeed.setNextPageToken(nextPageToken);
                        }
                    }
                }
            }
            ArrayList<BhangarwaleFacebookPosts> bhangarwaleFacebookFeedPosts = new ArrayList<BhangarwaleFacebookPosts>();
            JSONArray dataArray = result.getJSONArray("data");
            for (int i = 0; i < dataArray.length(); i++) {
                JSONObject dataObject = (JSONObject) dataArray.get(i);
                String id = null;
                if(dataObject.has("id")) {
                    id = dataObject.getString("id");
                }
                String postDate = null;
                if(dataObject.has("updated_time")) {
                    postDate = dataObject.getString("updated_time");
                }
                String pageUrl = null;
                if(dataObject.has("permalink_url")) {
                    pageUrl = dataObject.getString("permalink_url");
                }
                String message = null;
                if(dataObject.has("message")) {
                    message = dataObject.getString("message");
                }
                JSONObject attachmentsObject = null;
                if(dataObject.has("attachments")) {
                    attachmentsObject = dataObject.getJSONObject("attachments");
                }
                JSONArray data_Array = attachmentsObject.getJSONArray("data");
                JSONObject data_Array_Object = (JSONObject) data_Array.get(0);
                String postType = null;
                ArrayList<Media> media = null;
                if(data_Array_Object.getString("type").equals("album")) {
                    postType = "album";
                    if (data_Array_Object.has("subattachments")) {
                        JSONObject sub_Attachment_Object = data_Array_Object.getJSONObject("subattachments");
                        JSONArray sub_Attachment_Data_Array = sub_Attachment_Object.getJSONArray("data");
                        media = new ArrayList<Media>();
                        for (int j = 0; j < sub_Attachment_Data_Array.length(); j++) {
                            JSONObject sub_Attachment_Data_Array_Object = (JSONObject) sub_Attachment_Data_Array.get(j);
                            JSONObject target_sub_Attachment_Data_Array_Object = sub_Attachment_Data_Array_Object.getJSONObject("target");
                            String id_target_sub_Attachment_Data_Array_Object = target_sub_Attachment_Data_Array_Object.getString("id");
                            Media mediaObject = new Media();
                            mediaObject.setMediaId(id_target_sub_Attachment_Data_Array_Object);
                            JSONObject media_sub_Attachment_Data_Array_Object = sub_Attachment_Data_Array_Object.getJSONObject("media");
                            JSONObject image_media_sub_Attachment_Data_Array_Object = media_sub_Attachment_Data_Array_Object.getJSONObject("image");
                            String url = image_media_sub_Attachment_Data_Array_Object.getString("src");
                            int width = image_media_sub_Attachment_Data_Array_Object.getInt("width");
                            int height = image_media_sub_Attachment_Data_Array_Object.getInt("height");
                            mediaObject.setUrl(url);
                            mediaObject.setWidth(width);
                            mediaObject.setHeight(height);
                            media.add(mediaObject);
                        }
                    }
                }else if (data_Array_Object.getString("type").equals("photo")) {
                    postType = "photo";
                    JSONObject target_Data_Array_Object = data_Array_Object.getJSONObject("target");
                    String id_target_Data_Array_Object = target_Data_Array_Object.getString("id");
                    JSONObject media_Data_Array_Object = data_Array_Object.getJSONObject("media");
                    JSONObject image_Media_Data_Array_Object = media_Data_Array_Object.getJSONObject("image");
                    String url = image_Media_Data_Array_Object.getString("src");
                    int width = image_Media_Data_Array_Object.getInt("width");
                    int height = image_Media_Data_Array_Object.getInt("height");
                    Media mediaObject = new Media();
                    mediaObject.setMediaId(id_target_Data_Array_Object);
                    mediaObject.setUrl(url);
                    mediaObject.setWidth(width);
                    mediaObject.setHeight(height);
                    media = new ArrayList<Media>();
                    media.add(mediaObject);
                }else if (data_Array_Object.getString("type").equals("profile_media")) {
                    postType = "profile_media";
                    JSONObject target_Data_Array_Object = data_Array_Object.getJSONObject("target");
                    String id_target_Data_Array_Object = target_Data_Array_Object.getString("id");
                    JSONObject media_Data_Array_Object = data_Array_Object.getJSONObject("media");
                    JSONObject image_Media_Data_Array_Object = media_Data_Array_Object.getJSONObject("image");
                    String url = image_Media_Data_Array_Object.getString("src");
                    int width = image_Media_Data_Array_Object.getInt("width");
                    int height = image_Media_Data_Array_Object.getInt("height");
                    Media mediaObject = new Media();
                    mediaObject.setMediaId(id_target_Data_Array_Object);
                    mediaObject.setUrl(url);
                    mediaObject.setWidth(width);
                    mediaObject.setHeight(height);
                    media = new ArrayList<Media>();
                    media.add(mediaObject);
                }else if (data_Array_Object.getString("type").equals("video_inline")) {
                    postType = "video_inline";
                    JSONObject target_Data_Array_Object = data_Array_Object.getJSONObject("target");
                    String id_target_Data_Array_Object = target_Data_Array_Object.getString("id");
                    JSONObject media_Data_Array_Object = data_Array_Object.getJSONObject("media");
                    JSONObject image_Media_Data_Array_Object = media_Data_Array_Object.getJSONObject("image");
                    int width = image_Media_Data_Array_Object.getInt("width");
                    int height = image_Media_Data_Array_Object.getInt("height");
                    String url = media_Data_Array_Object.getString("source");
                    Media mediaObject = new Media();
                    mediaObject.setMediaId(id_target_Data_Array_Object);
                    mediaObject.setUrl(url);
                    mediaObject.setWidth(width);
                    mediaObject.setHeight(height);
                    media = new ArrayList<Media>();
                    media.add(mediaObject);
                }else if(data_Array_Object.getString("type").equals("share")) {
                    postType = "share";
                    JSONObject target_Data_Array_Object = data_Array_Object.getJSONObject("target");
                    String id_target_Data_Array_Object = target_Data_Array_Object.getString("id");
                    JSONObject media_Data_Array_Object = data_Array_Object.getJSONObject("media");
                    JSONObject image_Media_Data_Array_Object = media_Data_Array_Object.getJSONObject("image");
                    String url = image_Media_Data_Array_Object.getString("src");
                    int width = image_Media_Data_Array_Object.getInt("width");
                    int height = image_Media_Data_Array_Object.getInt("height");
                    Media mediaObject = new Media();
                    mediaObject.setMediaId(id_target_Data_Array_Object);
                    mediaObject.setUrl(url);
                    mediaObject.setWidth(width);
                    mediaObject.setHeight(height);
                    media = new ArrayList<Media>();
                    media.add(mediaObject);
                }else if(data_Array_Object.getString("type").equals("cover_photo")) {
                    postType = "cover_photo";
                    JSONObject target_Data_Array_Object = data_Array_Object.getJSONObject("target");
                    String id_target_Data_Array_Object = target_Data_Array_Object.getString("id");
                    JSONObject media_Data_Array_Object = data_Array_Object.getJSONObject("media");
                    JSONObject image_Media_Data_Array_Object = media_Data_Array_Object.getJSONObject("image");
                    String url = image_Media_Data_Array_Object.getString("src");
                    int width = image_Media_Data_Array_Object.getInt("width");
                    int height = image_Media_Data_Array_Object.getInt("height");
                    Media mediaObject = new Media();
                    mediaObject.setMediaId(id_target_Data_Array_Object);
                    mediaObject.setUrl(url);
                    mediaObject.setWidth(width);
                    mediaObject.setHeight(height);
                    media = new ArrayList<Media>();
                    media.add(mediaObject);
                }
                //init BhangarwaleFacebookFeed
                BhangarwaleFacebookPosts facebookPost = new BhangarwaleFacebookPosts();
                facebookPost.setPostId(id);
                facebookPost.setPostDate(postDate);
                facebookPost.setMessage(message);
                facebookPost.setPostType(postType);
                facebookPost.setMedia(media);
                facebookPost.setPageUrl(pageUrl);
                bhangarwaleFacebookFeedPosts.add(facebookPost);
            }
            bhangarwaleFacebookFeed.setBhangarwaleFacebookFeedPosts(bhangarwaleFacebookFeedPosts);
            return bhangarwaleFacebookFeed;
        } catch (Exception e) {
            return null;
        }
    }
}
