package com.ikhwanul.ikhlas.iiwandroid.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.ikhwanul.ikhlas.iiwandroid.R;
import com.ikhwanul.ikhlas.iiwandroid.entities.PPC;
import com.ikhwanul.ikhlas.iiwandroid.entities.Reward;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class PPCAdapter extends
        RecyclerView.Adapter<PPCAdapter.MyViewHolder> {

    private List<PPC> ppcList;
    private ArrayList<PPC> arraylist;
    Context context;

    /**
     * View holder class
     * */
    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNoTransaction,
                        tvDateTransaction,
                        tvPaket,
                        tvNamaLengkap,
                        tvPenerima;

        public MyViewHolder(View view) {
            super(view);
            tvNoTransaction = (TextView) view.findViewById(R.id.tv_no_transaksi);
            tvDateTransaction = (TextView) view.findViewById(R.id.tv_date_transaction);
            tvPaket = (TextView) view.findViewById(R.id.tv_paket);
            tvNamaLengkap = (TextView) view.findViewById(R.id.tv_nama_lengkap);
            tvPenerima= (TextView) view.findViewById(R.id.tv_penerima);
        }
    }

    public PPCAdapter(Context context, List<PPC> ppcList) {
        this.context = context;
        this.ppcList = ppcList;
        this.arraylist = new ArrayList<PPC>();
        this.arraylist.addAll(ppcList);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final PPC dataPPC = ppcList.get(position);
        holder.tvNoTransaction.setText(dataPPC.getNo_transaksi());
        holder.tvPaket.setText(dataPPC.getPaket());
        holder.tvNamaLengkap.setText(dataPPC.getNama_lengkap());
        holder.tvPenerima.setText(dataPPC.getPenerima());
        holder.tvDateTransaction.setText(dataPPC.getTgl_pembelian());
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        ppcList.clear();
        if (charText.length() == 0) {
            ppcList.addAll(arraylist);
        } else {
            for (PPC wp : arraylist) {
                if (wp.getNama_lengkap().toLowerCase(Locale.getDefault()).contains(charText)) {
                    ppcList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return ppcList.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_item_ppc,parent, false);
        return new MyViewHolder(v);
    }
}