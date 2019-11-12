package com.shangyunshi.timecontrol.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatCheckedTextView;
import androidx.recyclerview.widget.RecyclerView;
import com.shangyunshi.timecontrol.OnItemClickListener;
import com.shangyunshi.timecontrol.R;
import com.shangyunshi.timecontrol.model.AppInfo;
import com.shangyunshi.timecontrol.model.Label;
import java.util.ArrayList;
import java.util.List;

public class AppListAdapter extends RecyclerView.Adapter<AppListAdapter.LabelItemViewHolder> {

    private List<AppInfo> mAppInfos;
    private ArrayList<AppInfo> mSelectedApps = new ArrayList<>();
    private OnItemClickListener<AppInfo> mOnItemClickListener;

    public AppListAdapter(List<AppInfo> appInfos) {
        this.mAppInfos = appInfos;

    }

    public AppListAdapter() {
        this.mAppInfos = new ArrayList<>();
    }

    public void setOnItemClickListener(OnItemClickListener<AppInfo> listener) {
        this.mOnItemClickListener = listener;
    }

    @NonNull @Override
    public AppListAdapter.LabelItemViewHolder onCreateViewHolder(
        @NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_label_list, parent, false);
        return new LabelItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final LabelItemViewHolder holder, final int position) {
        final AppInfo info = mAppInfos.get(position);
        holder.bind(info);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                if (mOnItemClickListener != null) {
                    if (!holder.txtLabel.isChecked()) {
                        mSelectedApps.add(info);
                    }else {
                        mSelectedApps.remove(info);
                    }
                    mOnItemClickListener.OnItemClickListener(view, info,position);
                }
            }
        });
    }

    @Override public int getItemCount() {
        return mAppInfos == null ? 0 : mAppInfos.size();
    }

    public void setData(List<AppInfo> appInfos) {
        mAppInfos.clear();
        mAppInfos.addAll(appInfos);
        notifyDataSetChanged();
    }

    public ArrayList<AppInfo> getSelectedApps(){
        return mSelectedApps;
    }

    static class LabelItemViewHolder extends RecyclerView.ViewHolder {

        AppCompatCheckedTextView txtLabel;

        public LabelItemViewHolder(@NonNull View itemView) {
            super(itemView);
            txtLabel = itemView.findViewById(R.id.txt_label_name);
        }

        void bind(AppInfo appInfo) {
            txtLabel.setText(appInfo.mAppName);
            //txtLabel.setCompoundDrawables(appInfo.mIcon, null, null, null);
        }
    }
}
