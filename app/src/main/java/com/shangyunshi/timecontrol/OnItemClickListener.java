package com.shangyunshi.timecontrol;

import android.view.View;

public interface OnItemClickListener<T> {

    void OnItemClickListener(View itemView,T t,int pos);
}
