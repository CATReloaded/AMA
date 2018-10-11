package com.catreloaded.ama.Objects;

public class AnsweredQuestion extends Question {

    public AnsweredQuestion(){

    }

    public AnsweredQuestion(String mQuistion, String mAnswer, String mAsker, String mDate, String mReplier, boolean mHasAnswer,int mId) {
        super(mQuistion, mAnswer, mAsker, mDate, mReplier, mHasAnswer, mId);
    }
}
