package com.shangyunshi.timecontrol;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private ListAdapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list,container);
        mRecyclerView = v.findViewById(R.id.recycle_view);
        mRecyclerView.setAdapter(new ListAdapter(test()));
        return v;
    }

    private List<Task> test(){
        Task task = new Task();

        task.taskTitle = "Task Title";
        task.startTime = "Start Time";
        task.endedTime = "Ended Time";

        List<Task> tasks = new ArrayList<>();
        tasks.add(task);
        tasks.add(task);
        tasks.add(task);
        tasks.add(task);
        tasks.add(task);
        tasks.add(task);
        tasks.add(task);
        tasks.add(task);

        return tasks;
    }
}
