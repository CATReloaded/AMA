package com.catreloaded.ama.Objects;

public class User {

    private int mNFollowers;
    private int mNFollowing;
    private int mID;
    private String mAboutMe;
    private String mAvatarLink;
    private String mFollowersLink;
    private String mFollowingLink;
    private String mUserName;

    public User(int mNFollowers, int mNFollowing, int mId, String mAboutMe, String mAvatarLink, String mFollowersLink, String mFollowingLink, String mUserName) {
        this.mNFollowers = mNFollowers;
        this.mNFollowing = mNFollowing;
        this.mID = mId;
        this.mAboutMe = mAboutMe;
        this.mAvatarLink = mAvatarLink;
        this.mFollowersLink = mFollowersLink;
        this.mFollowingLink = mFollowingLink;
        this.mUserName = mUserName;
    }

    User() {

    }

    public int getNFollowers() {
        return mNFollowers;
    }

    public int getNFollowing() {
        return mNFollowing;
    }

    public int getID() {
        return mID;
    }

    public String getAboutMe() {
        return mAboutMe;
    }

    public String getAvatarLink() {
        return mAvatarLink;
    }

    public String getFollowersLink() {
        return mFollowersLink;
    }

    public String getFollowingLink() {
        return mFollowingLink;
    }

    public String getUserName() {
        return mUserName;
    }
}
