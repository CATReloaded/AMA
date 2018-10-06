package com.catreloaded.ama.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.catreloaded.ama.Adapters.QuestionsAdapter;
import com.catreloaded.ama.Objects.AnsweredQuestion;
import com.catreloaded.ama.Objects.Question;
import com.catreloaded.ama.Objects.UnAnsweredQuestion;
import com.catreloaded.ama.R;
import com.catreloaded.ama.Utils.JSONParser;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class UnansweredFragment extends Fragment {

    @BindView(R.id.rv_unanswered_questions)
    RecyclerView rvUnansweredQuestions;

    private String testData = "{\n" +
            "  \"next\": \"http://ama.localdomain:5000/api/users/testuser101/unanswered-questions?p=2&n=3\", \n" +
            "  \"unanswered_questions\": [\n" +
            "    {\n" +
            "      \"answer\": \"NULL\", \n" +
            "      \"asker\": \"diazvalerie\", \n" +
            "      \"date\": \"2018-09-26 01:53:36.086894\", \n" +
            "      \"has_answer\": false, \n" +
            "      \"question\": \"Bill natural gun letter position support speech.\", \n" +
            "      \"replier\": \"testuser101\"\n" +
            "    }, \n" +
            "    {\n" +
            "      \"answer\": \"NULL\", \n" +
            "      \"asker\": \"chapmanpatricia\", \n" +
            "      \"date\": \"2018-09-26 01:53:36.077867\", \n" +
            "      \"has_answer\": false, \n" +
            "      \"question\": \"Candidate cold may pass go part town.\", \n" +
            "      \"replier\": \"testuser101\"\n" +
            "    }, \n" +
            "    {\n" +
            "      \"answer\": \"NULL\", \n" +
            "      \"asker\": \"saunderskathryn\", \n" +
            "      \"date\": \"2018-09-26 01:53:36.067101\", \n" +
            "      \"has_answer\": false, \n" +
            "      \"question\": \"Far rather today hotel chance also.\", \n" +
            "      \"replier\": \"testuser101\"\n" +
            "    }\n" +
            "  ]\n" +
            "}\n";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_unanswered, container, false);
        ButterKnife.bind(this,view);
        try {
            List<UnAnsweredQuestion> unansweredQuestionsData = JSONParser.<UnAnsweredQuestion>parseQuestion(testData,new UnAnsweredQuestion());
            List<Question> questions = new ArrayList<>();
            questions.addAll(unansweredQuestionsData);
            QuestionsAdapter adapter = new QuestionsAdapter(questions);
            rvUnansweredQuestions.setLayoutManager(new LinearLayoutManager(getContext()));
            rvUnansweredQuestions.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return view;
    }

}
