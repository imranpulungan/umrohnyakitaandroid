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
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ikhwanul.ikhlas.iiwandroid.R;
import com.ikhwanul.ikhlas.iiwandroid.adapter.BinaanPerwakilanAdapter;
import com.ikhwanul.ikhlas.iiwandroid.api.response.ApiResponse;
import com.ikhwanul.ikhlas.iiwandroid.api.response.BinaanResponse;
import com.ikhwanul.ikhlas.iiwandroid.core.AppFragment;
import com.ikhwanul.ikhlas.iiwandroid.entities.Binaan;
import com.ikhwanul.ikhlas.iiwandroid.entities.User;
import com.ikhwanul.ikhlas.iiwandroid.presenters.Presenter;
import com.ikhwanul.ikhlas.iiwandroid.presenters.iPresenterResponse;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.INPUT_METHOD_SERVICE;

public class BinaanPerwakilanFragment extends AppFragment implements iPresenterResponse, SearchView.OnQueryTextListener{

    private OnFragmentInteractionListener mListener;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView rvBinaanPerwakilan;
    private SearchView svData;
    private BinaanPerwakilanAdapter mAdapter;
    private Presenter mPresenter;
    private List<Binaan> dataBinaan;
    private ProgressDialog progressDialog;
    private LinearLayout layoutInsufficient;

    User dataUser;

    public BinaanPerwakilanFragment() {
        super();
        this.res_layout = R.layout.fragment_binaan_perwakilan;
    }

    public static BinaanPerwakilanFragment newInstance() {
        BinaanPerwakilanFragment fragment = new BinaanPerwakilanFragment();
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
        mPresenter.getBinaanPerwakilan(Integer.valueOf(dataUser.id_perwakilan), progressDialog, true);
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
        layoutInsufficient = (LinearLayout) findViewById(R.id.insufficient);
        svData = (SearchView) findViewById(R.id.sv_data);
        svData.setOnQueryTextListener(this);
        rvBinaanPerwakilan = (RecyclerView) findViewById(R.id.rv_binaan_perwakilan);
        rvBinaanPerwakilan.setHasFixedSize(true);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swape_refresh);
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
                mPresenter.getBinaanPerwakilan(Integer.valueOf(dataUser.id_perwakilan), progressDialog, false);
            }
        });
    }

    @Override
    public void doSuccess(ApiResponse response, String tag) {
        if (tag.equals(Presenter.RES_GET_DATA_BINAAN)){
            dataBinaan = ((BinaanResponse)response).binaan;
            if (dataBinaan.size() > 0){
                mAdapter = new BinaanPerwakilanAdapter(getContext(), dataBinaan);
                rvBinaanPerwakilan.setLayoutManager(new GridLayoutManager(getContext(), 1));
                rvBinaanPerwakilan.setAdapter(mAdapter);
                rvBinaanPerwakilan.setVisibility(View.VISIBLE);
                layoutInsufficient.setVisibility(View.GONE);
            }else{
                layoutInsufficient.setVisibility(View.VISIBLE);
                rvBinaanPerwakilan.setVisibility(View.GONE);
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
