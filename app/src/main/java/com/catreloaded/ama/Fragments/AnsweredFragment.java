package com.catreloaded.ama.Fragments;

import android.content.Context;
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

import com.catreloaded.ama.Adapters.QuestionsAdapter;
import com.catreloaded.ama.Loaders.NetworkJsonResponseLoader;
import com.catreloaded.ama.Objects.AnsweredQuestion;
import com.catreloaded.ama.Objects.Question;
import com.catreloaded.ama.R;
import com.catreloaded.ama.Utils.JSONParser;
import com.catreloaded.ama.Utils.PreferencesConstants;
import com.catreloaded.ama.Utils.UrlBuilder;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AnsweredFragment extends Fragment implements LoaderManager.LoaderCallbacks<String> {

    @BindView(R.id.rv_answered_questions)
    RecyclerView rvAnsweredQuestions;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_answered, container, false);
        ButterKnife.bind(this,view);
        if(isOnline()){
            getLoaderManager().initLoader(0,null,this);
        }else{
            Toast.makeText(getContext(),R.string.no_internet_connection,Toast.LENGTH_SHORT).show();
        }
        return view;
    }


    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        NetworkJsonResponseLoader networkJsonResponseLoader =
                new NetworkJsonResponseLoader(getContext(),
                        UrlBuilder.buildAnsweredQuestionsUrl(PreferencesConstants.getUsername(getContext())),
                        NetworkJsonResponseLoader.GET);
        networkJsonResponseLoader.forceLoad();
        return networkJsonResponseLoader;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        //Log.d("DATA",data);
        try {
            List<AnsweredQuestion> answeredQuestionsData = JSONParser.<AnsweredQuestion>parseQuestion(data,new AnsweredQuestion());
            List<Question> questions = new ArrayList<>();
            questions.addAll(answeredQuestionsData);
            QuestionsAdapter adapter = new QuestionsAdapter(questions);
            rvAnsweredQuestions.setLayoutManager(new LinearLayoutManager(getContext()));
            rvAnsweredQuestions.setAdapter(adapter);
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
