package com.ikhwanul.ikhlas.iiwandroid.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.ikhwanul.ikhlas.iiwandroid.R;
import com.ikhwanul.ikhlas.iiwandroid.core.AppActivity;

public class PSCBeriKwitansiActivity extends AppActivity
{

    private TextView tvHeader;
    public PSCBeriKwitansiActivity() {
        super(R.layout.activity_pscberi_kwitansi, true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Beri Kwitansi");
        initView();
        initObject();
        attachData();
    }

    private void attachData() {
        tvHeader.setText("Beri Kwitansi ke Perwakilan");
    }

    private void initObject() {
    }

    private void initView() {
        tvHeader = (TextView) findViewById(R.id.tv_header);
    }
}
