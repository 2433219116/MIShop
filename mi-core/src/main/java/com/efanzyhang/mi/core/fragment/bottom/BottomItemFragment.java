package com.efanzyhang.mi.core.fragment.bottom;

import android.view.KeyEvent;
import android.view.View;

import com.efanzyhang.mi.core.R;
import com.efanzyhang.mi.core.fragment.MishopFragment;
import com.efanzyhang.mi.core.util.toast.TUtil;

/**
 * 项目名：MIShop
 * 包名：com.efanzyhang.mi.core.fragment.bottom
 * 文件名：BottomItemFragment
 * 创建者：efan.zyhang
 * 创建时间：2018/9/17 13:56
 * 描述： 底部item对应的Fragment，监听并处理退出事件
 */
public abstract class BottomItemFragment extends MishopFragment implements View.OnKeyListener {

    private long mExitTime = 0;

    private static final int EXIT_TIME = 2000;

    /**
     * 每次回到Fragment需要把Touch的一个focus重新request
     * 否则会出现回来之后Fragment双击无效
     * 可以查看fragment源码
     */
    @Override
    public void onResume() {
        super.onResume();
        View rootView = getView();
        if (rootView != null) {
            rootView.setFocusableInTouchMode(true);
            rootView.requestFocus();
            rootView.setOnKeyListener(this);
        }
    }

    @Override
    public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
        //判断动作为返回键，且动作为按下
        if (keyCode == KeyEvent.KEYCODE_BACK && keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - mExitTime) > EXIT_TIME) {
                StringBuilder builder = new StringBuilder().append("双击退出").append(R.string.app_name).append("应用");
                TUtil.info(getContext(), builder.toString());
                mExitTime = System.currentTimeMillis();
            } else {
                getProxyActivity().finish();
                //手动回收内存
                if (mExitTime != 0) {
                    mExitTime = 0;
                }
            }
        }
        //默认返回false
        //return true  表示当前点击事件被onKey消耗掉不能再被传到下一个监听事件
        //return false 代表不对事件进行拦截，事件可以传递给孩子
        return true;
    }
}
