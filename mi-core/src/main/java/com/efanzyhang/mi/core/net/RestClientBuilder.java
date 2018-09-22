package com.efanzyhang.mi.core.net;

import android.content.Context;

import com.efanzyhang.mi.core.net.callback.IError;
import com.efanzyhang.mi.core.net.callback.IFailure;
import com.efanzyhang.mi.core.net.callback.IRequest;
import com.efanzyhang.mi.core.net.callback.ISuccess;
import com.efanzyhang.mi.core.ui.loader.LoaderStyle;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * 项目名：MIShop
 * 包名：com.efanzyhang.mi.core.net
 * 文件名：RestClientBuilder
 * 创建者：efan.zyhang
 * 创建时间：2018/8/9 11:20
 * 描述： 建造者类（将建造者与宿主类分开，使得不使用静态内部类）
 * 进行传值操作
 */
public class RestClientBuilder {
    //     = null保持良好的编码习惯
    private String mUrl = null;
    private static final Map<String, Object> PARAMS = RestCreator.getParams();
    private IRequest mRequest = null;
    private ISuccess mSuccess = null;
    private IFailure mFailure = null;
    private IError mError = null;
    private RequestBody mBody = null;
    private File mFile = null;
    private Context mContext = null;
    private LoaderStyle mLoaderStyle = null;
    private String downloadDir = null;
    private String mExtension = null;
    private String mName = null;

    RestClientBuilder() {

    }

    public final RestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    public final RestClientBuilder params(WeakHashMap<String, Object> params) {
        PARAMS.putAll(params);
        return this;
    }

    /**
     * 正常要判断是否为空，但是获取的静态内部类声明，不能为空
     *
     * @param key
     * @param value
     * @return
     */
    public final RestClientBuilder params(String key, String value) {
        PARAMS.put(key, value);
        return this;
    }

    /**
     * 传文件方式上传
     *
     * @param file
     * @return
     */
    public final RestClientBuilder file(File file) {
        this.mFile = file;
        return this;
    }

    /**
     * 传文件路径方式上传
     *
     * @param filePath
     * @return
     */
    public final RestClientBuilder file(String filePath) {
        this.mFile = new File(filePath);
        return this;
    }

    /**
     * 下载文件的名字
     *
     * @param name
     * @return
     */
    public final RestClientBuilder name(String name) {
        this.mName = name;
        return this;
    }

    /**
     * 下载文件的名字
     *
     * @param extension
     * @return
     */
    public final RestClientBuilder extension(String extension) {
        this.mExtension = extension;
        return this;
    }

    /**
     * 下载文件的名字
     *
     * @param downLoadDir
     * @return
     */
    public final RestClientBuilder downLoadDir(String downLoadDir) {
        this.downloadDir = downLoadDir;
        return this;
    }


    public final RestClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }

    public final RestClientBuilder onRequest(IRequest iRequest) {
        this.mRequest = iRequest;
        return this;
    }

    public final RestClientBuilder success(ISuccess iSuccess) {
        this.mSuccess = iSuccess;
        return this;
    }

    public final RestClientBuilder failure(IFailure iFailure) {
        this.mFailure = iFailure;
        return this;
    }

    public final RestClientBuilder error(IError iError) {
        this.mError = iError;
        return this;
    }

    public final RestClientBuilder Loader(Context context, LoaderStyle loaderStyle) {
        this.mContext = context;
        this.mLoaderStyle = loaderStyle;
        return this;
    }

    public final RestClientBuilder Loader(Context context) {
        this.mContext = context;
        this.mLoaderStyle = LoaderStyle.BallClipRotatePulseIndicator;
        return this;
    }

    public final RestClient build() {
        return new RestClient(mUrl, PARAMS, mRequest, mSuccess, mFailure, mError, mBody, mFile, mContext, mLoaderStyle, downloadDir, mName, mExtension);
    }

}
