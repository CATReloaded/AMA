package com.catreloaded.ama.Networking;

import android.util.Log;

import java.io.IOException;

import okhttp3.Authenticator;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

/**
 * This class is used to request data from the internet
 */
public class RequestManager {

    private RequestManager(){
        //The default constructor is made private to prevent creating objects of this class
    }

    /**
     * This method is responsible for requesting json data using a given url
     * @param url the url which contains the json response
     * @throws IOException if the url was incorrect
     * @return a json response
     */
    public static String request(String url) throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient();
        //TODO replace these values with username and password cached in the shared preferences
        String credentials = Credentials.basic("","");
        Request request =
                new Request.Builder()
                        .header("Authorization",credentials)
                        .url(url).build();
        Response response = okHttpClient.newCall(request).execute();
        if(response.code() == 404){
            return "NULL";
        }else{
            return response.body().string();
        }
    }

}
