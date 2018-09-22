package com.efanzyhang.mi.core.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.efanzyhang.mi.core.activity.ProxyActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

/**
 * 项目名：MIShop
 * 包名：com.efanzyhang.mi.core.fragment
 * 文件名：BaseDelegate
 * 创建者：efan.zyhang
 * 创建时间：2018/8/8 13:23
 * 描述： 引入了Fragmentation的基类
 */
public abstract class BaseFragment extends SwipeBackFragment {

    //butterknife定义的类型
    private Unbinder mUnbinder;

    /**
     * 设置布局抽象方法
     *
     * @return 对象
     */
    public abstract Object setLayout();

    public abstract void onBindView(@Nullable Bundle savedInstanceState, View rootView);

    /**
     * 布局创建时设置布局，绑定控件
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = null;
        //判断返回类型，进行赋值
        if (setLayout() instanceof Integer) {
            rootView = inflater.inflate((Integer) setLayout(), container, false);
        } else if (setLayout() instanceof View) {
            rootView = (View) setLayout();
        } else {
            throw new ClassCastException("setLayout() must be int or View!");
        }
        if (rootView != null) {
            mUnbinder = ButterKnife.bind(this, rootView);
            onBindView(savedInstanceState, rootView);
        }
        return rootView;
    }

    public final ProxyActivity getProxyActivity(){
        return (ProxyActivity) _mActivity;
    }

    /**
     * 布局销毁时解除绑定
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }
}
