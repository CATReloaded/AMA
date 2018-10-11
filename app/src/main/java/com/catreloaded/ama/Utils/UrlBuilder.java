package com.catreloaded.ama.Utils;

import android.net.Uri;

/**
 * This class is used to facilitate the process of building urls
 */
public class UrlBuilder {

    private static final String SIGN_IN_URL = "http://ama-catreloaded.herokuapp.com/api/signin";
    private static final String SIGN_UP_URL = "http://ama-catreloaded.herokuapp.com/auth/signup";
    private static final String ASK_URL = "http://ama-catreloaded.herokuapp.com/api/ask";
    private static final String ANSWER_URL = "https://ama-catreloaded.herokuapp.com/api/answer";

    private UrlBuilder(){

    }

    private static Uri.Builder mUriBuilder;

    private static final String AUTHORITY = "ama-catreloaded.herokuapp.com";
    private static final String SCHEME = "http";
    private static final String USERS_PATH = "users";
    private static final String FOLLOWING_PATH = "following";
    private static final String FOLLOWERS_PATH = "followers";
    private static final String API_PATH = "api";
    private static final String ANSWERED_QUESTIONS_PATH = "answered-questions";
    private static final String UNANSWERED_QUESTIONS_PATH = "unanswered-questions";

    private static final String P_QUERY = "p";
    private static final String N_QUERY = "n";
    private static final int N = 1000;



    private static Uri.Builder getUrlBuilder(){
        mUriBuilder = new Uri.Builder();
        mUriBuilder.scheme(SCHEME);
        mUriBuilder.authority(AUTHORITY);
        mUriBuilder.appendPath(API_PATH);
        return mUriBuilder;
    }

    /**
     * A method that builds a url for all the users on the website
     * @return string url for the users of the website
     */
    public static String buildUsersUrl(){
        return getUrlBuilder()
                .appendPath(USERS_PATH)
                .appendQueryParameter(N_QUERY,N +"").build().toString();
    }

    /**
     * A method that builds a url for a specific user
     * @param username the target user
     * @return string url for the that specific user
     */
    public static String buildSpecificUserUrl(String username){
        return getUrlBuilder()
                .appendPath(USERS_PATH)
                .appendPath(username).build().toString();
    }

    /**
     * A method that builds a url for the followers of the given user
     * @param username the target user
     * @return string url for the followers of that user
     */
    public static String buildFollowersUrl(String username){
        return buildSpecificUserUrl(username) + "/" + FOLLOWERS_PATH + "?n=" + N;
    }

    /**
     * A method that builds a url for the followings of the given user
     * @param username the target user
     * @return string url for the followings of that user
     */
    public static String buildFollowingsUrl(String username){
        return buildSpecificUserUrl(username) + "/" + FOLLOWING_PATH + "?n=" + N;
    }

    /**
     * A method that builds a url for the answered questions of the given user
     * @param username the target user
     * @return string url for the answered questions of that user
     */
    public static String buildAnsweredQuestionsUrl(String username){
        return buildSpecificUserUrl(username) + "/" + ANSWERED_QUESTIONS_PATH + "?n=" + N;
    }

    /**
     * A method that builds a url for the unanswered questions of the given user
     * @param username the target user
     * @return string url for the unanswered questions of that user
     */
    public static String buildUnansweredQuestionsUrl(String username){
        return buildSpecificUserUrl(username) + "/" + UNANSWERED_QUESTIONS_PATH + "?n=" + N;
    }

    public static String buildSignInUrl(){
        return SIGN_IN_URL;
    }

    public static String buildSignUpUrl(){
        return SIGN_UP_URL;
    }

    public static String buildAskUrl(){
        return ASK_URL;
    }

    public static String buildAnswerUrl(){
        return ANSWER_URL;
    }
}
