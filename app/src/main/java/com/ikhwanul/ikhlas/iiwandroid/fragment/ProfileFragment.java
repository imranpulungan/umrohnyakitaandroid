package com.ikhwanul.ikhlas.iiwandroid.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ikhwanul.ikhlas.iiwandroid.BuildConfig;
import com.ikhwanul.ikhlas.iiwandroid.R;
import com.ikhwanul.ikhlas.iiwandroid.api.response.ApiResponse;
import com.ikhwanul.ikhlas.iiwandroid.core.AppFragment;
import com.ikhwanul.ikhlas.iiwandroid.entities.User;
import com.ikhwanul.ikhlas.iiwandroid.presenters.iPresenterResponse;
import com.ikhwanul.ikhlas.iiwandroid.utils.Session;
import com.squareup.picasso.Picasso;

public class ProfileFragment extends AppFragment implements iPresenterResponse {

    private OnFragmentInteractionListener mListener;
    private EditText mEditPhoneNumber,
                        mEditPerekomendasi,
                        mEditUsername,
                        mEditPassword,
                        mEditnik,
                        mEditAlamat,
                        mEdit_ttl,
                        mEditJkel,
                        mEditEmail;
    private TextView mTvName, mTvKode;
    public ImageView imgUser;
    private Button btnLogout;

    private User dataUser;

    public ProfileFragment() {
        super();
        this.res_layout = R.layout.fragment_profile;
    }

    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
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
        initEvent();
        attachData();

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
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

    private void attachData() {
        mEditPhoneNumber.setText(dataUser.no_telpon);
        mEditPerekomendasi.setText(dataUser.id_perekomendasi);
        mEditUsername.setText(dataUser.username);
        mEditnik.setText(dataUser.nik);
        mEdit_ttl.setText(dataUser.tempat_lahir + ", "+ dataUser.tanggal_lahir);
        mEditJkel.setText(dataUser.jenis_kelamin);
        mEditAlamat.setText(dataUser.alamat);
        mEditEmail.setText(dataUser.email);
        mTvName.setText(dataUser.nama_lengkap);
        mTvKode.setText(dataUser.kode);

        if (!dataUser.foto.equals("") || dataUser.foto != null){
            Picasso.with(getContext())
                    .load(BuildConfig.API_URL+"images_info/images_member/crop_mini/"+dataUser.foto)
                    .fit()
                    .centerCrop()
                    .placeholder(R.drawable.ic_menu_profile)
                    .into(imgUser);
        }
    }

    protected void initView(){
        imgUser = (ImageView) view.findViewById(R.id.img_user);
        mTvName = (TextView) findViewById(R.id.name);
        mTvKode = (TextView) findViewById(R.id.kode);
        mEditPhoneNumber = (EditText) findViewById(R.id.edit_no_hp);
        mEditPerekomendasi = (EditText) findViewById(R.id.edit_perekomendasi);
        mEditUsername = (EditText) findViewById(R.id.edit_username);
        mEditPassword= (EditText) findViewById(R.id.edit_password);
        mEditnik = (EditText) findViewById(R.id.edit_nik);
        mEdit_ttl = (EditText) findViewById(R.id.edit_ttl);
        mEditJkel= (EditText) findViewById(R.id.edit_jkel);
        mEditAlamat= (EditText) findViewById(R.id.edit_alamat);
        mEditEmail = (EditText) findViewById(R.id.edit_email);
        btnLogout = (Button) findViewById(R.id.btn_logout);
    }

    private void initEvent(){
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Session.with(getContext()).clearLoginSession();
            }
        });
    }

    @Override
    public void doSuccess(ApiResponse response, String tag) {
    }


    @Override
    public void doFail(String message) {
        super.doFail(message);
    }

    @Override
    public void doValidationError(boolean error) {

    }

    @Override
    public void doConnectionError() {
        super.doConnectionError();
    }
}
