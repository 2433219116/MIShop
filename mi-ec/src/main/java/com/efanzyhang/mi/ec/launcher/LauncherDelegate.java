package com.efanzyhang.mi.ec.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.efanzyhang.mi.core.app.AccountManager;
import com.efanzyhang.mi.core.app.IUserCheck;
import com.efanzyhang.mi.core.fragment.MishopFragment;
import com.efanzyhang.mi.core.ui.launcher.ILauncherListener;
import com.efanzyhang.mi.core.ui.launcher.LauncherFinishTag;
import com.efanzyhang.mi.core.ui.launcher.ScrollLauncherTag;
import com.efanzyhang.mi.core.util.storage.SPutil;
import com.efanzyhang.mi.core.util.timer.BaseTimerTask;
import com.efanzyhang.mi.core.util.timer.ITimerListener;
import com.efanzyhang.mi.ec.R;
import com.efanzyhang.mi.ec.R2;

import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 项目名：MIShop
 * 包名：com.efanzyhang.mi.ec.launcher
 * 文件名：LauncherDelegate
 * 创建者：efan.zyhang
 * 创建时间：2018/9/11 15:33
 * 描述： TODO
 */
public class LauncherDelegate extends MishopFragment implements ITimerListener {

    @BindView(R2.id.tv_launcher_timer)
    AppCompatTextView mTvTimer;

    private Timer mTimer;
    //计时数
    private int mCount = 5;

    private ILauncherListener mILauncherListener;

    @OnClick(R2.id.tv_launcher_timer)
    void onclickTimerView() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
            checkIsShowScroll();
        }
    }

    private void initTimer() {
        mTimer = new Timer();
        BaseTimerTask task = new BaseTimerTask(this);
        //指定任务延期执行
        mTimer.schedule(task, 0, 1000);
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
        return R.layout.fragment_launch;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initTimer();
    }

    //判断是否显示滑动启动页
    private void checkIsShowScroll() {
        if (!SPutil.getAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name())) {
            start(new LauncherScrollDelegate(), SINGLETASK);

        } else {
            //检查用户是否登录了
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
                        mILauncherListener.onLauncherFinish(LauncherFinishTag.NOT_SIGNED);
                    }
                }
            });
        }
    }

    /**
     * 利用Activity.runOnUiThread(Runnable)把更新ui的代码创建在Runnable中，
     * 然后在需要更新ui时，把这个Runnable对象传给Activity.runOnUiThread(Runnable).
     */
    @Override
    public void onTimer() {
        getProxyActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mTvTimer != null) {
                    mTvTimer.setText(MessageFormat.format("跳过\n{0}s", mCount));
                    mCount--;
                    if (mCount < 0) {
                        if (mTimer != null) {
                            mTimer.cancel();
                            mTimer = null;
                            checkIsShowScroll();
                        }
                    }
                }
            }
        });
    }
}
