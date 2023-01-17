package com.app.admin.repository;

import com.app.admin.datasource.local.BhangarTypeAndPriceDataSource;
import com.app.admin.datasource.local.BhangarwaleConfigDataSource;
import com.app.admin.datasource.network.FacebookNetworkDataSource;
import com.app.admin.entity.BhangarTypeAndPrice;
import com.app.admin.entity.BhangarwaleConfig;
import com.app.admin.entity.BhangarwaleFacebookFeed;
import com.app.admin.entity.BhangarwaleFacebookPageDetail;
import com.app.admin.exception.InvalidBhangarItemIdException;
import com.app.admin.exception.NoBhangarFoundException;
import com.app.admin.result.Success;
import com.app.admin.util.BhangarwaleFacebookAfterFirstFeedDecoder;
import com.app.admin.util.BhangarwaleFacebookFeedDecoder;
import com.app.admin.util.BhangarwaleFacebookPageDetailDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import java.util.LinkedHashMap;
import feign.gson.GsonDecoder;
import java.util.List;
import java.util.Map;
import feign.Feign;

@Service
public class RepositoryImpl implements Repository {

    @Autowired
    private BhangarwaleConfigDataSource bhangarwaleConfigDataSource;

    @Autowired
    private BhangarTypeAndPriceDataSource bhangarTypeAndPriceDataSource;

    @Autowired
    Environment environment;

    @Override
    public Success<BhangarwaleFacebookFeed> getBhangarwaleFacebookFeed(String nextPageAccessToken) {
        //get facebook token
        final Map<String, String> accessTokenQueryMap = new LinkedHashMap();
        accessTokenQueryMap.put("grant_type", environment.getProperty("facebook.grant-type"));
        accessTokenQueryMap.put("client_id", environment.getProperty("facebook.client-id"));
        accessTokenQueryMap.put("client_secret", environment.getProperty("facebook.client-secret"));
        accessTokenQueryMap.put("fb_exchange_token", bhangarwaleConfigDataSource.getFacebookAcessToken());
        bhangarwaleConfigDataSource.save(new BhangarwaleConfig(Feign.builder()
                .decoder(new GsonDecoder())
                .target(
                        FacebookNetworkDataSource.class,
                        environment.getProperty("facebook.base-url")
                )
                .getAccessToken(accessTokenQueryMap).getAccess_token()));

        //get facebook page detail
        final Map<String, String> pageDetailQueryMap = new LinkedHashMap();
        pageDetailQueryMap.put("access_token", bhangarwaleConfigDataSource.getFacebookAcessToken());
        pageDetailQueryMap.put("fields", environment.getProperty("facebook.page-fields"));
        final BhangarwaleFacebookPageDetail bhangarwaleFacebookPageDetail = Feign.builder()
                .decoder(new BhangarwaleFacebookPageDetailDecoder())
                .target(
                        FacebookNetworkDataSource.class,
                        environment.getProperty("facebook.base-url")
                )
                .getPageDetail(
                        environment.getProperty("facebook.page-id"),
                        pageDetailQueryMap
                );

        //get facebook feed
        final Map<String, String> feedQueryMap = new LinkedHashMap();
        BhangarwaleFacebookFeed bhangarwaleFacebookFeed = null;
        if (nextPageAccessToken != null && !nextPageAccessToken.trim().isEmpty()) {
            feedQueryMap.put("fields", environment.getProperty("facebook.feed-fields"));
            feedQueryMap.put("access_token", bhangarwaleConfigDataSource.getFacebookAcessToken());
            feedQueryMap.put("limit", environment.getProperty("facebook.limit"));
            feedQueryMap.put("after", nextPageAccessToken);
            bhangarwaleFacebookFeed = Feign.builder()
                    .decoder(new BhangarwaleFacebookAfterFirstFeedDecoder())
                    .target(
                            FacebookNetworkDataSource.class,
                            environment.getProperty("facebook.base-url")
                    )
                    .getPostsAfterFirst(
                            environment.getProperty("facebook.page-id"),
                            feedQueryMap
                    );
        } else {
            feedQueryMap.put("access_token", bhangarwaleConfigDataSource.getFacebookAcessToken());
            feedQueryMap.put("fields", "posts.limit(" + environment.getProperty("facebook.limit") + ")" + "{"
                    + environment.getProperty("facebook.feed-fields") + "}");
            bhangarwaleFacebookFeed = Feign.builder()
                    .decoder(new BhangarwaleFacebookFeedDecoder())
                    .target(
                            FacebookNetworkDataSource.class,
                            environment.getProperty("facebook.base-url")
                    )
                    .getPosts(
                            environment.getProperty("facebook.page-id"),
                            feedQueryMap
                    );
        }
        bhangarwaleFacebookFeed
                .getBhangarwaleFacebookFeedPosts()
                .stream()
                .map(bhangarwaleFacebookPost -> {
                    bhangarwaleFacebookPost.setPageTitle(bhangarwaleFacebookPageDetail.getTitle());
                    bhangarwaleFacebookPost.setPageImage(bhangarwaleFacebookPageDetail.getImage());
                    return bhangarwaleFacebookPost;
                });
        return new Success(
                bhangarwaleFacebookFeed
        );
    }

    @Override
    public BhangarTypeAndPrice saveBhangarTypeAndPrice(String bhangarType, String bhangarUnit, Double bhangarPrice) {
        return bhangarTypeAndPriceDataSource.findByBhangarType(bhangarType)
                .map(bhangarTypeAndPrice -> {
                    bhangarTypeAndPrice.setBhangarUnit(bhangarUnit);
                    bhangarTypeAndPrice.setBhangarPrice(bhangarPrice);
                    return bhangarTypeAndPriceDataSource.save(bhangarTypeAndPrice);
                })
                .orElseGet(() -> bhangarTypeAndPriceDataSource.save(new BhangarTypeAndPrice(
                        bhangarType,
                        bhangarUnit,
                        bhangarPrice
                )));
    }

    @Override
    public Success<List<BhangarTypeAndPrice>> getBhangarList() throws NoBhangarFoundException {
        final List<BhangarTypeAndPrice> bhangarList = bhangarTypeAndPriceDataSource.findAll();
        if (bhangarList.isEmpty())
            throw new NoBhangarFoundException();
        else
            return new Success(bhangarList);
    }

    @Override
    public Success<BhangarTypeAndPrice> getBhangarItemInfo(Long itemId) throws InvalidBhangarItemIdException {
        return bhangarTypeAndPriceDataSource
                .findById(itemId)
                .map(Success::new)
                .orElseThrow(InvalidBhangarItemIdException::new);
    }
}
