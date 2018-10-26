package com.ikhwanul.ikhlas.iiwandroid.adapterpsc;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ikhwanul.ikhlas.iiwandroid.R;
import com.ikhwanul.ikhlas.iiwandroid.entities.PSCDataJualKwitansi;
import com.ikhwanul.ikhlas.iiwandroid.entities.PSCDataKomisiRekomendasi;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class PSCDataKomisiRekomendasiAdapter extends
        RecyclerView.Adapter<PSCDataKomisiRekomendasiAdapter.MyViewHolder> {

    private List<PSCDataKomisiRekomendasi> dataPerwakilanListList;
    private ArrayList<PSCDataKomisiRekomendasi> arraylist;
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
        public LinearLayout layoutRainbow;

        public MyViewHolder(View view) {
            super(view);
            layoutRainbow = (LinearLayout) view.findViewById(R.id.layout_rainbow);
            textIdItem = (TextView) view.findViewById(R.id.tv_id);
            textNameItem = (TextView) view.findViewById(R.id.tv_nama_lengkap);
            textRekomendasi = (TextView) view.findViewById(R.id.tv_rekomendasi);
            textKontrakAkhir = (TextView) view.findViewById(R.id.tv_kontrak_akhir);
            textStatus = (TextView) view.findViewById(R.id.tv_status);
        }
    }

    public PSCDataKomisiRekomendasiAdapter(Context context, List<PSCDataKomisiRekomendasi> dataPerwakilanListList) {
        this.context = context;
        this.dataPerwakilanListList = dataPerwakilanListList;
        this.arraylist = new ArrayList<PSCDataKomisiRekomendasi>();
        this.arraylist.addAll(dataPerwakilanListList);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final PSCDataKomisiRekomendasi dataPerwakilan= dataPerwakilanListList.get(position);
        holder.textIdItem.setText(dataPerwakilan.getTipe());
        holder.textNameItem.setText(dataPerwakilan.getJumlah());
        holder.textRekomendasi.setText(dataPerwakilan.getKeterangan());
        holder.textKontrakAkhir.setText(dataPerwakilan.getBln_tahun());
        holder.textStatus.setText(dataPerwakilan.getStatus());

        if (dataPerwakilan.getStatus().equals("Sudah Diklaim")){
            holder.textStatus.setBackgroundResource(R.drawable.background_with_radius4);
        }else{
            holder.textStatus.setBackgroundResource(R.drawable.background_with_radius2);
        }

        if (position % 2 ==0){
            holder.layoutRainbow.setBackgroundResource(R.color.colorPrimary);
        }else{
            holder.layoutRainbow.setBackgroundResource(R.color.colorOrangeHolo);
        }
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        dataPerwakilanListList.clear();
        if (charText.length() == 0) {
            dataPerwakilanListList.addAll(arraylist);
        } else {
            for (PSCDataKomisiRekomendasi wp : arraylist) {
                if (wp.getTipe().toLowerCase(Locale.getDefault()).contains(charText)) {
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