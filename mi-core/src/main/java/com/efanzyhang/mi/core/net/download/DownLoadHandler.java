package com.efanzyhang.mi.core.net.download;

import android.os.AsyncTask;

import com.efanzyhang.mi.core.net.RestCreator;
import com.efanzyhang.mi.core.net.callback.IError;
import com.efanzyhang.mi.core.net.callback.IFailure;
import com.efanzyhang.mi.core.net.callback.IRequest;
import com.efanzyhang.mi.core.net.callback.ISuccess;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 项目名：MIShop
 * 包名：com.efanzyhang.mi.core.net.download
 * 文件名：DownLoadHandler
 * 创建者：efan.zyhang
 * 创建时间：2018/8/14 10:24
 * 描述： TODO
 */
public class DownLoadHandler {
    private final String URL;
    private static final Map<String, Object> PARAMS = RestCreator.getParams();
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final String DOWNLOAD_DIR;
    private final String NAME;
    private final String EXTENSION;


    public DownLoadHandler(String url, IRequest request, ISuccess success, IFailure failure, IError error, String downloadDir, String name, String extension) {
        this.URL = url;
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.DOWNLOAD_DIR = downloadDir;
        this.NAME = name;
        this.EXTENSION = extension;

    }

    public void handlerDownload() {
        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }

        RestCreator.getRestService().download(URL, PARAMS).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {

                    final ResponseBody responseBody = response.body();
                    //.executeOnExecutor()是以线程池的方式
                    //.execute()是以队列的方式执行（一个又一个）
                    final SaveFileTask task = new SaveFileTask(REQUEST, SUCCESS);
                    task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, DOWNLOAD_DIR, EXTENSION, responseBody, NAME);

                    //这里一定要判断，否则文件下载不全
                    if (task.isCancelled()) {
                        if (REQUEST != null) {
                            REQUEST.onRequestEnd();
                        }
                    }
                } else {
                    if (ERROR != null) {
                        ERROR.onError(response.code(), response.message());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if (FAILURE != null) {
                    FAILURE.onFailure();
                }
            }
        });
    }
}
