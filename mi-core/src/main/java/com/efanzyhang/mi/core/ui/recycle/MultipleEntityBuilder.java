package com.efanzyhang.mi.core.ui.recycle;

import java.util.LinkedHashMap;
import java.util.WeakHashMap;

/**
 * 项目名：MIShop
 * 包名：com.efanzyhang.mi.core.ui.recycle
 * 文件名：MultipleEntityBuilder
 * 创建者：efan.zyhang
 * 创建时间：2018/9/19 15:07
 * 描述： MultipleEntity的建造者模式，添加各种参数（为了防止类过长）
 */
public class MultipleEntityBuilder {

    private static final LinkedHashMap<Object, Object> FIELDS = new LinkedHashMap<>();

    public MultipleEntityBuilder() {
        //不能讲上次的数据追加，每次先清除
        FIELDS.clear();
    }

    /**
     * 设置Item类型
     * @param itemType
     * @return
     */
    public final MultipleEntityBuilder setItemType(int itemType) {
        FIELDS.put(MultipleFields.ITEM_TYPE, itemType);
        return this;
    }

    /**
     * 设置Filed键值对
     * @param key
     * @param value
     * @return
     */
    public final MultipleEntityBuilder setField(Object key, Object value) {
        FIELDS.put(key, value);
        return this;
    }

    /**
     * 设置所有的HashMap
     * @param fields
     * @return
     */
    public final MultipleEntityBuilder setFields(LinkedHashMap<Object, Object> fields) {
        FIELDS.putAll(fields);
        return this;
    }

    public final MultipleItemEntity build() {
        return new MultipleItemEntity(FIELDS);
    }
}
