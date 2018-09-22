package com.efanzyhang.mi.core.util.dimen;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.efanzyhang.mi.core.app.MIShop;

/**
 * 项目名：MIShop
 * 包名：com.efanzyhang.mi.core.util.dimen
 * 文件名：DimenUtil
 * 创建者：efan.zyhang
 * 创建时间：2018/8/12 21:34
 * 描述： 获取屏幕宽高的工具类
 */
public class DimenUtil {

    public static int getScreenHeight() {
        final Resources resources = MIShop.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }

    public static int getScreenWidth() {
        final Resources resources = MIShop.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }
}
