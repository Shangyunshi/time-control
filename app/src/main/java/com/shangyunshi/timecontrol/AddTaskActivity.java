package com.shangyunshi.timecontrol;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import com.loopeer.formitemview.FormEditItem;
import com.loopeer.formitemview.FormTextItem;
import com.shangyunshi.timecontrol.db.TaskDao;
import com.shangyunshi.timecontrol.model.Task;

public class AddTaskActivity extends BaseActivity {

    FormEditItem mItemTitle;
    FormTextItem mItemStartTime;
    FormTextItem mItemEndTime;
    FormEditItem mItemAddress;
    FormTextItem mItemWhiteList;

    TaskDao mTaskDao;

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        mTaskDao = new TaskDao(this);
        mItemTitle = findViewById(R.id.item_task_title);
        mItemStartTime = findViewById(R.id.item_start_time);
        mItemEndTime = findViewById(R.id.item_end_time);
        mItemWhiteList = findViewById(R.id.item_white_list);
        mItemAddress = findViewById(R.id.item_address);
    }

    @Override protected void onSetupToolbar(Toolbar toolbar) {
        toolbar.setTitle(R.string.label_add_task);
    }

    public void onItemStartTimeClick(View v) {
        new TimePickerDialog(this, AlertDialog.THEME_HOLO_LIGHT,
            new TimePickerDialog.OnTimeSetListener() {

                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    mItemStartTime.setContentText(hourOfDay + ":" + minute);
                }
            }, 0, 0, true).show();
    }

    public void onItemEndTimeClick(View v) {
        new TimePickerDialog(this, AlertDialog.THEME_HOLO_LIGHT,
            new TimePickerDialog.OnTimeSetListener() {

                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    mItemEndTime.setContentText(hourOfDay + ":" + minute);
                }
            }, 0, 0, true).show();
    }

    public void onItemWhiteListClick(View v) {
        Navigation.startAppInfoListActivity(this);
    }

    public void onAddTaskClick(View v) {
        Task task = new Task();
        task.taskTitle = mItemTitle.getContentText();
        task.startTime = mItemStartTime.getContentText();
        task.endedTime = mItemEndTime.getContentText();
        task.location = mItemAddress.getContentText();
        //task.whiteList = mItemWhiteList.getContentText();
        mTaskDao.insertTask(task);
    }
}
