package com.dengciping.ydroid.airconditioningsystem.data.netwark;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.PUT;

/**
 * $desc$
 *
 * @author DengCiPing
 * @date 2017/8/5 上午10:30
 */
public interface FboxAPI {

    @FormUrlEncoded
    @PUT("http://fbox360.com /api/client/group")
    Flowable<String> creatGroup(
            @Header("X-FBox-ClientId") String fboxClientID,
            @Field("name") String name);

}
