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
 * 描述： TODO
 */
public abstract class BaseBottomFragment extends MishopFragment implements View.OnClickListener {

    private final ArrayList<BottomTabBean> BEANS = new ArrayList<>();
    private final ArrayList<BottomItemFragment> DELEGATES = new ArrayList<>();
    private final LinkedHashMap<BottomTabBean, BottomItemFragment> ITEMS = new LinkedHashMap<>();

    private int mCurrentDelegate = 0;
    private int mIndexDelegate;
    private int mClickColor = Color.RED;


    @BindView(R2.id.bottom_bar)
    LinearLayoutCompat mBottomBar;

    public abstract LinkedHashMap<BottomTabBean, BottomItemFragment> setItems(ItemBuilder builder);

    @Override
    public Object setLayout() {
        return R.layout.fragment_bottom;
    }

    public abstract int setIndexDelegate();

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

        final ItemBuilder builder = ItemBuilder.builder();
        final LinkedHashMap<BottomTabBean, BottomItemFragment> items = setItems(builder);
        ITEMS.putAll(items);

        for (Map.Entry<BottomTabBean, BottomItemFragment> entry : ITEMS.entrySet()) {
            final BottomTabBean key = entry.getKey();
            final BottomItemFragment value = entry.getValue();
            BEANS.add(key);
            DELEGATES.add(value);
        }
    }

    /**
     * 获取控件设置控件显示内容
     *
     * @param savedInstanceState
     * @param rootView
     */
    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        final int size = ITEMS.size();
        for (int i = 0; i < size; i++) {
            LayoutInflater.from(getContext()).inflate(R.layout.bottom_item_icon_text_layout, mBottomBar);
            final RelativeLayout item = (RelativeLayout) mBottomBar.getChildAt(i);
            item.setTag(i);
            item.setOnClickListener(this);
            final IconTextView itemIcon = (IconTextView) item.getChildAt(0);
            final AppCompatTextView itemTitle = (AppCompatTextView) item.getChildAt(1);
            final BottomTabBean bean = BEANS.get(i);
            //初始化数据
            itemIcon.setText(bean.getIcon());
            itemTitle.setText(bean.getTitle());
            if (i == mIndexDelegate) {
                itemIcon.setTextColor(mClickColor);
                itemTitle.setTextColor(mClickColor);
            }
        }
        final ISupportFragment[] delegateArray = DELEGATES.toArray(new ISupportFragment[size]);
        getSupportDelegate().loadMultipleRootFragment(R.id.bottom_bar_delegate_container, mIndexDelegate, delegateArray);

    }

    private void resetColor() {
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
        final int tag = (int) v.getTag();
        resetColor();
        final RelativeLayout item = (RelativeLayout) v;
        final IconTextView itemIcon = (IconTextView) item.getChildAt(0);
        itemIcon.setTextColor(mClickColor);
        final AppCompatTextView itemTitle = (AppCompatTextView) item.getChildAt(1);
        itemTitle.setTextColor(mClickColor);
        //Fragmention中的方法
        getSupportDelegate().showHideFragment(DELEGATES.get(tag), DELEGATES.get(mCurrentDelegate));
        mCurrentDelegate = tag;
    }
}
