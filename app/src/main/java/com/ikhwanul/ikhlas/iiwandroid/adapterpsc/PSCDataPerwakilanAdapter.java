package com.ikhwanul.ikhlas.iiwandroid.adapterpsc;

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
import com.ikhwanul.ikhlas.iiwandroid.activities.PSCDetailPerwakilanActivity;
import com.ikhwanul.ikhlas.iiwandroid.entities.BinaanKDM_MM;
import com.ikhwanul.ikhlas.iiwandroid.entities.PSCDataPerwakilan;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class PSCDataPerwakilanAdapter extends
        RecyclerView.Adapter<PSCDataPerwakilanAdapter.MyViewHolder> {

    private List<PSCDataPerwakilan> dataPerwakilanListList;
    private ArrayList<PSCDataPerwakilan> arraylist;
    Context context;

    /**
     * View holder class
     * */
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textIdItem ;
        public TextView textNameItem;
        public TextView textRekomendasi;
        public TextView textKontrakAkhir;
        public TextView textStatus;

        public MyViewHolder(View view) {
            super(view);
            textIdItem = (TextView) view.findViewById(R.id.tv_id);
            textNameItem = (TextView) view.findViewById(R.id.tv_nama_lengkap);
            textRekomendasi = (TextView) view.findViewById(R.id.tv_rekomendasi);
            textKontrakAkhir = (TextView) view.findViewById(R.id.tv_kontrak_akhir);
            textStatus = (TextView) view.findViewById(R.id.tv_status);
        }
    }

    public PSCDataPerwakilanAdapter(Context context, List<PSCDataPerwakilan> dataPerwakilanListList) {
        this.context = context;
        this.dataPerwakilanListList = dataPerwakilanListList;
        this.arraylist = new ArrayList<PSCDataPerwakilan>();
        this.arraylist.addAll(dataPerwakilanListList);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final PSCDataPerwakilan dataPerwakilan= dataPerwakilanListList.get(position);
        holder.textIdItem.setText(dataPerwakilan.getKode());
        holder.textNameItem.setText(dataPerwakilan.getNama_lengkap());
        holder.textRekomendasi.setText(dataPerwakilan.getKode_per());
        holder.textKontrakAkhir.setText(dataPerwakilan.getTgl_berakhir());
        holder.textStatus.setText(dataPerwakilan.getStatus());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PSCDetailPerwakilanActivity.class);
                intent.putExtra("id", Integer.valueOf(dataPerwakilan.getId_perwakilan()));
                context.startActivity(intent);

            }
        });
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        dataPerwakilanListList.clear();
        if (charText.length() == 0) {
            dataPerwakilanListList.addAll(arraylist);
        } else {
            for (PSCDataPerwakilan wp : arraylist) {
                if (wp.getNama_lengkap().toLowerCase(Locale.getDefault()).contains(charText)) {
                    dataPerwakilanListList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return dataPerwakilanListList.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_item_perwakilan,parent, false);
        return new MyViewHolder(v);
    }
}