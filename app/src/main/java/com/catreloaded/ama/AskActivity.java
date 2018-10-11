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

public class AskActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {

    private static final String USERNAME_KEY = "username_key";
    private static final String STATUS_CODE_KEY = "status_code";
    private static final int CODE_OK = 200;

    @BindView(R.id.tv_ask_user_ask_act)
    TextView tvAskUser;
    @BindView(R.id.et_ask_ask_act)
    EditText etAsk;
    @BindView(R.id.btn_ask_ask_act)
    Button btnAsk;

    private String mUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask);

        ButterKnife.bind(this);

        mUsername = getIntent().getExtras().getString(USERNAME_KEY);
        tvAskUser.setText("Ask " + mUsername + " anything");
    }

    @OnClick(R.id.btn_ask_ask_act)
    void onAskButtonClicked(){
        if(isOnline()){
            getSupportLoaderManager().initLoader(0,null,this);
        }else {
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
        AskAnswerLoader askAnswerLoader = new AskAnswerLoader(getBaseContext(),AskAnswerLoader.TYPE_ASK,mUsername,etAsk.getText().toString());
        askAnswerLoader.forceLoad();
        return askAnswerLoader;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        try {
            JSONObject rootObject = new JSONObject(data);
            int statusCode = rootObject.getInt(STATUS_CODE_KEY);
            if(statusCode == CODE_OK){
                Toast.makeText(getBaseContext(), R.string.question_sent , Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getBaseContext(), R.string.something_went_wrong , Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getBaseContext(), R.string.something_went_wrong , Toast.LENGTH_SHORT).show();
        }
        finish();
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }
}
