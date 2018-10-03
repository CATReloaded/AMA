package com.catreloaded.ama.Utils;

import android.net.Uri;

/**
 * This class is used to facilitate the process of building urls
 */
public class UrlBuilder {

    private UrlBuilder(){
        //URL example http://ama.localdomain:5000/api/users/deannafrank/following?p=1
    }

    private static Uri.Builder mUriBuilder;

    private static final String AUTHORITY = "ama.localdomain:5000/api";
    private static final String SCHEME = "http";
    private static final String USERS_PATH = "users";
    private static final String FOLLOWING_PATH = "following";
    private static final String FOLLOWERS_PATH = "followers";

    private static final String P_QUERY = "p";
    private static final String N_QUERY = "n";

    /**
     * A getter for the Uri.Builder singleton
     * @return mUriBuilder
     */
    private static Uri.Builder getBuilderInstance(){
        if (mUriBuilder == null){
            mUriBuilder = new Uri.Builder();
        }
        mUriBuilder.scheme(SCHEME);
        mUriBuilder.authority(AUTHORITY);
        return mUriBuilder;
    }

    public static String buildUsersUrl(int numberOfUsers){
        return getBuilderInstance().appendPath(USERS_PATH)
                .appendQueryParameter(N_QUERY,numberOfUsers+"").build().toString();
    }
}
