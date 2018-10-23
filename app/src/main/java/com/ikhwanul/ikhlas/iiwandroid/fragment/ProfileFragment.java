package com.ikhwanul.ikhlas.iiwandroid.fragment;

import android.content.Context;
import android.content.Intent;
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
import com.ikhwanul.ikhlas.iiwandroid.MainActivity;
import com.ikhwanul.ikhlas.iiwandroid.MainActivityKDM;
import com.ikhwanul.ikhlas.iiwandroid.MainActivityMM;
import com.ikhwanul.ikhlas.iiwandroid.R;
import com.ikhwanul.ikhlas.iiwandroid.activities.SplashSecreenActivity;
import com.ikhwanul.ikhlas.iiwandroid.api.response.ApiResponse;
import com.ikhwanul.ikhlas.iiwandroid.api.response.AuthResponse;
import com.ikhwanul.ikhlas.iiwandroid.core.AppFragment;
import com.ikhwanul.ikhlas.iiwandroid.entities.User;
import com.ikhwanul.ikhlas.iiwandroid.presenters.DataFormPresenter;
import com.ikhwanul.ikhlas.iiwandroid.presenters.iPresenterResponse;
import com.ikhwanul.ikhlas.iiwandroid.utils.Session;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

import static com.ikhwanul.ikhlas.iiwandroid.presenters.Presenter.UPDATE_PROFILE_PERWAKILAN;

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
    private Button btnLogout, btnUpdate;

    DataFormPresenter dataFormPresenter;

    private User dataUser;

    Intent intent;

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
        initObject();
        initEvent();
        attachData();
        return view;
    }

    private void initObject() {
        dataFormPresenter = new DataFormPresenter(getContext(), this);
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
        mEditJkel.setText(dataUser.jenis_kelamin.equals("P")? "Wanita" : "Pria");
        mEditAlamat.setText(dataUser.alamat);
        mEditEmail.setText(dataUser.email);
        mTvName.setText(dataUser.nama_lengkap);
        mTvKode.setText(dataUser.kode);
        mEditPassword.setText(dataUser.pass_de);

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
        btnUpdate = (Button) view.findViewById(R.id.btn_update);
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

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mEditPhoneNumber.getText().toString().equals("") || mEditPhoneNumber.getText().toString().equals(null)){
                    Toast.makeText(getContext(), "Nomor Telepon masih kosong", Toast.LENGTH_LONG).show();
                }else if (mEditPassword.getText().toString().equals("") || mEditPassword.getText().toString().equals(null)){
                    Toast.makeText(getContext(), "Password masih kosong", Toast.LENGTH_LONG).show();
                }else if (mEditEmail.getText().toString().equals("") || mEditEmail.getText().toString().equals(null)){
                    Toast.makeText(getContext(), "Email masih Kosong", Toast.LENGTH_LONG).show();
                }else{ Map<String, String> data = new HashMap<String, String>();
                    data.put("id_perwakilan", dataUser.id_perwakilan);
                    data.put("no_telp", mEditPhoneNumber.getText().toString());
                    data.put("password", mEditPassword.getText().toString());
                    data.put("email", mEditEmail.getText().toString());
                    dataFormPresenter.updateProfilePerwakilan(data);
                }
            }
        });
    }

    @Override
    public void doSuccess(ApiResponse response, String tag) {
        if (tag.equals(UPDATE_PROFILE_PERWAKILAN)){
            if (!response.error){
                dataUser = ((AuthResponse) response).user;
                if (dataUser.peringkat.equals("1")){
                    intent = new Intent(getContext(), MainActivity.class);
                }else if(dataUser.peringkat.equals("2")){
                    intent = new Intent(getContext(), MainActivityMM.class);
                }else if(dataUser.peringkat.equals("3")){
                    intent = new Intent(getContext(), MainActivityKDM.class);
                }
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.putExtra("dataUser", dataUser);
                startActivity(intent);
            }
            Toast.makeText(getContext(), response.message, Toast.LENGTH_LONG).show();
        }
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
