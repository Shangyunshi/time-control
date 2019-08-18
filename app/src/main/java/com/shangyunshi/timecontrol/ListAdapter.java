package com.shangyunshi.timecontrol;

import android.content.ClipData;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

//fragment -> adapter -> viewpager
//item -> adapter -> recyclerView

public class ListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "ListAdapter";

    private List<Task> mTasks;

    public ListAdapter(List<Task> mTasks) {
        this.mTasks = mTasks;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
        return new ItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Task task = mTasks.get(position);
        ((ItemViewHolder)holder).bind(task);
    }

    @Override
    public int getItemCount() {
        return mTasks == null ? 0 : mTasks.size();
    }

    //用户输入 -> 硬盘（数据库SQLite）-> Task -> 屏幕上
   static class ItemViewHolder extends RecyclerView.ViewHolder{

        TextView txtTitle;
        TextView txtStartTime;
        TextView txtEndTime;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txt_task_title);
            txtEndTime = itemView.findViewById(R.id.txt_ended_time);
            txtStartTime = itemView.findViewById(R.id.txt_start_time);
        }

        public void bind(Task task){
            txtTitle.setText(task.taskTitle);
            txtStartTime.setText(task.startTime);
            txtEndTime.setText(task.endedTime);
        }

    }

}
