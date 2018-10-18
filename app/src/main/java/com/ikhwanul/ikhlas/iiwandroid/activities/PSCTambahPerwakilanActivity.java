package com.ikhwanul.ikhlas.iiwandroid.activities;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.ikhwanul.ikhlas.iiwandroid.R;
import com.ikhwanul.ikhlas.iiwandroid.api.response.ApiResponse;
import com.ikhwanul.ikhlas.iiwandroid.api.response.CalonPerwakilanResponse;
import com.ikhwanul.ikhlas.iiwandroid.api.response.KabupatenResponse;
import com.ikhwanul.ikhlas.iiwandroid.api.response.ProvinsiResponse;
import com.ikhwanul.ikhlas.iiwandroid.core.AppActivity;
import com.ikhwanul.ikhlas.iiwandroid.entities.Kabupaten;
import com.ikhwanul.ikhlas.iiwandroid.entities.Provinsi;
import com.ikhwanul.ikhlas.iiwandroid.fragment.HomeFragment;
import com.ikhwanul.ikhlas.iiwandroid.presenters.DataFormPresenter;
import com.ikhwanul.ikhlas.iiwandroid.presenters.Presenter;
import com.ikhwanul.ikhlas.iiwandroid.presenters.iPresenterResponse;
import com.ikhwanul.ikhlas.iiwandroid.ui.DatePickerFragment;
import com.ikhwanul.ikhlas.iiwandroid.ui.ImagePickerFragment;
import com.ikhwanul.ikhlas.iiwandroid.utils.DateUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class PSCTambahPerwakilanActivity extends AppActivity implements iPresenterResponse {

    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";


    List<String> gender = new ArrayList<String>();
    List<String> golDarah = new ArrayList<String>();
    List<Provinsi> dataProvinsi = new ArrayList<Provinsi>();
    List<String> provinsi = new ArrayList<String>();
    List<String> kabupaten = new ArrayList<String>();
    List<Kabupaten> dataKabupaten = new ArrayList<Kabupaten>();
    List<String> religion = new ArrayList<String>();
    List<String> statusPerkawinan = new ArrayList<String>();
    List<String> kewarganegaraan = new ArrayList<String>();

    private LinearLayout layoutPerekomendasi, layoutNIK, layoutTempatLahir, layoutTanggalLahir,
                            layoutGender, layoutGoldarah, layoutReligion, layoutPerkawinan, layoutNasionality;

    private Button mBtnSave;
    private Spinner mSpinnerNasionality;
    private Spinner mSpinnerGoldarah;
    private Spinner mSpinnerGender;
    private Spinner mSpinnerReligion;
    private Spinner mSpinnerPerkawinan;
    private Spinner mSpinnerProvinsi;
    private Spinner mSpinnerKabupaten;
    private EditText mEditPerekomendasi, mEditTglBerangkat, mEditAkhirKontrak,
                        mEditNamaLengkap, mEditTempatLahir, mEditTanggalLahir,
                        mEditNIK, mEditAlamat, mEditNoHp, mEditEmail,
                        mEditPekerjaan, mEditJabatan, mEditTerdaftar, mEditUsername;

    DataFormPresenter mPresenter;

    int id_perwakilan;
    public PSCTambahPerwakilanActivity() {
        super(R.layout.activity_psctambah_perwakilan, true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Tambah Perwakilan");
        id_perwakilan = getIntent().getIntExtra("id", 0);
        initView();
        initEvent();
        initObject();
    }

    private void initView() {
        layoutGender = (LinearLayout) findViewById(R.id.layout_gender);
        layoutGoldarah = (LinearLayout) findViewById(R.id.layout_golongan_darah);
        layoutNasionality = (LinearLayout) findViewById(R.id.layout_nasionality);
        layoutNIK = (LinearLayout) findViewById(R.id.layout_nik);
        layoutPerekomendasi = (LinearLayout) findViewById(R.id.layout_perekomendasi);
        layoutPerkawinan= (LinearLayout) findViewById(R.id.layout_status_perkawinan);
        layoutReligion = (LinearLayout) findViewById(R.id.layout_religion);
        layoutTanggalLahir = (LinearLayout) findViewById(R.id.layout_tanggal_lahir);
        layoutTempatLahir = (LinearLayout) findViewById(R.id.layout_tempat_lahir);

        mSpinnerGender = (Spinner) findViewById(R.id.spinner_gender);
        mSpinnerGoldarah = (Spinner) findViewById(R.id.spinner_goldarah);
        mSpinnerReligion = (Spinner) findViewById(R.id.spinner_religion);
        mSpinnerPerkawinan = (Spinner) findViewById(R.id.status_perkawinan) ;
        mSpinnerProvinsi = (Spinner) findViewById(R.id.spinner_provinsi);
        mSpinnerKabupaten = (Spinner) findViewById(R.id.spinner_kabupaten);
        mSpinnerNasionality= (Spinner) findViewById(R.id.spinner_nasionality);

        mEditUsername = (EditText) findViewById(R.id.edit_username);
        mEditPerekomendasi = (EditText) findViewById(R.id.edit_perekomendasi);
        mEditNoHp = (EditText) findViewById(R.id.edit_phone);
        mEditNamaLengkap = (EditText) findViewById(R.id.edit_nama_lengkap);
        mEditTempatLahir = (EditText) findViewById(R.id.edit_tempat_lahir);
        mEditTanggalLahir = (EditText) findViewById(R.id.edit_tanggal_lahir);
        mEditJabatan = (EditText) findViewById(R.id.edit_jabatan);
        mEditNIK = (EditText) findViewById(R.id.edit_nik);
        mEditAlamat = (EditText) findViewById(R.id.edit_alamat);
        mEditNoHp = (EditText) findViewById(R.id.edit_phone);
        mEditEmail = (EditText) findViewById(R.id.edit_email);
        mEditPekerjaan = (EditText) findViewById(R.id.edit_pekerjaan);
        mEditJabatan = (EditText) findViewById(R.id.edit_jabatan);
        mEditAkhirKontrak= (EditText) findViewById(R.id.edit_akhir_kontrak);
        mEditTerdaftar = (EditText) findViewById(R.id.edit_terdaftar);
        mBtnSave = (Button) findViewById(R.id.btn_save);
        gender.add("Pria");
        gender.add("Wanita");
        chooseWithSpinner(gender, mSpinnerGender);

        golDarah.add("A");
        golDarah.add("B");
        golDarah.add("AB");
        golDarah.add("O");
        chooseWithSpinner(golDarah, mSpinnerGoldarah);

        kewarganegaraan.add("WNI");
        kewarganegaraan.add("WNA");
        chooseWithSpinner(kewarganegaraan, mSpinnerNasionality);

        religion.add("Islam");
        chooseWithSpinner(religion, mSpinnerReligion);

        statusPerkawinan.add("Kawin");
        statusPerkawinan.add("Belum Kawin");
        statusPerkawinan.add("Janda/Duda");
        chooseWithSpinner(statusPerkawinan, mSpinnerPerkawinan);

    }

    public void chooseWithSpinner(List<String> option, Spinner elemSpiner){
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, option);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        elemSpiner.setAdapter(dataAdapter);
    }

    public RequestBody toRequestBody(String s){
        return RequestBody.create(MediaType.parse("text/plain"), s);
    }

    private void initEvent() {
//        cbIsReady.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (cbIsReady.isChecked()){
//                    mBtnSave.setEnabled(true);
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//                        mBtnSave.setBackground(getResources().getDrawable(R.drawable.background_with_radius2));
//                    }
//                }else{
//                    mBtnSave.setEnabled(false);
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//                        mBtnSave.setBackground(getResources().getDrawable(R.drawable.background_with_radius1));
//                    }
//                }
//            }
//        });
        mEditTanggalLahir.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(MotionEvent.ACTION_UP == event.getAction()) {
                    Calendar calendar = Calendar.getInstance();
                    if (!TextUtils.isEmpty(mEditTanggalLahir.getText().toString())){
                        calendar.setTime(DateUtils.parse(mEditTanggalLahir.getText().toString(), DateUtils.DEFAULT_OUT_FORMAT));
                    }
                    DialogFragment dialogFragment = DatePickerFragment.newInstance(getSupportFragmentManager(),
                            calendar, new DatePickerFragment.OnCompleteDatePickerLister() {
                                @Override
                                public void onComplete(String date) {
                                    mEditTanggalLahir.setText(date);
                                    Log.d("Date :", date);
                                }
                            },0, Calendar.getInstance().getTimeInMillis());
                }
                return false;
            }
        });

        mBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, RequestBody> dataAdd = new HashMap<>();
