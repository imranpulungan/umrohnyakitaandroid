package com.ikhwanul.ikhlas.iiwandroid.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ikhwanul.ikhlas.iiwandroid.R;
import com.ikhwanul.ikhlas.iiwandroid.entities.KomisiKDM_MM;
import com.ikhwanul.ikhlas.iiwandroid.utils.FormatRupiah;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class KomisiKDMAdapter extends
        RecyclerView.Adapter<KomisiKDMAdapter.MyViewHolder> {

    private List<KomisiKDM_MM> komisiKDMList;
    private ArrayList<KomisiKDM_MM> arraylist;

    Context context;

    /**
     * View holder class
     * */
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvIdKomisi;
        public TextView tvTipeKomisi;
        public TextView tvKeterangan;
        public TextView tvJumlah;
        public TextView tvTanggalKomisi;
        public TextView tvStatusKomisi;


        public MyViewHolder(View view) {
            super(view);
            tvIdKomisi = (TextView) view.findViewById(R.id.tv_id_komisi);
            tvTipeKomisi = (TextView) view.findViewById(R.id.tv_tipe_komisi);
            tvKeterangan = (TextView) view.findViewById(R.id.tv_keterangan);
            tvJumlah = (TextView) view.findViewById(R.id.tv_jumlah);
            tvTanggalKomisi = (TextView) view.findViewById(R.id.tv_tgl_komisi);
            tvStatusKomisi = (TextView) view.findViewById(R.id.tv_status_claim);
        }
    }

    public KomisiKDMAdapter(Context context, List<KomisiKDM_MM> komisiKDMList) {
        this.context = context;
        this.komisiKDMList = komisiKDMList;
        this.arraylist = new ArrayList<KomisiKDM_MM>();
        this.arraylist.addAll(komisiKDMList);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final KomisiKDM_MM dataKomisi = komisiKDMList.get(position);

        holder.tvIdKomisi.setText(dataKomisi.getId_komisi());
        holder.tvKeterangan.setText(dataKomisi.getKeterangan());
        holder.tvJumlah.setText(FormatRupiah.useFormat(dataKomisi.getJumlah()));
        holder.tvTanggalKomisi.setText(dataKomisi.getTgl_dibuat());
        holder.tvStatusKomisi.setText(dataKomisi.getStatus());
        holder.tvTipeKomisi.setVisibility(View.GONE);
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        komisiKDMList.clear();
        if (charText.length() == 0) {
            komisiKDMList.addAll(arraylist);
        } else {
            for (KomisiKDM_MM wp : arraylist) {
                if (wp.getId_komisi().toLowerCase(Locale.getDefault()).contains(charText)) {
                    komisiKDMList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return komisiKDMList.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_item_komisi_psc,parent, false);
        return new MyViewHolder(v);
    }
}