package com.shangyunshi.timecontrol.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shangyunshi.timecontrol.OnItemClickListener;
import com.shangyunshi.timecontrol.R;
import com.shangyunshi.timecontrol.model.Task;
import java.util.List;

//fragment -> adapter -> viewpager
//item -> adapter -> recyclerView

public class TaskListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "ListAdapter";

    private List<Task> mTasks;

    private OnItemClickListener<Task> mOnItemClickListener;

    public TaskListAdapter(List<Task> mTasks) {
        this.mTasks = mTasks;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_task_list, parent, false);
        return new ItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        final Task task = mTasks.get(position);
        Context context = holder.itemView.getContext();
        ((ItemViewHolder) holder).bind(context, task);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.OnItemClickListener(view, task, position);
                }
            }
        });
    }

    public List<Task> getTasks() {
        return mTasks;
    }

    @Override
    public int getItemCount() {
        return mTasks == null ? 0 : mTasks.size();
    }

    //用户输入 -> 硬盘（数据库SQLite）-> Task -> 屏幕上
    static class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView txtTitle;
        TextView txtStartTime;
        TextView txtEndTime;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txt_task_title);
            txtEndTime = itemView.findViewById(R.id.txt_ended_time);
            txtStartTime = itemView.findViewById(R.id.txt_start_time);
        }

        public void bind(Context context, Task task) {
            txtTitle.setText(context.getString(R.string.home_task_title, task.taskTitle));
            txtStartTime.setText(context.getString(R.string.home_start_time, task.startTime));
            txtEndTime.setText(context.getString(R.string.home_ended_time, task.endedTime));
        }

    }

}
