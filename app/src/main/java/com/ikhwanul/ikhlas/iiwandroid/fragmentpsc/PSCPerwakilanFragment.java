package com.ikhwanul.ikhlas.iiwandroid.fragmentpsc;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ikhwanul.ikhlas.iiwandroid.R;
import com.ikhwanul.ikhlas.iiwandroid.activities.PSCJualKwitansiActivity;
import com.ikhwanul.ikhlas.iiwandroid.activities.PSCTambahPerwakilanActivity;
import com.ikhwanul.ikhlas.iiwandroid.adapterpsc.PSCDataPerwakilanAdapter;
import com.ikhwanul.ikhlas.iiwandroid.api.response.ApiResponse;
import com.ikhwanul.ikhlas.iiwandroid.api.response.PSCDataPerwakilanResponse;
import com.ikhwanul.ikhlas.iiwandroid.core.AppFragment;
import com.ikhwanul.ikhlas.iiwandroid.entities.PSCDataPerwakilan;
import com.ikhwanul.ikhlas.iiwandroid.entities.User;
import com.ikhwanul.ikhlas.iiwandroid.fragment.OnFragmentInteractionListener;
import com.ikhwanul.ikhlas.iiwandroid.presenters.Presenter;
import com.ikhwanul.ikhlas.iiwandroid.presenters.iPresenterResponse;

import java.util.List;

public class PSCPerwakilanFragment extends AppFragment implements iPresenterResponse, SearchView.OnQueryTextListener{

    private OnFragmentInteractionListener mListener;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView rvDataPerwakilan;
    private SearchView svData;
    private PSCDataPerwakilanAdapter mAdapter;
    private Presenter mPresenter;
    private List<PSCDataPerwakilan> dataPerwakilanPSC;
    private LinearLayout layoutInsufficient;

    ProgressDialog progressDialog;
    User dataUser;


    public PSCPerwakilanFragment() {
        super();
        this.res_layout = R.layout.fragment_perwakilan;
    }

    public static PSCPerwakilanFragment newInstance() {
        PSCPerwakilanFragment fragment = new PSCPerwakilanFragment();
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
        setHasOptionsMenu(true);
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
        mPresenter.getPSCPerwakilan(Integer.valueOf(dataUser.id_perwakilan), progressDialog, true);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_jual, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_jual_kwitansi:
                Intent intent = new Intent(getContext(), PSCTambahPerwakilanActivity.class);

                intent.putExtra("id_psc", Integer.valueOf(dataUser.id_perwakilan));
                startActivity(intent);
                return true;
        }
        return false;
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.getPSCPerwakilan(Integer.valueOf(dataUser.id_perwakilan), progressDialog, true);
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
        rvDataPerwakilan = (RecyclerView) findViewById(R.id.rv_data_perwakilan);
        rvDataPerwakilan.setHasFixedSize(true);
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
                mPresenter.getPSCPerwakilan(Integer.valueOf(dataUser.id_perwakilan), progressDialog, false);
            }
        });
    }

    @Override
    public void doSuccess(ApiResponse response, String tag) {
        if (tag.equals(Presenter.RES_GET_DATA_BINAAN_KDM)){
            dataPerwakilanPSC = ((PSCDataPerwakilanResponse)response).data;
            mAdapter = new PSCDataPerwakilanAdapter(getContext(), dataPerwakilanPSC);
            rvDataPerwakilan.setLayoutManager(new GridLayoutManager(getContext(), 1));
            rvDataPerwakilan.setAdapter(mAdapter);
            rvDataPerwakilan.setVisibility(View.VISIBLE);
            layoutInsufficient.setVisibility(View.GONE);
        }else{
            rvDataPerwakilan.setVisibility(View.GONE);
            layoutInsufficient.setVisibility(View.VISIBLE);
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
