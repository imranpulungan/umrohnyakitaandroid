package com.ikhwanul.ikhlas.iiwandroid.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ikhwanul.ikhlas.iiwandroid.R;
import com.ikhwanul.ikhlas.iiwandroid.adapter.KomisiPSCAdapter;
import com.ikhwanul.ikhlas.iiwandroid.adapter.KwitansiAdapter;
import com.ikhwanul.ikhlas.iiwandroid.api.response.ApiResponse;
import com.ikhwanul.ikhlas.iiwandroid.api.response.KomisiPSCResponse;
import com.ikhwanul.ikhlas.iiwandroid.api.response.KwitansiResponse;
import com.ikhwanul.ikhlas.iiwandroid.core.AppFragment;
import com.ikhwanul.ikhlas.iiwandroid.entities.KomisiPSC;
import com.ikhwanul.ikhlas.iiwandroid.entities.Kwitansi;
import com.ikhwanul.ikhlas.iiwandroid.entities.User;
import com.ikhwanul.ikhlas.iiwandroid.presenters.Presenter;
import com.ikhwanul.ikhlas.iiwandroid.presenters.iPresenterResponse;

import java.util.ArrayList;
import java.util.List;

public class KwitansiFragment extends AppFragment implements iPresenterResponse, SearchView.OnQueryTextListener {

    private OnFragmentInteractionListener mListener;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView rvKwitansi;
    private KwitansiAdapter mAdapter;
    private SearchView svData;
    private Presenter mPresenter;
    private List<Kwitansi> dataKwitansi;
    private LinearLayout layoutInsufficent;

    ProgressDialog progressDialog;

    User dataUser;

    public KwitansiFragment() {
        super();
        this.res_layout = R.layout.fragment_kwitansi;
    }

    public static KwitansiFragment newInstance() {
        KwitansiFragment fragment = new KwitansiFragment();
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

        return view;
    }

    private void initObject() {
        mPresenter = new Presenter(getContext(), this);
        mPresenter.getKwitansi(Integer.valueOf(dataUser.id_perwakilan), progressDialog, true);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swape_refresh);
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

    protected void initView(){
        layoutInsufficent = (LinearLayout) findViewById(R.id.insufficient);
        svData = (SearchView) findViewById(R.id.sv_data);
        svData.setOnQueryTextListener(this);
        rvKwitansi = (RecyclerView) findViewById(R.id.rv_kwitansi);
        rvKwitansi.setHasFixedSize(true);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setProgress(0);
    }

    private void initEvent(){
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getKwitansi(Integer.valueOf(dataUser.id_perwakilan), progressDialog, false);
            }
        });
    }

    @Override
    public void doSuccess(ApiResponse response, String tag) {
        if (tag.equals(Presenter.RES_GET_DATA_KWITANSI)){
            dataKwitansi = ((KwitansiResponse)response).beli;
            if (dataKwitansi.size() > 0){
                mAdapter = new KwitansiAdapter(getContext(), dataKwitansi);
                rvKwitansi.setLayoutManager(new GridLayoutManager(getContext(), 1));
                rvKwitansi.setAdapter(mAdapter);
                rvKwitansi.setVisibility(View.VISIBLE);
                layoutInsufficent.setVisibility(View.GONE);
            }else{
                rvKwitansi.setVisibility(View.GONE);
                layoutInsufficent.setVisibility(View.VISIBLE);
            }
        }
        mSwipeRefreshLayout.setRefreshing(false);

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
        mAdapter.filter(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
