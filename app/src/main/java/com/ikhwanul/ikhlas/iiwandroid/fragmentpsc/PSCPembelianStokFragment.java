package com.ikhwanul.ikhlas.iiwandroid.fragmentpsc;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.ikhwanul.ikhlas.iiwandroid.R;
import com.ikhwanul.ikhlas.iiwandroid.adapterpsc.PSCKwitansiPerwakilanAdapter;
import com.ikhwanul.ikhlas.iiwandroid.api.response.ApiResponse;
import com.ikhwanul.ikhlas.iiwandroid.core.AppFragment;
import com.ikhwanul.ikhlas.iiwandroid.entities.PSCKwitansiPerwakilan;
import com.ikhwanul.ikhlas.iiwandroid.entities.User;
import com.ikhwanul.ikhlas.iiwandroid.fragment.OnFragmentInteractionListener;
import com.ikhwanul.ikhlas.iiwandroid.presenters.Presenter;
import com.ikhwanul.ikhlas.iiwandroid.presenters.iPresenterResponse;
import com.ikhwanul.ikhlas.iiwandroid.ui.DatePickerFragment;
import com.ikhwanul.ikhlas.iiwandroid.utils.DateUtils;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PSCPembelianStokFragment extends AppFragment implements iPresenterResponse, SearchView.OnQueryTextListener{
    private OnFragmentInteractionListener mListener;
    private RecyclerView rvDataPerwakilan;
    private SearchView svData;
    private PSCKwitansiPerwakilanAdapter mAdapter;
    private Presenter mPresenter;
    private List<PSCKwitansiPerwakilan> dataPerwakilanPSC;
    private LinearLayout layoutInsufficient;

    private FragmentActivity myContext;

    private EditText editTglMulai, editTglAkhir;
    FragmentManager fragManager;
    private Button btnFilter;
    ProgressDialog progressDialog;
    User dataUser;

    public PSCPembelianStokFragment() {
        super();
        this.res_layout = R.layout.fragment_laporan_filter;
    }

    public static PSCPembelianStokFragment newInstance() {
        PSCPembelianStokFragment fragment = new PSCPembelianStokFragment();
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
        setHasOptionsMenu(true);
        initView();
        initObject();
        initEvent();

        return view;
    }

    private void initObject() {
    }

    @Override
    public void onStart() {
        super.onStart();
    }


    @Override
    public void onAttach(Activity activity) {
        myContext=(FragmentActivity) activity;
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    protected void initView(){
        fragManager = myContext.getSupportFragmentManager(); //If using fragments from support v4
        btnFilter = (Button) view.findViewById(R.id.btn_filter);
        editTglMulai = (EditText) view.findViewById(R.id.edit_tgl_mulai);
        editTglAkhir = (EditText) view.findViewById(R.id.edit_tgl_akhir);
    }

    private void initEvent(){
//        editTglAkhir.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View view, boolean b) {
//                if (b) {
//                    editTglAkhir.clearFocus();
//                }
//            }
//        });
//
//        editTglMulai.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View view, boolean b) {
//                if (b) {
//                    editTglMulai.clearFocus();
//                }
//            }
//        });
        editTglMulai.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(MotionEvent.ACTION_UP == event.getAction()) {
                    Calendar calendar = Calendar.getInstance();
                    if (!TextUtils.isEmpty(editTglMulai.getText().toString())){
                        calendar.setTime(DateUtils.parse(editTglMulai.getText().toString(), DateUtils.DEFAULT_OUT_FORMAT));
                    }
                    DialogFragment dialogFragment = DatePickerFragment.newInstance(fragManager,
                            calendar, new DatePickerFragment.OnCompleteDatePickerLister() {
                                @Override
                                public void onComplete(String date) {
                                    editTglMulai.setText(date);
                                    Log.d("Date :", date);
                                }
                            },Calendar.getInstance().getTimeInMillis(), 0);
                }
                return false;
            }
        });

        editTglAkhir.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(MotionEvent.ACTION_UP == event.getAction()) {
                    Calendar calendar = Calendar.getInstance();
                    if (!TextUtils.isEmpty(editTglAkhir.getText().toString())){
                        calendar.setTime(DateUtils.parse(editTglAkhir.getText().toString(), DateUtils.DEFAULT_OUT_FORMAT));
                    }
                    DialogFragment dialogFragment = DatePickerFragment.newInstance(fragManager,
                            calendar, new DatePickerFragment.OnCompleteDatePickerLister() {
                                @Override
                                public void onComplete(String date) {
                                    editTglAkhir.setText(date);
                                    Log.d("Date :", date);
                                }
                            },0, Calendar.getInstance().getTimeInMillis());
                }
                return false;
            }
        });

        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> data = new HashMap<>();
                data.put("id_perwakilan", dataUser.id_perwakilan);
                data.put("mulai", editTglMulai.getText().toString());
                data.put("akhir", editTglAkhir.getText().toString());
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

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
