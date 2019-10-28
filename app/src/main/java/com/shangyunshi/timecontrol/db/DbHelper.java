package com.shangyunshi.timecontrol.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import com.shangyunshi.timecontrol.TimeControlApp;

/**
 * 用户输入 --> （数据库）SQLite --> CRUD 增删修查
 *
 * 使用数据
 *
 * 1.创建数据库 DbHelper 实例
 * 2.创建表
 * 3.封装CRUD
 */
public class DBHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "task.db";
    public static final String TASK_TABLE_NAME = "t_task";
    public static final String LABEL_TABLE_NAME = "t_label";
    private volatile static DBHelper sInstance;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public DBHelper(Context context, String name,
                    CursorFactory factory, int version) {
        super(context, name, factory, version);
        // TODO Auto-generated constructor stub
    }

    public static DBHelper getInstance() {
        if (sInstance == null) {
            sInstance = new DBHelper(TimeControlApp.getAppContext());
        }
        return sInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // TODO Auto-generated method stub
        String task_sql
            = "create table if not exists t_task (id text,title text,startTime text,endTime text,address text,role text, whiteList text)";
        String label_sql = "create table if not exists t_label(id text,name,text)";
        sqLiteDatabase.execSQL(task_sql);
        sqLiteDatabase.execSQL(label_sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int arg1, int arg2) {
        // TODO Auto-generated method stub
        onCreate(sqLiteDatabase);
    }

}
