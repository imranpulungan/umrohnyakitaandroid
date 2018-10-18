package com.ikhwanul.ikhlas.iiwandroid.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ikhwanul.ikhlas.iiwandroid.R;
import com.ikhwanul.ikhlas.iiwandroid.entities.KomisiPSC;
import com.ikhwanul.ikhlas.iiwandroid.utils.FormatRupiah;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class KomisiPSCAdapter extends
        RecyclerView.Adapter<KomisiPSCAdapter.MyViewHolder> {

    private List<KomisiPSC> komisiPSCList;
    private ArrayList<KomisiPSC> arraylist;

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

    public KomisiPSCAdapter(Context context, List<KomisiPSC> komisiPSCList) {
        this.context = context;
        this.komisiPSCList = komisiPSCList;
        this.arraylist = new ArrayList<KomisiPSC>();
        this.arraylist.addAll(komisiPSCList);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final KomisiPSC dataKomisi = komisiPSCList.get(position);

        holder.tvIdKomisi.setText(dataKomisi.getId_komisi());
        holder.tvTipeKomisi.setText(dataKomisi.getTipe().equals("1")? "Rekomendasi" : "Stok");
        holder.tvKeterangan.setText(dataKomisi.getKeterangan());
        holder.tvJumlah.setText(FormatRupiah.useFormat(dataKomisi.getJumlah()));
        holder.tvTanggalKomisi.setText(dataKomisi.getTgl_dibuat());
        holder.tvStatusKomisi.setText(dataKomisi.getStatus().equals("1")? "Sudah diclaim": "Belum diclaim");

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
        komisiPSCList.clear();
        if (charText.length() == 0) {
            komisiPSCList.addAll(arraylist);
        } else {
            for (KomisiPSC wp : arraylist) {
                if (wp.getId_komisi().toLowerCase(Locale.getDefault()).contains(charText)) {
                    komisiPSCList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return komisiPSCList.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_item_komisi_psc,parent, false);
        return new MyViewHolder(v);
    }
}