package com.efanzyhang.mi.core.ui.recycle;

import java.util.LinkedHashMap;
import java.util.WeakHashMap;

/**
 * 项目名：MIShop
 * 包名：com.efanzyhang.mi.core.ui.recycle
 * 文件名：MultipleEntityBuilder
 * 创建者：efan.zyhang
 * 创建时间：2018/9/19 15:07
 * 描述： TODO
 */
public class MultipleEntityBuilder {

    private static final LinkedHashMap<Object, Object> FIELDS = new LinkedHashMap<>();

    public MultipleEntityBuilder() {
        //清除缓存
        FIELDS.clear();
    }

    public final MultipleEntityBuilder setItemType(int itemType) {
        FIELDS.put(MultipleFields.ITEM_TYPE, itemType);
        return this;
    }

    public final MultipleEntityBuilder setField(Object key, Object value) {
        FIELDS.put(key, value);
        return this;
    }

    public final MultipleEntityBuilder setFields(LinkedHashMap<Object, Object> fields) {
        FIELDS.putAll(fields);
        return this;
    }

    public final MultipleItemEntity build() {
        return new MultipleItemEntity(FIELDS);
    }
}
