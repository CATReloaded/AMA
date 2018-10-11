package com.catreloaded.ama;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.catreloaded.ama.Loaders.NetworkJsonResponseLoader;
import com.catreloaded.ama.Utils.PreferencesConstants;
import com.catreloaded.ama.Utils.UrlBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {

    private static final String STATUS_KEY = "status_code";
    private static final int STATUS_OK = 200;
    private static final int STATUS_NO = 403;

    @BindView(R.id.btn_submit_login)
    Button btnSubmit;
    @BindView(R.id.et_username_login)
    EditText etUsername;
    @BindView(R.id.et_password_login)
    EditText etPassword;
    @BindView(R.id.tv_sign_up)
    TextView tvSignUp;
    @BindView(R.id.cb_remember_me)
    CheckBox checkBoxRememberMe;

    private boolean firstClick = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean startMain = sharedPreferences.getBoolean(PreferencesConstants.REMEMBER_USER,false);
        if(startMain){
            startActivity(new Intent(this,MainActivity.class));
            finish();
        }
    }

    @OnClick(R.id.btn_submit_login)
    void onSubmitButtonClicked(){
        if(isOnline()){
            if(etUsername.getText().toString().trim().isEmpty() || etPassword.getText().toString().trim().isEmpty()){
                Toast.makeText(getBaseContext(), R.string.enter_user_and_pass_message,Toast.LENGTH_SHORT).show();
            }else{
                if(firstClick){
                    getSupportLoaderManager().initLoader(0,null,this);
                }else{
                    getSupportLoaderManager().restartLoader(0,null,this);
                }
            }
        }else{
            Toast.makeText(getBaseContext(),R.string.no_internet_connection,Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.tv_sign_up)
    void onSignUpClicked(){
        Intent signUpIntent = new Intent(Intent.ACTION_VIEW);
        signUpIntent.setData(Uri.parse(UrlBuilder.buildSignUpUrl()));
        startActivity(signUpIntent);
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        NetworkJsonResponseLoader networkJsonResponseLoader =
                new NetworkJsonResponseLoader(
                        getBaseContext(),
                        UrlBuilder.buildSignInUrl(),
                        NetworkJsonResponseLoader.POST,
                        etUsername.getText().toString(),
                        etPassword.getText().toString());
        networkJsonResponseLoader.forceLoad();
        return networkJsonResponseLoader;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        Log.d("SIGN IN DATA",data);
        firstClick = false;
        try {
            JSONObject rootObject = new JSONObject(data);
            int statusCode = rootObject.getInt(STATUS_KEY);

            if(statusCode == STATUS_OK){
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                SharedPreferences.Editor editor = sharedPreferences.edit();
                if(checkBoxRememberMe.isChecked()){
                    editor.putBoolean(PreferencesConstants.REMEMBER_USER,true);
                }
                editor.putString(PreferencesConstants.USERNAME,etUsername.getText().toString());
                editor.putString(PreferencesConstants.PASSWORD,etPassword.getText().toString());
                editor.apply();
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
                finish();
                Toast.makeText(getBaseContext(), R.string.logged_in_message ,Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getBaseContext(), R.string.incorrect_user_or_pass_message ,Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            Toast.makeText(getBaseContext(), R.string.incorrect_user_or_pass_message ,Toast.LENGTH_SHORT).show();
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
        ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }
}
