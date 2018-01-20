/*
 * Copyright (C) VijayK
 */

package com.example.data;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.example.data.network.Network;
import com.example.data.network.NetworkImpl;
import com.example.tracking.data.Tracking;
import com.example.tracking.data.TrackingImpl;
import com.example.tracking.data.db.tables.Category;
import com.example.utils.AppExecutors;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class DataManagerImpl implements DataManager {

    private static DataManagerImpl sDataManagerImpl;
    private Tracking mTracking;
    private Network mNetwork;
    private AppExecutors mAppExecutors;
    private ConcurrentHashMap<String, SoftReference<Drawable>> drawableMap;

    public static final String SUCCESS = "Success";

    private DataManagerImpl(Tracking tracking, Network network) {
        mAppExecutors = new AppExecutors();
        mTracking = tracking;
        mNetwork = network;
        drawableMap = new ConcurrentHashMap<String, SoftReference<Drawable>>();
    }

    public static DataManagerImpl getInstance(Tracking tracking, Network network) {
        synchronized (DataManagerImpl.class) {
            if (sDataManagerImpl == null) {
                synchronized (TrackingImpl.class) {
                    sDataManagerImpl = new DataManagerImpl(tracking, network);
                }
            }
            return sDataManagerImpl;
        }
    }

    /**
     * Gets category from cache, local data source (SQLite) or remote data source, whichever is
     * available first.
     */
    @Override
    public void getCategories(final CallBack<List<Category>> callback) {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final List<Category> categories = mTracking.getCategories();
                if (categories.size() == 0) {
                    List<Category> temp = mNetwork.getCategories();
                    if (temp != null) {
                        categories.addAll(temp);
                    }
                    if (categories.size() != 0) {
                        mTracking.deleteAll();
                        mTracking.insertCategories(categories);
                    }
                }
                mAppExecutors.mainThread().execute(() -> callback.onComplete(categories, SUCCESS));
            }
        };
        mAppExecutors.diskIO().execute(runnable);
    }

    @Override
    public void getImages(final CallBack<List<Category>> callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final List<Category> images = mNetwork.getImages();
                mAppExecutors.mainThread().execute(() -> callback.onComplete(images, SUCCESS));
            }
        };
        mAppExecutors.diskIO().execute(runnable);
    }

    @Override
    public void trackClick(final String url) {
        Runnable runnable = () -> mTracking.trackClick(url);
        mAppExecutors.diskIO().execute(runnable);
    }

    @Override
    public void isFirstTimeUser(final CallBack<Boolean> callBack) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final Boolean isFirstTime = mTracking.isFirstTimeUser();
                mAppExecutors.mainThread().execute(() -> callBack.onComplete(isFirstTime, SUCCESS));
            }
        };
        mAppExecutors.diskIO().execute(runnable);
    }

    @Override
    public void getMostViewedUrl(final CallBack<String> callBack) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final String url = mTracking.getMostViewUrl();
                mAppExecutors.mainThread().execute(() -> callBack.onComplete(url, SUCCESS));
            }
        };
        mAppExecutors.diskIO().execute(runnable);
    }

    @Override
    public void loadImages(String url, WeakReference<ImageView> imageView) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Drawable drawable = null;
                ImageView strong = imageView.get();
                if (strong == null) {
                    return;  // if image view is destroyed, return
                }
                if (drawableMap.containsKey(url)) {  // is cached
                    SoftReference<Drawable> softReference = drawableMap.get(url);
                    drawable = softReference.get();     // is it possible to get it?
                }
                if (drawable == null) {
                    drawable = new NetworkImpl.ImageLoader().loadImageFromUrl(url);
                    drawableMap.put(url, new SoftReference<Drawable>(drawable));
                }
                strong = imageView.get();
                if (drawable != null && strong != null) {
                    ImageView finalStrong = strong;
                    Drawable finalDrawable = drawable;
                    mAppExecutors.mainThread().execute(() -> {
                        finalStrong.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        finalStrong.setImageDrawable(finalDrawable);
                    });
                }
            }
        };
        mAppExecutors.networkIO().execute(runnable);
    }

    public static void dispose() {
        sDataManagerImpl = null;
    }
}
