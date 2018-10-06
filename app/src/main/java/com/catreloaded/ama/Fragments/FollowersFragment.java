package com.catreloaded.ama.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.catreloaded.ama.Adapters.UsersAdapter;
import com.catreloaded.ama.Interfaces.UserClickListener;
import com.catreloaded.ama.Objects.Follower;
import com.catreloaded.ama.Objects.User;
import com.catreloaded.ama.ProfileActivity;
import com.catreloaded.ama.R;
import com.catreloaded.ama.Utils.JSONParser;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FollowersFragment extends Fragment implements UserClickListener {

    private static final String USER_KEY = "user_key";

    @BindView(R.id.rv_followers)
    RecyclerView rvFollowers;
    //TODO replace this testData String with a network json response
    String testData = "{\n" +
            "  \"followers\": [\n" +
            "    {\n" +
            "      \"#followers\": 3, \n" +
            "      \"#following\": 4, \n" +
            "      \"about_me\": \"Left leave official hard meeting various similar hold change travel southern indicate major happen wrong evening kitchen mouth avoid decade local democratic establish risk drive control week poor beautiful situation seek I positive interview conference identify compare medical ability thank space sense course PM to field keep behavior least color step number western nothing officer fine eye end production increase.\", \n" +
            "      \"avatar_uri\": \"http://www.gravatar.com/avatar/8d15d3fb508c015a34076652bd4d509c?s=100&d=identicon\", \n" +
            "      \"followers\": \"http://ama.localdomain:5000/api/users/thomasjoy/followers?p=1\", \n" +
            "      \"following\": \"http://ama.localdomain:5000/api/users/thomasjoy/following?p=1\", \n" +
            "      \"id\": 65, \n" +
            "      \"username\": \"thomasjoy\"\n" +
            "    }, \n" +
            "    {\n" +
            "      \"#followers\": 4, \n" +
            "      \"#following\": 3, \n" +
            "      \"about_me\": \"Industry edge billion pressure watch fast season here radio page south often sell hour campaign entire avoid population letter instead toward sit wait after modern interview way market understand computer describe standard energy month candidate design certainly stuff learn none even year result hear college seem.\", \n" +
            "      \"avatar_uri\": \"http://www.gravatar.com/avatar/a2e0bc622b192a04b05256d54d6a2ff0?s=100&d=identicon\", \n" +
            "      \"followers\": \"http://ama.localdomain:5000/api/users/nfloyd/followers?p=1\", \n" +
            "      \"following\": \"http://ama.localdomain:5000/api/users/nfloyd/following?p=1\", \n" +
            "      \"id\": 84, \n" +
            "      \"username\": \"nfloyd\"\n" +
            "    }, \n" +
            "    {\n" +
            "      \"#followers\": 3, \n" +
            "      \"#following\": 6, \n" +
            "      \"about_me\": \"Later newspaper including president standard option music do evening trial agency enough any environment current shake you through because think walk real now his pass third enjoy until front end travel marriage push Congress little plant remember movement skill argue near run back decision seek view particularly.\", \n" +
            "      \"avatar_uri\": \"http://www.gravatar.com/avatar/c956ec19d9f68d76cb93051b8617e235?s=100&d=identicon\", \n" +
            "      \"followers\": \"http://ama.localdomain:5000/api/users/jackie23/followers?p=1\", \n" +
            "      \"following\": \"http://ama.localdomain:5000/api/users/jackie23/following?p=1\", \n" +
            "      \"id\": 78, \n" +
            "      \"username\": \"jackie23\"\n" +
            "    }\n" +
            "  ], \n" +
            "  \"next\": \"http://ama.localdomain:5000/api/users/testuser101/followers?p=2&n=3\"\n" +
            "}\n";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_followers,container,false);
        ButterKnife.bind(this,view);
        try {
            List<Follower> followersData = JSONParser.<Follower>parseUsers(testData,new Follower());
            List<User> usersData = new ArrayList<>();
            Log.d("Data Size",followersData.size() +"");
            usersData.addAll(followersData);
            UsersAdapter adapter = new UsersAdapter(usersData,this);
            rvFollowers.setLayoutManager(new LinearLayoutManager(getContext()));
            rvFollowers.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return view;
    }

    @Override
    public void onUserClickedListener(User clickedUser) {
        Intent profileIntent = new Intent(getActivity(), ProfileActivity.class);
        profileIntent.putExtra(USER_KEY,clickedUser);
        startActivity(profileIntent);
    }
}
