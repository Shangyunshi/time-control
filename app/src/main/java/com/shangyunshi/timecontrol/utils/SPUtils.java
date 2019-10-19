package com.shangyunshi.timecontrol.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.shangyunshi.timecontrol.TimeControlApp;
import java.util.HashMap;

/***
 *@date 创建时间 2018/4/18 15:16
 *@author 作者: W.YuLong
 *@description SharedPreferences的单例模式，支持不同的命名
 */
public class SPUtils {
    private static volatile HashMap<String, SPUtils> instanceMap = new HashMap<>();

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    //是否是执行apply的模式，false表示为commit保存数据
    private boolean isApplyMode = false;
    private static final String DEFAULT = "default";

    private SPUtils(String name) {
        if (DEFAULT.equals(name)) {
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(
                TimeControlApp.getAppContext());
        } else {
            sharedPreferences = TimeControlApp.getAppContext()
                .getSharedPreferences(name, Context.MODE_PRIVATE);
        }
        editor = sharedPreferences.edit();
    }

    public static SPUtils get(String name) {
        if (instanceMap.get(name) == null) {
            synchronized (SPUtils.class) {
                if (instanceMap.get(name) == null) {
                    instanceMap.put(name, new SPUtils(name));
                }
            }
        }
        //这里每次get操作时强制将保存模式改为commit的方式
        instanceMap.get(name).isApplyMode = false;
        return instanceMap.get(name);
    }

    public static SPUtils get() {
        return get(DEFAULT);
    }

    // 如果用apply模式的话，得要先调用这个方法，
    // 然后链式调用后续的存储方法，最后以commit方法结尾
    public SPUtils applyMode() {
        isApplyMode = true;
        return this;
    }

    public void commit() {
        editor.commit();
    }

    public SPUtils putBoolean(String key, boolean value) {
        editor.putBoolean(key, value);
        save();
        return this;
    }

    private void save() {
        if (isApplyMode) {
            editor.apply();
        } else {
            editor.commit();
        }
    }

    public SPUtils putFloat(String key, float value) {
        editor.putFloat(key, value);
        save();
        return this;
    }

    public float getFloat(String key, float defValue) {
        return sharedPreferences.getFloat(key, defValue);
    }

    public SPUtils putLong(String key, long value) {
        editor.putLong(key, value);
        save();
        return this;
    }

    public long getLong(String key, long defValue) {
        return sharedPreferences.getLong(key, defValue);
    }

    public SPUtils putInt(String key, int value) {
        editor.putInt(key, value);
        save();
        return this;
    }

    public SPUtils putString(String key, String value) {
        editor.putString(key, value);
        save();
        return this;
    }

    public String getString(String key, String defValue) {
        return sharedPreferences.getString(key, defValue);
    }

    public void delete(String key) {
        editor.remove(key);
        save();
    }

    public int getInt(String key, int defValue) {
        return sharedPreferences.getInt(key, defValue);
    }

    public boolean getBoolean(String key, boolean defValue) {
        return sharedPreferences.getBoolean(key, defValue);
    }

}