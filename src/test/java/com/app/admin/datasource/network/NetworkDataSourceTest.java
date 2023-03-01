package com.app.admin.datasource.network;

import com.app.admin.datasource.environment.EnvironmentDataSource;
import com.app.admin.datasource.local.DatabaseDataSource;
import com.app.admin.entity.BhangarwaleFacebookPageDetail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NetworkDataSourceTest {

    @Autowired
    private DatabaseDataSource databaseDataSourceImpl;

    @Autowired
    private EnvironmentDataSource environmentDataSourceImpl;

    @Autowired
    private NetworkDataSource networkDataSourceImpl;

    @Test
    void test_getPageDetail(){
        String facebookAccessToken = databaseDataSourceImpl.getFacebookAccessToken();
        String facebookPageId = environmentDataSourceImpl.getFacebookPageId();
        String facebookPageFields = environmentDataSourceImpl.getFacebookPageFields();
        BhangarwaleFacebookPageDetail bhangarwaleFacebookPageDetail = networkDataSourceImpl.getPageDetail(
                facebookAccessToken,
                facebookPageId,
                facebookPageFields
        );
        System.out.println(bhangarwaleFacebookPageDetail);
    }

}