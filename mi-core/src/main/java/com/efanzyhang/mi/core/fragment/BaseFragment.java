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

    /**
     * 需要view来帮顶控件时实现抽象方法
     *
     * @param savedInstanceState 重建布局时保存的数据
     * @param rootView 界面view
     */
    public abstract void onBindView(@Nullable Bundle savedInstanceState, View rootView);

    /**
     * Fragment生命周期onCreateView方法中进行布局的绑定，Butterknife的绑定以及rootView的传递
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return view
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = null;
        //判断返回类型，进行赋值

        //根据类型进行转化
        if (setLayout() instanceof Integer) {
            rootView = inflater.inflate((Integer) setLayout(), container, false);
        } else if (setLayout() instanceof View) {
            rootView = (View) setLayout();
        } else {
            //所有的Fragment加载时，都要经过赋值，否则就会报错，setLayout为空的异常
            throw new ClassCastException("setLayout() must be int or View!");
        }
        //如果rootView不为空也就是通过setLayout进行了赋值，那么我们绑定ButterKnife，
        // 同时如果需要view时，我们通过onBindView抽象方法传递view参数，实现类实现抽象方法，在通过rootView来写绑定控件
        if (rootView != null) {
            mUnbinder = ButterKnife.bind(this, rootView);
            onBindView(savedInstanceState, rootView);
        }
        return rootView;
    }

    /**
     *
     * @return 根Activity
     */
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
