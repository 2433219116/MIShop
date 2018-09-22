package com.efanzyhang.mi.ec.icon;

import com.joanzapata.iconify.Icon;
import com.joanzapata.iconify.IconFontDescriptor;

/**
 * 项目名：MIShop
 * 包名：com.efanzyhang.mi.ec.icon
 * 文件名：FontECModel
 * 创建者：efan.zyhang
 * 创建时间：2018/8/7 15:38
 * 描述： TODO
 */
public class FontECModel implements IconFontDescriptor {
    @Override
    public String ttfFileName() {
        return "iconfont.ttf";
    }

    @Override
    public Icon[] characters() {
        return ECIcons.values();
    }
}
