package com.shangyunshi.timecontrol;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.shangyunshi.timecontrol.adapter.TaskListAdapter;

public class TaskItemTouchCallback extends ItemTouchHelper.Callback {

    private TaskListAdapter mAdapter;

    public TaskItemTouchCallback(TaskListAdapter adapter) {
        this.mAdapter = adapter;
    }

    @Override
    public int getMovementFlags(
        @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        int drag = ItemTouchHelper.LEFT;
        int swipe = ItemTouchHelper.LEFT;
        return makeMovementFlags(drag, swipe);
    }

    @Override
    public boolean onMove(
        @NonNull RecyclerView recyclerView,
        @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        final int position = viewHolder.getAdapterPosition();
        mAdapter.getTasks().remove(position);
        mAdapter.notifyItemRemoved(position);
    }
}
