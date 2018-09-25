package com.catreloaded.ama;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.catreloaded.ama.Fragments.AnsweredFragment;
import com.catreloaded.ama.Fragments.UnAnsweredFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.vp)
    ViewPager vp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startActivity(new Intent(this,ResetMyPasswordActivity.class));

        ButterKnife.bind(this);

        vp.setAdapter(new CustomPagerAdapter(getSupportFragmentManager()));
    }

    class CustomPagerAdapter extends FragmentPagerAdapter{


        public CustomPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return new UnAnsweredFragment();
                case 1:
                    return new AnsweredFragment();
                    default:
                        return null;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
