package com.app.admin.datasource.network.client;

import com.app.admin.datasource.environment.EnvironmentDataSource;
import com.app.admin.datasource.local.DatabaseDataSource;
import com.app.admin.entity.BhangarwaleFacebookAcessTokenResponse;
import com.app.admin.util.facebook.BhangarwaleFacebookPageDetailDecoder;
import feign.Feign;
import feign.gson.GsonDecoder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.core.env.Environment;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FacebookGraphClientTest {

    @Autowired
    private Environment environment;

    @Autowired
    private EnvironmentDataSource environmentDataSourceImpl;

    @Autowired
    private DatabaseDataSource databaseDataSourceImpl;

    @Test
    void test_getAccessToken(){
        try{
            String facebookGrantType = environmentDataSourceImpl.getFacebookGrantType();
            String facebookClientId = environmentDataSourceImpl.getFacebookClientId();
            String facebookClientSecret = environmentDataSourceImpl.getFacebookClientSecret();
            String facebookAccessToken = databaseDataSourceImpl.getFacebookAccessToken();

            final Map<String, String> accessTokenQueryMap = new LinkedHashMap();
            accessTokenQueryMap.put("grant_type", facebookGrantType);
            accessTokenQueryMap.put("client_id", facebookClientId);
            accessTokenQueryMap.put("client_secret", facebookClientSecret);
            accessTokenQueryMap.put("fb_exchange_token", facebookAccessToken);
            BhangarwaleFacebookAcessTokenResponse bhangarwaleFacebookAcessTokenResponse = Feign.builder()
                    .decoder(new GsonDecoder())
                    .target(
                            FacebookGraphClient.class,
                            environment.getProperty("facebook.base-url")
                    )
                    .getAccessToken(accessTokenQueryMap);

            System.out.println(bhangarwaleFacebookAcessTokenResponse);
        }catch (Exception ex){
            System.out.println(ex);
        }
    }

}