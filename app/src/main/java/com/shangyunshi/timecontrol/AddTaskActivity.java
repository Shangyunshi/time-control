package com.shangyunshi.timecontrol;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import com.loopeer.formitemview.FormTextItem;

public class AddTaskActivity extends BaseActivity {

    FormTextItem mItemStartTime;
    FormTextItem mItemEndTime;
    FormTextItem mItemWhiteList;

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        mItemStartTime = findViewById(R.id.item_start_time);
        mItemEndTime = findViewById(R.id.item_end_time);
        mItemWhiteList = findViewById(R.id.item_white_list);
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

    }
}
