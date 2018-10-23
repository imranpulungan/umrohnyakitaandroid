package com.ikhwanul.ikhlas.iiwandroid.activities;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.ikhwanul.ikhlas.iiwandroid.BuildConfig;
import com.ikhwanul.ikhlas.iiwandroid.R;
import com.ikhwanul.ikhlas.iiwandroid.api.response.ApiResponse;
import com.ikhwanul.ikhlas.iiwandroid.api.response.PSCDataPerwakilanDetailResponse;
import com.ikhwanul.ikhlas.iiwandroid.core.AppActivity;
import com.ikhwanul.ikhlas.iiwandroid.entities.PSCDataPerwakilanDetail;
import com.ikhwanul.ikhlas.iiwandroid.presenters.Presenter;
import com.ikhwanul.ikhlas.iiwandroid.presenters.iPresenterResponse;
import com.squareup.picasso.Picasso;

import static com.ikhwanul.ikhlas.iiwandroid.presenters.Presenter.RES_GET_DATA_BINAAN_DETAIL;

public class PSCDetailPerwakilanActivity extends AppActivity implements iPresenterResponse {

    private ImageView img_recomendation;
    private TextView tv_id_perwakilan,tv_nik, tv_nama_lengkap, tv_tempat_lahir,
            tv_tanggal_lahir, tv_gender, tv_goldarah, tv_alamat, tv_kabupaten, tv_provinsi,
            tv_agama, tv_status_perkawinan, tv_pekerjaan, tv_no_telp, tv_email, tv_status, tv_terdaftar, tv_username,
            tv_kode_perekomendasi, tv_no_telp_perekomendasi, tv_nama_perekomendasi, tv_alamat_perekomendasi;
    ProgressDialog progressDialog;
    Presenter mPresenter;
    int id;
    public PSCDetailPerwakilanActivity() {
        super(R.layout.activity_pscdetail_perwakilan, true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Detail Perwakilan");
        id = getIntent().getIntExtra("id", 0);
        initView();
        initObject();
    }

    private void initObject() {
        mPresenter = new Presenter(this, this);
        mPresenter.getDetailPerwakilan(id, progressDialog, true);
    }

    private void initView() {
        img_recomendation = (ImageView) findViewById(R.id.img_recomendation);
        tv_kode_perekomendasi = (TextView) findViewById(R.id.tv_kode_perekomendasi);
        tv_alamat_perekomendasi = (TextView) findViewById(R.id.tv_alamat_perekomendasi);
        tv_nama_perekomendasi = (TextView) findViewById(R.id.tv_nama_perekomendasi);
        tv_no_telp_perekomendasi= (TextView) findViewById(R.id.tv_no_telp_perekomendasi);
        tv_agama = (TextView) findViewById(R.id.tv_agama);
        tv_alamat = (TextView) findViewById(R.id.tv_alamat);
        tv_email = (TextView) findViewById(R.id.tv_email);
        tv_gender = (TextView) findViewById(R.id.tv_gender);
        tv_goldarah = (TextView) findViewById(R.id.tv_goldarah);
        tv_id_perwakilan = (TextView) findViewById(R.id.tv_id_perwakilan);
        tv_kabupaten = (TextView) findViewById(R.id.tv_kabupaten);
        tv_nama_lengkap = (TextView) findViewById(R.id.tv_nama_lengkap);
        tv_nik = (TextView) findViewById(R.id.tv_nik);
        tv_no_telp = (TextView) findViewById(R.id.tv_no_telp);
        tv_pekerjaan = (TextView) findViewById(R.id.tv_pekerjaan);
        tv_provinsi= (TextView) findViewById(R.id.tv_provinsi);
        tv_status = (TextView) findViewById(R.id.tv_status);
        tv_status_perkawinan= (TextView) findViewById(R.id.tv_status_perkawinan);
        tv_tanggal_lahir = (TextView) findViewById(R.id.tv_tanggal_lahir);
        tv_tempat_lahir = (TextView) findViewById(R.id.tv_tempat_lahir);
        tv_terdaftar = (TextView) findViewById(R.id.tv_terdaftar);
        tv_username = (TextView) findViewById(R.id.tv_username);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setProgress(0);
    }

    @Override
    public void doSuccess(ApiResponse response, String tag) {
        if (tag.equals(RES_GET_DATA_BINAAN_DETAIL)){
            if (!response.error){
                PSCDataPerwakilanDetail resp = ((PSCDataPerwakilanDetailResponse) response).data;
                tv_username.setText(resp.getUsername());
                tv_terdaftar.setText(resp.getTerdaftar());
                tv_tempat_lahir.setText(resp.getTempat_lahir());
                tv_tanggal_lahir.setText(resp.getTanggal_lahir());
                tv_status_perkawinan.setText(resp.getStatus_perkawinan());
                tv_provinsi.setText(resp.getPropinsi());
                tv_pekerjaan.setText(resp.getPekerjaan());
                tv_status.setText(resp.getStatus().equals("1") ? "Aktif" : "Tidak aktif");
                tv_no_telp.setText(resp.getNo_telpon());
                tv_nik.setText(resp.getNik());
                tv_nama_lengkap.setText(resp.getNama_a());
                tv_kabupaten.setText(resp.getKabupaten());
                tv_id_perwakilan.setText(resp.getId_perwakilan());
                tv_goldarah.setText(resp.getGol_darah());
                tv_gender.setText(resp.getJenis_kelamin().equals("L") ? "Pria" : "Wanita");
                tv_email.setText(resp.getEmail());
                tv_alamat.setText(resp.getAlamat());
                tv_email.setText(resp.getEmail());
                tv_agama.setText(resp.getId_agama().equals("1") ? "Islam" : "-");

                tv_kode_perekomendasi.setText(resp.getId_perekomendasi());
                tv_nama_perekomendasi.setText(resp.getNama_lengkap());
                tv_no_telp_perekomendasi.setText(resp.getNo_telpon_b());
                tv_alamat_perekomendasi.setText(resp.getAlamat_b());

                if (!resp.getFoto_b().equals("") || resp.getFoto_b()!= null){
                    Picasso.with(this)
                            .load(BuildConfig.API_URL+"images_info/images_member/crop_mini/"+resp.getFoto_b())
                            .fit()
                            .centerCrop()
                            .placeholder(R.drawable.ic_menu_profile)
                            .into(img_recomendation);
                }
            }

        }
    }

    @Override
    public void doValidationError(boolean error) {

    }
}
