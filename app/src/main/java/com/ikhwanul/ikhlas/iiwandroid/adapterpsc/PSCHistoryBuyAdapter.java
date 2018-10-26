package com.ikhwanul.ikhlas.iiwandroid.adapterpsc;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ikhwanul.ikhlas.iiwandroid.R;
import com.ikhwanul.ikhlas.iiwandroid.entities.PSCDataHistoryStok;
import com.ikhwanul.ikhlas.iiwandroid.entities.PSCKwitansiPerwakilan;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class PSCHistoryBuyAdapter extends
        RecyclerView.Adapter<PSCHistoryBuyAdapter.MyViewHolder> {

    private List<PSCDataHistoryStok> dataPerwakilanListList;
    private ArrayList<PSCDataHistoryStok> arraylist;
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

    public PSCHistoryBuyAdapter(Context context, List<PSCDataHistoryStok> dataPerwakilanListList) {
        this.context = context;
        this.dataPerwakilanListList = dataPerwakilanListList;
        this.arraylist = new ArrayList<PSCDataHistoryStok>();
        this.arraylist.addAll(dataPerwakilanListList);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final PSCDataHistoryStok dataPerwakilan= dataPerwakilanListList.get(position);
        holder.textIdItem.setText(dataPerwakilan.getNo_transaksi());
        holder.textNameItem.setText("Jumlah Stok " + dataPerwakilan.getJumlah_stock() );
        holder.textRekomendasi.setText(dataPerwakilan.getTgl_dibuat());
        holder.textStatus.setVisibility(View.GONE);
        holder.textKontrakAkhir.setVisibility(View.GONE);

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
            for (PSCDataHistoryStok wp : arraylist) {
                if (wp.getNo_transaksi().toLowerCase(Locale.getDefault()).contains(charText)) {
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