package com.efanzyhang.mi.core.app;

import com.efanzyhang.mi.core.util.storage.SPutil;

/**
 * 项目名：MIShop
 * 包名：com.efanzyhang.mi.core.app
 * 文件名：AccountManager
 * 创建者：efan.zyhang
 * 创建时间：2018/9/15 12:07
 * 描述： TODO
 */
public class AccountManager {

    //保存用户登录状态，登录后调用
    public static void setSignState(boolean state) {
        SPutil.setAppFlag(SignTag.SIGN_TAG.name(), state);
    }

    public static boolean isSignIn() {
        return SPutil.getAppFlag(SignTag.SIGN_TAG.name());
    }

    public static void checkAccount(IUserCheck check){
        if (isSignIn()){
            check.onSignIn();
        }else {
            check.onNotSignIn();
        }
    }

}
