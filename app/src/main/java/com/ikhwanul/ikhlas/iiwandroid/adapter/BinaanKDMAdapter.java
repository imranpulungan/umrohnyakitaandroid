package com.ikhwanul.ikhlas.iiwandroid.adapter;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ikhwanul.ikhlas.iiwandroid.R;
import com.ikhwanul.ikhlas.iiwandroid.entities.BinaanKDM_MM;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class BinaanKDMAdapter extends
        RecyclerView.Adapter<BinaanKDMAdapter.MyViewHolder> {

    private List<BinaanKDM_MM> binaanList;
    private ArrayList<BinaanKDM_MM> arraylist;
    Context context;

    /**
     * View holder class
     * */
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textIdItem ;
        public TextView textNameItem;
        public TextView textPhoneItem;
        public ImageView imgIcon;
        private ImageButton imgbtnCall;

        public MyViewHolder(View view) {
            super(view);
            imgIcon = (ImageView) view.findViewById(R.id.img_perwakilan);
            imgbtnCall = (ImageButton) view.findViewById(R.id.imgbtn_call);
            textIdItem = (TextView) view.findViewById(R.id.tv_id_perwakilan);
            textNameItem = (TextView) view.findViewById(R.id.tv_name_perwakilan);
            textPhoneItem = (TextView) view.findViewById(R.id.tv_phone_perwakilan);
        }
    }

    public BinaanKDMAdapter(Context context, List<BinaanKDM_MM> binaanList) {
        this.context = context;
        this.binaanList = binaanList;
        this.arraylist = new ArrayList<BinaanKDM_MM>();
        this.arraylist.addAll(binaanList);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final BinaanKDM_MM dataBinaan = binaanList.get(position);
        holder.textIdItem.setText(dataBinaan.getId_perwakilan());
        holder.textNameItem.setText(dataBinaan.getNama_lengkap());
        holder.textPhoneItem.setText(dataBinaan.getNo_telpon());

        holder.imgbtnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeCall(dataBinaan.getNo_telpon());
                Toast.makeText(context, dataBinaan.getNo_telpon(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void makeCall(String phoneNumber){
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions((Activity) context,
                    new String[]{Manifest.permission.CALL_PHONE},
                    1);
        }else{
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel: " + phoneNumber.toString()));
            context.startActivity(intent);
        }
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        binaanList.clear();
        if (charText.length() == 0) {
            binaanList.addAll(arraylist);
        } else {
            for (BinaanKDM_MM wp : arraylist) {
                if (wp.getNama_lengkap().toLowerCase(Locale.getDefault()).contains(charText)) {
                    binaanList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return binaanList.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_item,parent, false);
        return new MyViewHolder(v);
    }
}