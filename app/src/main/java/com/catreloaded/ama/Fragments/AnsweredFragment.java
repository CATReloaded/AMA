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
import com.catreloaded.ama.R;
import com.catreloaded.ama.Utils.JSONParser;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AnsweredFragment extends Fragment {

    @BindView(R.id.rv_answered_questions)
    RecyclerView rvAnsweredQuestions;
    //TODO replace this testData with a network json response
    private String testData = "{\n" +
            "  \"answered_questions\": [\n" +
            "    {\n" +
            "      \"answer\": \"Article effect performance position sing offer effort.\", \n" +
            "      \"asker\": \"trevor16\", \n" +
            "      \"date\": \"2018-09-26 01:53:36.082956\", \n" +
            "      \"has_answer\": true, \n" +
            "      \"question\": \"Once throughout boy far past which might police current pay war road.\", \n" +
            "      \"replier\": \"testuser101\"\n" +
            "    }, \n" +
            "    {\n" +
            "      \"answer\": \"Body political baby mother benefit pay feeling seem also within sit civil imagine.\", \n" +
            "      \"asker\": \"katherinemelton\", \n" +
            "      \"date\": \"2018-09-26 01:53:36.072465\", \n" +
            "      \"has_answer\": true, \n" +
            "      \"question\": \"Sea grow Republican feeling black sure when perform analysis theory.\", \n" +
            "      \"replier\": \"testuser101\"\n" +
            "    }, \n" +
            "    {\n" +
            "      \"answer\": \"Myself rich night action attorney whether industry yes very necessary.\", \n" +
            "      \"asker\": \"thomasjoy\", \n" +
            "      \"date\": \"2018-09-26 01:53:36.062046\", \n" +
            "      \"has_answer\": true, \n" +
            "      \"question\": \"Head hard hear year country career specific sign war popular our need hundred.\", \n" +
            "      \"replier\": \"testuser101\"\n" +
            "    }\n" +
            "  ], \n" +
            "  \"next\": \"http://ama.localdomain:5000/api/users/testuser101/answered-questions?p=2&n=3\"\n" +
            "}\n";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_answered, container, false);
        ButterKnife.bind(this,view);
        try {
            List<AnsweredQuestion> answeredQuestionsData = JSONParser.<AnsweredQuestion>parseQuestion(testData,new AnsweredQuestion());
            List<Question> questions = new ArrayList<>();
            questions.addAll(answeredQuestionsData);
            QuestionsAdapter adapter = new QuestionsAdapter(questions);
            rvAnsweredQuestions.setLayoutManager(new LinearLayoutManager(getContext()));
            rvAnsweredQuestions.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return view;
    }

}
