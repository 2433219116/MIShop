package com.efanzyhang.mi.ec.main;

import android.graphics.Color;

import com.efanzyhang.mi.core.fragment.bottom.BaseBottomFragment;
import com.efanzyhang.mi.core.fragment.bottom.BottomItemFragment;
import com.efanzyhang.mi.core.fragment.bottom.BottomTabBean;
import com.efanzyhang.mi.core.fragment.bottom.ItemBuilder;
import com.efanzyhang.mi.ec.main.index.IndexFragment;
import com.efanzyhang.mi.ec.main.sort.SortFragment;

import java.util.LinkedHashMap;

/**
 * 项目名：MIShop
 * 包名：com.efanzyhang.mi.ec.main
 * 文件名：EcBottomFragment
 * 创建者：efan.zyhang
 * 创建时间：2018/9/18 13:55
 * 描述： 主页BottomItem Fragment具体现实
 */
public class EcBottomFragment extends BaseBottomFragment {


    /**
     * 实现抽象方法
     * @param builder 构建Item的静态工厂
     * @return   返回Item的HashMap
     */
    @Override
    public LinkedHashMap<BottomTabBean, BottomItemFragment> setItems(ItemBuilder builder) {

        final LinkedHashMap<BottomTabBean,BottomItemFragment> items=new LinkedHashMap<>();

        items.put(new BottomTabBean("{fa-home}","主页"),new IndexFragment());
        items.put(new BottomTabBean("{fa-sort}","分类"),new SortFragment());
        items.put(new BottomTabBean("{fa-compass}","发现"),new IndexFragment());
        items.put(new BottomTabBean("{fa-shopping-cart}","购物车"),new IndexFragment());
        items.put(new BottomTabBean("{fa-user}","我的"),new IndexFragment());

        return builder.addItems(items).build();
    }


    @Override
    public int setIndexDelegate() {
        return 0;
    }

    @Override
    public int setClickColor() {
        return Color.parseColor("#ffff8800");
    }
}
