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
 * 描述： 多形态item，需要继承MultiItemEntity
 */
public class MultipleItemEntity implements MultiItemEntity {

    //数据太过于庞大时，容易溢出，推荐使用ReferenceQueue，当内存不够用时进行释放
    private final ReferenceQueue<LinkedHashMap<Object, Object>> TIME_QUEUE = new ReferenceQueue<>();
    //有序的键值对
    private final LinkedHashMap<Object, Object> MULTIPLE_FIELDS = new LinkedHashMap<>();
    //软引用，在需要释放内存时进行释放
    private final SoftReference<LinkedHashMap<Object, Object>> FIELDS_REFERENCE =
            new SoftReference<>(MULTIPLE_FIELDS, TIME_QUEUE);

    //构造方法
    MultipleItemEntity(LinkedHashMap<Object, Object> fields) {
        // FIELDS_REFERENCE.get()返回的就是 MULTIPLE_FIELDS
        FIELDS_REFERENCE.get().putAll(fields);
    }

    public static MultipleEntityBuilder builder() {
        return new MultipleEntityBuilder();
    }

    /**
     * BRVAH框架方法
     * MultiItemEntity获取不同的item类别
     *
     * @return
     */
    @Override
    public int getItemType() {
        return (int) FIELDS_REFERENCE.get().get(MultipleFields.ITEM_TYPE);
    }

    /**
     * 通过自己的输入加入key选取值
     *
     * @param key
     * @param <T> 强制类型转换
     * @return
     */
    public final <T> T getField(Object key) {
        return (T) FIELDS_REFERENCE.get().get(key);
    }

    /**
     *
     * @return LinkedHashMap全部
     */
    public final LinkedHashMap<?, ?> getFields() {
        return FIELDS_REFERENCE.get();
    }

    /**
     * 将数据添加进LinkedHashMap
     *
     * @param key
     * @param value
     * @return
     */
    public final MultipleItemEntity setField(Object key, Object value) {
        FIELDS_REFERENCE.get().put(key, value);
        return this;
    }
}
