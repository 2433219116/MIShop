package com.efanzyhang.mi.core.util.toast;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;

/**
 * 项目名：MIShop
 * 包名：com.efanzyhang.mi.core.util.toast
 * 文件名：TUtil
 * 创建者：efan.zyhang
 * 创建时间：2018/9/15 13:15
 * 描述： Toast工具类，结合Toasty
 */
public class TUtil {

    private TUtil() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static boolean isShow = true;

    /**
     *
     * @param context
     * @param message
     */
    public static void normal(Context context, CharSequence message) {
        if (isShow)
            Toasty.normal(context, message, Toast.LENGTH_SHORT).show();
    }

    /**
     *
     * @param context
     * @param message
     */
    public static void normal(Context context, int message) {
        if (isShow)
            Toasty.normal(context, String.valueOf(message), Toast.LENGTH_SHORT).show();
    }


    /**
     *
     * @param context
     * @param message
     * @param duration
     */
    public static void normal(Context context, CharSequence message, int duration) {
        if (isShow)
            Toasty.normal(context, message, duration).show();
    }

    /**
     *
     * @param context
     * @param message
     * @param yourIconDrawable
     */
    public static void normal(Context context, CharSequence message, Drawable yourIconDrawable) {
        if (isShow)
            Toasty.normal(context, message, Toast.LENGTH_SHORT, yourIconDrawable).show();
    }

    /**
     *
     * @param context
     * @param message
     * @param duration
     * @param yourIconDrawable
     */
    public static void normal(Context context, CharSequence message, int duration, Drawable yourIconDrawable) {
        if (isShow)
            Toasty.normal(context, message, duration, yourIconDrawable).show();
    }
/**
 *————————————————————————————————————————————————————————————
 */

    /**
     *
     * @param context
     * @param message
     */
    public static void success(Context context, CharSequence message) {
        if (isShow)
            Toasty.success(context, message, Toast.LENGTH_SHORT).show();
    }

    /**
     *
     * @param context
     * @param message
     */
    public static void success(Context context, int message) {
        if (isShow)
            Toasty.success(context, String.valueOf(message), Toast.LENGTH_SHORT).show();
    }


    /**
     *
     * @param context
     * @param message
     * @param duration
     */
    public static void success(Context context, CharSequence message, int duration) {
        if (isShow)
            Toasty.success(context, message, duration).show();
    }

    /**
     *
     * @param context
     * @param message
     * @param duration
     * @param isIcon
     */
    public static void success(Context context, CharSequence message, int duration, boolean isIcon) {
        if (isShow)
            Toasty.success(context, message, duration, isIcon).show();
    }

/**
 *————————————————————————————————————————————————————————————
 */
    /**
     *
     * @param context
     * @param message
     */
    public static void info(Context context, CharSequence message) {
        if (isShow)
            Toasty.info(context, message, Toast.LENGTH_SHORT).show();
    }

    /**
     *
     * @param context
     * @param message
     */
    public static void info(Context context, int message) {
        if (isShow)
            Toasty.warning(context, String.valueOf(message), Toast.LENGTH_SHORT).show();
    }


    /**
     *
     * @param context
     * @param message
     * @param duration
     */
    public static void info(Context context, CharSequence message, int duration) {
        if (isShow)
            Toasty.warning(context, message, duration).show();
    }

    /**
     *
     * @param context
     * @param message
     * @param duration
     * @param isIcon
     */
    public static void info(Context context, CharSequence message, int duration, boolean isIcon) {
        if (isShow)
            Toasty.warning(context, message, duration, isIcon).show();
    }
    /**
     *————————————————————————————————————————————————————————————
     */

    /**
     *
     * @param context
     * @param message
     */
    public static void warning(Context context, CharSequence message) {
        if (isShow)
            Toasty.warning(context, message, Toast.LENGTH_SHORT).show();
    }

    /**
     *
     * @param context
     * @param message
     */
    public static void warning(Context context, int message) {
        if (isShow)
            Toasty.warning(context, String.valueOf(message), Toast.LENGTH_SHORT).show();
    }


    /**
     *
     * @param context
     * @param message
     * @param duration
     */
    public static void warning(Context context, CharSequence message, int duration) {
        if (isShow)
            Toasty.warning(context, message, duration).show();
    }

    /**
     *
     * @param context
     * @param message
     * @param duration
     * @param isIcon
     */
    public static void warning(Context context, CharSequence message, int duration, boolean isIcon) {
        if (isShow)
            Toasty.warning(context, message, duration, isIcon).show();
    }
/**
 *————————————————————————————————————————————————————————————
 */

    /**
     *
     * @param context
     * @param message
     */
    public static void error(Context context, CharSequence message) {
        if (isShow)
            Toasty.error(context, message, Toast.LENGTH_SHORT).show();
    }

    /**
     *
     * @param context
     * @param message
     */
    public static void error(Context context, int message) {
        if (isShow)
            Toasty.error(context, String.valueOf(message), Toast.LENGTH_SHORT).show();
    }


    /**
     *
     * @param context
     * @param message
     * @param duration
     */
    public static void error(Context context, CharSequence message, int duration) {
        if (isShow)
            Toasty.error(context, message, duration).show();
    }

    /**
     *
     * @param context
     * @param message
     * @param duration
     * @param isIcon
     */
    public static void error(Context context, CharSequence message, int duration, boolean isIcon) {
        if (isShow)
            Toasty.error(context, message, duration, isIcon).show();
    }

}
