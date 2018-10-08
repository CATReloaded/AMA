package com.catreloaded.ama.Loaders;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;

import com.catreloaded.ama.Networking.RequestManager;

import java.io.IOException;

public class NetworkJsonResponseLoader extends AsyncTaskLoader<String> {

    private String mUrl;

    public NetworkJsonResponseLoader(@NonNull Context context, String url) {
        super(context);
        mUrl = url;
    }


    @Nullable
    @Override
    public String loadInBackground() {
        try {
            return RequestManager.request(mUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
