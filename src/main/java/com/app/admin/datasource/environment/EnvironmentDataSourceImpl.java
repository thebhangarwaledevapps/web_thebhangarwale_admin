package com.app.admin.datasource.environment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class EnvironmentDataSourceImpl implements EnvironmentDataSource {

    @Autowired
    private Environment environment;

    @Override
    public String getFacebookGrantType() {
        return environment.getProperty("facebook.grant-type");
    }

    @Override
    public String getFacebookClientId() {
        return environment.getProperty("facebook.client-id");
    }

    @Override
    public String getFacebookClientSecret() {
        return environment.getProperty("facebook.client-secret");
    }

    @Override
    public String getFacebookPageId() {
        return environment.getProperty("facebook.page-id");
    }

    @Override
    public String getFacebookPageFields() {
        return environment.getProperty("facebook.page-fields");
    }

    @Override
    public String getFacebookFeedFields() {
        return environment.getProperty("facebook.feed-fields");
    }

    @Override
    public String getFacebookLimit() {
        return environment.getProperty("facebook.limit");
    }
}
