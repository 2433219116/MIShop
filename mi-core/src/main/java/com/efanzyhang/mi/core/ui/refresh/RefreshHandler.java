package com.efanzyhang.mi.core.ui.refresh;

import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.Toast;

import com.efanzyhang.mi.core.app.MIShop;
import com.efanzyhang.mi.core.net.RestClient;
import com.efanzyhang.mi.core.net.callback.ISuccess;
import com.efanzyhang.mi.core.util.log.LUitl;
import com.efanzyhang.mi.core.util.toast.TUtil;

/**
 * 项目名：MIShop
 * 包名：com.efanzyhang.mi.core.ui.refresh
 * 文件名：RefreshHandler
 * 创建者：efan.zyhang
 * 创建时间：2018/9/18 23:31
 * 描述： TODO
 */
public class RefreshHandler implements SwipeRefreshLayout.OnRefreshListener {

    private final SwipeRefreshLayout REFRESH_LAYOUT;

    public RefreshHandler(SwipeRefreshLayout refreshLayout) {
        this.REFRESH_LAYOUT = refreshLayout;
        REFRESH_LAYOUT.setOnRefreshListener(this);
    }

    private void refresh() {
        REFRESH_LAYOUT.setRefreshing(true);
        MIShop.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //进行一些网络请求
                //这句话可以放在success中
                REFRESH_LAYOUT.setRefreshing(false);
            }
        }, 2000);
    }

    @Override
    public void onRefresh() {
        refresh();
    }

    public void firstPage(String url) {
//        RestClient.builder()
//                .url(url)
//                .success(new ISuccess() {
//                    @Override
//                    public void onSuccess(String responds) {
////                        LUitl.json("index",responds);
//                        Toast.makeText(MIShop.getApplicationContext(),responds,Toast.LENGTH_SHORT).show();
//                    }
//                })
//                .build()
//                .get();
    }
}
