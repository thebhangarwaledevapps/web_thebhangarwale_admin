package com.app.admin.repository;

import com.app.admin.datasource.environment.EnvironmentDataSource;
import com.app.admin.datasource.local.DatabaseDataSource;
import com.app.admin.datasource.network.NetworkDataSource;
import com.app.admin.entity.BhangarTypeAndPrice;
import com.app.admin.entity.BhangarwaleFacebookFeed;
import com.app.admin.entity.BhangarwaleFacebookPageDetail;
import com.app.admin.entity.BhangarwaleFacebookPosts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RepositoryImpl implements Repository {

    @Autowired
    private DatabaseDataSource databaseDataSourceImpl;

    @Autowired
    private EnvironmentDataSource environmentDataSourceImpl;

    @Autowired
    private NetworkDataSource networkDataSourceImpl;

    @Override
    public Optional<BhangarwaleFacebookFeed> getBhangarwaleFacebookFeed(String nextPageAccessToken) {
        databaseDataSourceImpl.saveBhangarwaleConfig(
                networkDataSourceImpl.getAccessToken(
                        environmentDataSourceImpl.getFacebookGrantType(),
                        environmentDataSourceImpl.getFacebookClientId(),
                        environmentDataSourceImpl.getFacebookClientSecret(),
                        databaseDataSourceImpl.getFacebookAccessToken()
                )
        );
        final BhangarwaleFacebookPageDetail bhangarwaleFacebookPageDetail = networkDataSourceImpl.getPageDetail(
                databaseDataSourceImpl.getFacebookAccessToken(),
                environmentDataSourceImpl.getFacebookPageId(),
                environmentDataSourceImpl.getFacebookPageFields()
        );
        return Optional.ofNullable(Optional.ofNullable(
                nextPageAccessToken != null && !nextPageAccessToken.trim().isEmpty()
                        ? nextPageAccessToken
                        : null
        ).map(token -> networkDataSourceImpl.getPostsAfterFirst(
                environmentDataSourceImpl.getFacebookFeedFields(),
                databaseDataSourceImpl.getFacebookAccessToken(),
                environmentDataSourceImpl.getFacebookLimit(),
                token,
                environmentDataSourceImpl.getFacebookPageId()
        )).orElseGet(() -> networkDataSourceImpl.getPosts(
                databaseDataSourceImpl.getFacebookAccessToken(),
                environmentDataSourceImpl.getFacebookLimit(),
                environmentDataSourceImpl.getFacebookFeedFields(),
                environmentDataSourceImpl.getFacebookPageId()
        ))).map(bhangarwaleFacebookFeed -> {
            bhangarwaleFacebookFeed.setBhangarwaleFacebookFeedPosts(
                    (ArrayList<BhangarwaleFacebookPosts>) bhangarwaleFacebookFeed
                            .getBhangarwaleFacebookFeedPosts()
                            .stream()
                            .peek(bhangarwaleFacebookPost -> {
                                bhangarwaleFacebookPost.setPageTitle(bhangarwaleFacebookPageDetail.getTitle());
                                bhangarwaleFacebookPost.setPageImage(bhangarwaleFacebookPageDetail.getImage());
                            }).collect(Collectors.toList())
            );
            return bhangarwaleFacebookFeed;
        });
    }

    @Override
    public BhangarTypeAndPrice saveBhangarTypeAndPrice(String bhangarType, String bhangarUnit, Double bhangarPrice) {
        return !databaseDataSourceImpl.isBhangarTypeAvailable(bhangarType)
                ? databaseDataSourceImpl.saveBhangarTypeAndPrice(new BhangarTypeAndPrice(
                bhangarType,
                bhangarUnit,
                bhangarPrice
        )) : null;
    }

    @Override
    public Optional<List<BhangarTypeAndPrice>> getBhangarList() {
        final List<BhangarTypeAndPrice> bhangarList = databaseDataSourceImpl.findBhangarList();
        return Optional.ofNullable(
                bhangarList != null && !bhangarList.isEmpty()
                        ? bhangarList
                        : null
        );
    }

    @Override
    public Optional<BhangarTypeAndPrice> getBhangarItemInfo(Long itemId) {
        return databaseDataSourceImpl.getBhangarItemInfo(itemId);
    }

    @Override
    public boolean validatedAdminId(String adminId) {
        return databaseDataSourceImpl.validatedAdminId(adminId);
    }

    @Override
    public BhangarTypeAndPrice updateBhangarTypeAndPrice(Long bhangarId, String bhangarType, String bhangarUnit, Double bhangarPrice) {
        return Optional.ofNullable(databaseDataSourceImpl.findBhangarType(bhangarId,bhangarType))
                .map(bhangarTypeAndPrice -> {
                    bhangarTypeAndPrice.setBhangarUnit(bhangarUnit);
                    bhangarTypeAndPrice.setBhangarPrice(bhangarPrice);
                    return databaseDataSourceImpl.saveBhangarTypeAndPrice(bhangarTypeAndPrice);
                })
                .orElseGet(() -> databaseDataSourceImpl.saveBhangarTypeAndPrice(new BhangarTypeAndPrice(
                        bhangarType,
                        bhangarUnit,
                        bhangarPrice
                )));
    }

    @Override
    public Long deleteById(Long bhangarId) {
        Long numberOfBhangar = databaseDataSourceImpl.deleteById(bhangarId);
        return numberOfBhangar > 0 ? numberOfBhangar : null;
    }
}
