package com.shangyunshi.timecontrol.model;

import android.graphics.drawable.Drawable;

public class AppInfo {

    public Drawable mIcon;
    public String mAppName;
    public String mTag;

    public AppInfo() {
    }

    public AppInfo(Drawable icon, String appName) {
        this.mIcon = icon;
        this.mAppName = appName;
    }


}
