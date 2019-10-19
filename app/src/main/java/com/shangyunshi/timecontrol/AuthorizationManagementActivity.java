package com.shangyunshi.timecontrol;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import com.afollestad.materialdialogs.DialogBehavior;
import com.afollestad.materialdialogs.MaterialDialog;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//1.绘制权限界面
//2.java 关键字
public class AuthorizationManagementActivity extends AppCompatActivity {

    private SwitchCompat[] switches = new SwitchCompat[4];
    private Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization_management);

        switches[0] = findViewById(R.id.switch_auz_1);
        switches[1] = findViewById(R.id.switch_auz_2);
        switches[2] = findViewById(R.id.switch_auz_3);
        switches[3] = findViewById(R.id.switch_auz_4);

        btnNext = findViewById(R.id.btn_next);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.startHomeActivity(AuthorizationManagementActivity.this);
                finish();
            }
        });

        // AlertDialog dialog = new AlertDialog.Builder(this)
        //     .setTitle("XXXX")
        //     .setMessage("XXXXXXXXXXXXXXXXXX")
        //     .create();
        //
        // dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        // dialog.setCanceledOnTouchOutside(false);//点击屏幕不消失
        //
        // dialog.show();

    }


}
