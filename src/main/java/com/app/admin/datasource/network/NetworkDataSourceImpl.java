package com.app.admin.datasource.network;

import com.app.admin.datasource.network.client.FacebookGraphClient;
import com.app.admin.entity.BhangarwaleFacebookFeed;
import com.app.admin.entity.BhangarwaleFacebookPageDetail;
import com.app.admin.util.facebook.BhangarwaleFacebookAfterFirstFeedDecoder;
import com.app.admin.util.facebook.BhangarwaleFacebookFeedDecoder;
import com.app.admin.util.facebook.BhangarwaleFacebookPageDetailDecoder;
import feign.Feign;
import feign.gson.GsonDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.core.env.Environment;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

@Service
public class NetworkDataSourceImpl implements NetworkDataSource {

    @Autowired
    private Environment environment;

    @Override
    public String getAccessToken(String facebookGrantType, String facebookClientId, String facebookClientSecret, String facebookAccessToken) {
        final Map<String, String> accessTokenQueryMap = new LinkedHashMap();
        accessTokenQueryMap.put("grant_type", facebookGrantType);
        accessTokenQueryMap.put("client_id", facebookClientId);
        accessTokenQueryMap.put("client_secret", facebookClientSecret);
        accessTokenQueryMap.put("fb_exchange_token", facebookAccessToken);
        return Optional.ofNullable(Feign.builder()
                .decoder(new GsonDecoder())
                .target(
                        FacebookGraphClient.class,
                        environment.getProperty("facebook.base-url")
                )
                .getAccessToken(accessTokenQueryMap))
                .map(bhangarwaleFacebookAcessTokenResponse -> bhangarwaleFacebookAcessTokenResponse.getAccess_token())
                .orElse(null);
    }

    @Override
    public BhangarwaleFacebookPageDetail getPageDetail(String facebookAccessToken, String facebookPageId, String facebookPageFields) {
        final Map<String, String> pageDetailQueryMap = new LinkedHashMap();
        pageDetailQueryMap.put("access_token", facebookAccessToken);
        pageDetailQueryMap.put("fields", facebookPageFields);
        return Optional.ofNullable(
                Feign.builder()
                        .decoder(new BhangarwaleFacebookPageDetailDecoder())
                        .target(
                                FacebookGraphClient.class,
                                environment.getProperty("facebook.base-url")
                        )
                        .getPageDetail(
                                facebookPageId,
                                pageDetailQueryMap
                        )
        ).orElse(null);
    }

    @Override
    public BhangarwaleFacebookFeed getPostsAfterFirst(
            String facebookFeedFields,
            String facebookAccessToken,
            String facebookLimit,
            String nextPageAccessToken,
            String facebookPageId
    ) {
        final Map<String, String> feedQueryMap = new LinkedHashMap();
        feedQueryMap.put("fields", facebookFeedFields);
        feedQueryMap.put("access_token", facebookAccessToken);
        feedQueryMap.put("limit", facebookLimit);
        feedQueryMap.put("after", nextPageAccessToken);
        return Optional.ofNullable(
                Feign.builder()
                        .decoder(new BhangarwaleFacebookAfterFirstFeedDecoder())
                        .target(
                                FacebookGraphClient.class,
                                environment.getProperty("facebook.base-url")
                        )
                        .getPostsAfterFirst(
                                facebookPageId,
                                feedQueryMap
                        )
        ).orElse(null);
    }

    @Override
    public BhangarwaleFacebookFeed getPosts(String facebookAccessToken, String facebookLimit, String facebookFeedFields, String facebookPageId) {
        final Map<String, String> feedQueryMap = new LinkedHashMap();
        feedQueryMap.put("access_token", facebookAccessToken);
        feedQueryMap.put("fields", "posts.limit(" + facebookLimit + ")" + "{"
                + facebookFeedFields + "}");
        return Optional.ofNullable(Feign.builder()
                .decoder(new BhangarwaleFacebookFeedDecoder())
                .target(
                        FacebookGraphClient.class,
                        environment.getProperty("facebook.base-url")
                )
                .getPosts(
                        facebookPageId,
                        feedQueryMap
                )).orElse(null);
    }


}
