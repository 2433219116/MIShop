package com.efanzyhang.mi.ec.database;

import android.content.Context;

import org.greenrobot.greendao.database.Database;

/**
 * 项目名：MIShop
 * 包名：com.efanzyhang.mi.ec.database
 * 文件名：DatabaseManager
 * 创建者：efan.zyhang
 * 创建时间：2018/9/14 21:26
 * 描述： TODO
 */
public class DatabaseManager {

    private DaoSession mDaoSession;
    private UserProfileDao mDao;

    private DatabaseManager() {
    }

    public DatabaseManager init(Context context) {
        initDao(context);
        return this;
    }

    private static final class Holder {
        private static final DatabaseManager INSTANCE = new DatabaseManager();
    }

    public static DatabaseManager getInstance() {
        return Holder.INSTANCE;
    }

    private void initDao(Context context) {
        final ReleaseOpenHelper helper = new ReleaseOpenHelper(context, "fast_ec.db");
        final Database database = helper.getWritableDb();
        mDaoSession = new DaoMaster(database).newSession();
        mDao = mDaoSession.getUserProfileDao();
    }

    public final UserProfileDao getDao(){
        return mDao;
    }
}
