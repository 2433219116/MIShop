package com.efanzyhang.mi.core.fragment.bottom;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.efanzyhang.mi.core.R;
import com.efanzyhang.mi.core.R2;
import com.efanzyhang.mi.core.fragment.MishopFragment;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import me.yokeyword.fragmentation.ISupportFragment;

/**
 * 项目名：MIShop
 * 包名：com.efanzyhang.mi.core.fragment.bottom
 * 文件名：BaseBottomFragment
 * 创建者：efan.zyhang
 * 创建时间：2018/9/17 13:54
 * 描述： 底部布局Fragment的基类，逻辑实现，没有加载数据，具体的图片信息等
 */
public abstract class BaseBottomFragment extends MishopFragment implements View.OnClickListener {
    //存储底部item的bean
    private final ArrayList<BottomTabBean> BEANS = new ArrayList<>();
    //存储Fragment
    private final ArrayList<BottomItemFragment> DELEGATES = new ArrayList<>();
    //存储映射
    private final LinkedHashMap<BottomTabBean, BottomItemFragment> ITEMS = new LinkedHashMap<>();

    //默认显示的Fragment，以及每次切换之后显示的Fragment
    private int mCurrentDelegate = 0;
    //每次切换的Fragment Id
    private int mIndexDelegate;
    //点击变色
    private int mClickColor = Color.RED;

    //通过ButterKnife绑定底部布局的控件
    @BindView(R2.id.bottom_bar)
    LinearLayoutCompat mBottomBar;

    //抽象强制继承类实现方法
    //设置Item
    public abstract LinkedHashMap<BottomTabBean, BottomItemFragment> setItems(ItemBuilder builder);

    /**
     * 主布局
     */
    @Override
    public Object setLayout() {
        return R.layout.fragment_bottom;
    }

    //设置Fragment
    public abstract int setIndexDelegate();

    //设置点击颜色
    @ColorInt
    public abstract int setClickColor();

    /**
     * 获取内容放在成员变量中以待使用
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIndexDelegate = setIndexDelegate();
        if (setClickColor() != 0) {
            mClickColor = setClickColor();
        }
        //返回的是新创建的ItemBuilder对象
        final ItemBuilder builder = ItemBuilder.builder();
        //创建LinkedHashMap接收  上面抽象方法定义返回值，传入的是builder对象
        final LinkedHashMap<BottomTabBean, BottomItemFragment> items = setItems(builder);
        //将Item传入映射集合
        ITEMS.putAll(items);

        //有序遍历LinkedHashMap
        for (Map.Entry<BottomTabBean, BottomItemFragment> entry : ITEMS.entrySet()) {
            final BottomTabBean key = entry.getKey();
            final BottomItemFragment value = entry.getValue();
            BEANS.add(key);
            DELEGATES.add(value);
        }
    }

    /**
     * 获取View设置绑定，同时显示内容
     *
     * @param savedInstanceState
     * @param rootView
     */
    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        //size定义在外边防止多次调用size()
        final int size = ITEMS.size();
        //根据Item的项数遍历
        for (int i = 0; i < size; i++) {
            //底部xml布局转化为view对象
            LayoutInflater.from(getContext()).inflate(R.layout.bottom_item_icon_text_layout, mBottomBar);
            //获取子View RelativeLayout
            final RelativeLayout item = (RelativeLayout) mBottomBar.getChildAt(i);
            //设置Tag，目的为了监听事件时使用id
            // 但是正常的话需要写一个ids文件来保证id唯一性，为了简单就用Tag，为下角标
            item.setTag(i);
            //设置监听事件
            item.setOnClickListener(this);
            //获取每个RelativeLayout的子控件
            final IconTextView itemIcon = (IconTextView) item.getChildAt(0);
            final AppCompatTextView itemTitle = (AppCompatTextView) item.getChildAt(1);
            final BottomTabBean bean = BEANS.get(i);
            //初始化数据
            itemIcon.setText(bean.getIcon());
            itemTitle.setText(bean.getTitle());
            //角标如果为当前要展示的Fragment，当前页设置颜色
            if (i == mIndexDelegate) {
                itemIcon.setTextColor(mClickColor);
                itemTitle.setTextColor(mClickColor);
            }
        }
        //Fragmention中的方法，创建一个Fragment数组，塞进了刚才加载出来的Fragment，长度也确定
        //框架是为了原子性，我们不应去更改它，应该去扩充
        final ISupportFragment[] delegateArray = DELEGATES.toArray(new ISupportFragment[size]);

        //加载多个同级根Fragment,类似Wechat, QQ主页的场景
        //int containerId, int showPosition, ISupportFragment... toFragments
        //切换的根布局，加载页的编号，Fragment数组
        getSupportDelegate().loadMultipleRootFragment(R.id.bottom_bar_delegate_container, mIndexDelegate, delegateArray);

    }

    /**
     * 重置布局颜色
     */
    private void resetColor() {
        //通过mBottomBar的子布局限制遍历次数
        final int count = mBottomBar.getChildCount();
        for (int i = 0; i < count; i++) {
            final RelativeLayout item = (RelativeLayout) mBottomBar.getChildAt(i);
            final IconTextView itemIcon = (IconTextView) item.getChildAt(0);
            int mNormalColor = Color.GRAY;
            itemIcon.setTextColor(mNormalColor);
            final AppCompatTextView itemTitle = (AppCompatTextView) item.getChildAt(1);
            itemTitle.setTextColor(mNormalColor);
        }
    }

    @Override
    public void onClick(View v) {
        //获取角标tag
        final int tag = (int) v.getTag();
        resetColor();
        final RelativeLayout item = (RelativeLayout) v;
        //分别获取控件设置控件颜色
        final IconTextView itemIcon = (IconTextView) item.getChildAt(0);
        itemIcon.setTextColor(mClickColor);

        final AppCompatTextView itemTitle = (AppCompatTextView) item.getChildAt(1);
        itemTitle.setTextColor(mClickColor);
        //Fragmention中的方法，展示和隐藏Fragment
        getSupportDelegate().showHideFragment(DELEGATES.get(tag), DELEGATES.get(mCurrentDelegate));
        //注意先后顺序，把上一个隐藏掉
        mCurrentDelegate = tag;
    }
}
