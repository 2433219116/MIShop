package com.efanzyhang.mi.core.net;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * 项目名：MIShop
 * 包名：com.efanzyhang.mi.core.net
 * 文件名：RestService
 * 创建者：efan.zyhang
 * 创建时间：2018/8/8 20:06
 * 描述： 网络接口类
 */
public interface RestService {

    @GET
    Call<String> get(@Url String url, @QueryMap Map<String, Object> params);

    @FormUrlEncoded
    @POST
    Call<String> post(@Url String url, @FieldMap Map<String, Object> params);

    //post原始数据不使用FormUrlEncoded
    @POST
    Call<String> postRam(@Url String url, @Body RequestBody body);

    @FormUrlEncoded
    @PUT
    Call<String> put(@Url String url, @FieldMap Map<String, Object> params);

    @PUT
    Call<String> putRam(@Url String url, @FieldMap RequestBody body);

    @DELETE
    Call<String> delete(@Url String url, @QueryMap Map<String, Object> params);

    /**
     * download是往内存里下载 完全下载后写入文件 文件过大会造成内存溢出
     * Streaming作用 边下载边写入文件中
     * 还要在异步线程中去写，主线程依旧报错
     */
    @Streaming
    @GET
    Call<ResponseBody> download(@Url String url, @QueryMap Map<String, Object> params);

    @FormUrlEncoded
    @POST
    Call<String> upload(@Url String url, @Part MultipartBody.Part file);
}
