package com.efanzyhang.mi.core.ui.launcher;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;

/**
 * 项目名：MIShop
 * 包名：com.efanzyhang.mi.core.ui.launcher
 * 文件名：LauncherHolderCreate
 * 创建者：efan.zyhang
 * 创建时间：2018/9/13 16:43
 * 描述： TODO
 */
public class LauncherHolderCreate implements CBViewHolderCreator<LauncherHolder>{

    @Override
    public LauncherHolder createHolder() {
        return new LauncherHolder();
    }
}
