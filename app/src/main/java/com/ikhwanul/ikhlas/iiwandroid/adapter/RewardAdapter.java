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
import com.ikhwanul.ikhlas.iiwandroid.entities.Reward;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class RewardAdapter extends
        RecyclerView.Adapter<RewardAdapter.MyViewHolder> {

    private List<Reward> rewardList;
    private ArrayList<Reward> arraylist;
    Context context;

    /**
     * View holder class
     * */
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textIdItem ;
        public TextView textNameItem;
        public TextView textPhoneItem;
        public ImageView imgIcon;
        private ImageButton imgbtnCall;

        public MyViewHolder(View view) {
            super(view);

        }
    }

    public RewardAdapter(Context context, List<Reward> rewardList) {
        this.context = context;
        this.rewardList = rewardList;
        this.arraylist = new ArrayList<Reward>();
        this.arraylist.addAll(rewardList);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Reward dataReward= rewardList.get(position);
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        rewardList.clear();
        if (charText.length() == 0) {
            rewardList.addAll(arraylist);
        } else {
            for (Reward wp : arraylist) {
                if (wp.getBarang_reward().toLowerCase(Locale.getDefault()).contains(charText)) {
                    rewardList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return rewardList.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_item_reward,parent, false);
        return new MyViewHolder(v);
    }
}