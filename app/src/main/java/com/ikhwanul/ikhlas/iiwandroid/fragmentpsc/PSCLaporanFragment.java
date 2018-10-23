package com.ikhwanul.ikhlas.iiwandroid.fragmentpsc;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ikhwanul.ikhlas.iiwandroid.R;
import com.ikhwanul.ikhlas.iiwandroid.adapterpsc.SimpleFragmentPagerAdapter;
import com.ikhwanul.ikhlas.iiwandroid.api.response.ApiResponse;
import com.ikhwanul.ikhlas.iiwandroid.core.AppFragment;
import com.ikhwanul.ikhlas.iiwandroid.fragment.OnFragmentInteractionListener;
import com.ikhwanul.ikhlas.iiwandroid.presenters.iPresenterResponse;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.List;

public class PSCLaporanFragment extends AppFragment{
    private OnFragmentInteractionListener mListener;
    private FragmentActivity myContext;
    public PSCLaporanFragment() {
        super();
        this.res_layout = R.layout.fragment_laporan;
    }

    public static PSCLaporanFragment newInstance() {
        PSCLaporanFragment fragment = new PSCLaporanFragment();
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
        initView();
        initObject();
        initEvent();

        return view;
    }

    @Override
    public void doSuccess(ApiResponse response, String tag) {

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
        FragmentManager fragManager = myContext.getSupportFragmentManager(); //If using fragments from support v4
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        SimpleFragmentPagerAdapter adapter = new SimpleFragmentPagerAdapter(getContext(), fragManager);
        viewPager.setAdapter(adapter);
        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initEvent(){

    }
}
