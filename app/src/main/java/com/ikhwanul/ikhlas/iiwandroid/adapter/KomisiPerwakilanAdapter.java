package com.ikhwanul.ikhlas.iiwandroid.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ikhwanul.ikhlas.iiwandroid.BuildConfig;
import com.ikhwanul.ikhlas.iiwandroid.R;
import com.ikhwanul.ikhlas.iiwandroid.entities.Komisi;
import com.ikhwanul.ikhlas.iiwandroid.utils.FormatRupiah;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class KomisiPerwakilanAdapter extends
        RecyclerView.Adapter<KomisiPerwakilanAdapter.MyViewHolder> {

    private List<Komisi> komisiPerwakilanList;
    private ArrayList<Komisi> arraylist;

    Context context;

    /**
     * View holder class
     * */
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvIdPerwakilan;
        public TextView tvNamaPerwakilan;
        public TextView tvTipeKomisi;
        public TextView tvKeterangan;
        public TextView tvJumlah;
        public TextView tvTanggalKomisi;
        public TextView tvStatusKomisi;
        public ImageView imgPerwakilan;
        public LinearLayout layoutRainbow;


        public MyViewHolder(View view) {
            super(view);
            layoutRainbow = (LinearLayout) view.findViewById(R.id.layout_rainbow);
            imgPerwakilan = (ImageView) view.findViewById(R.id.img_perwakilan);
            tvNamaPerwakilan = (TextView) view.findViewById(R.id.tv_nama_perwakilan);
            tvIdPerwakilan = (TextView) view.findViewById(R.id.tv_id_perwakilan);
            tvKeterangan = (TextView) view.findViewById(R.id.tv_keterangan);
            tvJumlah = (TextView) view.findViewById(R.id.tv_jumlah);
            tvTanggalKomisi = (TextView) view.findViewById(R.id.tv_tgl_komisi);
            tvStatusKomisi = (TextView) view.findViewById(R.id.tv_status_claim);
        }
    }

    public KomisiPerwakilanAdapter(Context context, List<Komisi> komisiPerwakilanList) {
        this.context = context;
        this.komisiPerwakilanList = komisiPerwakilanList;
        this.arraylist = new ArrayList<Komisi>();
        this.arraylist.addAll(komisiPerwakilanList);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Komisi dataKomisi = komisiPerwakilanList.get(position);
        holder.tvNamaPerwakilan.setText(dataKomisi.getNama_lengkap());
        holder.tvKeterangan.setText(dataKomisi.getKet());
        holder.tvJumlah.setText(FormatRupiah.useFormat(dataKomisi.getJumlah()));
        holder.tvTanggalKomisi.setText(dataKomisi.getTgl_dibuat());
        holder.tvStatusKomisi.setText(dataKomisi.getStatus().equals("1")? "Sudah diklaim": "Belum diklaim");
        if (position % 2 ==0){
            holder.layoutRainbow.setBackgroundResource(R.color.colorPrimary);
        }else{
            holder.layoutRainbow.setBackgroundResource(R.color.colorOrangeHolo);
        }

        if (Integer.valueOf(dataKomisi.getStatus()) == 1){
            holder.tvStatusKomisi.setBackgroundResource(R.drawable.background_with_radius4);
        }else{
            holder.tvStatusKomisi.setBackgroundResource(R.drawable.background_with_radius2);
        }

        holder.tvIdPerwakilan.setVisibility(View.GONE);
        if (!dataKomisi.getFoto().equals("") || dataKomisi.getFoto() != null){
            Picasso.with(context)
                    .load(BuildConfig.API_URL+"images_info/images_member/crop_mini/"+dataKomisi.getFoto())
                    .placeholder(R.drawable.ic_menu_profile)
                    .into(holder.imgPerwakilan);
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
        komisiPerwakilanList.clear();
        if (charText.length() == 0) {
            komisiPerwakilanList.addAll(arraylist);
        } else {
            for (Komisi wp : arraylist) {
                if (wp.getNama_lengkap().toLowerCase(Locale.getDefault()).contains(charText)) {
                    komisiPerwakilanList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return komisiPerwakilanList.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_item_komisi,parent, false);
        return new MyViewHolder(v);
    }
}