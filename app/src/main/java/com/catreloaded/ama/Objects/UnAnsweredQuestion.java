package com.catreloaded.ama.Objects;

public class UnAnsweredQuestion extends Question {

    public UnAnsweredQuestion(){

    }

    public UnAnsweredQuestion(String mQuestion, String mAnswer, String mAsker, String mDate, String mReplier, boolean mHasAnswer) {
        super(mQuestion, mAnswer, mAsker, mDate, mReplier, mHasAnswer);
    }
}
