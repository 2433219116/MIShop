package com.efanzyhang.mi.core.ui.launcher;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;

/**
 * 项目名：MIShop
 * 包名：com.efanzyhang.mi.core.ui.launcher
 * 文件名：LauncherHolder
 * 创建者：efan.zyhang
 * 创建时间：2018/9/13 16:46
 * 描述： TODO
 */
public class LauncherHolder implements Holder<Integer> {
    AppCompatImageView mImageView;
    @Override
    public View createView(Context context) {
        mImageView=new AppCompatImageView(context);
        return mImageView;
    }

    @Override
    public void UpdateUI(Context context, int position, Integer data) {
        mImageView.setBackgroundResource(data);
    }
}
