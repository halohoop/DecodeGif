package com.halohoop.decodegif;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.halohoop.decodegif.core.GifDecoder;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private final String url = "http://g.hiphotos.baidu.com/image/h%3D200/sign=bf7ead2a2e7f9e2f6f351a082f32e962/d8f9d72a6059252dcadf02c83d9b033b5ab5b935.jpg";
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv = (ImageView) findViewById(R.id.iv);
        iv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        try {
            GifDecoder.with(this)
                    .load(this.getAssets().open("timg.gif"))
                    .into(iv);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
