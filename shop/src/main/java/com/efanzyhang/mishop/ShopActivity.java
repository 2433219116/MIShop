package com.efanzyhang.mishop;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.widget.Toast;

import com.efanzyhang.mi.core.activity.ProxyActivity;
import com.efanzyhang.mi.core.fragment.MishopFragment;
import com.efanzyhang.mi.core.ui.launcher.ILauncherListener;
import com.efanzyhang.mi.core.ui.launcher.LauncherFinishTag;
import com.efanzyhang.mi.core.util.toast.TUtil;
import com.efanzyhang.mi.ec.main.EcBottomFragment;
import com.efanzyhang.mi.ec.sign.ISignListener;
import com.efanzyhang.mi.ec.sign.SignInDelegate;

/**
 * 项目名：MIShop
 * 包名：com.efanzyhang.mishop
 * 文件名：ShopActivity
 * 创建者：efan.zyhang
 * 创建时间：2018/8/8 16:36
 * 描述： 主界面，根Activity，继承ProxyActivity的就是多Fragment依赖的Activity
 */

public class ShopActivity extends ProxyActivity implements
        ISignListener,
        ILauncherListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    @Override
    public MishopFragment setRootDelegate() {
        return new EcBottomFragment();
//                LauncherDelegate();

    }

    @Override
    public void onSignInSuccess() {

    }

    @Override
    public void onSignUpSuccess() {
//        TUtil.success(getBaseContext(), "!!!", Toast.LENGTH_SHORT);
    }

    @Override
    public void onLauncherFinish(LauncherFinishTag tag) {
        switch (tag) {
            case SIGNED:
                TUtil.normal(getBaseContext(),"用户登录了",Toast.LENGTH_LONG);
                startWithPop(new EcBottomFragment());
                break;
            case NOT_SIGNED:
                TUtil.normal(getBaseContext(),"用户未登录",Toast.LENGTH_LONG);
                startWithPop(new SignInDelegate());
                break;
            default:
                break;
        }
    }
}
