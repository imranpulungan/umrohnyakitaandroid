package com.ikhwanul.ikhlas.iiwandroid.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ikhwanul.ikhlas.iiwandroid.R;
import com.ikhwanul.ikhlas.iiwandroid.api.response.ApiResponse;
import com.ikhwanul.ikhlas.iiwandroid.core.AppActivity;
import com.ikhwanul.ikhlas.iiwandroid.presenters.DataFormPresenter;
import com.ikhwanul.ikhlas.iiwandroid.presenters.iPresenterResponse;

import java.util.HashMap;
import java.util.Map;

import static com.ikhwanul.ikhlas.iiwandroid.presenters.Presenter.SELL_KWITANSI;
import static com.ikhwanul.ikhlas.iiwandroid.presenters.Presenter.SHARE_KWITANSI;

public class PSCJualKwitansiActivity extends AppActivity implements iPresenterResponse {

    DataFormPresenter mPresenter;
    private TextView tvHeader, tvKodeOrId;
    private EditText editKodePerwakilan, editJlhKwitansi, editKeterangan;
    private Button btnSave;
    int id;

    public PSCJualKwitansiActivity() {
        super(R.layout.activity_pscberi_kwitansi, true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Jual Kwitansi");
        id = getIntent().getIntExtra("id", 0);

        initView();
        initObject();
        initEvent();
        attachData();
    }

    private void initEvent() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> data = new HashMap<>();
                data.put("id_perwakilan_psc", String.valueOf(id));
                data.put("id_perwakilan", editKodePerwakilan.getText().toString());
                data.put("jumlah_kwitansi",editJlhKwitansi.getText().toString());
                data.put("keterangan",editKeterangan.getText().toString());
                mPresenter.sellKwitansi(data);
            }
        });
    }

    private void initObject() {
        mPresenter = new DataFormPresenter(this, this);
    }

    private void attachData(){
        tvHeader.setText("Jual Kwitansi Perwakilan");
    }

    private void initView() {
        tvHeader = (TextView) findViewById(R.id.tv_header);
        tvKodeOrId = (TextView) findViewById(R.id.tv_kode_or_id);
        btnSave = (Button) findViewById(R.id.btn_save);
        editKodePerwakilan = (EditText) findViewById(R.id.edit_kode_perwakilan);
        editJlhKwitansi = (EditText) findViewById(R.id.edit_jlh_kwitansi);
        editKeterangan = (EditText) findViewById(R.id.edit_keterangan);
    }

    @Override
    public void doSuccess(ApiResponse response, String tag) {
        if (tag.equals(SELL_KWITANSI)){
            Toast.makeText(this, response.message, Toast.LENGTH_LONG).show();
            if (!response.error){
                onBackPressed();
            }
        }
    }

    @Override
    public void doValidationError(boolean error) {

    }
}
