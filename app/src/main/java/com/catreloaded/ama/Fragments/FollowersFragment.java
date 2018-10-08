package com.catreloaded.ama.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.catreloaded.ama.Adapters.UsersAdapter;
import com.catreloaded.ama.Interfaces.UserClickListener;
import com.catreloaded.ama.Loaders.NetworkJsonResponseLoader;
import com.catreloaded.ama.Objects.Follower;
import com.catreloaded.ama.Objects.User;
import com.catreloaded.ama.ProfileActivity;
import com.catreloaded.ama.R;
import com.catreloaded.ama.Utils.JSONParser;
import com.catreloaded.ama.Utils.UrlBuilder;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FollowersFragment extends Fragment implements UserClickListener, LoaderManager.LoaderCallbacks<String> {

    private static final String USER_KEY = "user_key";

    @BindView(R.id.rv_followers)
    RecyclerView rvFollowers;
    //TODO fix UI

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_followers,container,false);
        ButterKnife.bind(this,view);
        getLoaderManager().initLoader(0,null,this);
        return view;
    }

    @Override
    public void onUserClickedListener(User clickedUser) {
        Intent profileIntent = new Intent(getActivity(), ProfileActivity.class);
        profileIntent.putExtra(USER_KEY,clickedUser);
        startActivity(profileIntent);
    }


    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        NetworkJsonResponseLoader networkJsonResponseLoader =
                new NetworkJsonResponseLoader(getContext(), UrlBuilder.buildFollowersUrl("stevensonwalter"));//TODO replace with the logged user
        networkJsonResponseLoader.forceLoad();
        return networkJsonResponseLoader;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        try {
            //TODO make the site receive more than 3 items
            List<Follower> followersData = JSONParser.<Follower>parseUsers(data,new Follower());
            List<User> usersData = new ArrayList<>();
            usersData.addAll(followersData);
            UsersAdapter adapter = new UsersAdapter(usersData,this);
            rvFollowers.setLayoutManager(new LinearLayoutManager(getContext()));
            rvFollowers.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }
}
