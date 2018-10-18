package com.ikhwanul.ikhlas.iiwandroid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.ikhwanul.ikhlas.iiwandroid.MainActivity;
import com.ikhwanul.ikhlas.iiwandroid.MainActivityKDM;
import com.ikhwanul.ikhlas.iiwandroid.MainActivityMM;
import com.ikhwanul.ikhlas.iiwandroid.MainActivityPSC;
import com.ikhwanul.ikhlas.iiwandroid.R;
import com.ikhwanul.ikhlas.iiwandroid.api.response.ApiResponse;
import com.ikhwanul.ikhlas.iiwandroid.api.response.AuthResponse;
import com.ikhwanul.ikhlas.iiwandroid.presenters.Presenter;
import com.ikhwanul.ikhlas.iiwandroid.presenters.iPresenterResponse;
import com.ikhwanul.ikhlas.iiwandroid.utils.Session;

public class SplashSecreenActivity extends AppCompatActivity implements iPresenterResponse {
    private static int SPLASH_TIME_OUT = 1000;

    Presenter mPresenter;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_secreen);
        mPresenter = new Presenter(this, this);
        if (isGooglePlayServicesAvailable()){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (Session.with(getApplicationContext()).isSignIn()) {
                        callMain();
                    }else{
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                    }
                }
            }, SPLASH_TIME_OUT);
        }
    }

    private void callMain(){
        mPresenter.isLogged();
    }

    public boolean isGooglePlayServicesAvailable() {
        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
        int status = googleApiAvailability.isGooglePlayServicesAvailable(this);
        if(status != ConnectionResult.SUCCESS) {
            if(googleApiAvailability.isUserResolvableError(status)) {
                googleApiAvailability.getErrorDialog(this, status, 2404).show();
            }
            return false;
        }
        return true;
    }

    @Override
    public void doSuccess(ApiResponse response, String tag) {
        if (tag.equals(Presenter.RES_IS_LOGGED)){
            AuthResponse dataresp = ((AuthResponse)response);
            if (!dataresp.error){
                if (dataresp.user.tipe == 0){
                    if (dataresp.user.peringkat.equals("1")){
                        intent = new Intent(getApplicationContext(), MainActivity.class);
                    }else if(dataresp.user.peringkat.equals("2")){
                        intent = new Intent(getApplicationContext(), MainActivityMM.class);
                    }else if(dataresp.user.peringkat.equals("3")){
                        intent = new Intent(getApplicationContext(), MainActivityKDM.class);
                    }
                }else if (dataresp.user.tipe == 1){
                    intent = new Intent(getApplicationContext(), MainActivityPSC.class);
                }
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.putExtra("dataUser", dataresp.user);
                startActivity(intent);
                finish();
            }else{
                Toast.makeText(this, dataresp.message +" : "+ Session.with(this).getToken(), Toast.LENGTH_LONG).show();
                intent = new Intent(getApplicationContext(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.putExtra("dataUser", dataresp.user);
                startActivity(intent);
                finish();
            }
        }
    }

    @Override
    public void doFail(String message) {

    }

    @Override
    public void doValidationError(boolean error) {

    }

    @Override
    public void doConnectionError() {

    }
}
