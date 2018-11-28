package com.efanzyhang.mi.core.net.interceptors;

import android.support.annotation.NonNull;
import android.support.annotation.RawRes;

import com.efanzyhang.mi.core.util.file.FileUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * 项目名：MIShop
 * 包名：com.efanzyhang.mi.core.net.interceptors
 * 文件名：DebugInterceptor
 * 创建者：efan.zyhang
 * 创建时间：2018/8/16 16:12
 * 描述： 测试拦截器，模拟请求地址
 */
public class DebugInterceptor extends BaseInterceptor {

    //模拟url
    private final String DEBUG_URL;
    //app raw文件夹下存放json（会在R文件中生成唯以id）
    private final int DEBUG_RAW_ID;

    public DebugInterceptor(String debugUrl, int debugRawId) {
        this.DEBUG_URL = debugUrl;
        this.DEBUG_RAW_ID = debugRawId;
    }

    private Response getResponse(Chain chain, String json) {
        return new Response.Builder()
                .code(200)
                .addHeader("Content-Type", "application/json")
                .body(ResponseBody.create(MediaType.parse("application/json"), json))
                .message("OK")
                .request(chain.request())
                .protocol(Protocol.HTTP_1_1)
                .build();
    }

    private Response debugResponse(Chain chain, @RawRes int rawId) {
        final String json = FileUtil.getRawFile(rawId);
        return getResponse(chain, json);
    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        //得到拦截的url
        final String url = chain.request().url().toString();
        //如果包含url关键字，返回想要返回的关键字
        if (url.contains(DEBUG_URL)) {
            return debugResponse(chain, DEBUG_RAW_ID);
        }
        return chain.proceed(chain.request());
    }
}
