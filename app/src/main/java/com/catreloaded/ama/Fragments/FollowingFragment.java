package com.catreloaded.ama.Fragments;

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
import com.catreloaded.ama.Objects.Follower;
import com.catreloaded.ama.Objects.Following;
import com.catreloaded.ama.Objects.User;
import com.catreloaded.ama.R;
import com.catreloaded.ama.Utils.JSONParser;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FollowingFragment extends Fragment {

    @BindView(R.id.rv_following)
    RecyclerView rvFollowing;

    private String testData = "";

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
            UsersAdapter adapter = new UsersAdapter(usersData);
            rvFollowing.setLayoutManager(new LinearLayoutManager(getContext()));
            rvFollowing.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return view;
    }
}
