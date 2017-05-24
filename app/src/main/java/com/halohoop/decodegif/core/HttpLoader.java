package com.halohoop.decodegif.core;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Pooholah on 2017/5/24.
 */

class HttpLoader {
    public static InputStream getInputStreamFrom(String url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) new URL(url).openConnection();
        return urlConnection.getInputStream();
    }
}
