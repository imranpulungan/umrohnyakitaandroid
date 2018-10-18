package com.ikhwanul.ikhlas.iiwandroid.adapterpsc;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ikhwanul.ikhlas.iiwandroid.R;
import com.ikhwanul.ikhlas.iiwandroid.entities.PSCDataPerwakilan;
import com.ikhwanul.ikhlas.iiwandroid.entities.PSCKwitansiPerwakilan;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class PSCKwitansiPerwakilanAdapter extends
        RecyclerView.Adapter<PSCKwitansiPerwakilanAdapter.MyViewHolder> {

    private List<PSCKwitansiPerwakilan> dataPerwakilanListList;
    private ArrayList<PSCKwitansiPerwakilan> arraylist;
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

    public PSCKwitansiPerwakilanAdapter(Context context, List<PSCKwitansiPerwakilan> dataPerwakilanListList) {
        this.context = context;
        this.dataPerwakilanListList = dataPerwakilanListList;
        this.arraylist = new ArrayList<PSCKwitansiPerwakilan>();
        this.arraylist.addAll(dataPerwakilanListList);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final PSCKwitansiPerwakilan dataPerwakilan= dataPerwakilanListList.get(position);
        holder.textIdItem.setText(dataPerwakilan.getNo_kwitansi());
        holder.textNameItem.setText(dataPerwakilan.getNo_transaksi());
        holder.textRekomendasi.setText(dataPerwakilan.getUntuk());
        holder.textKontrakAkhir.setText(dataPerwakilan.getGuna());
        holder.textStatus.setText(dataPerwakilan.getStatus());
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        dataPerwakilanListList.clear();
        if (charText.length() == 0) {
            dataPerwakilanListList.addAll(arraylist);
        } else {
            for (PSCKwitansiPerwakilan wp : arraylist) {
                if (wp.getNo_kwitansi().toLowerCase(Locale.getDefault()).contains(charText)) {
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