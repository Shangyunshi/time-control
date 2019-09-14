package com.shangyunshi.timecontrol;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shangyunshi.timecontrol.db.TaskDao;
import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private ListAdapter mAdapter;
    private TaskDao mTaskDao;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTaskDao = new TaskDao(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list,container,false);
        mRecyclerView = v.findViewById(R.id.recycle_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(new ListAdapter(test()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        return v;
    }

    private List<Task> test(){
        Task task = new Task();

        task.taskTitle = getString(R.string.home_task_title,"test");
        task.startTime = getString(R.string.home_start_time,"16:10");
        task.endedTime = getString(R.string.home_ended_time,"18:10");

        mTaskDao.insertTask(task);
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
