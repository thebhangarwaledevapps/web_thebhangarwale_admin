package com.app.admin.datasource.network;

import com.app.admin.entity.BhangarwaleFacebookFeed;
import com.app.admin.entity.BhangarwaleFacebookPageDetail;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import com.app.admin.entity.BhangarwaleFacebookAcessTokenResponse;

import java.util.Map;

public interface FacebookNetworkDataSource {

    @RequestLine("GET /v12.0/oauth/access_token")
    BhangarwaleFacebookAcessTokenResponse getAccessToken(
            @QueryMap Map<String, String> queryMap
    );

    @RequestLine("GET /v12.0/{page_id}")
    BhangarwaleFacebookPageDetail getPageDetail(
            @Param("page_id") String pageId,
            @QueryMap Map<String, String> queryMap
    );

    @RequestLine("GET /v12.0/{page_id}")
    BhangarwaleFacebookFeed getPosts(
            @Param("page_id") String pageId,
            @QueryMap Map<String, String> queryMap
    );

    @RequestLine("GET /v12.0/{page_id}/posts")
    BhangarwaleFacebookFeed getPostsAfterFirst(
            @Param("page_id") String pageId,
            @QueryMap Map<String, String> queryMap
    );

}
