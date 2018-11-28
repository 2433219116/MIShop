package com.efanzyhang.mi.ec.main.index;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.efanzyhang.mi.core.ui.recycle.DataConverter;
import com.efanzyhang.mi.core.ui.recycle.ItemType;
import com.efanzyhang.mi.core.ui.recycle.MultipleFields;
import com.efanzyhang.mi.core.ui.recycle.MultipleItemEntity;

import java.net.IDN;
import java.util.ArrayList;

/**
 * 项目名：MIShop
 * 包名：com.efanzyhang.mi.ec.main.index
 * 文件名：IndexDataConverter
 * 创建者：efan.zyhang
 * 创建时间：2018/9/19 15:38
 * 描述： 主页数据处理
 */
public class IndexDataConverter extends DataConverter {
    @Override
    public ArrayList<MultipleItemEntity> convert() {
        //取出Data下的数据集合
        final JSONArray dataArray = JSONObject.parseObject(getJsonData()).getJSONArray("data");
        //取出Data下的每一组数据
        final int size = dataArray.size();
        for (int i = 0; i < size; i++) {
            final JSONObject data = dataArray.getJSONObject(i);
            // 定义接收的类型
            final String imageUrl = data.getString("imageUrl");
            final String text = data.getString("text");
            final int spanSize = data.getInteger("spanSize");
            final int id = data.getInteger("goodsId");
            //banner是一个Array
            final JSONArray banners = data.getJSONArray("banners");

            final ArrayList<String> bannerImage = new ArrayList<>();
            //判断不同的类型来设置Item类型
            int type = 0;
            if (imageUrl == null && text != null) {
                type = ItemType.TEXT;
            } else if (imageUrl != null && text == null) {
                type = ItemType.IMAGE;
            } else if (imageUrl != null) {
                type = ItemType.TEXT_IMAGE;
            } else if (!banners.isEmpty()) {
                type = ItemType.BANNERS;
                final int bannerSize = bannerImage.size();
                for (int j = 0; j < bannerSize; j++) {
                    final String banner = banners.getString(j);
                    bannerImage.add(banner);
                }
            }

            //初始化entity ，通过建造者模式创建entity
            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setField(MultipleFields.ITEM_TYPE, type)
                    .setField(MultipleFields.SPAN_SIZE, spanSize)
                    .setField(MultipleFields.ID, id)
                    .setField(MultipleFields.IMAGE_URL, imageUrl)
                    .setField(MultipleFields.TEXT, text)
                    .setField(MultipleFields.BANNERS, bannerImage)
                    .build();

            //最后加入到DataConvert父类中的ArrayList中
            ENTITIES.add(entity);

        }
        return ENTITIES;
    }
}
