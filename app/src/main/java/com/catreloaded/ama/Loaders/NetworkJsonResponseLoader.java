package com.catreloaded.ama.Loaders;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.catreloaded.ama.Networking.RequestManager;

import java.io.IOException;

public class NetworkJsonResponseLoader extends AsyncTaskLoader<String> {

    private String mUrl;
    private String mMethod;
    private String mUsername;
    private String mPassword;

    public static final String GET = "GET";
    public static final String POST = "POST";


    public NetworkJsonResponseLoader(@NonNull Context context, String url,String method) {
        super(context);
        mUrl = url;
        mMethod = method;
    }

    public NetworkJsonResponseLoader(@NonNull Context context,String url,String method,String username,String password){
        super(context);
        mUrl = url;
        mMethod = method;
        mUsername = username;
        mPassword = password;
    }


    @Nullable
    @Override
    public String loadInBackground() {
        try {
            switch (mMethod){
                case GET:
                    return RequestManager.requestData(mUrl,getContext());
                case POST:
                    return RequestManager.requestSignIn(mUrl,mUsername,mPassword);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