//                dataAdd.put("id_perwakilan", );

//                dataAdd.put("kode", toRequestBody(String.valueOf()));
                dataAdd.put("nik", toRequestBody(String.valueOf(mEditNIK.getText())));
                dataAdd.put("nama_lengkap", toRequestBody(String.valueOf(mEditNamaLengkap.getText())));
                dataAdd.put("tempat_lahir", toRequestBody(String.valueOf(mEditTempatLahir.getText())));
                dataAdd.put("tanggal_lahir", toRequestBody(String.valueOf(mEditTanggalLahir.getText())));
                dataAdd.put("jenis_kelamin", toRequestBody(String.valueOf(mSpinnerGender.getSelectedItem())));
                dataAdd.put("gol_darah", toRequestBody(String.valueOf(mSpinnerGoldarah.getSelectedItem())));
                dataAdd.put("alamat", toRequestBody(String.valueOf(mEditAlamat.getText())));
                dataAdd.put("kabupaten", toRequestBody(String.valueOf(mSpinnerKabupaten.getSelectedItem())));
                dataAdd.put("agama", toRequestBody(String.valueOf(mSpinnerReligion.getSelectedItem())));
                dataAdd.put("status_perkawinan", toRequestBody(String.valueOf(mSpinnerPerkawinan.getSelectedItem())));
                dataAdd.put("pekerjaan", toRequestBody(String.valueOf(mEditPekerjaan.getText())));
                dataAdd.put("kewarganegaraan", toRequestBody(String.valueOf(mSpinnerNasionality.getSelectedItem())));
                dataAdd.put("no_telpon", toRequestBody(String.valueOf(mEditNoHp.getText())));
                dataAdd.put("email", toRequestBody(String.valueOf(mEditEmail.getText())));
                dataAdd.put("perekomendasi", toRequestBody(String.valueOf(mEditPerekomendasi.getText())));
                dataAdd.put("id_perwakilan", toRequestBody(String.valueOf(id_perwakilan)));
                dataAdd.put("terdaftar", toRequestBody(String.valueOf(mEditTerdaftar.getText())));
                dataAdd.put("username", toRequestBody(String.valueOf(mEditUsername.getText())));
                dataAdd.put("status", toRequestBody(String.valueOf(1)));
                dataAdd.put("jabatan", toRequestBody(String.valueOf(mEditJabatan.getText())));

                mPresenter.addNewPerwakilan(dataAdd);
                Log.d("POST", dataAdd.toString());
                Toast.makeText(PSCTambahPerwakilanActivity.this, "CLICKED", Toast.LENGTH_SHORT).show();
            }
        });

        mSpinnerProvinsi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int index = parent.getSelectedItemPosition();
                mPresenter.getKabupaten(Integer.valueOf(dataProvinsi.get(index).id_propinsi));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        mSpinnerKabupaten.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int index = parent.getSelectedItemPosition();
