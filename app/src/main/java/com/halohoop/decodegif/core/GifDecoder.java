package com.halohoop.decodegif.core;

import android.content.Context;
import android.os.AsyncTask;

import com.halohoop.decodegif.core.utils.MD5Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Pooholah on 2017/5/24.
 */

public class GifDecoder {
    private final static GifDecoder instance = new GifDecoder();
    private static Context mContext;

    private GifDecoder() {
    }

    public static GifDecoder with(Context c) {
        mContext = c;
        return instance;
    }

    public GifDrawer load(InputStream inputStream) {
        GifDrawer drawer = new GifDrawer();
        drawer.setIs(inputStream);
        return drawer;
    }

    public GifDrawer load(String url) {
        String fileName = MD5Utils.md5(url);
        String path = mContext.getExternalCacheDir() + File.separator + "GifDecoder" + File.separator;
//        String path = Environment.getExternalStorageDirectory() + File.separator + "GifDecoder" + File.separator;
        File file = new File(path + fileName);
        if (file.exists()) {
            FileInputStream fileInputStream = null;
            try {
                fileInputStream = new FileInputStream(file);
                return load(fileInputStream);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return null;
            }
            /* cannot close
            finally {
                if (fileInputStream!=null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }*/
        } else {
            GifDrawer drawer = new GifDrawer();
            new LoadTask(drawer).execute(url);
            return drawer;
        }
    }

    static class LoadTask extends AsyncTask<String, Void, String> {

        private GifDrawer mDrawer;

        public LoadTask(GifDrawer drawer) {
            this.mDrawer = drawer;
        }

        @Override
        protected String doInBackground(String... params) {
            String url = params[0];
            String fileName = MD5Utils.md5(url);
            String dirPath = mContext.getExternalCacheDir() + File.separator + "GifDecoder";
//            String dirPath = Environment.getExternalStorageDirectory() + File.separator + "GifDecoder";

            File file = new File(dirPath);
            if(!file.exists()) file.mkdirs();
            file = new File(file, fileName);

            InputStream is = null;
            FileOutputStream fos = null;
            //保存文件
            try {
                is = HttpLoader.getInputStreamFrom(url);
                fos = new FileOutputStream(file);
                byte[] buf = new byte[1024];
                int len = -1;
                while ((len = is.read(buf)) != -1) {
                    fos.write(buf, 0, len);
                }
                mDrawer.setIs(is);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return fileName;
        }

        @Override
        protected void onPostExecute(String fileName) {
            super.onPostExecute(fileName);
            mDrawer.into(mDrawer.getTargetIv());
        }
    }
}
