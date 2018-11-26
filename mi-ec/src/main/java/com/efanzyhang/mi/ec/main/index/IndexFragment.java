package com.efanzyhang.mi.ec.main.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.efanzyhang.mi.core.fragment.bottom.BottomItemFragment;
import com.efanzyhang.mi.core.net.RestClient;
import com.efanzyhang.mi.core.net.callback.ISuccess;
import com.efanzyhang.mi.core.ui.recycle.MultipleFields;
import com.efanzyhang.mi.core.ui.recycle.MultipleItemEntity;
import com.efanzyhang.mi.core.ui.refresh.RefreshHandler;
import com.efanzyhang.mi.core.util.toast.TUtil;
import com.efanzyhang.mi.ec.R;
import com.efanzyhang.mi.ec.R2;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 项目名：MIShop
 * 包名：com.efanzyhang.mi.ec.main.index
 * 文件名：IndexFragment
 * 创建者：efan.zyhang
 * 创建时间：2018/9/18 13:57
 * 描述： 主页Fragment实现
 */
public class IndexFragment extends BottomItemFragment {

    @BindView(R2.id.rv_index)
    RecyclerView mRecyclerView;
    @BindView(R2.id.srl_index)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R2.id.tb_index)
    Toolbar mToolbar;
    @BindView(R2.id.icon_index_scan)
    IconTextView mIconScan;
    @BindView(R2.id.ed_index_search)
    AppCompatEditText mEdSearch;
    @BindView(R2.id.icon_index_message)
    IconTextView mIconMessage;

    private RefreshHandler mRefreshHandler;

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mRefreshHandler = new RefreshHandler(mSwipeRefreshLayout);
        RestClient.builder()
                .url("api")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String responds) {
                        final IndexDataConverter converter = new IndexDataConverter();
                        converter.setJsonData(responds);
                        final ArrayList<MultipleItemEntity> entities = converter.convert();
                        final String imageurl = entities.get(1).getField(MultipleFields.IMAGE_URL);
                        TUtil.success(getContext(), imageurl);
                    }
                })
                .build()
                .get();
    }

    private void initRefreshLayout() {
        mSwipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );
        mSwipeRefreshLayout.setProgressViewOffset(true, 120, 300);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initRefreshLayout();
//        mRefreshHandler.firstPage("api");
    }

    @Override
    public Object setLayout() {
        return R.layout.fragment_sort;
    }


}
