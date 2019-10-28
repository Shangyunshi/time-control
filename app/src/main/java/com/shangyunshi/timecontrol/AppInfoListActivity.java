package com.shangyunshi.timecontrol;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.shangyunshi.timecontrol.adapter.AppInfoListAdapter;
import com.shangyunshi.timecontrol.model.AppInfo;
import java.util.ArrayList;
import java.util.List;

public class AppInfoListActivity extends BaseActivity {

    RecyclerView mRecyclerView;

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_list);
        mRecyclerView = findViewById(R.id.recycle_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new AppInfoListAdapter(getPackageInfo()));
        mRecyclerView.addItemDecoration(
            new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.app_info_menu, menu);
        return true;
    }

    @Override public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_label) {
            Navigation.startLabelListActivity(this);
        }
        return super.onOptionsItemSelected(item);
    }

    private List<AppInfo> getPackageInfo() {
        // 获取已经安装的所有应用, PackageInfo　系统类，包含应用信息
        List<AppInfo> appInfoList = new ArrayList<>();
        List<PackageInfo> packages = getPackageManager().getInstalledPackages(0);
        for (int i = 0; i < packages.size(); i++) {
            PackageInfo packageInfo = packages.get(i);
            if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) { //非系统应用
                // AppInfo 自定义类，包含应用信息
                AppInfo appInfo = new AppInfo();
                appInfo.mAppName = packageInfo.applicationInfo.loadLabel(getPackageManager())
                    .toString();//获取应用名称
                appInfo.mIcon = packageInfo.applicationInfo.loadIcon(getPackageManager());//获取应用图标
                System.out.println(appInfo.toString());
                appInfoList.add(appInfo);
            } else { // 系统应用

            }
        }
        return appInfoList;
    }
}
