package com.efanzyhang.mi.core.net.callback;

import android.os.Handler;

import com.efanzyhang.mi.core.app.ConfigKeys;
import com.efanzyhang.mi.core.app.MIShop;
import com.efanzyhang.mi.core.ui.loader.LoaderStyle;
import com.efanzyhang.mi.core.ui.loader.MishopLoader;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 项目名：MIShop
 * 包名：com.efanzyhang.mi.core.net.callback
 * 文件名：RequestCallbacks
 * 创建者：efan.zyhang
 * 创建时间：2018/8/10 14:05
 * 描述： 实现retrofit的返回callback，重写了onResponse，onFailure
 * 请求相应和请求失败的方法，内容赋值给自己定义的接口
 */
public class RequestCallbacks implements Callback<String> {

    //Callback<String>选择为retrofit2类型的，其他的同名很多，容易出现问题
    //自己定义的话，最好加上前缀名，方便我们使用时不必出现错误

    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final LoaderStyle LOADER_STYLE;
    //handler最好用static修饰,避免内存了泄漏
    private static final Handler HANDLER = MIShop.getHandler();

    public RequestCallbacks(IRequest request, ISuccess success, IFailure failure, IError error, LoaderStyle loaderStyle) {
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.LOADER_STYLE = loaderStyle;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if (response.isSuccessful()) {
            if (call.isExecuted()) {
                if (SUCCESS != null) {
                    SUCCESS.onSuccess(response.body());
                }
            }
        } else {
            if (ERROR != null) {
                ERROR.onError(response.code(), response.message());
            }
        }
        onRequestFinish();
    }


    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if (FAILURE != null) {
            FAILURE.onFailure();
        }
        if (REQUEST != null) {
            REQUEST.onRequestEnd();
        }
        onRequestFinish();
    }

    /**
     * 停止转动方法  ::注意
     */
    private void onRequestFinish() {
        final long delayed = MIShop.getConfiguration(ConfigKeys.LOADER_DELAYED);
        if (LOADER_STYLE != null) {
            HANDLER.postDelayed(MishopLoader::stopLoading, delayed);
        }
    }

}
