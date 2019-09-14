package com.shangyunshi.timecontrol.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 *  用户输入 --> （数据库）SQLite --> CRUD 增删修查
 *
 *  使用数据
 *
 *  1.创建数据库 DbHelper 实例
 *  2.创建表
 *  3.封装CRUD
 */
public class DbHelper extends SQLiteOpenHelper {
	private static final int DB_VERSION = 1;
	private static final String DB_NAME = "employclent.db";
	public static final String TASKTABLE_NAME = "t_task";

	public DbHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	public DbHelper(Context context, String name,
                    CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase sqLiteDatabase) {
		// TODO Auto-generated method stub
		String task_sql = "create table if not exists t_task (id text,title text,startTime text,endTime text,address text,role text, whiteList text)";
		sqLiteDatabase.execSQL(task_sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase sqLiteDatabase, int arg1, int arg2) {
		// TODO Auto-generated method stub
		onCreate(sqLiteDatabase);
	}


}
