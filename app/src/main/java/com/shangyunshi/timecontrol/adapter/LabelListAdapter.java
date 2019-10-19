package com.shangyunshi.timecontrol.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LabelListAdapter extends RecyclerView.Adapter {

    @NonNull @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override public int getItemCount() {
        return 0;
    }

    static class LabelItemViewHolder extends RecyclerView.ViewHolder{

        TextView txtLabel;

        public LabelItemViewHolder(@NonNull View itemView) {
            super(itemView);
        }


    }
}
