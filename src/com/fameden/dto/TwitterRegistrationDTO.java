/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fameden.dto;

/**
 *
 * @author Ravjot
 */
public class TwitterRegistrationDTO {

    private String token;
    private String tokenSecret;
    private long userId;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTokenSecret() {
        return tokenSecret;
    }

    public void setTokenSecret(String tokenSecret) {
        this.tokenSecret = tokenSecret;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
