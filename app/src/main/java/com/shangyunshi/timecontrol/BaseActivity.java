package com.shangyunshi.timecontrol;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void setToolbar(String text){
        toolbar = findViewById(R.id.tool_bar);
        if(toolbar != null){
            toolbar.setTitle(text);
        }
    }

}
