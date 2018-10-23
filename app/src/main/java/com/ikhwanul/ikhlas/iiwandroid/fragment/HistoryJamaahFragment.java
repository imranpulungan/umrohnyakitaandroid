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
import com.ikhwanul.ikhlas.iiwandroid.adapter.JamaahAdapter;
import com.ikhwanul.ikhlas.iiwandroid.api.response.ApiResponse;
import com.ikhwanul.ikhlas.iiwandroid.api.response.JamaahResponse;
import com.ikhwanul.ikhlas.iiwandroid.core.AppFragment;
import com.ikhwanul.ikhlas.iiwandroid.entities.Jamaah;
import com.ikhwanul.ikhlas.iiwandroid.entities.User;
import com.ikhwanul.ikhlas.iiwandroid.presenters.Presenter;
import com.ikhwanul.ikhlas.iiwandroid.presenters.iPresenterResponse;

import java.util.List;

public class HistoryJamaahFragment extends AppFragment implements iPresenterResponse , SearchView.OnQueryTextListener{

    private OnFragmentInteractionListener mListener;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView rvJamaah;
    private SearchView svData;

    private JamaahAdapter mAdapter;
    private LinearLayout layoutInsufficient;

    ProgressDialog progressDialog;

    Presenter mPresenter;
    List<Jamaah> dataJamaah;

    User dataUser;

    public HistoryJamaahFragment() {
        super();
        this.res_layout = R.layout.fragment_jamaah;
    }

    public static HistoryJamaahFragment newInstance() {
        HistoryJamaahFragment fragment = new HistoryJamaahFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
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
        mPresenter.getHistoryJamaah(dataUser.id_perwakilan, progressDialog, false);
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
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swape_refresh);
        svData = (SearchView) findViewById(R.id.sv_data);
        svData.setOnQueryTextListener(this);
        rvJamaah = (RecyclerView) findViewById(R.id.rv_jamaah);
        rvJamaah.setHasFixedSize(true);

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
                mPresenter.getHistoryJamaah(dataUser.id_perwakilan, progressDialog, false);
            }
        });
    }

    @Override
    public void doSuccess(ApiResponse response, String tag) {
        if (tag.equals(Presenter.RES_GET_DATA_JAMAAH)){
            dataJamaah = ((JamaahResponse)response).jamaah;
            if (dataJamaah.size() > 0){
                mAdapter = new JamaahAdapter(getContext(), dataJamaah, true);
                rvJamaah.setLayoutManager(new GridLayoutManager(getContext(), 1));
                rvJamaah.setAdapter(mAdapter);
                rvJamaah.setVisibility(View.VISIBLE);
                layoutInsufficient.setVisibility(View.GONE);
            }else{
                rvJamaah.setVisibility(View.GONE);
                layoutInsufficient.setVisibility(View.VISIBLE);
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
