package com.ikhwanul.ikhlas.iiwandroid.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ikhwanul.ikhlas.iiwandroid.R;
import com.ikhwanul.ikhlas.iiwandroid.entities.Pembelian;
import com.ikhwanul.ikhlas.iiwandroid.utils.DateUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class PembelianAdapter extends
        RecyclerView.Adapter<PembelianAdapter.MyViewHolder> {

    private List<Pembelian> pembelianList;
    private ArrayList<Pembelian> arraylist;

    Context context;

    /**
     * View holder class
     * */
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvNoTransaksi;
        public TextView tvDateTransaction;
        public TextView tvJumlahKwitansi;
        public TextView tvJenisPaket;
        public LinearLayout layoutRainbow;

        public MyViewHolder(View view) {
            super(view);
            layoutRainbow = (LinearLayout) view.findViewById(R.id.layout_rainbow);
            tvNoTransaksi = (TextView) view.findViewById(R.id.tv_no_transaksi);
            tvDateTransaction = (TextView) view.findViewById(R.id.tv_date_transaction);
            tvJumlahKwitansi = (TextView) view.findViewById(R.id.tv_jumlah_kwitansi);
            tvJenisPaket = (TextView) view.findViewById(R.id.tv_jenis_paket);
        }
    }

    public PembelianAdapter(Context context, List<Pembelian> pembelianList) {
        this.context = context;
        this.pembelianList = pembelianList;
        this.arraylist = new ArrayList<Pembelian>();
        this.arraylist.addAll(pembelianList);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Pembelian dataPembelian = pembelianList.get(position);

        holder.tvNoTransaksi.setText(dataPembelian.getNo_transaksi());
        holder.tvJumlahKwitansi.setText(dataPembelian.getJumlah());
        holder.tvDateTransaction.setText(DateUtils.format(dataPembelian.getTgl_dibuat()));
        holder.tvJenisPaket.setText("PPC");

        if (position % 2 ==0){
            holder.layoutRainbow.setBackgroundResource(R.color.colorPrimary);
        }else{
            holder.layoutRainbow.setBackgroundResource(R.color.colorOrangeHolo);
        }

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
        pembelianList.clear();
        if (charText.length() == 0) {
            pembelianList.addAll(arraylist);
        } else {
            for (Pembelian wp : arraylist) {
                if (wp.getNo_transaksi().toLowerCase(Locale.getDefault()).contains(charText)) {
                    pembelianList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return pembelianList.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_item_pembelian,parent, false);
        return new MyViewHolder(v);
    }
}