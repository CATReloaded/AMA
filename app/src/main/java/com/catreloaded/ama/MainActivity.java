package com.catreloaded.ama;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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

import com.catreloaded.ama.Fragments.AnsweredFragment;
import com.catreloaded.ama.Fragments.FollowersFragment;
import com.catreloaded.ama.Fragments.FollowingFragment;
import com.catreloaded.ama.Fragments.UnansweredFragment;
import com.catreloaded.ama.Loaders.TestLoader;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String>{

    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.main_tab_layout)
    TabLayout mainTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        vp.setAdapter(new CustomPagerAdapter(getSupportFragmentManager()));

        mainTabLayout.setupWithViewPager(vp);

        mainTabLayout.getTabAt(0).setCustomView(R.layout.custom_tab_test);

        getSupportLoaderManager().initLoader(0,null,this);

        //Log.d("URL",UrlBuilder.buildUsersUrl(5));

    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        TestLoader testLoader = new TestLoader(this);
        testLoader.forceLoad();
        return testLoader;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        Log.d("DATA",data);
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
}
