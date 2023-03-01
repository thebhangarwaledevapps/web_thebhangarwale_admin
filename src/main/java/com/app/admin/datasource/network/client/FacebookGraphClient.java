package com.app.admin.datasource.network.client;

import com.app.admin.entity.BhangarwaleFacebookFeed;
import com.app.admin.entity.BhangarwaleFacebookPageDetail;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import com.app.admin.entity.BhangarwaleFacebookAcessTokenResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

public interface FacebookGraphClient {

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

    /*@GetMapping(value="/oauth/access_token")
    BhangarwaleFacebookAcessTokenResponse getAccessToken(
            @RequestParam("grant_type") String grantType,
            @RequestParam("client_id") String clientId,
            @RequestParam("client_secret") String clientSecret,
            @RequestParam("fb_exchange_token") String fbExchangeToken
    );

    @GetMapping(value = "/{page_id}")
    BhangarwaleFacebookPageDetail getPageDetail(
            @PathVariable("page_id") String pageId,
            @RequestParam("access_token") String accessToken,
            @RequestParam("fields") String fields
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
    );*/

}
