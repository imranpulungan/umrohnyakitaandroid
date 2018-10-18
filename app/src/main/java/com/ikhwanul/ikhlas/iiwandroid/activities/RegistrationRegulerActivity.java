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

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class RegistrationRegulerActivity extends AppActivity implements iPresenterResponse {

    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";


    List<String> grupGo = new ArrayList<String>();
    List<String> nominalDP = new ArrayList<String>();
    List<String> jenisPaket = new ArrayList<String>();
    List<String> souvenir = new ArrayList<String>();
    List<String> provinsi = new ArrayList<String>();
    List<Provinsi> dataProvinsi = new ArrayList<Provinsi>();
    List<String> kabupaten = new ArrayList<String>();
    List<Kabupaten> dataKabupaten = new ArrayList<Kabupaten>();

    private CheckBox cbIsReady;
    private Spinner mSpinnerGrupKeberangkaran;
    private Spinner mSpinnerJenisPaket;
    private Spinner mSpinnerNominalDP;
    private Spinner mSpinnerSouvenir;
    private Spinner mSpinnerProvinsi;
    private Spinner mSpinnerKabupaten;
    private EditText mEditHargaPaket, mEditTglBerangkat, mEditNoKwitansi, mEditNominalJamaahPay,
                        mEditNamaLengkap, mEditNamaAyah, mEditTempatLahir, mEditTanggalLahir,
                        mEditNoPaspor, mEditTempatPasporDikeluarkan, mEditNIK, mEditAlamat,
                        mEditNoHp, mEditEmail, mEditPekerjaan, mEditPendidikanTerakhir,
                        mEditSizeSouvenir, mEditAhliWaris, mEditNorek, mEditAtasNama,
                        mEditNamaLengkapKerabat, mEditNikKerabat, mEditNoHpKerabat, mEditHubungan,
                        mEditAlamatKerabat, mEditKeteranganKerabat;

    private ImageView imgIdentity, imgPassFoto;
    private Button mBtnSave;
    Uri imageItem, imageItemFoto;
    DataFormPresenter mPresenter;

    String id_perwakilan, type_registration;

    public RegistrationRegulerActivity() {
        super(R.layout.activity_registration_reguler, true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id_perwakilan = getIntent().getStringExtra("id_perwakilan");
        type_registration = getIntent().getStringExtra("type_registration");
        initObject();
        initView();
        initEvent();
    }

    private void initView() {
        cbIsReady = (CheckBox) findViewById(R.id.cb_isready);
        imgIdentity = (ImageView) findViewById(R.id.img_identity);
        imgPassFoto = (ImageView) findViewById(R.id.img_pass_foto);
        mSpinnerGrupKeberangkaran = (Spinner) findViewById(R.id.spinner_grup_keberangkaran);
        mSpinnerJenisPaket = (Spinner) findViewById(R.id.spinner_paket);
        mSpinnerNominalDP = (Spinner) findViewById(R.id.spinner_nominal_dp);
        mSpinnerSouvenir = (Spinner) findViewById(R.id.spinner_souvenir) ;
        mSpinnerProvinsi = (Spinner) findViewById(R.id.spinner_provinsi);
        mSpinnerKabupaten = (Spinner) findViewById(R.id.spinner_kabupaten);
        mEditHargaPaket = (EditText) findViewById(R.id.edit_harga_paket);
        mEditTglBerangkat = (EditText) findViewById(R.id.edit_tgl_keberangkatan);
        mEditNoKwitansi = (EditText) findViewById(R.id.edit_no_kwitansi);
        mEditNominalJamaahPay = (EditText) findViewById(R.id.edit_nominal_jamaah_pay);
        mEditNamaLengkap = (EditText) findViewById(R.id.edit_nama_lengkap);
        mEditNamaAyah = (EditText) findViewById(R.id.edit_nama_ayah);
        mEditTempatLahir = (EditText) findViewById(R.id.edit_tempat_lahir);
        mEditTanggalLahir = (EditText) findViewById(R.id.edit_tanggal_lahir);
        mEditNoPaspor = (EditText) findViewById(R.id.edit_no_paspor);
        mEditTempatPasporDikeluarkan = (EditText) findViewById(R.id.edit_tempat_paspor_dikeluarkan);
        mEditNIK = (EditText) findViewById(R.id.edit_nik);
        mEditAlamat = (EditText) findViewById(R.id.edit_alamat);
        mEditNoHp = (EditText) findViewById(R.id.edit_no_hp);
        mEditEmail = (EditText) findViewById(R.id.edit_email);
        mEditPekerjaan = (EditText) findViewById(R.id.edit_pekerjaan);
        mEditPendidikanTerakhir = (EditText) findViewById(R.id.edit_pendidikan_terakhir);
        mEditSizeSouvenir = (EditText) findViewById(R.id.edit_size_souvenir);
        mEditAhliWaris = (EditText) findViewById(R.id.edit_ahli_waris);
        mEditNorek = (EditText) findViewById(R.id.edit_norek);
        mEditAtasNama = (EditText) findViewById(R.id.edit_atas_nama);
        mEditNamaLengkapKerabat = (EditText) findViewById(R.id.edit_nama_lengkap_kerabat);
        mEditNikKerabat = (EditText) findViewById(R.id.edit_nik_kerabat);
        mEditNoHpKerabat = (EditText) findViewById(R.id.edit_no_hp_kerabat);
        mEditHubungan = (EditText) findViewById(R.id.edit_hubungan);
        mEditAlamatKerabat = (EditText) findViewById(R.id.edit_alamat_kerabat);
        mEditKeteranganKerabat = (EditText) findViewById(R.id.edit_keterangan_kerabat);
        mBtnSave = (Button) findViewById(R.id.btn_save);


        grupGo.add("Umroh Reguler");
        grupGo.add("Umroh Plus");
        chooseWithSpinner(grupGo, mSpinnerGrupKeberangkaran);

        jenisPaket.add("Paket Umroh Standar");
        jenisPaket.add("Paket Umroh Executive");
        jenisPaket.add("Biaya Progressive Visa");
        chooseWithSpinner(jenisPaket, mSpinnerJenisPaket);


        nominalDP.add("1500000");
        nominalDP.add("2500000");
        chooseWithSpinner(nominalDP, mSpinnerNominalDP);

        souvenir.add("1500000");
        souvenir.add("2500000");
        chooseWithSpinner(souvenir, mSpinnerSouvenir);

        if (type_registration.equals(HomeFragment.TAG_FREE)){
            mEditNominalJamaahPay.setText("0");
            mEditNominalJamaahPay.setEnabled(false);
        }
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
        cbIsReady.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbIsReady.isChecked()){
                    mBtnSave.setEnabled(true);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        mBtnSave.setBackground(getResources().getDrawable(R.drawable.background_with_radius2));
                    }
                }else{
                    mBtnSave.setEnabled(false);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        mBtnSave.setBackground(getResources().getDrawable(R.drawable.background_with_radius1));
                    }
                }
            }
        });
        mEditTglBerangkat.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(MotionEvent.ACTION_UP == event.getAction()) {
                    Calendar calendar = Calendar.getInstance();
                    if (!TextUtils.isEmpty(mEditTglBerangkat.getText().toString())){
                        calendar.setTime(DateUtils.parse(mEditTglBerangkat.getText().toString(), DateUtils.DEFAULT_OUT_FORMAT));
                    }
                    DialogFragment dialogFragment = DatePickerFragment.newInstance(getSupportFragmentManager(),
                            calendar, new DatePickerFragment.OnCompleteDatePickerLister() {
                                @Override
                                public void onComplete(String date) {
                                    mEditTglBerangkat.setText(date);
                                    Log.d("Date :", date);
                                }
                            },Calendar.getInstance().getTimeInMillis(), 0);
                }
                return false;
            }
        });

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
                dataAdd.put("id_perwakilan", toRequestBody(id_perwakilan));
                dataAdd.put("grup", toRequestBody(String.valueOf(mSpinnerGrupKeberangkaran.getSelectedItem())));

                dataAdd.put("paket", toRequestBody(String.valueOf(mSpinnerJenisPaket.getSelectedItem())));
                dataAdd.put("dp", toRequestBody(String.valueOf(mSpinnerNominalDP.getSelectedItem())));
                dataAdd.put("harga", toRequestBody(mEditHargaPaket.getText().toString()));
                dataAdd.put("berangkat", toRequestBody(mEditTglBerangkat.getText().toString()));
                dataAdd.put("no_kwitansi", toRequestBody(mEditNoKwitansi.getText().toString()));
                dataAdd.put("dibayar", toRequestBody(mEditNominalJamaahPay.getText().toString()));
                dataAdd.put("nama", toRequestBody(mEditNamaLengkap.getText().toString()));
                dataAdd.put("nama_ayah", toRequestBody(mEditNamaAyah.getText().toString()));
                dataAdd.put("tempat_lahir", toRequestBody(mEditTempatLahir.getText().toString()));
                dataAdd.put("tgl_lahir", toRequestBody(mEditTanggalLahir.getText().toString()));
                dataAdd.put("no_paspor", toRequestBody(mEditNoPaspor.getText().toString()));
                dataAdd.put("tempat_keluar_paspor", toRequestBody(mEditTempatPasporDikeluarkan.getText().toString()));
                dataAdd.put("no_identitas", toRequestBody(mEditNIK.getText().toString()));
                dataAdd.put("alamat", toRequestBody(mEditAlamat.getText().toString()));
                dataAdd.put("kabupaten", toRequestBody(String.valueOf(dataKabupaten.get(mSpinnerKabupaten.getSelectedItemPosition()).id_kabupaten)));
                dataAdd.put("no_telpon", toRequestBody(mEditNoHp.getText().toString()));
                dataAdd.put("email", toRequestBody(mEditEmail.getText().toString()));

                dataAdd.put("pekerjaan", toRequestBody(mEditPekerjaan.getText().toString()));
                dataAdd.put("pendidikan_terakhir", toRequestBody(mEditPendidikanTerakhir.getText().toString()));
                dataAdd.put("souvenir", toRequestBody(String.valueOf(mSpinnerSouvenir.getSelectedItem())));
                dataAdd.put("size", toRequestBody(mEditSizeSouvenir.getText().toString()));
                dataAdd.put("ahli_waris", toRequestBody(mEditAhliWaris.getText().toString()));
                dataAdd.put("no_rek", toRequestBody(mEditNorek.getText().toString()));
                dataAdd.put("atas_nama", toRequestBody(mEditAtasNama.getText().toString()));
                dataAdd.put("nama_kerabat", toRequestBody(mEditNamaLengkapKerabat.getText().toString()));
                dataAdd.put("no_identitas_kerabat", toRequestBody(mEditNikKerabat.getText().toString()));
                dataAdd.put("no_telpon_kerabat", toRequestBody(mEditNoHpKerabat.getText().toString()));
                dataAdd.put("hubungan_kerabat", toRequestBody(mEditHubungan.getText().toString()));
                dataAdd.put("alamat_kerabat", toRequestBody(mEditAlamatKerabat.getText().toString()));
                dataAdd.put("keterangan", toRequestBody(mEditKeteranganKerabat.getText().toString()));

