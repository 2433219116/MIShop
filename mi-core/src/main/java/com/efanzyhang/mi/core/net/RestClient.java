package com.efanzyhang.mi.core.net;

import android.content.Context;

import com.efanzyhang.mi.core.net.callback.IError;
import com.efanzyhang.mi.core.net.callback.IFailure;
import com.efanzyhang.mi.core.net.callback.IRequest;
import com.efanzyhang.mi.core.net.callback.ISuccess;
import com.efanzyhang.mi.core.net.callback.RequestCallbacks;
import com.efanzyhang.mi.core.net.download.DownLoadHandler;
import com.efanzyhang.mi.core.ui.loader.LoaderStyle;
import com.efanzyhang.mi.core.ui.loader.MishopLoader;

import java.io.File;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Callback;

/**
 * 项目名：MIShop
 * 包名：com.efanzyhang.mi.core.net
 * 文件名：RestClient
 * 创建者：efan.zyhang
 * 创建时间：2018/8/8 19:51
 * 描述： 网络连接实现层
 */
public class RestClient {

    //参数不允许修改
    //final修饰要求大写

    private final String URL;
    private static final Map<String, Object> PARAMS = RestCreator.getParams();
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final RequestBody BODY;
    //上传文件
    private final File FILE;
    //loading加载
    private final Context CONTEXT;
    private final LoaderStyle LOADER_STYLE;
    //下载文件 传入第一个就不用第二和第三，反之亦然
    private final String DOWNLOAD_DIR;
    private final String NAME;
    //后缀名
    private final String EXTENSION;


    /**
     * final修饰的必须复制，所以在构造方法中进行赋值
     *
     * @param url
     * @param params
     * @param request
     * @param success
     * @param failure
     * @param error
     * @param body
     */
    public RestClient(String url, Map<String, Object> params, IRequest request, ISuccess success, IFailure failure, IError error, RequestBody body, File file, Context context,
                      LoaderStyle loaderStyle, String downloadDir, String name, String extension) {
        this.URL = url;
        PARAMS.putAll(params);
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.BODY = body;
        this.FILE = file;
        this.CONTEXT = context;
        this.LOADER_STYLE = loaderStyle;
        this.DOWNLOAD_DIR = downloadDir;
        this.NAME = name;
        this.EXTENSION = extension;
    }


    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }

    private void request(HttpMethod httpMethod) {
        final RestService service = RestCreator.getRestService();
        retrofit2.Call<String> call = null;

        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }

        if (LOADER_STYLE != null) {
            MishopLoader.showLoading(CONTEXT, LOADER_STYLE);
        }

        switch (httpMethod) {
            case GET:
                call = service.get(URL, PARAMS);
                break;
            case POST:
                call = service.get(URL, PARAMS);
                break;
            //post原始数据
            case POST_RAM:
                call = service.postRam(URL, BODY);
                break;
            case PUT:
                call = service.get(URL, PARAMS);
                break;
            case PUT_RAW:
                call = service.putRam(URL, BODY);
                break;
            case DELETE:
                call = service.get(URL, PARAMS);
                break;
            case UPLOAD:
                RequestBody requestBody = RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), FILE);
                MultipartBody.Part body = MultipartBody.Part.createFormData("file", FILE.getName(), requestBody);
                call = service.upload(URL, body);
                break;
            default:
                break;
        }
        //execute();在主线程中使用，不推荐
        //enqueue();会在其他新开的线程中使用
        if (call != null) {
            call.enqueue(getRequestCallback());
        }
    }

    private Callback<String> getRequestCallback() {
        return new RequestCallbacks(
                REQUEST,
                SUCCESS,
                FAILURE,
                ERROR,
                LOADER_STYLE
        );
    }

    public final void get() {
        request(HttpMethod.GET);
    }

    public final void post() {
        if (BODY == null) {
            request(HttpMethod.POST);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params is not null!");
            }
            request(HttpMethod.POST_RAM);
        }
    }

    public final void put() {
        if (BODY == null) {
            request(HttpMethod.PUT);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params is not null!");
            }
            request(HttpMethod.PUT_RAW);
        }
    }

    public final void delete() {
        request(HttpMethod.DELETE);
    }


    public final void upload() {
        request(HttpMethod.UPLOAD);
    }


    public final void downLoad() {
        new DownLoadHandler(URL, REQUEST, SUCCESS, FAILURE, ERROR, DOWNLOAD_DIR, EXTENSION, NAME).handlerDownload();
    }

}
