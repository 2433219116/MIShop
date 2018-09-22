package com.efanzyhang.mi.ec.sign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.efanzyhang.mi.core.app.AccountManager;
import com.efanzyhang.mi.ec.database.DatabaseManager;
import com.efanzyhang.mi.ec.database.UserProfile;

/**
 * 项目名：MIShop
 * 包名：com.efanzyhang.mi.ec.sign
 * 文件名：SignHandler
 * 创建者：efan.zyhang
 * 创建时间：2018/9/15 10:25
 * 描述： 注册SQLite数据库帮助类
 */
public class SignHandler {
    public static void onSignIn(String response, ISignListener signListener) {
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");

        final UserProfile userProfile = new UserProfile(userId, name, avatar, gender, address);
        DatabaseManager.getInstance().getDao().insert(userProfile);

        //已经注册并且记录状态
        AccountManager.setSignState(true);

        signListener.onSignInSuccess();

    }

    public static void onSignUp(String response, ISignListener signListener) {
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");

        final UserProfile userProfile = new UserProfile(userId, name, avatar, gender, address);
        DatabaseManager.getInstance().getDao().insert(userProfile);

        //已经注册并且记录状态
        AccountManager.setSignState(true);

        signListener.onSignUpSuccess();

    }
}
