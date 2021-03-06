package com.ikhwanul.ikhlas.iiwandroid.adapter;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ikhwanul.ikhlas.iiwandroid.R;
import com.ikhwanul.ikhlas.iiwandroid.activities.DetailJamaahActivity;
import com.ikhwanul.ikhlas.iiwandroid.entities.Jamaah;
import com.ikhwanul.ikhlas.iiwandroid.utils.PDFTools;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class JamaahAdapter extends
        RecyclerView.Adapter<JamaahAdapter.MyViewHolder> {

    private List<Jamaah> jamaahList;
    private ArrayList<Jamaah> arraylist;
    boolean isHistory;
    Context context;

    /**
     * View holder class
     */
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvNoKwitansi;
        public TextView tvNameJamaah;
        public TextView tvDateCreated;
        public TextView textPhoneItem;
        public TextView tvDateGo;
        public TextView tvHarga;
        private ImageButton imgbtnCall;
        private ImageButton imgbtnDownload;
        private LinearLayout layoutRainbow;
        public MyViewHolder(View view) {
            super(view);
            layoutRainbow = (LinearLayout) view.findViewById(R.id.layout_rainbow);
            tvHarga = (TextView) view.findViewById(R.id.tv_harga);
            tvDateGo = (TextView) view.findViewById(R.id.tv_date_go);
            tvNameJamaah = (TextView) view.findViewById(R.id.tv_name_jamaah);
            imgbtnCall = (ImageButton) view.findViewById(R.id.imgbtn_call);
            imgbtnDownload = (ImageButton) view.findViewById(R.id.imgbtn_download);
            tvNoKwitansi = (TextView) view.findViewById(R.id.tv_no_kwitansi);
            tvDateCreated = (TextView) view.findViewById(R.id.tv_date_created);
            textPhoneItem = (TextView) view.findViewById(R.id.tv_phone_jamaah);
        }
    }

    public JamaahAdapter(Context context, List<Jamaah> jamaahList, boolean isHistory) {
        this.context = context;
        this.jamaahList = jamaahList;
        this.arraylist = new ArrayList<Jamaah>();
        this.arraylist.addAll(jamaahList);
        this.isHistory = isHistory;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Jamaah dataJamaah = jamaahList.get(position);
        holder.tvHarga.setText("$"+dataJamaah.getHarga());
        holder.tvNoKwitansi.setText(dataJamaah.getNo_kwitansi());
        holder.tvDateCreated.setText(dataJamaah.getTgl_dibuat());
        holder.textPhoneItem.setText(dataJamaah.getNo_hp());
        holder.tvNameJamaah.setText(dataJamaah.getNama_jamaah());
        holder.tvDateGo.setText(dataJamaah.getTgl_berangkat());

        if (position % 2 ==0){
            holder.layoutRainbow.setBackgroundResource(R.color.colorPrimary);
        }else{
            holder.layoutRainbow.setBackgroundResource(R.color.colorOrangeHolo);
        }


        if (isHistory){
            holder.imgbtnDownload.setVisibility(View.GONE);
        }

        holder.imgbtnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeCall(dataJamaah.getNo_hp());
                Toast.makeText(context, dataJamaah.getNo_hp(), Toast.LENGTH_SHORT).show();
            }
        });

        holder.imgbtnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(context);
                builder.setCancelable(true);
                builder.setTitle("Download Kwitansi");
                builder.setMessage("Yakin ingin download Kwitansi sekarang?");
                builder.setPositiveButton("Download",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                PDFTools.showPDFUrl(context, "https://ikhwanulikhlaswisata.com/perwakilan/pdf/pdf.php?page=jamaahwithandroid&id=" + dataJamaah.getId_pendaftaraan());
                            }
                        });
                android.support.v7.app.AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailJamaahActivity.class);
                intent.putExtra("data", dataJamaah);
                context.startActivity(intent);
            }
        });
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        jamaahList.clear();
        if (charText.length() == 0) {
            jamaahList.addAll(arraylist);
        } else {
            for (Jamaah wp : arraylist) {
                if (wp.getNama_jamaah().toLowerCase(Locale.getDefault()).contains(charText)) {
                    jamaahList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

    private void makeCall(String phoneNumber) {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) context,
                    new String[]{Manifest.permission.CALL_PHONE},
                    1);
        } else {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel: " + phoneNumber.toString()));
            context.startActivity(intent);
        }
    }

    @Override
    public int getItemCount() {
        return jamaahList.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_item_jamaah, parent, false);
        return new MyViewHolder(v);
    }
}