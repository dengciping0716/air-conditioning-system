package com.dengciping.ydroid.airconditioningsystem.data.bean;

import android.text.TextUtils;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

import cn.droidlover.xdroidmvp.event.IBus;
import cn.droidlover.xdroidmvp.net.IModel;

/**
 * $desc$
 *
 * @author DengCiPing
 * @date 2017/8/3 下午5:38
 */

@JsonInclude(JsonInclude.Include.NON_NULL)

public class UserBean implements IModel, IBus.IEvent {
    private String userName;
    @JsonProperty("access_token")
    protected String access_token;
    @JsonProperty("expires_in")
    protected long expires_in;
    @JsonProperty("refresh_token")
    protected String refresh_token;
    @JsonProperty("token_type")
    protected String token_type;
    @JsonProperty("error")
    protected String error;

    @JsonProperty("access_token")

    public String getAccess_token() {
        return access_token;
    }

    @JsonProperty("access_token")

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    @JsonProperty("expires_in")

    public long getExpires_in() {
        return expires_in;
    }

    @JsonProperty("expires_in")

    public void setExpires_in(long expires_in) {
        this.expires_in = expires_in;
    }

    @JsonProperty("refresh_token")

    public String getRefresh_token() {
        return refresh_token;
    }

    @JsonProperty("refresh_token")
    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    @JsonProperty("token_type")
    public String getToken_type() {
        return token_type;
    }

    @JsonProperty("token_type")
    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    @JsonProperty("error")
    public String getError() {
        return error;
    }

    @JsonProperty("error")
    public void setError(String error) {
        this.error = error;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public boolean isNull() {
        return false;
    }

    @Override
    public boolean isAuthError() {
        return false;
    }

    @Override
    public boolean isBizError() {
        return !TextUtils.isEmpty(error);
    }

    @Override
    public String getErrorMsg() {
        return getError();
    }

    @Override
    public int getTag() {
        return 0;
    }
}
