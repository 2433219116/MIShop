package com.efanzyhang.mi.core.util.timer;

import java.util.TimerTask;

/**
 * 项目名：MIShop
 * 包名：com.efanzyhang.mi.core.util.timer
 * 文件名：BaseTimerTask
 * 创建者：efan.zyhang
 * 创建时间：2018/9/11 15:25
 * 描述： TODO
 */
public class BaseTimerTask extends TimerTask {
    private ITimerListener mTimerListener;

    public BaseTimerTask(ITimerListener timerListener) {
        this.mTimerListener = timerListener;
    }

    @Override
    public void run() {
        if (mTimerListener != null) {
            mTimerListener.onTimer();
        }
    }
}
