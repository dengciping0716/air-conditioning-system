package com.dengciping.ydroid.airconditioningsystem.data.netwark;

import cn.droidlover.xdroidmvp.net.RequestHandler;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * $desc$
 *
 * @author DengCiPing
 * @date 2017/8/5 上午10:23
 */
public class HeaderRequestHandler implements RequestHandler {
    @Override
    public Request onBeforeRequest(Request request, Interceptor.Chain chain) {
        return request.newBuilder()
                .addHeader("Authorization", "Bearer " + UserRepository.getToken())
                .build();
    }

    @Override
    public Response onAfterRequest(Response response, Interceptor.Chain chain) {
        return response;
    }
}
