package com.efanzyhang.mi.core.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;

import com.efanzyhang.mi.core.R;
import com.efanzyhang.mi.core.fragment.MishopFragment;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * 项目名：MIShop
 * 包名：com.efanzyhang.mi.core.activity
 * 文件名：ProxyActivity
 * 创建者：efan.zyhang
 * 创建时间：2018/8/8 13:21
 * 描述： 多个fragment依赖的唯一activity，与fragment实现类似
 */
public abstract class ProxyActivity extends SupportActivity {

    public abstract MishopFragment setRootDelegate();

    /**
     * 创建activity
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContainer(savedInstanceState);
    }

    /**
     * 加载fragment
     *
     * @param savedInstanceState
     */
    private void initContainer(@Nullable Bundle savedInstanceState) {
        @SuppressLint("RestrictedApi") final ContentFrameLayout container = new ContentFrameLayout(this);
        container.setId(R.id.delegate_container);

        setContentView(container);
        if (savedInstanceState == null) {
            loadRootFragment(R.id.delegate_container, setRootDelegate());
        }
    }

    /**
     * 销毁activity时进行垃圾回收处理
     * <p>
     * 这两个方法是本来执行的，由于我们单activity
     * 所以我们在activity退出则进行垃圾回收即可
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
        System.runFinalization();
    }
}
