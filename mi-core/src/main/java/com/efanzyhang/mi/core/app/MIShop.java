package com.efanzyhang.mi.core.app;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 项目名：MIShop
 * 包名：com.efanzyhang.mi.core.app
 * 文件名：MIShop
 * 创建者：efan.zyhang
 * 创建时间：2018/8/3 16:36
 * 描述： MiShop同一管理
 */
public final class MIShop {
    /**
     * app初始化
     * 将对象的引用转入到配置项中
     *
     * @return 实例化的Configurator
     */
    public static Configurator init(Context context) {
        Configurator.getInstance().
                getMishopConfigs().
                put(ConfigKeys.APPLICATION_CONTEXT, context.getApplicationContext());
        return Configurator.getInstance();
    }

    /**
     *
     * @return 配置类的获取
     */
    public static Configurator getConfiguration() {
        return Configurator.getInstance();
    }

    /**
     * 获取配置信息
     *
     * @return 配置集合
     */
    public static <T> T getConfiguration(Object key) {
        return Configurator.getInstance().getConfiguration(key);
    }

    /**
     * 获取全局application对象
     *
     * @return 全局上下文
     */
    public static Application getApplicationContext() {
        return getConfiguration(ConfigKeys.APPLICATION_CONTEXT);

    }

    /**
     * 获取全局Hnadler对象
     *
     * @return 配置项中的HANDLER
     */
    public static Handler getHandler() {
        return getConfiguration(ConfigKeys.HANDLER);
    }


}
