package com.shangyunshi.timecontrol;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.shangyunshi.timecontrol.adapter.LabelListAdapter;
import com.shangyunshi.timecontrol.db.LabelDao;
import com.shangyunshi.timecontrol.model.AppInfo;
import com.shangyunshi.timecontrol.model.Label;
import java.util.ArrayList;

public class LabelListActivity extends BaseActivity implements OnItemClickListener<Label> {

    RecyclerView mRecyclerView;
    LabelListAdapter mInfoListAdapter;
    LabelDao mLabelDao;
    ArrayList<Label> mLabels;
    int  mCurLabelIndex;
    FloatingActionButton mFloatingActionButton;
    AlertDialog mDialog;

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_list);
        mRecyclerView = findViewById(R.id.recycle_view);
        mFloatingActionButton = findViewById(R.id.fab_label);
        final View dialog = LayoutInflater.from(this).inflate(R.layout.dialog_add_label, null);
        mLabelDao = new LabelDao(this);
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
                    mInfoListAdapter.setData(mLabelDao.getAllLabels());
                }
            })
            .setNegativeButton("Cancel", null)
            .create();
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                mDialog.show();
            }
        });
        mInfoListAdapter = new LabelListAdapter(mLabels = mLabelDao.getAllLabels());
        mInfoListAdapter.setOnItemClickListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mInfoListAdapter);
        mRecyclerView.addItemDecoration(
            new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.label_list_menu, menu);
        return true;
    }

    @Override public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_label_confirm) {
           Intent intent = new Intent();
           intent.putExtra("label",mLabels.get(mCurLabelIndex));
           setResult(RESULT_OK,intent);
           finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override public void OnItemClickListener(View itemView, Label label,int pos) {
        mCurLabelIndex = pos;
        Navigation.startAppListActivityForResult(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 10001) {
            if (resultCode == RESULT_OK) {
                ArrayList<AppInfo> appInfos = (ArrayList<AppInfo>) data.getSerializableExtra("app_list");
                mLabels.get(mCurLabelIndex).appInfos = appInfos;
                mInfoListAdapter.notifyDataSetChanged();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
