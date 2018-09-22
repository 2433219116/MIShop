package com.efanzyhang.mishop;

import android.annotation.SuppressLint;
import android.app.Application;
import android.graphics.Color;
import android.graphics.fonts.FontVariationAxis;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.util.Log;

import com.efanzyhang.mi.core.app.Configurator;
import com.efanzyhang.mi.core.app.MIShop;
import com.efanzyhang.mi.core.net.interceptors.DebugInterceptor;
import com.efanzyhang.mi.core.util.log.LUitl;
import com.efanzyhang.mi.ec.database.DatabaseManager;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.joanzapata.iconify.fonts.IoniconsModule;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

import org.greenrobot.greendao.database.Database;

import es.dmoral.toasty.Toasty;

/**
 * 项目名：MIShop
 * 包名：com.efanzyhang.mishop
 * 文件名：ShopApplication
 * 创建者：efan.zyhang
 * 创建时间：2018/8/3 16:52
 * 描述： Android系统会在启动应用进程时创建一个Application对象，Application类是维护应用全局状态的基类
 */
public class ShopApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //图标、loader、拦截器、服务器地址等基础配置
        MIShop.init(this)
                .withIcon(new FontAwesomeModule())
                .withIcon(new IoniconsModule())
                .withLoaderDelayed(10000)
                .withApiHost("http://mock.fulingjie.com/mock/")
                .withInterceptor(new StethoInterceptor())
                .withInterceptor(new DebugInterceptor("index", R.raw.test))
                .configure();
        //GreenDao配置
        DatabaseManager.getInstance().init(this);
        //Stetho配置
        Stetho.initializeWithDefaults(this);
        //Logger配置
//        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
//                .showThreadInfo(true)  // (Optional) Whether to show thread info or not. Default true
//                .methodCount(0)         // (Optional) How many method line to show. Default 2
//                .methodOffset(7)        // (Optional) Hides internal method calls up to offset. Default 5
//                .logStrategy(customLog) // (Optional) Changes the log strategy to print out. Default LogCat
//                .tag("test")   // (Optional) Global tag for every log. Default PRETTY_LOGGER
//                .build();
        Logger.addLogAdapter(new AndroidLogAdapter());
        //Toasty
        Toasty.Config.getInstance()
//                .setErrorColor(Color.RED) // optional
//                .setInfoColor(Color.BLACK) // optional
//                .setSuccessColor(Color.GREEN) // optional
//                .setWarningColor(Color.DKGRAY) // optional
//                .setTextColor(Color.WHITE) // optional
//                .tintIcon() // optional (apply textColor also to the icon)
//                .setToastTypeface() // optional
//                .setTextSize() // optional
                .apply();

        LUitl.i("Application", Configurator.getInstance().getMishopConfigs().toString());
    }
}
