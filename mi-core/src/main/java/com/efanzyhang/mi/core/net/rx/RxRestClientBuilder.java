package com.efanzyhang.mi.core.net.rx;

import android.content.Context;

import com.efanzyhang.mi.core.net.RestCreator;
import com.efanzyhang.mi.core.ui.loader.LoaderStyle;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

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
public class RxRestClientBuilder {
    //     = null保持良好的编码习惯
    private String mUrl = null;
    private static final Map<String, Object> PARAMS = RestCreator.getParams();
    private RequestBody mBody = null;
    private File mFile = null;
    private Context mContext = null;
    private LoaderStyle mLoaderStyle = null;

    RxRestClientBuilder() {

    }

    public final RxRestClientBuilder Url(String url) {
        this.mUrl = url;
        return this;
    }

    public final RxRestClientBuilder params(WeakHashMap<String, Object> params) {
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
    public final RxRestClientBuilder params(String key, String value) {
        PARAMS.put(key, value);
        return this;
    }

    /**
     * 传文件方式上传
     *
     * @param file
     * @return
     */
    public final RxRestClientBuilder file(File file) {
        this.mFile = file;
        return this;
    }

    /**
     * 传文件路径方式上传
     *
     * @param filePath
     * @return
     */
    public final RxRestClientBuilder file(String filePath) {
        this.mFile = new File(filePath);
        return this;
    }

    public final RxRestClientBuilder Loader(Context context, LoaderStyle loaderStyle) {
        this.mContext = context;
        this.mLoaderStyle = loaderStyle;
        return this;
    }

    public final RxRestClientBuilder Loader(Context context) {
        this.mContext = context;
        this.mLoaderStyle = LoaderStyle.BallClipRotatePulseIndicator;
        return this;
    }

    public final RxRestClient build() {
        return new RxRestClient(mUrl,
                PARAMS,
                mBody,
                mFile,
                mContext,
                mLoaderStyle);
    }

}
