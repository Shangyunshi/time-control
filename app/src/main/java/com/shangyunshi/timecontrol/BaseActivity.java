package com.shangyunshi.timecontrol;

import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;

public class BaseActivity extends AppCompatActivity {

    private boolean mHasParent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        try {
            mHasParent = NavUtils.getParentActivityName(this, getComponentName()) != null;
        } catch (PackageManager.NameNotFoundException e) {
        }
        Toolbar toolbar = findViewById(R.id.tool_bar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            if (mHasParent) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
            onSetupToolbar(toolbar);
        }
    }

    protected void onSetupToolbar(Toolbar toolbar) {

    }


}
