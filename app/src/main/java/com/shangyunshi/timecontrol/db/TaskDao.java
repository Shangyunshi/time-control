package com.shangyunshi.timecontrol.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;
import com.shangyunshi.timecontrol.model.Task;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//DAO(Data Access Object)数据访问对象 访问 TASK 表
public class TaskDao {

    private static final String TAG = "TaskDao";

    // String task_sql = "create table if not exists t_task (id text,title text,startTime text,endTime text,address text,role text, whiteList text)";
    // 列定义
    private final String[] KEYS_COLUMNS = new String[] { "id", "title", "startTime", "endTime",
        "address", "role", "whiteList" };

    private Context mContext;
    private DBHelper mUserDBHelper;

    public TaskDao(Context context) {
        this.mContext = context;
        mUserDBHelper = DBHelper.getInstance();
    }

    /**
     * 插入一条Task
     */
    public boolean insertTask(Task task) {
        SQLiteDatabase db = null;

        try {
            db = mUserDBHelper.getWritableDatabase();

            db.beginTransaction();

            ContentValues contentValues = new ContentValues();
            contentValues.put("id", UUID.randomUUID().toString());
            contentValues.put("title", task.taskTitle);
            contentValues.put("startTime", task.startTime);
            contentValues.put("endTime", task.endedTime);
            contentValues.put("address", task.location);
            contentValues.put("role", task.role);
            contentValues.put("whiteList", task.labelId);

            db.insertOrThrow(DBHelper.TASK_TABLE_NAME, null, contentValues);

            db.setTransactionSuccessful();
            return true;
        } catch (SQLiteConstraintException e) {
            Toast.makeText(mContext, "插入失败", Toast.LENGTH_SHORT).show();
        } finally {
            if (db != null) {
                db.endTransaction();
                db.close();
            }
        }
        return false;
    }

    /**
     * 更新Task
     */
    public boolean updateTask(Task task) {
        SQLiteDatabase db = null;
        try {
            db = mUserDBHelper.getWritableDatabase();
            db.beginTransaction();
            ContentValues contentValues = new ContentValues();
            contentValues.put("title", task.taskTitle);
            contentValues.put("startTime", task.startTime);
            contentValues.put("endTime", task.endedTime);
            contentValues.put("address", task.location);
            contentValues.put("role", task.role);
            //contentValues.put("whiteList", task.whiteList);
            db.update(DBHelper.TASK_TABLE_NAME, contentValues, "id=?",
                new String[] { task.id });
            db.setTransactionSuccessful();
            return true;
        } catch (SQLiteConstraintException e) {
            Toast.makeText(mContext, "主键重复", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e(TAG, "", e);
        } finally {
            if (db != null) {
                db.endTransaction();
                db.close();
            }
        }
        return false;
    }

    /**
     * 查询 title
     */

    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();
        SQLiteDatabase db = null;
        Cursor cursor = null;

        try {
            db = mUserDBHelper.getReadableDatabase();
            cursor = db.query(DBHelper.TASK_TABLE_NAME, KEYS_COLUMNS, "", null,
                null, null, null);

            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    Task task = new Task();
                    task.id = cursor.getString(cursor.getColumnIndex("id"));
                    task.taskTitle = cursor.getString(cursor.getColumnIndex("title"));
                    task.startTime = cursor.getString(cursor.getColumnIndex("startTime"));
                    task.endedTime = cursor.getString(cursor.getColumnIndex("endTime"));
                    task.location = cursor.getString(cursor.getColumnIndex("address"));
                    task.role = cursor.getString(cursor.getColumnIndex("role"));
                    task.labelId = cursor.getString(cursor.getColumnIndex("whiteList"));
                    tasks.add(task);
                }
                return tasks;
            }
        } catch (Exception e) {
            Log.e(TAG, "error:", e);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }

        return tasks;
    }

    public List<Task> getTasksByDate(String date) {
        List<Task> tasks = new ArrayList<>();
        SQLiteDatabase db = null;
        Cursor cursor = null;

        try {
            db = mUserDBHelper.getReadableDatabase();
            cursor = db.query(
                    DBHelper.TASK_TABLE_NAME,
                KEYS_COLUMNS,
                KEYS_COLUMNS[2] + " LIKE ?",
                new String[]{"%" + date + "%"},
                null,
                null,
                null);

            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    Task task = new Task();
                    task.id = cursor.getString(cursor.getColumnIndex("id"));
                    task.taskTitle = cursor.getString(cursor.getColumnIndex("title"));
                    task.startTime = cursor.getString(cursor.getColumnIndex("startTime"));
                    task.endedTime = cursor.getString(cursor.getColumnIndex("endTime"));
                    task.location = cursor.getString(cursor.getColumnIndex("address"));
                    task.role = cursor.getString(cursor.getColumnIndex("role"));
                    task.labelId = cursor.getString(cursor.getColumnIndex("whiteList"));
                    tasks.add(task);
                }
                return tasks;
            }
        } catch (Exception e) {
            Log.e(TAG, "error:", e);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }

        return tasks;
    }

    /**
     * 删除Task
     */
    public boolean deleteTask(String id) {
        SQLiteDatabase db = null;

        try {
            db = mUserDBHelper.getWritableDatabase();
            db.beginTransaction();
            String[] selectargs = new String[] { id };
            db.delete(DBHelper.TASK_TABLE_NAME, " id=? ", selectargs);
            db.setTransactionSuccessful();
            return true;
        } catch (Exception e) {
            Log.e(TAG, "", e);
        } finally {
            if (db != null) {
                db.endTransaction();
                db.close();
            }
        }
        return false;
    }

}
