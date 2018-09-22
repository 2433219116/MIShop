package com.efanzyhang.mi.core.ui.recycle;

import java.util.ArrayList;

/**
 * 项目名：MIShop
 * 包名：com.efanzyhang.mi.core.ui.recycle
 * 文件名：DataConverter
 * 创建者：efan.zyhang
 * 创建时间：2018/9/19 0:39
 * 描述： RecycleView的抽象数据转换基类
 */
public abstract class DataConverter {

    protected final ArrayList<MultipleItemEntity> ENTITIES = new ArrayList<>();

    private String mJsonData;

    public abstract ArrayList<MultipleItemEntity> convert();

    public DataConverter setJsonData(String json) {
        this.mJsonData = json;
        return this;
    }

    protected String getJsonData() {
        if (mJsonData == null || mJsonData.isEmpty()) {
            throw new NullPointerException("DATA IS NULL!");
        }
        return mJsonData;
    }

}
