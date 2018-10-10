package com.catreloaded.ama;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.catreloaded.ama.Fragments.AnsweredFragment;
import com.catreloaded.ama.Fragments.FollowersFragment;
import com.catreloaded.ama.Fragments.FollowingFragment;
import com.catreloaded.ama.Fragments.UnansweredFragment;
import com.catreloaded.ama.Loaders.NetworkJsonResponseLoader;
import com.catreloaded.ama.Objects.User;
import com.catreloaded.ama.Utils.JSONParser;
import com.catreloaded.ama.Utils.PreferencesConstants;
import com.catreloaded.ama.Utils.UrlBuilder;

import org.json.JSONException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String>{

    private static final String USERNAME_KEY = "username";
    private static final String USER_KEY = "user_key";

    private static boolean isFirstLoading = true;

    private static CoordinatorLayout coordinatorLayout;


    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.main_tab_layout)
    TabLayout mainTabLayout;
    @BindView(R.id.iv_search_toolbar)
    ImageView ivSearch;
    @BindView(R.id.et_search_toolbar)
    EditText etSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        coordinatorLayout = findViewById(R.id.coordinator_layout);

        vp.setAdapter(new CustomPagerAdapter(getSupportFragmentManager()));

        mainTabLayout.setupWithViewPager(vp);
        mainTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);


        mainTabLayout.getTabAt(0).setText(R.string.unanswered);
        mainTabLayout.getTabAt(1).setText(R.string.answered);
        mainTabLayout.getTabAt(2).setText(R.string.followers);
        mainTabLayout.getTabAt(3).setText(R.string.following);


    }

    @OnClick(R.id.iv_search_toolbar)
    void onSearchClicked(){
        String username = etSearch.getText().toString().trim();
        if(username.isEmpty()){
            Toast.makeText(getApplicationContext(), R.string.empty_search_bar_message,Toast.LENGTH_LONG).show();
        }else{
            Bundle bundle = new Bundle();
            bundle.putString(USERNAME_KEY,username);
            if(isFirstLoading){
                getSupportLoaderManager().initLoader(0,bundle,this);
                isFirstLoading = false;
            }else{
                getSupportLoaderManager().restartLoader(0,bundle,this);
            }
        }
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        Log.d("BUNDLE",args.getString(USERNAME_KEY));
        Log.d("URL",UrlBuilder.buildSpecificUserUrl(args.getString(USERNAME_KEY)));
        NetworkJsonResponseLoader networkJsonResponseLoader =
                new NetworkJsonResponseLoader(this,
                        UrlBuilder.buildSpecificUserUrl(args.getString(USERNAME_KEY))
                        ,NetworkJsonResponseLoader.GET);
        networkJsonResponseLoader.forceLoad();
        return networkJsonResponseLoader;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        if(data.equals("NULL")){
            Toast.makeText(getBaseContext(), R.string.user_not_found,Toast.LENGTH_SHORT).show();
        }else{
            try {
                User user = JSONParser.parseOneUser(data);
                Intent profileIntent = new Intent(this, ProfileActivity.class);
                profileIntent.putExtra(USER_KEY,user);
                startActivity(profileIntent);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }


    class CustomPagerAdapter extends FragmentPagerAdapter{


        CustomPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return new UnansweredFragment();
                case 1:
                    return new AnsweredFragment();
                case 2:
                    return new FollowersFragment();
                case 3:
                    return new FollowingFragment();
                    default:
                        return null;
            }
        }

        @Override
        public int getCount() {
            return 4;
        }
    }

    public static void showSnackbar(String text){
        Snackbar snackbar = Snackbar
                .make(coordinatorLayout, text, Snackbar.LENGTH_LONG);
        snackbar.show();
    }
}
