package com.shangyunshi.timecontrol;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatCheckedTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.shangyunshi.timecontrol.adapter.AppListAdapter;
import com.shangyunshi.timecontrol.db.LabelDao;
import com.shangyunshi.timecontrol.model.AppInfo;
import java.util.ArrayList;
import java.util.List;

public class AppListActivity extends BaseActivity implements OnItemClickListener<AppInfo> {

    RecyclerView mRecyclerView;
    LabelDao mLabelDao;
    AppListAdapter mAppListAdapter;

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_label_list);

        mRecyclerView = findViewById(R.id.recycle_view);
        mAppListAdapter = new AppListAdapter(getAppList());
        mAppListAdapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(mAppListAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(
            new DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        );
    }

    @Override protected void onSetupToolbar(Toolbar toolbar) {
        super.onSetupToolbar(toolbar);
        toolbar.setTitle("App List");
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.app_list_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_app_confirm) {
            Intent intent = new Intent();
            intent.putExtra("app_list", mAppListAdapter.getSelectedApps());
            setResult(RESULT_OK, intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override public void OnItemClickListener(View itemView, AppInfo label,int pos) {
        AppCompatCheckedTextView checkedTextView = itemView.findViewById(R.id.txt_label_name);
        checkedTextView.toggle();
    }

    private List<AppInfo> getAppList() {
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
               // appInfo.mIcon = packageInfo.applicationInfo.loadIcon(getPackageManager());//获取应用图标
                System.out.println(appInfo.toString());
                appInfoList.add(appInfo);
            } else { // 系统应用

            }
        }
        return appInfoList;
    }
}
