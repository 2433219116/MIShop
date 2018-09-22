package com.efanzyhang.mi.core.net.download;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;

import com.efanzyhang.mi.core.app.MIShop;
import com.efanzyhang.mi.core.net.callback.IRequest;
import com.efanzyhang.mi.core.net.callback.ISuccess;
import com.efanzyhang.mi.core.util.file.FileUtil;

import java.io.File;
import java.io.InputStream;

import okhttp3.ResponseBody;
import retrofit2.http.Url;

/**
 * 项目名：MIShop
 * 包名：com.efanzyhang.mi.core.net.download
 * 文件名：SaveFileTask
 * 创建者：efan.zyhang
 * 创建时间：2018/8/14 12:33
 * 描述： TODO
 */
public class SaveFileTask extends AsyncTask<Object, Void, File> {
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;

    public SaveFileTask(IRequest REQUEST, ISuccess SUCCESS) {
        this.REQUEST = REQUEST;
        this.SUCCESS = SUCCESS;
    }

    @Override
    protected File doInBackground(Object... objects) {
        String downloadDir = (String) objects[0];
        String extension = (String) objects[1];
        final ResponseBody body = (ResponseBody) objects[2];
        final String name = (String) objects[3];
        final InputStream inputStream = body.byteStream();

        if (downloadDir == null || extension.equals("")) {
            downloadDir = "down_loads";
        }

        if (extension == null || extension.equals("")) {
            extension = "";
        }

        if (name == null) {
            return FileUtil.writeToDisk(inputStream, downloadDir, extension.toUpperCase(), extension);
        } else {
            return FileUtil.writeToDisk(inputStream, downloadDir, name);
        }

    }

    /**
     * 回到主线程的操作
     *
     * @param file
     */
    @Override
    protected void onCancelled(File file) {
        super.onCancelled(file);
        if (SUCCESS != null) {
            SUCCESS.onSuccess(file.getPath());
        }

        if (REQUEST != null) {
            REQUEST.onRequestEnd();
        }
    }

    private void autoInstallApk(File file) {
        if (FileUtil.getExtension(file.getPath()).equals("apk")) {
            final Intent install = new Intent();
            install.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            install.setAction(Intent.ACTION_VIEW);
            install.setDataAndType(Uri.fromFile(file),"application/vnd.android.package-archive");
            MIShop.getApplicationContext().startActivity(install);
        }
    }
}
