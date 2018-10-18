package com.ikhwanul.ikhlas.iiwandroid.activities;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.ikhwanul.ikhlas.iiwandroid.R;
import com.ikhwanul.ikhlas.iiwandroid.api.response.ApiResponse;
import com.ikhwanul.ikhlas.iiwandroid.core.AppActivity;
import com.ikhwanul.ikhlas.iiwandroid.entities.Jamaah;
import com.ikhwanul.ikhlas.iiwandroid.presenters.DataFormPresenter;
import com.ikhwanul.ikhlas.iiwandroid.presenters.Presenter;
import com.ikhwanul.ikhlas.iiwandroid.presenters.iPresenterResponse;
import com.ikhwanul.ikhlas.iiwandroid.ui.ImagePickerFragment;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

import static com.ikhwanul.ikhlas.iiwandroid.activities.RegistrationRegulerActivity.randomAlphaNumeric;

public class DetailJamaahActivity extends AppActivity implements iPresenterResponse {

    private Jamaah dataJamaah;
    private EditText mEditGrupKeberangkatan;
    private EditText mEditJenisPaket;
    private EditText mEditTglKeberangkatan;
    private EditText mEditNoKwitansi;
    private EditText mEditHargaPaket;
    private EditText mEditNominalDp;
    private EditText mEditNominalJamaahPay;
    private EditText mEditStatusKwitansi;
    private EditText mEditNamaLengkap;
    private EditText mEditNamaAyah;

    private ImageView imgIdentity, imgPassFoto;
    private Button mBtnSave;
    Uri imageItem, imageItemFoto;

    private DataFormPresenter mPresenter;

    public DetailJamaahActivity() {
        super(R.layout.activity_detail_jamaah, true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Detail Jamaah");

        dataJamaah = (Jamaah) getIntent().getSerializableExtra("data");
        initView();
        initObject();
        attachData();
        initEvent();
    }

    private void initObject() {
        mPresenter = new DataFormPresenter(this, this);
    }

    public RequestBody toRequestBody(String s){
        return RequestBody.create(MediaType.parse("text/plain"), s);
    }

    private void initEvent() {
        mBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, RequestBody> dataAdd = new HashMap<>();
                dataAdd.put("id_jamaah", toRequestBody(String.valueOf(dataJamaah.getId_pendaftaraan())));
                dataAdd.put("nama_jamaah", toRequestBody(String.valueOf(mEditNamaLengkap.getText())));

                if (null != imageItem) {
                    String generatedString = randomAlphaNumeric(10);
                    File _file = new File(imageItem.getPath());
                    RequestBody _requestbody = RequestBody.create(MediaType.parse("image/png"), _file);
                    dataAdd.put("foto_ktp\"; filename=\""+generatedString+".jpg\"", _requestbody);
                }

                if(null != imageItemFoto) {
                    String generatedString = randomAlphaNumeric(10);
                    File _file = new File(imageItemFoto.getPath());
                    RequestBody _requestbody = RequestBody.create(MediaType.parse("image/png"), _file);
                    dataAdd.put("foto_pass\"; filename=\""+generatedString+".jpg\"", _requestbody);
                }

                mPresenter.updateJamaah(dataAdd);
            }
        });

        imgPassFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePickerFragment.newInstance(new ImagePickerFragment.OnFinishedListener() {
                    @Override
                    public void onFinished(int requestCode, int resultCode, Uri uri) {
                        imageItemFoto = uri;
                        imgPassFoto.setImageURI(imageItemFoto);
                    }
                }, 1).show(getSupportFragmentManager(), "image_picker");
            }
        });

        imgIdentity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePickerFragment.newInstance(new ImagePickerFragment.OnFinishedListener() {
                    @Override
                    public void onFinished(int requestCode, int resultCode, Uri uri) {
                        imageItem = uri;
                        imgIdentity.setImageURI(imageItem);
                    }
                }, 1).show(getSupportFragmentManager(), "image_picker");
            }
        });
    }

    private void attachData() {
        mEditGrupKeberangkatan.setText(dataJamaah.getKode_grup() + "(Kode Grup)");
        mEditJenisPaket.setText(dataJamaah.getKode_paket() + "(Kode Paket)");
        mEditGrupKeberangkatan.setText(dataJamaah.getKode_grup() + "(Kode Grup)");
        mEditGrupKeberangkatan.setText(dataJamaah.getKode_grup() + "(Kode Grup)");
        mEditTglKeberangkatan.setText(dataJamaah.getTgl_berangkat());
        mEditNominalJamaahPay.setText(dataJamaah.getDibayar());
        mEditNamaLengkap.setText(dataJamaah.getNama_jamaah());
        mEditNamaAyah.setText(dataJamaah.getNama_ayah());
        mEditNoKwitansi.setText(dataJamaah.getNo_kwitansi());
        mEditHargaPaket.setText(dataJamaah.getHarga());
    }

    private void initView() {
        mEditGrupKeberangkatan  = (EditText) findViewById(R.id.edit_grup_keberangkatan);
        mEditJenisPaket         = (EditText) findViewById(R.id.edit_jenis_paket);
        mEditTglKeberangkatan   = (EditText) findViewById(R.id.edit_tgl_keberangkatan);
        mEditHargaPaket         = (EditText) findViewById(R.id.edit_harga_paket);
        mEditNominalDp          = (EditText) findViewById(R.id.edit_nominal_dp);
        mEditNominalJamaahPay   = (EditText) findViewById(R.id.edit_nominal_jamaah_pay);
        mEditNoKwitansi         = (EditText) findViewById(R.id.edit_no_kwitansi);
        mEditStatusKwitansi     = (EditText) findViewById(R.id.edit_status_kwitansi);
        mEditNamaLengkap        = (EditText) findViewById(R.id.edit_nama_lengkap);
        mEditNamaAyah           = (EditText) findViewById(R.id.edit_nama_ayah);
        imgIdentity = (ImageView) findViewById(R.id.img_identity);
        imgPassFoto = (ImageView) findViewById(R.id.img_pass_foto);
        mBtnSave = (Button) findViewById(R.id.btn_save);
    }

    @Override
    public void doSuccess(ApiResponse response, String tag) {
        if (tag.equals(Presenter.UPDATE_NEW_JAMAAH)){
            if (!response.error){
                Toast.makeText(this, "SUCCESS", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, response.message, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void doValidationError(boolean error) {

    }
}
