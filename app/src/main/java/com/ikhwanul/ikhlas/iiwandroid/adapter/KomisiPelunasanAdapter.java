package com.ikhwanul.ikhlas.iiwandroid.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ikhwanul.ikhlas.iiwandroid.BuildConfig;
import com.ikhwanul.ikhlas.iiwandroid.R;
import com.ikhwanul.ikhlas.iiwandroid.entities.Komisi;
import com.ikhwanul.ikhlas.iiwandroid.entities.KomisiPelunasan;
import com.ikhwanul.ikhlas.iiwandroid.utils.FormatRupiah;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class KomisiPelunasanAdapter extends
        RecyclerView.Adapter<KomisiPelunasanAdapter.MyViewHolder> {

    private List<KomisiPelunasan> komisiPelunasanList;
    private ArrayList<KomisiPelunasan> arraylist;

    Context context;

    /**
     * View holder class
     * */
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvIdKomisi;
        public TextView tvNamaJamaah;
        public TextView tvKeterangan;
        public TextView tvJumlah;
        public TextView tvTanggalKomisi;
        public TextView tvStatusKomisi;

        public MyViewHolder(View view) {
            super(view);
            tvIdKomisi = (TextView) view.findViewById(R.id.tv_id_komisi);
            tvNamaJamaah = (TextView) view.findViewById(R.id.tv_nama_jamaah);
            tvKeterangan = (TextView) view.findViewById(R.id.tv_keterangan);
            tvJumlah = (TextView) view.findViewById(R.id.tv_jumlah);
            tvTanggalKomisi = (TextView) view.findViewById(R.id.tv_tgl_komisi);
            tvStatusKomisi = (TextView) view.findViewById(R.id.tv_status_claim);
        }
    }

    public KomisiPelunasanAdapter(Context context, List<KomisiPelunasan> komisiPelunasanList) {
        this.context = context;
        this.komisiPelunasanList = komisiPelunasanList;
        this.arraylist = new ArrayList<KomisiPelunasan>();
        this.arraylist.addAll(komisiPelunasanList);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final KomisiPelunasan dataKomisi = komisiPelunasanList.get(position);
        holder.tvIdKomisi.setText(dataKomisi.getNo_kwitansi());
        holder.tvKeterangan.setText(dataKomisi.getKet());
        holder.tvNamaJamaah.setText(dataKomisi.getNama_jamaah());
        holder.tvJumlah.setText(FormatRupiah.useFormat(dataKomisi.getJumlah()));
        holder.tvTanggalKomisi.setText(dataKomisi.getTgl_dibuat());
        holder.tvStatusKomisi.setText(dataKomisi.getStatus().equals("1")? "Sudah diklaim": "Belum diklaim");
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        komisiPelunasanList.clear();
        if (charText.length() == 0) {
            komisiPelunasanList.addAll(arraylist);
        } else {
            for (KomisiPelunasan wp : arraylist) {
                if (wp.getNama_jamaah().toLowerCase(Locale.getDefault()).contains(charText)) {
                    komisiPelunasanList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return komisiPelunasanList.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_item_komisi_pelunasan ,parent, false);
        return new MyViewHolder(v);
    }
}