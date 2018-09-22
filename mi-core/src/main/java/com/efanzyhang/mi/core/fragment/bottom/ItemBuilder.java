package com.efanzyhang.mi.core.fragment.bottom;

import java.util.LinkedHashMap;

/**
 * 项目名：MIShop
 * 包名：com.efanzyhang.mi.core.fragment.bottom
 * 文件名：ItemBuilder
 * 创建者：efan.zyhang
 * 创建时间：2018/9/17 15:26
 * 描述： TODO
 */
public final class ItemBuilder {

    private final LinkedHashMap<BottomTabBean, BottomItemFragment> ITEMS = new LinkedHashMap<>();

    static ItemBuilder builder() {
        return new ItemBuilder();
    }

    public final ItemBuilder addItem(BottomTabBean bean, BottomItemFragment delegate) {
        ITEMS.put(bean, delegate);
        return this;
    }

    public final ItemBuilder addItems(LinkedHashMap<BottomTabBean, BottomItemFragment> items) {
        ITEMS.putAll(items);
        return this;
    }

    public final LinkedHashMap<BottomTabBean, BottomItemFragment> build() {
        return ITEMS;
    }
}
