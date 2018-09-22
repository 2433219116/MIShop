package com.efanzyhang.mi.core.app;

import android.os.Handler;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Interceptor;

/**
 * 项目名：MIShop
 * 包名：com.efanzyhang.mi.core.app
 * 文件名：Configurator
 * 创建者：efan.zyhang
 * 创建时间：2018/8/4 14:16
 * 描述： 配置文件的存储与获取
 */
public class Configurator {
    //创建存储信息的数据结构
    private static final HashMap<Object, Object> MISHOP_CONFIGS = new HashMap<>();
    //存储图标对象的list
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();
    //拦截器
    private static final ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();
    //延迟handler
    private static final Handler HANDLER = new Handler();

    /**
     * 构造方法私有化 配置开始了（未完成）
     */
    private Configurator() {
        MISHOP_CONFIGS.put(ConfigKeys.CONFIG_READY, false);
        MISHOP_CONFIGS.put(ConfigKeys.HANDLER, HANDLER);
    }

    /**
     * 静态内部类声明configurator对象
     */
    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }
    /**
     * 获取单例
     * <p>
     * 这个和上一个方法组成单例模式，就是非常完美的线程安全懒汉模式（使用静态内部类，然后获取）
     *
     * @return Configurator对象
     */
    public static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * 返回配置信息
     *
     * @return 配置集合
     */
    public HashMap<Object, Object> getMishopConfigs() {
        return MISHOP_CONFIGS;
    }

    /**
     * 完成了配置
     */
    public final void configure() {
        initIcons();
        MISHOP_CONFIGS.put(ConfigKeys.CONFIG_READY, true);
    }

    /**
     * 配置ApiHost
     *
     * @return Configurator对象
     */
    public final Configurator withApiHost(String host) {
        MISHOP_CONFIGS.put(ConfigKeys.API_HOST, host);
        return this;
    }

    /**
     * 加载延迟
     *
     * @param delayed
     * @return
     */
    public final Configurator withLoaderDelayed(long delayed) {
        MISHOP_CONFIGS.put(ConfigKeys.LOADER_DELAYED, delayed);
        return this;
    }

    /**
     * 传入拦截器到配置文件中
     *
     * @param interceptor
     * @return
     */
    public final Configurator withInterceptor(Interceptor interceptor) {
        INTERCEPTORS.add(interceptor);
        MISHOP_CONFIGS.put(ConfigKeys.INTERCEPTOR, INTERCEPTORS);
        return this;
    }

    /**
     * 负载方法
     *
     * @param interceptor
     * @return
     */
    public final Configurator withInterceptors(ArrayList<Interceptor> interceptor) {
        INTERCEPTORS.addAll(interceptor);
        MISHOP_CONFIGS.put(ConfigKeys.INTERCEPTOR, INTERCEPTORS);
        return this;
    }


    /**
     * 检测是否完成配置，在应用程序获取配置时调用，如果没有使用configure的方法时，会异常，保证配置的完成性与正确性
     */
    private void checkConfiguration() {
        final boolean isReady = (boolean) MISHOP_CONFIGS.get(ConfigKeys.CONFIG_READY);
        //如果我们的配置并没完成，但是我们急着做以下的操作，需要抛出运行时异常
        if (!isReady) {
            throw new RuntimeException("Configure is not ready,call configure");
        }
    }

    /**
     * 初始化图标
     */
    private void initIcons() {
        if (ICONS.size() > 0) {
            final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for (int i = 0; i < ICONS.size(); i++) {
                initializer.with(ICONS.get(i));
            }
        }
    }

    /**
     * 加载图标库
     *
     * @return Configurator对象
     */
    public final Configurator withIcon(IconFontDescriptor fontDescriptor) {
        ICONS.add(fontDescriptor);
        return this;
    }

    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Object key) {
        checkConfiguration();
        final Object value = MISHOP_CONFIGS.get(key);
        if (value == null) {
            throw new NullPointerException(key.toString() + " IS NULL");
        }
        return (T) MISHOP_CONFIGS.get(key);
    }
}
