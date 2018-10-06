package com.catreloaded.ama.Objects;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable{

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

    protected User(Parcel in) {
        mNFollowers = in.readInt();
        mNFollowing = in.readInt();
        mID = in.readInt();
        mAboutMe = in.readString();
        mAvatarLink = in.readString();
        mFollowersLink = in.readString();
        mFollowingLink = in.readString();
        mUserName = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(mNFollowers);
        parcel.writeInt(mNFollowing);
        parcel.writeInt(mID);
        parcel.writeString(mAboutMe);
        parcel.writeString(mAvatarLink);
        parcel.writeString(mFollowersLink);
        parcel.writeString(mFollowingLink);
        parcel.writeString(mUserName);
    }
}
