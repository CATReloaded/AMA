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
import com.catreloaded.ama.Objects.Following;
import com.catreloaded.ama.Objects.User;
import com.catreloaded.ama.ProfileActivity;
import com.catreloaded.ama.R;
import com.catreloaded.ama.Utils.JSONParser;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FollowingFragment extends Fragment implements UserClickListener {

    private static final String USER_KEY = "user_key";

    @BindView(R.id.rv_following)
    RecyclerView rvFollowing;
    //TODO replace this testData with a network json response
    private String testData = "{\n" +
            "  \"following\": [\n" +
            "    {\n" +
            "      \"#followers\": 20, \n" +
            "      \"#following\": 18, \n" +
            "      \"about_me\": \"Available ahead now west whole recently like poor these door green husband pay order hot president detail pick well bed ok response year much partner movie factor cell around small generation job person popular participant response sit difference join friend here individual religious benefit financial.\", \n" +
            "      \"avatar_uri\": \"http://www.gravatar.com/avatar/1e8aeebc17283e073b24db5b3080cd37?s=100&d=identicon\", \n" +
            "      \"followers\": \"http://ama.localdomain:5000/api/users/diazvalerie/followers?p=1\", \n" +
            "      \"following\": \"http://ama.localdomain:5000/api/users/diazvalerie/following?p=1\", \n" +
            "      \"id\": 92, \n" +
            "      \"username\": \"diazvalerie\"\n" +
            "    }, \n" +
            "    {\n" +
            "      \"#followers\": 20, \n" +
            "      \"#following\": 19, \n" +
            "      \"about_me\": \"Blood chair respond commercial pressure line before week particular customer learn quality both according though operation herself property fast instead ball usually type item particular election himself system commercial myself as arrive back American just meeting process foot attorney prevent.\", \n" +
            "      \"avatar_uri\": \"http://www.gravatar.com/avatar/93bfc42d1cabf7d9df88f2dc93ac96b7?s=100&d=identicon\", \n" +
            "      \"followers\": \"http://ama.localdomain:5000/api/users/alexanderhampton/followers?p=1\", \n" +
            "      \"following\": \"http://ama.localdomain:5000/api/users/alexanderhampton/following?p=1\", \n" +
            "      \"id\": 29, \n" +
            "      \"username\": \"alexanderhampton\"\n" +
            "    }, \n" +
            "    {\n" +
            "      \"#followers\": 22, \n" +
            "      \"#following\": 17, \n" +
            "      \"about_me\": \"Senior everyone number nature account night structure perhaps form pick sea or particular talk world ready glass role night notice country culture right son station painting form great pretty close while letter method east be community act model pick compare fear work director ready year ability national glass his customer among herself true role question yeah share end report.\", \n" +
            "      \"avatar_uri\": \"http://www.gravatar.com/avatar/74ce413edced0bcd4d09c07c85f9f743?s=100&d=identicon\", \n" +
            "      \"followers\": \"http://ama.localdomain:5000/api/users/morriskelly/followers?p=1\", \n" +
            "      \"following\": \"http://ama.localdomain:5000/api/users/morriskelly/following?p=1\", \n" +
            "      \"id\": 16, \n" +
            "      \"username\": \"morriskelly\"\n" +
            "    }\n" +
            "  ], \n" +
            "  \"next\": \"http://ama.localdomain:5000/api/users/testuser101/following?p=2&n=3\"\n" +
            "}\n";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_following,container,false);
        ButterKnife.bind(this,view);
        try {
            List<Following> followingData = JSONParser.<Following>parseUsers(testData,new Following());
            List<User> usersData = new ArrayList<>();
            Log.d("Data Size",followingData.size() +"");
            usersData.addAll(followingData);
            UsersAdapter adapter = new UsersAdapter(usersData,this);
            rvFollowing.setLayoutManager(new LinearLayoutManager(getContext()));
            rvFollowing.setAdapter(adapter);
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
