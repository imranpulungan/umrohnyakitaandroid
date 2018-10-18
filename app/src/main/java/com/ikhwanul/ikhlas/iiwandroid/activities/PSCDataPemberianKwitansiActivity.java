package com.ikhwanul.ikhlas.iiwandroid.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ikhwanul.ikhlas.iiwandroid.R;
import com.ikhwanul.ikhlas.iiwandroid.adapterpsc.PSCDataPenjualanKwitansiAdapter;
import com.ikhwanul.ikhlas.iiwandroid.api.response.ApiResponse;
import com.ikhwanul.ikhlas.iiwandroid.api.response.PSCDataJualKwitansiResponse;
import com.ikhwanul.ikhlas.iiwandroid.api.response.PSCKwitansiPerwakilanResponse;
import com.ikhwanul.ikhlas.iiwandroid.core.AppActivity;
import com.ikhwanul.ikhlas.iiwandroid.entities.PSCDataJualKwitansi;
import com.ikhwanul.ikhlas.iiwandroid.entities.User;
import com.ikhwanul.ikhlas.iiwandroid.presenters.Presenter;
import com.ikhwanul.ikhlas.iiwandroid.presenters.iPresenterResponse;

import java.util.List;

import static com.ikhwanul.ikhlas.iiwandroid.presenters.Presenter.RES_GET_DATA_BINAAN_KDM;

public class PSCDataPemberianKwitansiActivity extends AppActivity implements iPresenterResponse, SearchView.OnQueryTextListener
{
    private RecyclerView rvDataPemberianKwitansiPerwakilan;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private List<PSCDataJualKwitansi> dataPemberianKwitansiFree;
    PSCDataPenjualanKwitansiAdapter mAdapter;
    ProgressDialog progressDialog;
    LinearLayout layoutInsufficient;

    Presenter mPresenter;
    User dataUser;

    public PSCDataPemberianKwitansiActivity() {
        super(R.layout.activity_data_pscberi_kwitansi, true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Pemberian Kwitansi Free");
        dataUser = (User) getIntent().getSerializableExtra("send");
        initView();
        initObject();
        initEvent();
        attachData();
    }

    private void initObject() {
        mPresenter = new Presenter(this, this);
        mPresenter.getPemberianKwitansi_walk(Integer.valueOf(dataUser.id_perwakilan), progressDialog, true);
    }

    private void initEvent() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getPemberianKwitansi_walk(Integer.valueOf(dataUser.id_perwakilan), progressDialog, false);
            }
        });
    }

    private void attachData() {
    }

    private void initView() {
        layoutInsufficient = (LinearLayout) findViewById(R.id.insufficient);
        rvDataPemberianKwitansiPerwakilan = (RecyclerView) findViewById(R.id.rv_data_pemberian_kwitansi_perwakilan);

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swape_refresh);
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setProgress(0);
    }

    @Override
    public void doSuccess(ApiResponse response, String tag) {
        if (tag.equals(RES_GET_DATA_BINAAN_KDM)){
            dataPemberianKwitansiFree = ((PSCDataJualKwitansiResponse)response).data;
            if (dataPemberianKwitansiFree.size() > 0){
                mAdapter = new PSCDataPenjualanKwitansiAdapter(this, dataPemberianKwitansiFree);
                rvDataPemberianKwitansiPerwakilan.setLayoutManager(new GridLayoutManager(this, 1));
                rvDataPemberianKwitansiPerwakilan.setAdapter(mAdapter);
                rvDataPemberianKwitansiPerwakilan.setVisibility(View.VISIBLE);
                layoutInsufficient.setVisibility(View.GONE);
            }else{
                rvDataPemberianKwitansiPerwakilan.setVisibility(View.GONE);
                layoutInsufficient.setVisibility(View.VISIBLE);
            }
        }
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void doValidationError(boolean error) {

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
