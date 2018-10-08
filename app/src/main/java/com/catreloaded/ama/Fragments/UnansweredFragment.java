package com.catreloaded.ama.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.catreloaded.ama.Adapters.QuestionsAdapter;
import com.catreloaded.ama.Loaders.NetworkJsonResponseLoader;
import com.catreloaded.ama.Objects.AnsweredQuestion;
import com.catreloaded.ama.Objects.Question;
import com.catreloaded.ama.Objects.UnAnsweredQuestion;
import com.catreloaded.ama.R;
import com.catreloaded.ama.Utils.JSONParser;
import com.catreloaded.ama.Utils.UrlBuilder;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class UnansweredFragment extends Fragment implements LoaderManager.LoaderCallbacks<String> {

    @BindView(R.id.rv_unanswered_questions)
    RecyclerView rvUnansweredQuestions;
    //TODO fix UI

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_unanswered, container, false);
        ButterKnife.bind(this,view);
        getLoaderManager().initLoader(0,null,this);
        return view;
    }


    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        NetworkJsonResponseLoader networkJsonResponseLoader =
                new NetworkJsonResponseLoader(getContext(), UrlBuilder.buildUnansweredQuestionsUrl("stevensonwalter"));//TODO replace with the logged user
        networkJsonResponseLoader.forceLoad();
        return networkJsonResponseLoader;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        try {
            //TODO make the app receive more than 3 items
            List<UnAnsweredQuestion> unansweredQuestionsData = JSONParser.<UnAnsweredQuestion>parseQuestion(data,new UnAnsweredQuestion());
            List<Question> questions = new ArrayList<>();
            questions.addAll(unansweredQuestionsData);
            QuestionsAdapter adapter = new QuestionsAdapter(questions);
            rvUnansweredQuestions.setLayoutManager(new LinearLayoutManager(getContext()));
            rvUnansweredQuestions.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }
}
