package com.efanzyhang.mi.core.fragment.bottom;

import java.util.LinkedHashMap;

/**
 * 项目名：MIShop
 * 包名：com.efanzyhang.mi.core.fragment.bottom
 * 文件名：ItemBuilder
 * 创建者：efan.zyhang
 * 创建时间：2018/9/17 15:26
 * 描述： 简单工厂模式创建Item集合
 */
public final class ItemBuilder {

    //LinkHashMap是有序的
    private final LinkedHashMap<BottomTabBean, BottomItemFragment> ITEMS = new LinkedHashMap<>();

    //创建新的builder
    static ItemBuilder builder() {
        return new ItemBuilder();
    }
    //添加Item集合，一个bean对应着一个Fragment
    public final ItemBuilder addItem(BottomTabBean bean, BottomItemFragment delegate) {
        ITEMS.put(bean, delegate);
        return this;
    }
    //上面方法的重载
    public final ItemBuilder addItems(LinkedHashMap<BottomTabBean, BottomItemFragment> items) {
        ITEMS.putAll(items);
        return this;
    }
    //返回Item集合
    public final LinkedHashMap<BottomTabBean, BottomItemFragment> build() {
        return ITEMS;
    }
}
