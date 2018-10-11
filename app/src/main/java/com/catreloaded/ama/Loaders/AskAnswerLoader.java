package com.catreloaded.ama.Loaders;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.catreloaded.ama.Networking.RequestManager;
import com.catreloaded.ama.Utils.UrlBuilder;

import java.io.IOException;

public class AskAnswerLoader extends AsyncTaskLoader<String> {

    public static final String TYPE_ASK = "ask";
    public static final String TYPE_ANSWER = "answer";

    private String mType;
    private Context mContext;
    private String mReplier;
    private String mQuestion;

    private String mAnswer;
    private int mQuestionId;

    public AskAnswerLoader(Context context,String type,String replier,String question) {
        super(context);
        mType = type;
        mContext = context;
        mReplier = replier;
        mQuestion = question;
    }

    public AskAnswerLoader(Context context,String type,String answer,int questionId) {
        super(context);
        mType = type;
        mContext = context;
        mAnswer = answer;
        mQuestionId = questionId;
    }

    @Override
    public String loadInBackground() {
        switch (mType){
            case TYPE_ASK:
                try {
                    return RequestManager.requestASk(mContext,UrlBuilder.buildAskUrl(),mReplier,mQuestion);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case TYPE_ANSWER:
                try {
                    return RequestManager.requestAnswer(mContext,UrlBuilder.buildAnswerUrl(),mQuestionId,mAnswer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
        return null;
    }
}
