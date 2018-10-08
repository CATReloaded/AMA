package com.catreloaded.ama.Utils;

import android.support.annotation.NonNull;

import com.catreloaded.ama.Objects.AnsweredQuestion;
import com.catreloaded.ama.Objects.Follower;
import com.catreloaded.ama.Objects.Following;
import com.catreloaded.ama.Objects.Question;
import com.catreloaded.ama.Objects.UnAnsweredQuestion;
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

    private static final String QUESTIONS_KEY = "questions";
    private static final String ANSWERED_QUESTIONS_KEY = "answered_questions";
    private static final String UNANSWERED_QUESTIONS_KEY = "unanswered_questions";
    private static final String ANSWER_KEY = "answer";
    private static final String ASKER_KEY = "asker";
    private static final String DATE_KEY = "date";
    private static final String HAS_ANSWER_KEY = "has_answer";
    private static final String QUESTION_KEY = "question";
    private static final String REPLIER_KEY = "replier";


    private JSONParser(){

    }

    /**
     * This method is used to parse JSON string into a list of users.
     * @param jsonString json formatted string, usually a json response from internet connection
     * @param <T> the type of the data you want to fill the list with. It MUST be a class extending from {@link User}
     * @param type an object of the same type of T
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

    /**
     * This method is used to parse JSON string into a list of questions.
     * @param jsonString json formatted string, usually a json response from internet connection
     * @param <T> the type of the data you want to fill the list with. It MUST be a class extending from {@link Question}
     * @param type an object of the same type of T
     * @return array of T objects
     * @throws JSONException if the passing json response is incorrect
     */
    public static <T extends Question> List<T> parseQuestion(String jsonString,Question type) throws JSONException {
        JSONObject rootObject = new JSONObject(jsonString);
        JSONArray questionsArray;
        if(type instanceof AnsweredQuestion){
            questionsArray = rootObject.getJSONArray(ANSWERED_QUESTIONS_KEY);
        }else if(type instanceof UnAnsweredQuestion){
            questionsArray = rootObject.getJSONArray(UNANSWERED_QUESTIONS_KEY);
        }else {
            questionsArray = rootObject.getJSONArray(QUESTIONS_KEY);
        }
        List<T> result = new ArrayList<>();
        int n = questionsArray.length();
        T tempQuestion;
        JSONObject tempObject;
        String answer,asker,replier,question,date;
        boolean hasAnswer;
        for(int i = 0 ; i < n ; i++){
            tempObject = questionsArray.getJSONObject(i);
            answer = tempObject.getString(ANSWER_KEY);
            asker = tempObject.getString(ASKER_KEY);
            replier = tempObject.getString(REPLIER_KEY);
            question = tempObject.getString(QUESTION_KEY);
            date = tempObject.getString(DATE_KEY);
            hasAnswer = tempObject.getBoolean(HAS_ANSWER_KEY);
            tempQuestion = (T) new Question(question,answer,asker,date,replier,hasAnswer);
            result.add(tempQuestion);
        }
        return result;
    }

    /**
     * This method is used to parse the data of one user
     * @param jsonString data formatted as json
     * @return User Object
     * @throws JSONException if the passed json is incorrect
     */
    public static User parseOneUser(String jsonString) throws JSONException {
        JSONObject rootObject = new JSONObject(jsonString);

        int nFollowers,nFollowing,id;
        String aboutMe,avatarLink,followersLink,followingLink,username;

        nFollowers = rootObject.getInt(N_FOLLOWERS_KEY);
        nFollowing = rootObject.getInt(N_FOLLOWING_KEY);
        id = rootObject.getInt(ID_KEY);
        aboutMe = rootObject.getString(ABOUT_ME_KEY);
        avatarLink = rootObject.getString(AVATAR_URI_KEY);
        followersLink = rootObject.getString(FOLLOWERS_KEY);
        followingLink = rootObject.getString(FOLLOWING_KEY);
        username = rootObject.getString(USERNAME_KEY);

        return  new User(nFollowers,nFollowing,id,aboutMe,avatarLink,followersLink,followingLink,username);
    }
}
