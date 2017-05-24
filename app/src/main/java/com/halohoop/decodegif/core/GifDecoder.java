package com.halohoop.decodegif.core;

import android.content.Context;

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
}
