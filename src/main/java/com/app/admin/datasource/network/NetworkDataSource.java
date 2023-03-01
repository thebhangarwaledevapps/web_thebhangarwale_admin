package com.app.admin.datasource.network;

import com.app.admin.entity.BhangarwaleFacebookFeed;
import com.app.admin.entity.BhangarwaleFacebookPageDetail;
import org.springframework.stereotype.Service;

@Service
public interface NetworkDataSource {
    String getAccessToken(String facebookGrantType, String facebookClientId, String facebookClientSecret, String facebookAccessToken);

    BhangarwaleFacebookPageDetail getPageDetail(String facebookAccessToken, String facebookPageId, String facebookPageFields);

    BhangarwaleFacebookFeed getPostsAfterFirst(String facebookFeedFields, String facebookAccessToken, String facebookLimit, String nextPageAccessToken, String facebookPageId);

    BhangarwaleFacebookFeed getPosts(String facebookAccessToken, String facebookLimit, String facebookFeedFields, String facebookPageId);
}
