package com.shangyunshi.timecontrol;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.shangyunshi.timecontrol.adapter.TaskListAdapter;
import com.shangyunshi.timecontrol.db.TaskDao;

public class TaskActivity extends BaseActivity {

    RecyclerView mRecyclerView;

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_list);
        mRecyclerView = findViewById(R.id.recycle_view);
        TaskDao dao = new TaskDao(this);
        TaskListAdapter adapter = new TaskListAdapter(dao.getAllTasks());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);
    }

    @Override protected void onSetupToolbar(Toolbar toolbar) {
        super.onSetupToolbar(toolbar);
        toolbar.setTitle(R.string.label_task);
    }
}
