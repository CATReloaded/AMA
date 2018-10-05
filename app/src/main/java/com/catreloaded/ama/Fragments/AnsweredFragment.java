package com.catreloaded.ama.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.catreloaded.ama.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AnsweredFragment extends Fragment {

    @BindView(R.id.rv_answered_questions)
    RecyclerView rvAnsweredQuestions;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_answered, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

}
