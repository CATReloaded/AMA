package com.catreloaded.ama.Objects;

public class Question {

    private String mQuestion;
    private String mAnswer;
    private String mAsker;
    private String mDate;
    private String mReplier;
    private boolean mHasAnswer;
    private int mId;

    Question(){

    }

    public Question(String mQuestion, String mAnswer, String mAsker, String mDate, String mReplier, boolean mHasAnswer,int mId) {
        this.mQuestion = mQuestion;
        this.mAnswer = mAnswer;
        this.mAsker = mAsker;
        this.mDate = mDate;
        this.mReplier = mReplier;
        this.mHasAnswer = mHasAnswer;
        this.mId = mId;
    }

    public String getQuestion() {
        return mQuestion;
    }

    public String getAnswer() {
        return mAnswer;
    }

    public String getAsker() {
        return mAsker;
    }

    public String getmDate() {
        return mDate;
    }

    public String getReplier() {
        return mReplier;
    }

    public boolean isAnswered() {
        return mHasAnswer;
    }

    public int getmId() {
        return mId;
    }

}
