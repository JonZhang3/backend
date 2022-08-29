package com.future.common.utils.jwt;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.auth0.jwt.interfaces.Claim;

public class UserPayload {

    private static final String KEY_USERNAME = "username";
    private static final String KEY_USERID = "id";

    private Map<String, Claim> claims;
    private Map<String, Object> map;

    UserPayload(Map<String, Claim> claims) {
        this.claims = claims;
    }

    UserPayload() {

    }

    public String username() {
        return claims.get(KEY_USERNAME).asString();
    }

    public Long userId() {
        return claims.get(KEY_USERID).asLong();
    }

    public String getString(String key) {
        return claims.get(key).asString();
    }

    public Integer getInteger(String key) {
        return claims.get(key).asInt();
    }

    public Long getLong(String key) {
        return claims.get(key).asLong();
    }

    public Double getDouble(String key) {
        return claims.get(key).asDouble();
    }

    public Boolean getBoolean(String key) {
        return claims.get(key).asBoolean();
    }

    public Date getDate(String key) {
        return claims.get(key).asDate();
    }

    public Instant getInstant(String key) {
        return claims.get(key).asInstant();
    }
    
    public <T> List<T> getList(String key, Class<T> clazz) {
        return claims.get(key).asList(clazz);
    }

    public Map<String, Object> getMap(String key) {
        return claims.get(key).asMap();
    }

    public <T> T[] getArray(String key, Class<T> clazz) {
        return claims.get(key).asArray(clazz);
    }

    public <T> T get(String key, Class<T> clazz) {
        return claims.get(key).as(clazz);
    }

    Map<String, Object> map() {
        return this.map;
    }

    public static class Builder {

        private UserPayload payload;

        public Builder(Long userId, String username) {
            payload = new UserPayload();
            payload.map = new HashMap<>();
            payload.map.put(KEY_USERID, userId);
            payload.map.put(KEY_USERNAME, username);
        }

        public Builder userId(Long userId) {
            payload.map.put(KEY_USERID, userId);
            return this;
        }

        public Builder username(String username) {
            payload.map.put(KEY_USERNAME, username);
            return this;
        }

        public Builder param(String key, Object value) {
            payload.map.put(key, value);
            return this;
        }

        public UserPayload build() {
            return payload;
        }

    }

}
