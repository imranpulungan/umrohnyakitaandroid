package com.ikhwanul.ikhlas.iiwandroid.fragment;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ikhwanul.ikhlas.iiwandroid.BuildConfig;
import com.ikhwanul.ikhlas.iiwandroid.R;
import com.ikhwanul.ikhlas.iiwandroid.activities.RegistrationRegulerActivity;
import com.ikhwanul.ikhlas.iiwandroid.adapter.HomeInfoAdapter;
import com.ikhwanul.ikhlas.iiwandroid.adapter.JamaahAdapter;
import com.ikhwanul.ikhlas.iiwandroid.adapterpsc.PSCDataPenjualanKwitansiAdapter;
import com.ikhwanul.ikhlas.iiwandroid.api.response.ApiResponse;
import com.ikhwanul.ikhlas.iiwandroid.api.response.BannerResponse;
import com.ikhwanul.ikhlas.iiwandroid.api.response.DollarResponse;
import com.ikhwanul.ikhlas.iiwandroid.api.response.JamaahResponse;
import com.ikhwanul.ikhlas.iiwandroid.api.response.KDMBelanjaResponse;
import com.ikhwanul.ikhlas.iiwandroid.api.response.PSCDataJualKwitansiResponse;
import com.ikhwanul.ikhlas.iiwandroid.core.AppFragment;
import com.ikhwanul.ikhlas.iiwandroid.entities.Banner;
import com.ikhwanul.ikhlas.iiwandroid.entities.Dollar;
import com.ikhwanul.ikhlas.iiwandroid.entities.Jamaah;
import com.ikhwanul.ikhlas.iiwandroid.entities.KDMBelanja;
import com.ikhwanul.ikhlas.iiwandroid.entities.PSCDataJualKwitansi;
import com.ikhwanul.ikhlas.iiwandroid.entities.User;
import com.ikhwanul.ikhlas.iiwandroid.presenters.Presenter;
import com.ikhwanul.ikhlas.iiwandroid.presenters.iPresenterResponse;
import com.ikhwanul.ikhlas.iiwandroid.utils.FormatRupiah;
import com.squareup.picasso.Picasso;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import static com.ikhwanul.ikhlas.iiwandroid.presenters.Presenter.RES_GET_DATA_JAMAAH;

public class HomeFragment extends AppFragment implements iPresenterResponse{

    public static final String TAG_FREE = "free_registration";
    public static final String TAG_REGULER = "reguler_registration";
    public static final String TAG_FREE_REGULER = "free_reguler_registration";
    CarouselView carouselView;

    private LinearLayout layoutKwitansiReguler, layoutLwitansiFree, layoutKwitansiOld;
    private User dataUser;
    private TextView tvNama, tvIdPerwakilan, tvKursDollar, tvJabatan, tvTitleList;
    private RecyclerView rvInfo;
    private List<PSCDataJualKwitansi> dataPerwakilanPSC;
    private List<Jamaah> dataJamaah;
    private List<Banner> dataBanner;
    protected LinearLayout layoutBottomSheet, layoutInfoPSC, layoutInfoPerwakilan, layoutInsufficent;
    ProgressDialog progressDialog;

    RecyclerView rvDataPerwakilan;

    Presenter mPresenter;

    HomeInfoAdapter mAdapter;
    JamaahAdapter mAdapterJamaah;

    PSCDataPenjualanKwitansiAdapter pscDataPenjualanKwitansiAdapter;

    int[] sampleImages = {R.drawable.carousel_image1, R.drawable.carousel_image2, R.drawable.background_with_radius_border2};


    private OnFragmentInteractionListener mListener;

    public HomeFragment() {
        super();
        this.res_layout = R.layout.fragment_home;
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        if (getArguments() != null) {
            dataUser = (User) getArguments().getSerializable("send");
        }
        initView();
        initObject();
        initEvent();
//        attachData();


        return view;
    }

    private void initObject() {
        mPresenter = new Presenter(getContext(), this);
        mPresenter.getKursDollar();
        if (dataUser.tipe == 1){
            mPresenter.getInfoPSC(dataUser.id_perwakilan, dataUser.peringkat);
        }else{
            mPresenter.getKdmBelanja(dataUser.id_perwakilan, dataUser.peringkat);
        }
        mPresenter.getSlide();
    }