//
//                if (null != imageItem) {
//                    String generatedString = randomAlphaNumeric(10);
//                    File _file = new File(imageItem.getPath());
//                    RequestBody _requestbody = RequestBody.create(MediaType.parse("image/png"), _file);
//                    dataAdd.put("foto_ktp\"; filename=\""+generatedString+".jpg\"", _requestbody);
//                }
//
//                if(null != imageItemFoto) {
//                    String generatedString = randomAlphaNumeric(10);
//                    File _file = new File(imageItemFoto.getPath());
//                    RequestBody _requestbody = RequestBody.create(MediaType.parse("image/png"), _file);
//                    dataAdd.put("foto_pass\"; filename=\""+generatedString+".jpg\"", _requestbody);
//                }

//                mPresenter.addNewJamaah(dataAdd);
                Log.d("POST", dataAdd.toString());
                Toast.makeText(RegistrationRegulerActivity.this, "CLICKED", Toast.LENGTH_SHORT).show();
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
//                Toast.makeText(RegistrationRegulerActivity.this, dataKabupaten.get(index).kabupaten, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public static String randomAlphaNumeric(int count) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }

    private void initObject() {
        mPresenter = new DataFormPresenter(RegistrationRegulerActivity.this, this);
        mPresenter.getProvinsi();
        if (type_registration.equals(HomeFragment.TAG_REGULER)){
            setTitle("Kwitansi Reguler");
            mPresenter.getKwitansiForJamaah(id_perwakilan);
        }
        else if(type_registration.equals(HomeFragment.TAG_FREE)){
            setTitle("Kwitansi Free");
            mPresenter.getKwitansiForJamaahFree(id_perwakilan);
        }
        else if(type_registration.equals(HomeFragment.TAG_FREE_REGULER)){
            setTitle("Kwitansi Lama");
            mPresenter.getKwitansiForJamaahFreeReguler(id_perwakilan);
        }
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
        }else if(tag.equals(Presenter.ADD_NEW_JAMAAH)){
            Toast.makeText(this, response.message, Toast.LENGTH_SHORT).show();
        }else if(tag.equals(Presenter.RES_GET_DATA_KWITANSI_JAMAAH)){
            if (!response.error){
                mEditNoKwitansi.setText(response.message);
            }else{
                Toast.makeText(this, response.message, Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        }

    }

    @Override
    public void doValidationError(boolean error) {
        Toast.makeText(this, R.string.network_error, Toast.LENGTH_SHORT).show();
    }
}
