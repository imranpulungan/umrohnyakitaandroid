package com.ikhwanul.ikhlas.iiwandroid.activities;

import android.os.Bundle;
import android.widget.TextView;

import com.ikhwanul.ikhlas.iiwandroid.R;
import com.ikhwanul.ikhlas.iiwandroid.core.AppActivity;

public class PSCJualKwitansiActivity extends AppActivity{

    private TextView tvHeader;
    public PSCJualKwitansiActivity() {
        super(R.layout.activity_pscberi_kwitansi, true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Jual Kwitansi");
        initView();
        attachData();
    }

    private void attachData(){
        tvHeader.setText("Jual Kwitansi Perwakilan");
    }

    private void initView() {
        tvHeader = (TextView) findViewById(R.id.tv_header);
    }
}
