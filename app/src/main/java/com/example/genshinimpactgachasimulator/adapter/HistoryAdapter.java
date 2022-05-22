package com.example.genshinimpactgachasimulator.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.genshinimpactgachasimulator.R;
import com.example.genshinimpactgachasimulator.database.History;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private ArrayList<History> arrayOfHistory;

    public HistoryAdapter(ArrayList<History> arrayOfHistory) {
        this.arrayOfHistory = arrayOfHistory;
    }

    @Override
    public HistoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_history, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HistoryAdapter.ViewHolder holder, int position) {
        holder.tvItemType.setText(arrayOfHistory.get(position).getItem_type());
        holder.tvItemName.setText(arrayOfHistory.get(position).getItem_name());
        holder.tvWishType.setText(arrayOfHistory.get(position).getWish_type());
        holder.tvTimeReceived.setText(arrayOfHistory.get(position).getTime_received());
    }

    @Override
    public int getItemCount() {
        return arrayOfHistory.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvItemType, tvItemName, tvWishType, tvTimeReceived;

        public ViewHolder(View itemView) {
            super(itemView);
            tvItemType = itemView.findViewById(R.id.history_tv_item_type);
            tvItemName = itemView.findViewById(R.id.history_tv_item_name);
            tvWishType = itemView.findViewById(R.id.history_tv_wish_type);
            tvTimeReceived = itemView.findViewById(R.id.history_tv_time_received);
        }
    }
}
