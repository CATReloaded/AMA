package com.catreloaded.ama.Networking;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * This class is used to request data from the internet
 */
public class RequestManager {

    private RequestManager(){

    }

    /**
     * This method is responsible for requesting json data using a given url
     * @param url the url which contains the json response
     * @throws IOException if the url was incorrect
     * @return a json response
     */
    public static String request(String url) throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Response response = okHttpClient.newCall(request).execute();
        return response.body().string();
    }

}