    private void attachData() {
        tvNama.setText(dataUser.nama_lengkap);
        tvIdPerwakilan.setText(dataUser.kode);
        if (dataUser.tipe == 0){
            if (dataUser.peringkat.equals("1")){
                tvJabatan.setText(R.string.jabatan_perwakilan);
            }else if(dataUser.peringkat.equals("2")){
                tvJabatan.setText(R.string.jabatan_perwakilan_mm);
            }else if (dataUser.peringkat.equals("3")){
                tvJabatan.setText(R.string.jabatan_perwakilan_kdm);
            }
            layoutInfoPerwakilan.setVisibility(View.VISIBLE);
            layoutInfoPSC.setVisibility(View.VISIBLE);
            mPresenter.getJamaah(dataUser.id_perwakilan, progressDialog, false, 0);
            tvTitleList.setText("Data Jamaah Terakhir");
        }else{
            tvJabatan.setText(R.string.channel_psc);
            layoutInfoPerwakilan.setVisibility(View.GONE);
            layoutInfoPSC.setVisibility(View.VISIBLE);
            mPresenter.getPenjualanKwitansi(Integer.valueOf(dataUser.id_perwakilan), progressDialog, false, 0);
            tvTitleList.setText("Data Penjualan Terakhir");
        }

        ImageListener imageListener = new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                if (dataBanner.get(position).getGambar() != null){
                    Log.d("IMG", BuildConfig.API_URL+"images_info/images_slide/"+ dataBanner.get(position).getGambar());
                    Picasso.with(getContext())
                            .load(BuildConfig.API_URL+"images_info/images_slide/"+ dataBanner.get(position).getGambar())
                            .into(imageView);
                }
            }
        };

        carouselView.setImageListener(imageListener);
        carouselView.setPageCount(dataBanner.size());
    }

    protected void initView(){
        rvInfo = (RecyclerView) findViewById(R.id.rv_info);
        LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);

        tvTitleList = (TextView) findViewById(R.id.tv_title_list);
        layoutInsufficent = (LinearLayout) findViewById(R.id.insufficient);
        rvInfo.setLayoutManager(horizontalLayoutManagaer);
        tvJabatan = (TextView) findViewById(R.id.tv_jabatan);
        tvKursDollar = (TextView) findViewById(R.id.tv_kurs_dollar);
        tvIdPerwakilan = (TextView) findViewById(R.id.tv_id_perwakilan);
        tvNama = (TextView) findViewById(R.id.tv_nama_lengkap);
        layoutKwitansiReguler = (LinearLayout) findViewById(R.id.layout_kwitansi_reguler);
        layoutLwitansiFree = (LinearLayout) findViewById(R.id.layout_kwitansi_free);
        layoutKwitansiOld = (LinearLayout) findViewById(R.id.layout_kwitansi_old);
        carouselView = (CarouselView) findViewById(R.id.carouselView);
        layoutBottomSheet = (LinearLayout) findViewById(R.id.bottom_sheet_info);
        layoutInfoPerwakilan = (LinearLayout) findViewById(R.id.info_perwakilan);
        layoutInfoPSC = (LinearLayout) findViewById(R.id.info_list);

        rvDataPerwakilan = (RecyclerView) findViewById(R.id.rv_data_perwakilan);
        rvDataPerwakilan.setHasFixedSize(true);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setProgress(0);
    }

    private void initEvent(){
        layoutKwitansiOld.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), RegistrationRegulerActivity.class);
                intent.putExtra("id_perwakilan", dataUser.id_perwakilan);
                intent.putExtra("type_registration", TAG_FREE_REGULER);
                startActivity(intent);
            }
        });

        layoutKwitansiReguler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), RegistrationRegulerActivity.class);
                intent.putExtra("id_perwakilan", dataUser.id_perwakilan);
                intent.putExtra("type_registration", TAG_REGULER);
                startActivity(intent);
            }
        });

        layoutLwitansiFree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), RegistrationRegulerActivity.class);
                intent.putExtra("id_perwakilan", dataUser.id_perwakilan);
                intent.putExtra("type_registration", TAG_FREE);
                startActivity(intent);
            }
        });

        layoutLwitansiFree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), RegistrationRegulerActivity.class);
                intent.putExtra("id_perwakilan", dataUser.id_perwakilan);
                intent.putExtra("type_registration", TAG_FREE);
                startActivity(intent);
            }
        });


        carouselView.setImageClickListener(new ImageClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(getContext(), "Clicked item: "+ position, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + "must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void doSuccess(ApiResponse response, String tag) {
        if (tag.equals(Presenter.RES_GET_DATA_DOLLAR)){
            DollarResponse dollarResponse = (DollarResponse) response;
            tvKursDollar.setText(FormatRupiah.useFormat(dollarResponse.dollar.get(0).getRupiah()));
        }else if(tag.equals(Presenter.RES_GET_DATA_KDM_BELANJA)){
            KDMBelanjaResponse resp = (KDMBelanjaResponse) response;
            KDMBelanja mItem = new KDMBelanja();
            if (dataUser.tipe == 0){
                mItem.setLabel("Berakhir Kontrak");
                mItem.setNumber(dataUser.tgl_berakhir);
                resp.data.add(mItem);
            }
            mAdapter = new HomeInfoAdapter(getContext(), resp.data);
            rvInfo.setAdapter(mAdapter);
        }else if (tag.equals(Presenter.RES_GET_DATA_BINAAN_KDM)){
            dataPerwakilanPSC = ((PSCDataJualKwitansiResponse)response).data;
            if (dataPerwakilanPSC.size() > 0){
                pscDataPenjualanKwitansiAdapter = new PSCDataPenjualanKwitansiAdapter(getContext(), dataPerwakilanPSC);
                rvDataPerwakilan.setLayoutManager(new GridLayoutManager(getContext(), 1));
                rvDataPerwakilan.setAdapter(pscDataPenjualanKwitansiAdapter);
                rvDataPerwakilan.setVisibility(View.VISIBLE);
                layoutInsufficent.setVisibility(View.GONE);
            }else{
                rvDataPerwakilan.setVisibility(View.GONE);
                layoutInsufficent.setVisibility(View.VISIBLE);
            }
        }else if (tag.equals(Presenter.RES_GET_DATA_JAMAAH)){
            dataJamaah = ((JamaahResponse)response).jamaah;
            if (dataJamaah.size() > 0){
                mAdapterJamaah = new JamaahAdapter(getContext(), dataJamaah, false);
                rvDataPerwakilan.setLayoutManager(new GridLayoutManager(getContext(), 1));
                rvDataPerwakilan.setAdapter(mAdapterJamaah);
                rvDataPerwakilan.setVisibility(View.VISIBLE);
                layoutInsufficent.setVisibility(View.GONE);
            }else{
                rvDataPerwakilan.setVisibility(View.GONE);
                layoutInsufficent.setVisibility(View.VISIBLE);
            }
        }else if(tag.equals(Presenter.RES_GET_BANNER)){
            dataBanner = ((BannerResponse) response).slide;
            attachData();
        }
    }

    @Override
    public void doValidationError(boolean error) {
    }

}
