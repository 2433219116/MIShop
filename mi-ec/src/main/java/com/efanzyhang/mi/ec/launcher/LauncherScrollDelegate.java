package com.efanzyhang.mi.ec.launcher;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.efanzyhang.mi.core.app.AccountManager;
import com.efanzyhang.mi.core.app.IUserCheck;
import com.efanzyhang.mi.core.fragment.MishopFragment;
import com.efanzyhang.mi.core.ui.launcher.ILauncherListener;
import com.efanzyhang.mi.core.ui.launcher.LauncherFinishTag;
import com.efanzyhang.mi.core.ui.launcher.LauncherHolderCreate;
import com.efanzyhang.mi.core.ui.launcher.ScrollLauncherTag;
import com.efanzyhang.mi.core.util.storage.SPutil;
import com.efanzyhang.mi.core.util.toast.TUtil;
import com.efanzyhang.mi.ec.R;

import java.util.ArrayList;

/**
 * 项目名：MIShop
 * 包名：com.efanzyhang.mi.ec.launcher
 * 文件名：LauncherScrollDelegate
 * 创建者：efan.zyhang
 * 创建时间：2018/9/13 16:18
 * 描述： 运行时展示滑动界面
 */
public class LauncherScrollDelegate extends MishopFragment implements OnItemClickListener {

    private ConvenientBanner mConvenientBanner;
    private static final ArrayList<Integer> INTEGERS = new ArrayList<>();
    private ILauncherListener mILauncherListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ILauncherListener) {
            mILauncherListener = (ILauncherListener) activity;
        }
    }

    @Override
    public Object setLayout() {
        mConvenientBanner = new ConvenientBanner(getContext());
        return mConvenientBanner;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initBanner();
    }

    private void initBanner() {
        INTEGERS.add(R.mipmap.ic_launcher);
        INTEGERS.add(R.mipmap.ic_launcher);
        INTEGERS.add(R.mipmap.ic_launcher);
        INTEGERS.add(R.mipmap.ic_launcher);
        INTEGERS.add(R.mipmap.ic_launcher);
        mConvenientBanner
                .setPages(new LauncherHolderCreate(), INTEGERS)
                .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(this)
                .setCanLoop(false);
    }

    @Override
    public void onItemClick(int position) {
        //如果用户点击的是最后一个
        if (position == INTEGERS.size() - 1) {
            SPutil.setAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name(), true);
            //检查用户是否已经登录
            AccountManager.checkAccount(new IUserCheck() {
                @Override
                public void onSignIn() {
                    if (mILauncherListener != null) {
                        mILauncherListener.onLauncherFinish(LauncherFinishTag.SIGNED);
                    }
                }

                @Override
                public void onNotSignIn() {
                    if (mILauncherListener != null) {
                        TUtil.normal(getContext(), "666");
                        mILauncherListener.onLauncherFinish(LauncherFinishTag.NOT_SIGNED);
                    }
                }
            });
        }
    }
}
