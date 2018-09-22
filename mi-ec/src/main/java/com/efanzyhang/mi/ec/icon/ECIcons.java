package com.efanzyhang.mi.ec.icon;

import com.joanzapata.iconify.Icon;

/**
 * 项目名：MIShop
 * 包名：com.efanzyhang.mi.ec.icon
 * 文件名：ECIcons
 * 创建者：efan.zyhang
 * 创建时间：2018/8/8 12:10
 * 描述： TODO
 */
public enum ECIcons implements Icon {
    icon_scan('\ue606'),
    icon_ali_pay('\ue606');
    private char character;

    ECIcons(char character) {
        this.character = character;
    }

    @Override
    public String key() {
        return name().replace('_', '-');
    }

    @Override
    public char character() {
        return character;
    }
}
