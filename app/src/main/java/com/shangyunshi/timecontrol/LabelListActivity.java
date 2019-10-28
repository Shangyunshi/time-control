package com.shangyunshi.timecontrol;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.shangyunshi.timecontrol.adapter.LabelListAdapter;
import com.shangyunshi.timecontrol.db.LabelDao;
import com.shangyunshi.timecontrol.model.Label;

public class LabelListActivity extends BaseActivity {

    RecyclerView mRecyclerView;
    LabelDao mLabelDao;
    FloatingActionButton mFloatingActionButton;
    LabelListAdapter mLabelListAdapter;
    AlertDialog mDialog;

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_label_list);
        mLabelDao = new LabelDao(this);
        mFloatingActionButton = findViewById(R.id.fab_label);
        final View dialog = LayoutInflater.from(this).inflate(R.layout.dialog_add_label, null);
        mDialog = new AlertDialog.Builder(LabelListActivity.this)
            .setTitle("Add Label")
            .setView(dialog)
            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override public void onClick(DialogInterface dialogInterface, int i) {
                    EditText etLabel = dialog.findViewById(R.id.et_label_name);
                    final String name = etLabel.getText().toString();
                    Label label = new Label();
                    label.name = name;
                    mLabelDao.insertLabel(label);
                    mLabelListAdapter.setData(mLabelDao.getAllLabels());
                }
            })
            .setNegativeButton("Cancel", null)
            .create();
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                mDialog.show();
            }
        });
        mRecyclerView = findViewById(R.id.recycle_view);
        mLabelListAdapter = new LabelListAdapter(mLabelDao.getAllLabels());
        mRecyclerView.setAdapter(mLabelListAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(
            new DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        );
    }

    @Override protected void onSetupToolbar(Toolbar toolbar) {
        super.onSetupToolbar(toolbar);
        toolbar.setTitle("Label List");
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.label_list_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
