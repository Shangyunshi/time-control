package com.shangyunshi.timecontrol;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import com.loopeer.formitemview.FormEditItem;
import com.loopeer.formitemview.FormTextItem;
import com.shangyunshi.timecontrol.db.TaskDao;
import com.shangyunshi.timecontrol.model.Label;
import com.shangyunshi.timecontrol.model.Task;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class AddTaskActivity extends BaseActivity {

    FormEditItem mItemTitle;
    FormTextItem mItemStartTime;
    FormTextItem mItemEndTime;
    FormEditItem mItemAddress;
    FormTextItem mItemWhiteList;

    TaskDao mTaskDao;

    Label mLabel;

    Calendar mStartCalendar;
    Calendar mEndCalendar;
    SimpleDateFormat mFormat;

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        mTaskDao = new TaskDao(this);
        mItemTitle = findViewById(R.id.item_task_title);
        mItemStartTime = findViewById(R.id.item_start_time);
        mItemEndTime = findViewById(R.id.item_end_time);
        mItemWhiteList = findViewById(R.id.item_white_list);
        mItemAddress = findViewById(R.id.item_address);
        mStartCalendar = GregorianCalendar.getInstance();
        mEndCalendar = GregorianCalendar.getInstance();
        mFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    }

    @Override protected void onSetupToolbar(Toolbar toolbar) {
        toolbar.setTitle(R.string.label_add_task);
    }

    public void onItemStartTimeClick(View v) {
        new TimePickerDialog(this, AlertDialog.THEME_HOLO_LIGHT,
            new TimePickerDialog.OnTimeSetListener() {

                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    int year = mStartCalendar.get(Calendar.YEAR);
                    int month = mStartCalendar.get(Calendar.MONTH);
                    int date = mStartCalendar.get(Calendar.DATE);
                    mStartCalendar.set(year, month, date, hourOfDay, minute);
                    mItemStartTime.setContentText(mFormat.format(mStartCalendar.getTime()));
                }
            }, 12, 0, true).show();
    }

    public void onItemEndTimeClick(View v) {
        new TimePickerDialog(this, AlertDialog.THEME_HOLO_LIGHT,
            new TimePickerDialog.OnTimeSetListener() {

                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    int year = mEndCalendar.get(Calendar.YEAR);
                    int month = mEndCalendar.get(Calendar.MONTH);
                    int date = mEndCalendar.get(Calendar.DATE);
                    mEndCalendar.set(year, month, date, hourOfDay, minute);
                    mItemEndTime.setContentText(mFormat.format(mEndCalendar.getTime()));
                }
            }, 12, 0, true).show();
    }

    public void onItemWhiteListClick(View v) {
        Navigation.startLabelListActivityForResult(this);
    }

    public void onAddTaskClick(View v) {
        if (mEndCalendar.compareTo(mStartCalendar) < 0) {
            Toast.makeText(this, "end time must latter than start time!", Toast.LENGTH_SHORT)
                .show();
            return;
        }
        Task task = new Task();
        task.taskTitle = mItemTitle.getContentText();
        task.startTime = mItemStartTime.getContentText();
        task.endedTime = mItemEndTime.getContentText();
        task.location = mItemAddress.getContentText();
        if (mLabel != null) {
            task.labelId = mLabel.id;
        }
        mTaskDao.insertTask(task);
        Toast.makeText(this, "add task success!", Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 10002) {
            if (resultCode == RESULT_OK) {
                mLabel = (Label) data.getSerializableExtra("label");
                if (mLabel != null) {
                    mItemWhiteList.setContentText(mLabel.name);
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