//                Toast.makeText(PSCTambahPerwakilanActivity.this, dataKabupaten.get(index).kabupaten, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initObject() {
        mPresenter = new DataFormPresenter(PSCTambahPerwakilanActivity.this, this);
        mPresenter.getProvinsi();
        mPresenter.generateCalonPerwakilan();
    }

    @Override
    public void doSuccess(ApiResponse response, String tag) {
        if (tag.equals(Presenter.RES_GET_DATA_PROVINSI)){
            dataProvinsi = ((ProvinsiResponse) response).provinsi;
            if (dataProvinsi.size() > 0){
                for(int i=0; i< dataProvinsi.size(); i++){
                    provinsi.add(dataProvinsi.get(i).propinsi);
                }
                chooseWithSpinner(provinsi, mSpinnerProvinsi);
            }
        }else if(tag.equals(Presenter.RES_GET_DATA_KABUPATEN)){
            dataKabupaten = ((KabupatenResponse) response).kabupaten;
            if (dataKabupaten.size() > 0){
                kabupaten.clear();
                for(int i=0; i< dataKabupaten.size(); i++){
                    kabupaten.add(dataKabupaten.get(i).kabupaten);
                }
                chooseWithSpinner(kabupaten, mSpinnerKabupaten);
            }
        }else if(tag.equals(Presenter.RES_GET_GENERATE_PERWAKILAN)){
            if (!response.error){
                CalonPerwakilanResponse.DataCalon resp = ((CalonPerwakilanResponse) response).data;
                mEditUsername.setText(resp.username);
                mEditJabatan.setText(resp.jabatan);
                mEditAkhirKontrak.setText(resp.kontrak);
                mEditTerdaftar.setText(resp.today);
                if ((resp.perigkat <= 5 && resp.perigkat < 6)){
                    layoutPerekomendasi.setVisibility(View.GONE);
                    layoutNIK.setVisibility(View.GONE);
                    layoutTempatLahir.setVisibility(View.GONE);
                    layoutTanggalLahir.setVisibility(View.GONE);
                    layoutGender.setVisibility(View.GONE);
                    layoutGoldarah.setVisibility(View.GONE);
                    layoutReligion.setVisibility(View.GONE);
                    layoutPerkawinan.setVisibility(View.GONE);
                    layoutNasionality.setVisibility(View.GONE);
                }
            }
        }else if(tag.equals(Presenter.ADD_NEW_PERWAKILAN)){

        }

    }

    @Override
    public void doValidationError(boolean error) {
        Toast.makeText(this, R.string.network_error, Toast.LENGTH_SHORT).show();
    }
}
