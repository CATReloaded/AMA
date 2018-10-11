package com.catreloaded.ama;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.catreloaded.ama.Loaders.AskAnswerLoader;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AnswerActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {

    private static final String ID_KEY = "id";
    private static final String QUESTION_KEY = "question";

    @BindView(R.id.tv_question_answer_act)
    TextView tvQuestion;
    @BindView(R.id.et_answer_answer_act)
    EditText etAnswer;
    @BindView(R.id.btn_answer_answer_act)
    Button btnAnswer;

    private int mQuestionId;
    private String mQuestion;
    private String STATUS_CODE_KEY = "status_code";
    private int CODE_OK = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        Bundle bundle = getIntent().getExtras();
        mQuestion = bundle.getString(QUESTION_KEY);
        mQuestionId = bundle.getInt(ID_KEY);
        ButterKnife.bind(this);

        tvQuestion.setText(mQuestion + " ?");
    }

    @OnClick(R.id.btn_answer_answer_act)
    void onAnswerButtonClicked(){
        if(isOnline()){
            if(etAnswer.getText().toString().trim().isEmpty()){
                Toast.makeText(getBaseContext(), R.string.please_enter_your_answer,Toast.LENGTH_SHORT).show();
            }else{
                getSupportLoaderManager().initLoader(0,null,this);
            }
        }else{
            Toast.makeText(getBaseContext(),R.string.no_internet_connection,Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * This method checks the internet state of the device
     * @return true if there is an active connection
     */
    private boolean isOnline(){
        ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }


    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        AskAnswerLoader askAnswerLoader = new AskAnswerLoader(this,AskAnswerLoader.TYPE_ANSWER,etAnswer.getText().toString(),mQuestionId);
        askAnswerLoader.forceLoad();
        return askAnswerLoader;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        try {
            JSONObject jsonObject = new JSONObject(data);
            int statusCode = jsonObject.getInt(STATUS_CODE_KEY);
            if(statusCode == CODE_OK){
                Toast.makeText(getBaseContext(), R.string.answer_sent,Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getBaseContext(),R.string.something_went_wrong,Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getBaseContext(),R.string.something_went_wrong,Toast.LENGTH_SHORT).show();
        }
        finish();
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }
}
