package com.efanzyhang.mi.core.ui.recycle;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.security.Key;
import java.util.LinkedHashMap;
import java.util.Objects;

/**
 * 项目名：MIShop
 * 包名：com.efanzyhang.mi.core.ui.recycle
 * 文件名：MultipleItemEntity
 * 创建者：efan.zyhang
 * 创建时间：2018/9/19 0:44
 * 描述： 多形态item
 */
public class MultipleItemEntity implements MultiItemEntity {

    private final ReferenceQueue<LinkedHashMap<Object, Object>> TIME_QUEUE = new ReferenceQueue<>();
    private final LinkedHashMap<Object, Object> MULTIPLE_FIELDS = new LinkedHashMap<>();
    private final SoftReference<LinkedHashMap<Object, Object>> FIELDS_REFERENCE =
            new SoftReference<>(MULTIPLE_FIELDS, TIME_QUEUE);


    MultipleItemEntity(LinkedHashMap<Object, Object> fields) {
        FIELDS_REFERENCE.get().putAll(fields);
    }

    public static MultipleEntityBuilder builder() {
        return new MultipleEntityBuilder();
    }

    /**
     * BRVAH框架方法
     * MultiItemEntity获取item类别
     *
     * @return
     */
    @Override
    public int getItemType() {
        return (int) FIELDS_REFERENCE.get().get(MultipleFields.ITEM_TYPE);
    }

    public final <T> T getField(Object key) {
        return (T) FIELDS_REFERENCE.get().get(key);
    }

    public final <T> T getFields() {
        return (T) FIELDS_REFERENCE.get();
    }

    public final MultipleItemEntity setField(Object key, Object value) {
        FIELDS_REFERENCE.get().put(key, value);
        return this;
    }
}
