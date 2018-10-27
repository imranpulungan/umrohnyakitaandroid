package com.ikhwanul.ikhlas.iiwandroid.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ikhwanul.ikhlas.iiwandroid.R;
import com.ikhwanul.ikhlas.iiwandroid.api.response.ApiResponse;
import com.ikhwanul.ikhlas.iiwandroid.api.response.AuthResponse;
import com.ikhwanul.ikhlas.iiwandroid.core.AppActivity;
import com.ikhwanul.ikhlas.iiwandroid.entities.User;
import com.ikhwanul.ikhlas.iiwandroid.presenters.DataFormPresenter;
import com.ikhwanul.ikhlas.iiwandroid.presenters.iPresenterResponse;

import java.util.HashMap;
import java.util.Map;

import static com.ikhwanul.ikhlas.iiwandroid.presenters.Presenter.GET_DATA_PERWAKILAN_BY_CODE;
import static com.ikhwanul.ikhlas.iiwandroid.presenters.Presenter.GET_STOK_KWITANSI;
import static com.ikhwanul.ikhlas.iiwandroid.presenters.Presenter.SHARE_KWITANSI;

public class PSCBeriKwitansiActivity extends AppActivity implements iPresenterResponse
{

    DataFormPresenter mPresenter;
    private TableLayout detailInfo;
    private TextView tvHeader, tvMyStock, tvKodeOrId, tvNamaLengkap, tvNoTelp, tvAlamat;
    private EditText editKodePerwakilan, editJlhKwitansi, editKeterangan;
    private Button btnSave;
    int id;

    User dataUser;


    public PSCBeriKwitansiActivity() {
        super(R.layout.activity_pscberi_kwitansi, true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Beri Kwitansi");
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
                if (dataUser != null){
                    int jlhKwitansi = 0;
                    if (!editJlhKwitansi.getText().toString().equals( "" ))
                        jlhKwitansi += Integer.valueOf( editJlhKwitansi.getText().toString() );
                    if (dataUser.id_perwakilan == null){
                        Toast.makeText( PSCBeriKwitansiActivity.this, "Perwakilan belum didapat, silahkan cek lagi dengan kode perwakilan", Toast.LENGTH_SHORT ).show();
                    }else if( jlhKwitansi < 1){
                        Toast.makeText( PSCBeriKwitansiActivity.this, "Jumlah Kwitansi Tidak Valid", Toast.LENGTH_SHORT ).show();
                    }else{
                        Map<String, String> data = new HashMap<>();
                        data.put("id_perwakilan", String.valueOf(id));
                        data.put("kode_perwakilan", editKodePerwakilan.getText().toString());
                        data.put("jumlah_kwitansi",editJlhKwitansi.getText().toString());
                        data.put("keterangan",editKeterangan.getText().toString());
                        mPresenter.shareKwitansi(data);
                    }
                }else{
                    Toast.makeText( PSCBeriKwitansiActivity.this, "Perwakilan belum didapat, silahkan cek lagi dengan kode perwakilan", Toast.LENGTH_SHORT ).show();
                }
            }
        });

        editKodePerwakilan.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    Map<String, String> data = new HashMap<>();
                    data.put("kode_perwakilan", editKodePerwakilan.getText().toString());
                    mPresenter.getPerwakilanByCode(data);
                    return true;
                }
                return false;
            }
        });

    }

    private void attachData() {
        tvHeader.setText("Beri Kwitansi ke Perwakilan");
    }

    private void initObject() {
        mPresenter = new DataFormPresenter(this, this);
        mPresenter.getStokkwitansi(id);
    }

    private void initView() {
        detailInfo = (TableLayout) findViewById(R.id.detail_info);
        editKodePerwakilan = (EditText) findViewById(R.id.edit_kode_perwakilan);
        editJlhKwitansi = (EditText) findViewById(R.id.edit_jlh_kwitansi);
        editKeterangan = (EditText) findViewById(R.id.edit_keterangan);
        tvMyStock = (TextView) findViewById(R.id.tv_my_stock);
        tvHeader = (TextView) findViewById(R.id.tv_header);
        tvAlamat = (TextView) findViewById(R.id.tv_alamat);
        tvNoTelp = (TextView) findViewById(R.id.tv_no_telp);
        tvNamaLengkap = (TextView) findViewById(R.id.tv_nama_lengkap);
        tvKodeOrId = (TextView) findViewById(R.id.tv_kode_or_id);
        btnSave = (Button) findViewById(R.id.btn_save);
    }

    @Override
    public void doSuccess(ApiResponse response, String tag) {
        if (tag.equals(GET_STOK_KWITANSI)){
            tvMyStock.setText("Jumlah Stok Kwitansi Free anda "+ response.message);
        }else if (tag.equals(SHARE_KWITANSI)){
            Toast.makeText(this, response.message, Toast.LENGTH_LONG).show();
            if (!response.error){
                onBackPressed();
            }
        }else if(tag.equals(GET_DATA_PERWAKILAN_BY_CODE)){
            if (!response.error){
                dataUser = ((AuthResponse)response).user;
                if (dataUser != null){
                    tvNamaLengkap.setText(dataUser.nama_lengkap);
                    tvNoTelp.setText(dataUser.no_telpon);
                    tvAlamat.setText(dataUser.alamat);
                    detailInfo.setVisibility(View.VISIBLE);
                }else
                    detailInfo.setVisibility(View.GONE);
            }else
                detailInfo.setVisibility(View.GONE);

        }
    }

    @Override
    public void doValidationError(boolean error) {
    }
}
