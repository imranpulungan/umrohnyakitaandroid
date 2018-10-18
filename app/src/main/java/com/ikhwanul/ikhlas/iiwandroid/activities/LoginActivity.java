package com.ikhwanul.ikhlas.iiwandroid.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ikhwanul.ikhlas.iiwandroid.MainActivity;
import com.ikhwanul.ikhlas.iiwandroid.MainActivityKDM;
import com.ikhwanul.ikhlas.iiwandroid.MainActivityMM;
import com.ikhwanul.ikhlas.iiwandroid.MainActivityPSC;
import com.ikhwanul.ikhlas.iiwandroid.R;
import com.ikhwanul.ikhlas.iiwandroid.api.response.ApiResponse;
import com.ikhwanul.ikhlas.iiwandroid.api.response.AuthResponse;
import com.ikhwanul.ikhlas.iiwandroid.core.AppActivity;
import com.ikhwanul.ikhlas.iiwandroid.presenters.AuthPresenter;
import com.ikhwanul.ikhlas.iiwandroid.presenters.iPresenterResponse;
import com.ikhwanul.ikhlas.iiwandroid.utils.Session;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ikhwanul.ikhlas.iiwandroid.presenters.AuthPresenter.RES_LOGIN;

public class LoginActivity extends AppActivity implements iPresenterResponse, AdapterView.OnItemSelectedListener {

    private EditText mUsername;
    private EditText mPassword;
    private Button btnLogin;
    private Spinner mSpinner;
    private TextView errorInfo;

    AuthPresenter mPresenter;

    List<String> roles = new ArrayList<String>();
    Intent intent;

    boolean status = true;

    public LoginActivity() {
        super(R.layout.activity_login,false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initObject();
        initEvent();
    }

    private void initObject() {
        mPresenter = new AuthPresenter(this, this);
    }

    private void initEvent() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> _data = new HashMap<String, String>();
                _data.put("username", mUsername.getText().toString());
                _data.put("password",mPassword.getText().toString());
                _data.put("tipe", String.valueOf(mSpinner.getSelectedItem()));
                mPresenter.login(_data);
            }
        });

        mPassword.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if(event.getAction() == MotionEvent.ACTION_UP) {
                    if(event.getRawX() >= (mPassword.getRight() - mPassword.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        if (status){
                            mPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            status = false;
                        }
                        else{
                            mPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            status = true;
                        }
                        mPassword.clearFocus();
                        return true;
                    }
                }
                return false;
            }
        });
    }

    private void initView() {
        errorInfo = (TextView) findViewById(R.id.error_info);
        mUsername = (EditText) findViewById(R.id.username);
        mPassword = (EditText) findViewById(R.id.password);
        mSpinner = (Spinner) findViewById(R.id.spinner_role);
        btnLogin = (Button) findViewById(R.id.btn_login);
        mSpinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        roles.add("Perwakilan");
        roles.add("PSC");
//        roles.add("Penyalur");
//        roles.add("Pengguna");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, roles);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        mSpinner.setAdapter(dataAdapter);
    }

    @Override
    public void doSuccess(ApiResponse response, String tag) {
        if (tag.equals(RES_LOGIN)){
            AuthResponse dataresp = ((AuthResponse)response);
            if (!dataresp.error){
                errorInfo.setVisibility(View.GONE);
                Session.with(getApplicationContext()).createLoginSession(dataresp.user.remember_token);
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
                errorInfo.setText("Username atau Password Salah");
                errorInfo.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void doValidationError(boolean error) {
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
