package com.example.genshinimpactgachasimulator.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.genshinimpactgachasimulator.R;
import com.example.genshinimpactgachasimulator.database.Item;

import java.util.ArrayList;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.ViewHolder>{

    private ArrayList<Item> arrayOfBannerContents;

    public DetailAdapter(ArrayList<Item> arrayOfBannerContents) {
        this.arrayOfBannerContents = arrayOfBannerContents;
    }

    @NonNull
    @Override
    public DetailAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_detail, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailAdapter.ViewHolder holder, int position) {
        holder.tvItemType.setText(arrayOfBannerContents.get(position).getItemType());
        holder.tvItemName.setText(arrayOfBannerContents.get(position).getItemName());
        holder.tvItemRarity.setText(arrayOfBannerContents.get(position).getItemRarity().toString());
    }

    @Override
    public int getItemCount() {
        return arrayOfBannerContents.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvItemType, tvItemName, tvItemRarity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItemType = itemView.findViewById(R.id.detail_tv_item_type);
            tvItemName = itemView.findViewById(R.id.detail_tv_item_name);
            tvItemRarity = itemView.findViewById(R.id.detail_tv_item_rarity);
        }
    }
}
