package com.catreloaded.ama.Networking;

import android.content.Context;
import android.util.Log;

import com.catreloaded.ama.Utils.PreferencesConstants;

import java.io.IOException;

import okhttp3.Authenticator;
import okhttp3.Credentials;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
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
    public static String requestData(String url, Context context) throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient();
        String credentials = Credentials.basic(PreferencesConstants.getUsername(context),PreferencesConstants.getPassword(context));
        Request request =
                new Request.Builder()
                        .header("Authorization",credentials)
                        .url(url)
                        .build();
        Response response = okHttpClient.newCall(request).execute();
        if(response.code() == 404){
            return "NULL";
        }else{
            return response.body().string();
        }
    }

    /**
     * This method is responsible for posting data to the given api url
     * @param url the url which you want to send data
     * @throws IOException if the url was incorrect
     * @return a json response about the state of the sent data
     */
    public static String requestSignIn(String url, String username, String password) throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient();
        MediaType jsonType = MediaType.parse("application/json; charset=utf-8");
        String body = "{\"username\":\""+username+"\", \"password\":\""+password+"\"}";
        RequestBody requestBody = RequestBody.create(jsonType,body);

        String credentials = Credentials.basic(username,password);
        Request request =
                new Request.Builder()
                        .header("Authorization",credentials)
                        .url(url)
                        .post(requestBody)
                        .build();
        Response response = okHttpClient.newCall(request).execute();
        if(response.code() == 404){
            return "NULL";
        }else{
            return response.body().string();
        }
    }

}
