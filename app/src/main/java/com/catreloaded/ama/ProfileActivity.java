package com.catreloaded.ama;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.catreloaded.ama.Objects.User;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfileActivity extends AppCompatActivity {

    private static final String USER_KEY = "user_key";
    private static final String USERNAME_KEY = "username_key";

    @BindView(R.id.iv_avatar_profile_act)
    ImageView ivAvatar;
    @BindView(R.id.tv_username_profile_act)
    TextView tvUsername;
    @BindView(R.id.tv_followers_number)
    TextView tvFollowersNumber;
    @BindView(R.id.tv_following_number)
    TextView tvFollowingNumber;
    @BindView(R.id.tv_about_me_value_profile_act)
    TextView tvAboutMe;

    @BindView(R.id.btn_ask_profile_act)
    Button btnAsk;
    @BindView(R.id.btn_follow_profile_act)
    Button btnFollow;

    private User recievedUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);

        recievedUser = getIntent().getExtras().getParcelable(USER_KEY);
        fillData();
    }

    private void fillData(){
        Glide.with(this).load(recievedUser.getAvatarLink()).apply(RequestOptions.circleCropTransform().error(R.drawable.user)).into(ivAvatar);
        tvUsername.setText(recievedUser.getUserName());
        tvFollowersNumber.setText(recievedUser.getNFollowers() + "");
        tvFollowingNumber.setText(recievedUser.getNFollowing() + "");
        tvAboutMe.setText(recievedUser.getAboutMe());
    }

    @OnClick(R.id.btn_ask_profile_act)
    void onAskButtonClicked(){
        Intent askActivityIntent = new Intent(this,AskActivity.class);
        askActivityIntent.putExtra(USERNAME_KEY,recievedUser.getUserName());
        startActivity(askActivityIntent);
    }
}
