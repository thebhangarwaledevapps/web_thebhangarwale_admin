package com.app.admin.datasource.environment;

import org.springframework.stereotype.Service;

@Service
public interface EnvironmentDataSource {

    String getFacebookGrantType();

    String getFacebookClientId();

    String getFacebookClientSecret();

    String getFacebookPageId();

    String getFacebookPageFields();

    String getFacebookFeedFields();

    String getFacebookLimit();
}
