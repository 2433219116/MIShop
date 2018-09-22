package com.efanzyhang.mi.core.ui.loader;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.efanzyhang.mi.core.R;
import com.efanzyhang.mi.core.util.dimen.DimenUtil;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名：MIShop
 * 包名：com.efanzyhang.mi.core.ui.loader
 * 文件名：MishopLoader
 * 创建者：efan.zyhang
 * 创建时间：2018/8/12 20:07
 * 描述： LoadingDialog的配置，调整大小，位置，布局
 */
public class MishopLoader {
    //屏幕与dialog尺寸比例
    private static final int LOADING_SIZE_SCALE = 8;
    //无法理解的偏移量
    private static final int LOADING_OFFSET_SCALE = 10;
    //新建一个ArrayList统一管理dialog，当不需要使用时，遍历出来然后关闭
    //省下了很多同步的问题
    private static final List<AppCompatDialog> LOADERS = new ArrayList<>();
    //默认样式
    private static final String DEFAULT_LOADING = LoaderStyle.BallClipRotatePulseIndicator.name();

    /**
     * 重载，可以直接调用，传入枚举类型
     *
     * @param context   这几个方法中的上下文最好都传入当前使用对象的context，activity.getContext,fragment.getContext
     * @param styleEnum
     */
    public static void showLoading(Context context, Enum<LoaderStyle> styleEnum) {
        showLoading(context, styleEnum.name());
        System.out.print(styleEnum.name());
    }

    public static void showLoading(Context context, String type) {
        final AppCompatDialog dialog = new AppCompatDialog(context, R.style.dialog);

        final AVLoadingIndicatorView avLoadingIndicatorView = LoaderCreator.create(type, context);
        dialog.setContentView(avLoadingIndicatorView);

        int deviceWidth = DimenUtil.getScreenWidth();
        int deviceHeight = DimenUtil.getScreenHeight();

        final Window dialogWindow = dialog.getWindow();

        if (dialogWindow != null) {
            WindowManager.LayoutParams layoutParams = dialogWindow.getAttributes();
            layoutParams.width = deviceWidth / LOADING_SIZE_SCALE;
            layoutParams.height = deviceHeight / LOADING_SIZE_SCALE;
            layoutParams.height = layoutParams.height + deviceHeight / LOADING_OFFSET_SCALE;
            layoutParams.gravity = Gravity.CENTER;
        }
        LOADERS.add(dialog);
        dialog.show();
    }

    /**
     * 重载方法，启用默认方法
     *
     * @param context
     */
    public static void showLoading(Context context) {
        showLoading(context, DEFAULT_LOADING);
    }

    /**
     * 停止需要遍历出来所有的dialog，然后判断是否为空，是否展示，然后停止
     * 都是关闭，有什么不同
     * dialog.dismiss();单纯的消失掉
     * dialog.cancel();执行onCancel的一些回调
     */
    public static void stopLoading() {
        for (AppCompatDialog dialog : LOADERS) {
            if (dialog != null) {
                if (dialog.isShowing()) {
                    dialog.cancel();
                }
            }
        }
    }
}
