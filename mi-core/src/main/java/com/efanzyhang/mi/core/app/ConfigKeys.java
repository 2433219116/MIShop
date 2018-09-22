package com.efanzyhang.mi.core.app;

/**
 * 项目名：MIShop
 * 包名：com.efanzyhang.mishop
 * 文件名：ConfigKeys
 * 创建者：efan.zyhang
 * 创建时间：2018/8/4 14:40
 * 描述： 枚举类，应用程序中唯一的单例，并且只能被初始化一次，我们可以用枚举进行多线程操作，也就是线程安全的懒汉模式
 */
public enum ConfigKeys {
    //网络请求域名
    API_HOST,
    //全局上下文
    APPLICATION_CONTEXT,
    //控制我们的初始化完成了没有
    CONFIG_READY,
    //存储字体的初始化项目
    ICON,
    LOADER_DELAYED,
    HANDLER,
    //拦截器
    INTERCEPTOR

}
