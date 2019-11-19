package com.shangyunshi.timecontrol;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.shangyunshi.timecontrol.adapter.TaskListAdapter;
import com.shangyunshi.timecontrol.db.TaskDao;
import com.shangyunshi.timecontrol.model.Task;
import java.util.List;

public class TaskActivity extends BaseActivity {

    RecyclerView mRecyclerView;

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_list);
        mRecyclerView = findViewById(R.id.recycle_view);
        TaskDao dao = new TaskDao(this);
        final String date = getIntent().getStringExtra("task_date");
        final List<Task> tasks = dao.getTasksByDate(date);
        TaskListAdapter adapter = new TaskListAdapter(tasks);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(
            new DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        );
        mRecyclerView.setAdapter(adapter);
    }

    @Override protected void onSetupToolbar(Toolbar toolbar) {
        super.onSetupToolbar(toolbar);
        toolbar.setTitle(R.string.label_task);
    }
}
