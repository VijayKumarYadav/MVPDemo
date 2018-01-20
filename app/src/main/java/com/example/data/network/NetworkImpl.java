/*
 * Copyright (C) VijayK
 */
package com.example.data.network;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;

import com.example.tracking.data.db.tables.Category;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Network implementation.
 */
public class NetworkImpl implements Network {

    private static NetworkImpl INSTANCE;
    private static final Object sLock = new Object();

    // network connection settings
    private static final int CONNECT_TIMEOUT_MS = 3000;
    private static final int READ_TIMEOUT_MS = 5000;
    private static final int CATEGORY_NUM = 6;
    private static final int TOTAL_NUM = 12;

    // urls
    private static final String BASE_URL = "https://api.flickr.com/services/feeds/photos_public.gne?format=json";
    private static final String CAT_API_URL = BASE_URL + "&tagmode=nature&tags=nature";
    private static final String RANDOM_API_URL = BASE_URL + "&tags=flower";

    private HttpURLConnection httpURLConnection;
    private DataInputStream dataInputStream;
    private ByteArrayOutputStream byteArrayOutputStream;

    public static NetworkImpl getInstance() {
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE = new NetworkImpl();
            }
            return INSTANCE;
        }
    }

    @Override
    public List<Category> getCategories() {
        String jsonResponse = getResponse(buildUrl(CAT_API_URL));
        return new Parser().parse(jsonResponse, CATEGORY_NUM);
    }

    @Override
    public List<Category> getImages() {
        String jsonResponse = getResponse(buildUrl(RANDOM_API_URL));
        return new Parser().parse(jsonResponse, TOTAL_NUM);
    }


    private String buildUrl(String url) {
        return Uri.parse(url).toString();
    }

    public boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    public String getResponse(String urlStr) {
        String responseStr = null;

        try {
            InputStream inputStream = null;
            URL urlToConnect = new URL(urlStr);
            httpURLConnection = (HttpURLConnection) urlToConnect.openConnection();
            httpURLConnection.setRequestProperty("user-agent", "Android");
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(CONNECT_TIMEOUT_MS);
            httpURLConnection.connect();
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                inputStream = httpURLConnection.getInputStream();
                dataInputStream = new DataInputStream(inputStream);
                int a = -1;
                byte b[] = new byte[1024];
                byteArrayOutputStream = new ByteArrayOutputStream();
                while ((a = dataInputStream.read(b)) != -1) {
                    byteArrayOutputStream.write(b, 0, a);
                }
                responseStr = new String(byteArrayOutputStream.toByteArray());
                byteArrayOutputStream.close();
                dataInputStream.close();
                httpURLConnection.disconnect();
            } else {
                httpURLConnection.disconnect();

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                byteArrayOutputStream = null;
            }
            if (dataInputStream != null) {
                try {
                    dataInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                dataInputStream = null;
            }
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
                httpURLConnection = null;
            }
        }
        return responseStr;
    }

    /**
     * Parser to parse response data.
     */
    public static class Parser {

        public List<Category> parse(String jsonResponse, int limit) {
            try {
                if (jsonResponse == null || jsonResponse.trim().length() == 0) {
                    return null;
                }
                JSONObject root = new JSONObject(jsonResponse.replace("jsonFlickrFeed(", "").replace(")", ""));
                JSONArray imageJSONArray = root.getJSONArray("items");
                List<Category> categoryList = new ArrayList<>();
                for (int i = 0; i < imageJSONArray.length(); i++) {
                    if (i == limit) break;
                    JSONObject item = imageJSONArray.getJSONObject(i);
                    categoryList.add(new Category(item.getString("title"),
                            item.getJSONObject("media").getString("m"), 1, item.getString("date_taken")));
                }
                return categoryList;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public static class ImageLoader {

        public Drawable loadImageFromUrl(String url) {
            InputStream inputStream = null;
            try {
                inputStream = new URL(url).openStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return Drawable.createFromStream(inputStream, "src");
        }
    }

}
