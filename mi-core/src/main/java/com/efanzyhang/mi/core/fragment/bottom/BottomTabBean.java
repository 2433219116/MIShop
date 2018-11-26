package com.efanzyhang.mi.core.fragment.bottom;

/**
 * 项目名：MIShop
 * 包名：com.efanzyhang.mi.core.fragment.bottom
 * 文件名：BottomTabBean
 * 创建者：efan.zyhang
 * 创建时间：2018/9/17 15:22
 * 描述： 底部item的bean对象，图标对应着Fragment
 */
public final class BottomTabBean {
    private final CharSequence ICON;
    private final CharSequence TITLE;

    public BottomTabBean(CharSequence icon, CharSequence title) {
        this.ICON = icon;
        this.TITLE = title;
    }

    public CharSequence getIcon() {
        return ICON;
    }

    public CharSequence getTitle() {
        return TITLE;
    }
}
