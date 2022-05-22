package com.example.genshinimpactgachasimulator.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.genshinimpactgachasimulator.R;
import com.example.genshinimpactgachasimulator.database.Item;

import java.util.ArrayList;

public class WishResultAdapter extends RecyclerView.Adapter<WishResultAdapter.ViewHolder> {

    ArrayList<Item> arrayOfItems;

    public WishResultAdapter(ArrayList<Item> arrayOfItems) {
        this.arrayOfItems = arrayOfItems;
    }

    @Override
    public WishResultAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_wish_result, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WishResultAdapter.ViewHolder holder, int position) {

        if (arrayOfItems.get(position).getItemRarity().equals(5)) {
            holder.tvItemRarity.setText("⭐⭐⭐⭐⭐");
            holder.layout.setBackgroundColor(holder.layout.getResources().getColor(R.color.orange));
        } else if (arrayOfItems.get(position).getItemRarity().equals(4)) {
            holder.tvItemRarity.setText("⭐⭐⭐⭐");
            holder.layout.setBackgroundColor(holder.layout.getResources().getColor(R.color.purple));
        }
        holder.tvItemName.setText(arrayOfItems.get(position).getItemName());
        holder.tvItemType.setText(arrayOfItems.get(position).getItemType());
    }

    @Override
    public int getItemCount() {
        return arrayOfItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout layout;
        TextView tvItemRarity, tvItemName, tvItemType;

        public ViewHolder(View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.wish_result_layout);
            tvItemRarity = itemView.findViewById(R.id.wish_result_tv_rarity);
            tvItemName = itemView.findViewById(R.id.wish_result_tv_item_name);
            tvItemType = itemView.findViewById(R.id.wish_result_tv_item_type);
        }
    }
}
