package com.shangyunshi.timecontrol;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

//fragment -> adapter -> viewpager
//item -> adapter -> recyclerView

public class ListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Task> mTasks;

    public ListAdapter(List<Task> mTasks) {
        this.mTasks = mTasks;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,null);
        return new ItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mTasks == null ? 0 : mTasks.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder{
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
