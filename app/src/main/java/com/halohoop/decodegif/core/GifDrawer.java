package com.halohoop.decodegif.core;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;

/**
 * Created by Pooholah on 2017/5/24.
 */

public class GifDrawer {
    private static final String TAG = "GifDrawer";
    private ImageView targetIv;
    private InputStream is;
    private Movie mMovie;
    private Canvas mCanvas;
    private Handler mHandler = new Handler();
    private Runnable mDrawTask = new Runnable() {
        @Override
        public void run() {
            draw();
            mHandler.postDelayed(this, 16);
        }

        private void draw() {
            mCanvas.save();
            mMovie.draw(mCanvas, 0, 0);
            mCanvas.restore();
            mMovie.setTime((int) (System.currentTimeMillis() % mMovie.duration()));
            targetIv.setImageBitmap(mBitmap);
        }
    };
    private Bitmap mBitmap;


    public void into(ImageView iv) {
        this.targetIv = iv;
        if (is == null) {
            return;
        } else if (iv == null) {
            throw new RuntimeException("R u sure!?iv == null");
        }
        //show
        mMovie = Movie.decodeStream(is);
        if (mMovie == null) {
            throw new RuntimeException("R u sure!?mMovie == null");
        }
        if (mMovie.width() <= 0 || mMovie.height() <= 0) {
            Log.i(TAG, "into: show shit!");
            return;
        }
        //draw
        mBitmap = Bitmap.createBitmap(mMovie.width(), mMovie.height(), Bitmap.Config.ARGB_8888);
        //need a field casue need a recyle draw
        mCanvas = new Canvas(mBitmap);
        mHandler.post(mDrawTask);

    }


    public void setIs(InputStream is) {
        this.is = is;
    }
}
