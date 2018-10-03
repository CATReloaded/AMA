package com.catreloaded.ama.Loaders;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;

import com.catreloaded.ama.Networking.RequestManager;

import java.io.IOException;

public class TestLoader extends AsyncTaskLoader<String> {


    public TestLoader(@NonNull Context context) {
        super(context);
    }


    @Nullable
    @Override
    public String loadInBackground() {
        try {
            return RequestManager.request("https://www.getpostman.com/collections/bef779071b6208fcadd9");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
