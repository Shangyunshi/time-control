package com.shangyunshi.timecontrol;

import android.content.Context;
import android.content.Intent;

//0. git pull 拉取远程仓库的文件到本地仓库
//1. git add . 把本地新增 缓存添加到缓存区
//2. git commit -m ' message ' 把缓存区文件添加到本地仓库
//3. git push 本地仓库——远程仓库
public class Navigation {

    private Navigation() {
    }

    public static void startAddTaskActivityForResult(HomeActivity activity) {
        Intent intent = new Intent(activity, AddTaskActivity.class);
        activity.startActivityForResult(intent, 10003);
    }

    public static void startHomeActivity(Context context) {
        startActivity(context, HomeActivity.class);
    }

    //AuzActivity -> HomeActivity
    private static void startActivity(Context context, Class clz) {
        Intent intent = new Intent(context, clz);
        context.startActivity(intent);
    }

    public static void startLabelListActivityForResult(AddTaskActivity activity) {
        Intent intent = new Intent(activity, LabelListActivity.class);
        activity.startActivityForResult(intent, 10002);
    }

    public static void startAppListActivityForResult(LabelListActivity activity) {
        Intent intent = new Intent(activity, AppListActivity.class);
        activity.startActivityForResult(intent, 10001);
    }

    public static void startTaskActivity(Context context, String date) {
        Intent intent = new Intent(context, TaskActivity.class);
        intent.putExtra("task_date", date);
        context.startActivity(intent);
    }
}
