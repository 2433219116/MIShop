package com.efanzyhang.mi.core.ui.refresh;

import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.Toast;

import com.efanzyhang.mi.core.app.MIShop;
import com.efanzyhang.mi.core.net.RestClient;
import com.efanzyhang.mi.core.net.callback.IError;
import com.efanzyhang.mi.core.net.callback.IFailure;
import com.efanzyhang.mi.core.net.callback.ISuccess;
import com.efanzyhang.mi.core.util.log.LUitl;
import com.efanzyhang.mi.core.util.toast.TUtil;

/**
 * 项目名：MIShop
 * 包名：com.efanzyhang.mi.core.ui.refresh
 * 文件名：RefreshHandler
 * 创建者：efan.zyhang
 * 创建时间：2018/9/18 23:31
 * 描述： 刷新管理，刷新助手
 */
public class RefreshHandler implements SwipeRefreshLayout.OnRefreshListener {

    private final SwipeRefreshLayout REFRESH_LAYOUT;

    /**
     *  获取Refresh的控件
     * @param refreshLayout
     */
    public RefreshHandler(SwipeRefreshLayout refreshLayout) {
        //设置监听
        this.REFRESH_LAYOUT = refreshLayout;
        REFRESH_LAYOUT.setOnRefreshListener(this);
    }

    private void refresh() {
        //设置我们要开始加载了
        REFRESH_LAYOUT.setRefreshing(true);
        //获取全局Hnadler，发送2秒延迟
        MIShop.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //进行一些网络请求
                //这句话可以放在success中
                REFRESH_LAYOUT.setRefreshing(false);
            }
        }, 2000);
    }

    /**
     * 实现回调接口方法
     */
    @Override
    public void onRefresh() {
        refresh();
    }

    public void firstPage(String url) {
        RestClient.builder()
                .url(url)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String responds) {
                        LUitl.json("index",responds);
//                        Toast.makeText(MIShop.getApplicationContext(),responds,Toast.LENGTH_SHORT).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure(String e) {
                        Toast.makeText(MIShop.getApplicationContext(),e,Toast.LENGTH_SHORT).show();
                        LUitl.i("index",e);
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String message) {
                        Toast.makeText(MIShop.getApplicationContext(),"code"+code+"+message"+message,Toast.LENGTH_SHORT).show();
                    }
                })
                .build()
                .get();
    }
}
