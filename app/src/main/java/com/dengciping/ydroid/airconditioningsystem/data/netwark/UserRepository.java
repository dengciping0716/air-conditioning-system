package com.dengciping.ydroid.airconditioningsystem.data.netwark;

import com.dengciping.ydroid.airconditioningsystem.App;
import com.dengciping.ydroid.airconditioningsystem.Bean.UserBean;
import com.dengciping.ydroid.airconditioningsystem.Config;

import cn.droidlover.xdroidmvp.cache.DiskCache;
import cn.droidlover.xdroidmvp.net.ApiTransformer;
import cn.droidlover.xdroidmvp.net.SchedulerTransformer;
import cn.droidlover.xdroidmvp.net.XApi;
import io.reactivex.Flowable;

/**
 * $desc$
 *
 * @author DengCiPing
 * @date 2017/8/3 下午4:46
 */
public class UserRepository {

    public static final String KEY_TOKEN = "token";
    public static final String KEY_REFRESH_TOKEN = "refresh_token";
    public static final String KEY_USER_NAME = "user_name";

    public static final String getToken(){
       return DiskCache.getInstance(App.getContext()).get(UserRepository.KEY_TOKEN);
    }
    public static final String getRefreshToken(){
        return DiskCache.getInstance(App.getContext()).get(UserRepository.KEY_REFRESH_TOKEN);
    }
    public static final String getUserName(){
        return DiskCache.getInstance(App.getContext()).get(UserRepository.KEY_USER_NAME);
    }

    public Flowable<UserBean> login(String userName, String password) {
        UserAPI userApi = XApi.get(UserAPI.class);
        return userApi.login(userName, password,
                "openid offline_access fbox email profile"
                , Config.clientID
                , Config.clientSecret
                , "password")
                .compose(new ApiTransformer<>())
                .compose(new SchedulerTransformer<>())
                .map((userBean -> {
                    cacheToken(userBean);
                    DiskCache.getInstance(App.getContext()).put(KEY_USER_NAME, userName);
                    return userBean;
                }));
    }

    public Flowable<UserBean> refreshToken(String token) {
        UserAPI userApi = XApi.get(UserAPI.class);
        return userApi.refresToken(token,
                "openid offline_access fbox email profile"
                , Config.clientID
                , Config.clientSecret
                , "refresh_token")
                .compose(new ApiTransformer<>())
                .compose(new SchedulerTransformer<>())
                .map((userBean) -> {
                    cacheToken(userBean);
                    return userBean;
                });
    }

    private void cacheToken(UserBean userBean) {
        DiskCache.getInstance(App.getContext()).put(KEY_TOKEN, userBean.getAccess_token(), userBean.getExpires_in() * 1000l);
        DiskCache.getInstance(App.getContext()).put(KEY_REFRESH_TOKEN, userBean.getRefresh_token(), 30 * 24 * 60 * 60 * 1000l);
    }

}
