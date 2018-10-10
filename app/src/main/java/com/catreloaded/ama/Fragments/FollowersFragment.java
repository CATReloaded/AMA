package com.catreloaded.ama.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
import android.widget.Toast;

import com.catreloaded.ama.Adapters.UsersAdapter;
import com.catreloaded.ama.Interfaces.UserClickListener;
import com.catreloaded.ama.Loaders.NetworkJsonResponseLoader;
import com.catreloaded.ama.Objects.Follower;
import com.catreloaded.ama.Objects.User;
import com.catreloaded.ama.ProfileActivity;
import com.catreloaded.ama.R;
import com.catreloaded.ama.Utils.JSONParser;
import com.catreloaded.ama.Utils.PreferencesConstants;
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_followers,container,false);
        ButterKnife.bind(this,view);
        if(isOnline()){
            getLoaderManager().initLoader(0,null,this);
        }else{
            Toast.makeText(getContext(),R.string.no_internet_connection,Toast.LENGTH_SHORT).show();
        }
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
                new NetworkJsonResponseLoader(
                        getContext(),
                        UrlBuilder.buildFollowersUrl(PreferencesConstants.getUsername(getContext())),
                        NetworkJsonResponseLoader.GET);
        networkJsonResponseLoader.forceLoad();
        return networkJsonResponseLoader;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        try {
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

    /**
     * This method checks the internet state of the device
     * @return true if there is an active connection
     */
    private boolean isOnline(){
        ConnectivityManager cm = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }
}
