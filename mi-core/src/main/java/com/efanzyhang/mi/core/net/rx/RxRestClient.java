package com.efanzyhang.mi.core.net.rx;

import android.content.Context;

import com.efanzyhang.mi.core.net.HttpMethod;
import com.efanzyhang.mi.core.net.RestCreator;
import com.efanzyhang.mi.core.ui.loader.LoaderStyle;
import com.efanzyhang.mi.core.ui.loader.MishopLoader;

import java.io.File;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * 项目名：MIShop
 * 包名：com.efanzyhang.mi.core.net
 * 文件名：RestClient
 * 创建者：efan.zyhang
 * 创建时间：2018/8/8 19:51
 * 描述： 网络连接实现层
 */
public class RxRestClient {

    //参数不允许修改
    //final修饰要求大写

    private final String URL;
    private static final Map<String, Object> PARAMS = RestCreator.getParams();
    private final RequestBody BODY;
    //上传文件
    private final File FILE;
    //loading加载
    private final Context CONTEXT;
    private final LoaderStyle LOADER_STYLE;


    /**
     * final修饰的必须复制，所以在构造方法中进行赋值
     *
     * @param url
     * @param params
     * @param body
     */
    public RxRestClient(String url, Map<String, Object> params, RequestBody body, File file, Context context,
                        LoaderStyle loaderStyle) {
        this.URL = url;
        PARAMS.putAll(params);
        this.BODY = body;
        this.FILE = file;
        this.CONTEXT = context;
        this.LOADER_STYLE = loaderStyle;
    }


    public static RxRestClientBuilder builder() {
        return new RxRestClientBuilder();
    }

    private Observable<String> request(HttpMethod httpMethod) {
        final RxRestService service = RestCreator.getRxRestService();
        Observable<String> observable = null;

        if (LOADER_STYLE != null) {
            MishopLoader.showLoading(CONTEXT, LOADER_STYLE);
        }

        switch (httpMethod) {
            case GET:
                observable = service.get(URL, PARAMS);
                break;
            case POST:
                observable = service.get(URL, PARAMS);
                break;
            //post原始数据
            case POST_RAM:
                observable = service.postRam(URL, BODY);
                break;
            case PUT:
                observable = service.get(URL, PARAMS);
                break;
            case PUT_RAW:
                observable = service.putRam(URL, BODY);
                break;
            case DELETE:
                observable = service.get(URL, PARAMS);
                break;
            case UPLOAD:
                RequestBody requestBody = RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), FILE);
                MultipartBody.Part body = MultipartBody.Part.createFormData("file", FILE.getName(), requestBody);
                observable = service.upload(URL, body);
                break;
            default:
                break;
        }
        return observable;
    }

    public final Observable<String> get() {
        return request(HttpMethod.GET);
    }

    public final Observable<String> post() {
        if (BODY == null) {
            return request(HttpMethod.POST);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params is not null!");
            }
            return request(HttpMethod.POST_RAM);
        }
    }

    public final Observable<String> put() {
        if (BODY == null) {
            return request(HttpMethod.PUT);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params is not null!");
            }
            return request(HttpMethod.PUT_RAW);
        }
    }

    public final Observable<String> delete() {
        return request(HttpMethod.DELETE);
    }


    public final Observable<String> upload() {
        return request(HttpMethod.UPLOAD);
    }


    public final Observable<ResponseBody> downLoad() {
        return RestCreator.getRxRestService().download(URL, PARAMS);
    }

}
