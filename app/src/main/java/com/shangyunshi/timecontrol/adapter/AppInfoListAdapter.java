package com.shangyunshi.timecontrol.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.shangyunshi.timecontrol.model.AppInfo;
import com.shangyunshi.timecontrol.R;
import java.util.List;

public class AppInfoListAdapter extends RecyclerView.Adapter<AppInfoListAdapter.AppItemViewHolder> {

    private List<AppInfo> mAppInfoList;

    public AppInfoListAdapter(List<AppInfo> appInfoList) {
        this.mAppInfoList = appInfoList;
    }

    @NonNull @Override
    public AppInfoListAdapter.AppItemViewHolder onCreateViewHolder(
        @NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.app_info_item_list, parent, false);
        return new AppItemViewHolder(v);
    }

    @Override public void onBindViewHolder(@NonNull AppItemViewHolder holder, int position) {
        AppInfo info = mAppInfoList.get(position);
        holder.bind(info);
    }

    @Override public int getItemCount() {
        return mAppInfoList == null ? 0 : mAppInfoList.size();
    }

    static class AppItemViewHolder extends RecyclerView.ViewHolder {

        ImageView mImgIcon;
        TextView mTxtAppName;

        public AppItemViewHolder(@NonNull View itemView) {
            super(itemView);
            mImgIcon = itemView.findViewById(R.id.img_icon);
            mTxtAppName = itemView.findViewById(R.id.txt_app_name);
        }

        void bind(AppInfo info) {
            mImgIcon.setImageDrawable(info.mIcon);
            mTxtAppName.setText(info.mAppName);
        }
    }

}
