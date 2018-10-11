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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.catreloaded.ama.Adapters.QuestionsAdapter;
import com.catreloaded.ama.AnswerActivity;
import com.catreloaded.ama.Interfaces.QuestionClickListener;
import com.catreloaded.ama.Loaders.NetworkJsonResponseLoader;
import com.catreloaded.ama.Objects.Question;
import com.catreloaded.ama.Objects.UnAnsweredQuestion;
import com.catreloaded.ama.R;
import com.catreloaded.ama.Utils.JSONParser;
import com.catreloaded.ama.Utils.PreferencesConstants;
import com.catreloaded.ama.Utils.UrlBuilder;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class UnansweredFragment extends Fragment implements LoaderManager.LoaderCallbacks<String>,QuestionClickListener {

    @BindView(R.id.rv_unanswered_questions)
    RecyclerView rvUnansweredQuestions;

    private static final String ID_KEY = "id";
    private static final String QUESTION_KEY = "question";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_unanswered, container, false);
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
                new NetworkJsonResponseLoader(
                        getContext(),
                        UrlBuilder.buildUnansweredQuestionsUrl(PreferencesConstants.getUsername(getContext())),
                        NetworkJsonResponseLoader.GET);
        networkJsonResponseLoader.forceLoad();
        return networkJsonResponseLoader;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        try {
            List<UnAnsweredQuestion> unansweredQuestionsData = JSONParser.<UnAnsweredQuestion>parseQuestion(data,new UnAnsweredQuestion());
            List<Question> questions = new ArrayList<>();
            questions.addAll(unansweredQuestionsData);
            QuestionsAdapter adapter = new QuestionsAdapter(questions,this);
            rvUnansweredQuestions.setLayoutManager(new LinearLayoutManager(getContext()));
            rvUnansweredQuestions.setAdapter(adapter);
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

    @Override
    public void onQuestionClicked(int questionId,String question) {
        Intent answerActivityIntent = new Intent(getActivity(), AnswerActivity.class);
        answerActivityIntent.putExtra(ID_KEY,questionId);
        answerActivityIntent.putExtra(QUESTION_KEY,question);
        startActivity(answerActivityIntent);
    }
}
