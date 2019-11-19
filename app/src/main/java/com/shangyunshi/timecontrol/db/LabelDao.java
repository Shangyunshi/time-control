package com.shangyunshi.timecontrol.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;
import com.shangyunshi.timecontrol.model.Label;
import java.util.ArrayList;
import java.util.UUID;

public class LabelDao {

    private static final String TAG = "TaskDao";

    // String task_sql = "create table if not exists t_task (id text,title text,startTime text,endTime text,address text,role text, whiteList text)";
    // 列定义
    private final String[] KEYS_COLUMNS = new String[] { "id", "name" };

    private Context mContext;
    private DBHelper mUserDBHelper;

    public LabelDao(Context context) {
        this.mContext = context;
        mUserDBHelper = DBHelper.getInstance();
    }

    /**
     * 插入一条Label
     */
    public boolean insertLabel(Label label) {
        SQLiteDatabase db = null;

        try {
            db = mUserDBHelper.getWritableDatabase();

            db.beginTransaction();

            ContentValues contentValues = new ContentValues();
            contentValues.put("id", UUID.randomUUID().toString());
            contentValues.put("name", label.name);

            db.insertOrThrow(DBHelper.LABEL_TABLE_NAME, null, contentValues);

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
     * 查询 title
     */

    public ArrayList<Label> getAllLabels() {
        ArrayList<Label> labels = new ArrayList<>();
        SQLiteDatabase db = null;
        Cursor cursor = null;

        try {
            db = mUserDBHelper.getReadableDatabase();
            cursor = db.query(DBHelper.LABEL_TABLE_NAME, KEYS_COLUMNS, "", null,
                null, null, null);

            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    Label label = new Label();
                    label.id = cursor.getString(cursor.getColumnIndex("id"));
                    label.name = cursor.getString(cursor.getColumnIndex("name"));
                    labels.add(label);
                }
                return labels;
            }
        } catch (Exception e) {
            Log.e(TAG, "", e);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }

        return labels;
    }

    public Label getLabelById(String id) {
        Label label = null;
        SQLiteDatabase db = null;
        Cursor cursor = null;

        try {
            db = mUserDBHelper.getReadableDatabase();
            String[] args = new String[] { id };
            cursor = db.query(DBHelper.LABEL_TABLE_NAME, KEYS_COLUMNS, "id=? ", args,
                null, null, null);

            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    label = new Label();
                    label.id = cursor.getString(cursor.getColumnIndex("id"));
                    label.name = cursor.getString(cursor.getColumnIndex("name"));
                }
                return label;
            }
        } catch (Exception e) {
            Log.e(TAG, "error: ", e);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }

        return label;
    }

    /**
     * 删除Task
     */
    public boolean deleteLabel(String id) {
        SQLiteDatabase db = null;

        try {
            db = mUserDBHelper.getWritableDatabase();
            db.beginTransaction();
            String[] selectargs = new String[] { id };
            db.delete(DBHelper.LABEL_TABLE_NAME, " id=? ", selectargs);
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
