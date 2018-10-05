package com.catreloaded.ama.Utils;

import android.support.annotation.NonNull;

import com.catreloaded.ama.Objects.Follower;
import com.catreloaded.ama.Objects.Following;
import com.catreloaded.ama.Objects.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JSONParser {

    private static final String USERS_KEY = "uses";

    private static final String N_FOLLOWERS_KEY = "#followers";
    private static final String N_FOLLOWING_KEY = "#following";
    private static final String ABOUT_ME_KEY = "about_me";
    private static final String AVATAR_URI_KEY = "avatar_uri";
    private static final String FOLLOWERS_KEY = "followers";
    private static final String FOLLOWING_KEY = "following";
    private static final String ID_KEY = "id";
    private static final String USERNAME_KEY = "username";

    private JSONParser(){

    }

    /**
     * This method is used to parse JSON string into a list of users.
     * @param jsonString json formatted string, usually a json response from internet connection
     * @param <T> the type of the data you want to fill the list with. It MUST be a class extending from {@link User}
     * @return array of T objects
     * @throws JSONException if the passing json response is incorrect
     */
    public static <T extends User> List<T>  parseUsers(String jsonString,@NonNull User type) throws JSONException {
        JSONObject rootObject = new JSONObject(jsonString);
        JSONArray usersArray;

        if(type instanceof Follower){
            usersArray = rootObject.getJSONArray(FOLLOWERS_KEY);
        }else if(type instanceof Following){
            usersArray = rootObject.getJSONArray(FOLLOWING_KEY);
        }else {
            usersArray = rootObject.getJSONArray(USERS_KEY);
        }

        JSONObject tempObject;

        int n = usersArray.length();
        T tempT;
        List<T> result = new ArrayList<>();
        int nFollowers,nFollowing,id;
        String aboutMe,avatarLink,followersLink,followingLink,username;
        for(int i = 0 ; i < n ; i++){
            tempObject = usersArray.getJSONObject(i);
            nFollowers = tempObject.getInt(N_FOLLOWERS_KEY);
            nFollowing = tempObject.getInt(N_FOLLOWING_KEY);
            id = tempObject.getInt(ID_KEY);
            aboutMe = tempObject.getString(ABOUT_ME_KEY);
            avatarLink = tempObject.getString(AVATAR_URI_KEY);
            followersLink = tempObject.getString(FOLLOWERS_KEY);
            followingLink = tempObject.getString(FOLLOWING_KEY);
            username = tempObject.getString(USERNAME_KEY);

            tempT = (T) new User(nFollowers,nFollowing,id,aboutMe,avatarLink,followersLink,followingLink,username);
            result.add(tempT);
        }
        return result;
    }
}
