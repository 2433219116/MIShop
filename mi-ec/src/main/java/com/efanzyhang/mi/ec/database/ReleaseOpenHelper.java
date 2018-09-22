package com.efanzyhang.mi.ec.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import org.greenrobot.greendao.database.Database;

/**
 * 项目名：MIShop
 * 包名：com.efanzyhang.mi.ec.database
 * 文件名：ReleaseOpenHelper
 * 创建者：efan.zyhang
 * 创建时间：2018/9/14 21:25
 * 描述： TODO
 */
public class ReleaseOpenHelper extends DaoMaster.OpenHelper {
    public ReleaseOpenHelper(Context context, String name) {
        super(context, name);
    }

    public ReleaseOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onCreate(Database db) {
        super.onCreate(db);
    }
}
