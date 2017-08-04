package com.dengciping.ydroid.airconditioningsystem.data.netwark;


import com.dengciping.ydroid.airconditioningsystem.Bean.UserBean;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * 统计进程的接口
 *
 * @author caixudong
 * @date 2016/5/20
 */
public interface UserApi {

    @FormUrlEncoded
    @POST("/core/connect/token")
    Flowable<UserBean> login(@Field("username") String username,
                             @Field("password") String password,
                             @Field("scope") String scope,
                             @Field("client_id") String client_id,
                             @Field("client_secret") String client_secret,
                             @Field("grant_type") String grant_type);

    @FormUrlEncoded
    @POST("/core/connect/token")
    Flowable<UserBean> refresToken(
            @Field("refresh_token") String refresh_token,
            @Field("scope") String scope,
            @Field("client_id") String client_id,
            @Field("client_secret") String client_secret,
            @Field("grant_type") String grant_type);
}
