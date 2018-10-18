package com.ikhwanul.ikhlas.iiwandroid.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ikhwanul.ikhlas.iiwandroid.R;
import com.ikhwanul.ikhlas.iiwandroid.api.response.ApiResponse;
import com.ikhwanul.ikhlas.iiwandroid.core.AppActivity;
import com.ikhwanul.ikhlas.iiwandroid.presenters.iPresenterResponse;

public class PSCDetailPerwakilanActivity extends AppActivity implements iPresenterResponse {

    public PSCDetailPerwakilanActivity() {
        super(R.layout.activity_pscdetail_perwakilan, true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int id = getIntent().getIntExtra("id", 0);
    }

    @Override
    public void doSuccess(ApiResponse response, String tag) {
    }

    @Override
    public void doValidationError(boolean error) {

    }
}
