package com.ikhwanul.ikhlas.iiwandroid.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ikhwanul.ikhlas.iiwandroid.R;
import com.ikhwanul.ikhlas.iiwandroid.entities.KomisiPSC;
import com.ikhwanul.ikhlas.iiwandroid.entities.Kwitansi;
import com.ikhwanul.ikhlas.iiwandroid.utils.FormatRupiah;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class KwitansiAdapter extends
        RecyclerView.Adapter<KwitansiAdapter.MyViewHolder> {

    private List<Kwitansi> kwitansiList;
    private ArrayList<Kwitansi> arraylist;

    Context context;

    /**
     * View holder class
     * */
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvNoTransaksi;
        public TextView tvNoKwitansi;
        public TextView tvSudahBelum;
        public TextView tvStatus;

        public MyViewHolder(View view) {
            super(view);
            tvNoKwitansi = (TextView) view.findViewById(R.id.tv_no_kwitansi);
            tvNoTransaksi = (TextView) view.findViewById(R.id.tv_no_transaksi);
            tvSudahBelum= (TextView) view.findViewById(R.id.tv_sudah_belum);
            tvStatus = (TextView) view.findViewById(R.id.tv_status_kwitansi);
        }
    }

    public KwitansiAdapter(Context context, List<Kwitansi> kwitansiList) {
        this.context = context;
        this.kwitansiList = kwitansiList;
        this.arraylist = new ArrayList<Kwitansi>();
        this.arraylist.addAll(kwitansiList);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Kwitansi dataKwitansi = kwitansiList.get(position);

        holder.tvNoKwitansi.setText(dataKwitansi.getNo_kwintansi());
        holder.tvNoTransaksi.setText(dataKwitansi.getNo_transaksi());
        holder.tvStatus.setText(dataKwitansi.getStatus());


//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, DetailJamaahActivity.class);
//                intent.putExtra("data", dataJamaah);
//                context.startActivity(intent);
//            }
//        });
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        kwitansiList.clear();
        if (charText.length() == 0) {
            kwitansiList.addAll(arraylist);
        } else {
            for (Kwitansi wp : arraylist) {
                if (wp.getNo_kwintansi().toLowerCase(Locale.getDefault()).contains(charText)) {
                    kwitansiList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return kwitansiList.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_item_kwitansi,parent, false);
        return new MyViewHolder(v);
    }
}