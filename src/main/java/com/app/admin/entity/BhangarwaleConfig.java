package com.app.admin.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BhangarwaleConfig {

    @Id
    private final String id = getClass().getSimpleName().toLowerCase();
    private String facebookAcessToken;

    public BhangarwaleConfig() { }

    public BhangarwaleConfig(String facebookAcessToken) {
        this.facebookAcessToken = facebookAcessToken;
    }

    public String getId() {
        return id;
    }

    public String getFacebookAcessToken() {
        return facebookAcessToken;
    }

    public void setFacebookAcessToken(String facebookAcessToken) {
        this.facebookAcessToken = facebookAcessToken;
    }
}
