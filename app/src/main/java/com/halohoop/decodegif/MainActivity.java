package com.halohoop.decodegif;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.halohoop.decodegif.core.GifDecoder;

public class MainActivity extends AppCompatActivity {
    private final String url1 = "http://g.hiphotos.baidu.com/image/h%3D200/sign=bf7ead2a2e7f9e2f6f351a082f32e962/d8f9d72a6059252dcadf02c83d9b033b5ab5b935.jpg";
    private final String url2 = "http://ww1.sinaimg.cn/large/85cccab3gw1etebsnjuorg208w0504qp.jpg";
    private final String[] urls = {
            "http://g.hiphotos.baidu.com/image/h%3D200/sign=bf7ead2a2e7f9e2f6f351a082f32e962/d8f9d72a6059252dcadf02c83d9b033b5ab5b935.jpg",
            "http://ww2.sinaimg.cn/large/85cccab3gw1etdj744p5dg208c060jyw.jpg",
            "http://img5.duitang.com/uploads/blog/201311/21/20131121165831_tHUhG.thumb.600_0.gif",
            "http://ww2.sinaimg.cn/large/85cccab3tw1esf9wixrxzg207704pwph.jpg",
            "http://www.asqql.com/upfile/2009pasdfasdfic2009s305985-ts/gif_spic/2013-1/20131192212676822_asqql.com.gif",
            "http://ibaoan.sznews.com/images/attachement/gif/site3/20160620/IMG8c89a5be1e4341641532717.gif",
            "http://scimg.jb51.net/allimg/160720/2-160H0221131253.gif",
            "http://i2.hoopchina.com.cn/u/1308/21/357/4156357/c34f4289_530x.gif",
            "http://scimg.jb51.net/gaoxiaotupian/6.gif"
    };
    private ImageView iv1;
    private ImageView iv2;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv1 = (ImageView) findViewById(R.id.iv1);
        iv2 = (ImageView) findViewById(R.id.iv2);
        lv = (ListView) findViewById(R.id.lv);
    }

    public void click(View v) {
        if (lv.getAdapter() == null) {
            GifDecoder.with(this)
                    .load(url1)
                    .into(iv1);
            GifDecoder.with(this)
                    .load(url2)
                    .into(iv2);
            lv.setAdapter(new MyAdapter());
        }
    }

    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return urls.length;
        }

        @Override
        public Object getItem(int position) {
            return urls[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            if (convertView != null) {
                viewHolder = (ViewHolder) convertView.getTag();
            } else {
                convertView = View.inflate(MainActivity.this, R.layout.imageview, null);
                viewHolder = new ViewHolder();
                viewHolder.iv = (ImageView) convertView.findViewById(R.id.iv);
                convertView.setTag(viewHolder);
            }
            GifDecoder.with(MainActivity.this).load(urls[position]).into(viewHolder.iv);
            return convertView;
        }

        class ViewHolder {
            ImageView iv;
        }
    }
}
