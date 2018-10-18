package com.ikhwanul.ikhlas.iiwandroid.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ikhwanul.ikhlas.iiwandroid.R;
import com.ikhwanul.ikhlas.iiwandroid.entities.KDMBelanja;
import com.ikhwanul.ikhlas.iiwandroid.utils.FormatRupiah;

import java.util.List;


public class HomeInfoAdapter extends
        RecyclerView.Adapter<HomeInfoAdapter.MyViewHolder> {

    private List<KDMBelanja> infoList;
    Context context;

    /**
     * View holder class
     * */
    public class MyViewHolder extends RecyclerView.ViewHolder implements RecyclerView.OnClickListener{
        public TextView textLabel ;
        public TextView textNumber;
        public TextView textNotes;
        public LinearLayout itemInfo;
        public MyViewHolder(View view) {
            super(view);
            textNumber = (TextView)view.findViewById(R.id.tv_number);
            textLabel = (TextView)view.findViewById(R.id.tv_label);
            itemInfo = (LinearLayout) view.findViewById(R.id.item_info);
        }

        @Override
        public void onClick(View v) {

        }
    }

    public HomeInfoAdapter(Context context, List<KDMBelanja> infoList) {
        this.context = context;
        this.infoList = infoList;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final KDMBelanja dataBelanja = infoList.get(position);

        if (position % 2 == 0)
            holder.itemInfo.setBackgroundResource(R.drawable.background_with_radius3);
        else
            holder.itemInfo.setBackgroundResource(R.drawable.background_with_radius2);

        if (dataBelanja.isIsmoney()){
            holder.textNumber.setText(FormatRupiah.useFormat(dataBelanja.getNumber()));
        }else{
            holder.textNumber.setText(dataBelanja.getNumber());
        }

//        if (dataBelanja.getLabel().length() > 30 ){
//            holder.textLabel.setText(dataBelanja.getLabel().substring(0,27) + "...");
//        }else{
            holder.textLabel.setText(dataBelanja.getLabel());
//        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

    }

    @Override
    public int getItemCount() {
        return infoList.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_item_info, parent, false);

        if (v.getLayoutParams ().width == RecyclerView.LayoutParams.MATCH_PARENT)
            v.getLayoutParams ().width = parent.getWidth ();
        return new MyViewHolder(v);
    }
}