package com.shangyunshi.timecontrol.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.loopeer.formitemview.FormTextItem;
import com.shangyunshi.timecontrol.OnItemClickListener;
import com.shangyunshi.timecontrol.model.AppInfo;
import com.shangyunshi.timecontrol.R;
import com.shangyunshi.timecontrol.model.Label;
import java.util.ArrayList;
import java.util.List;

public class LabelListAdapter extends RecyclerView.Adapter<LabelListAdapter.AppItemViewHolder> {

    private List<Label> mLabels;
    private OnItemClickListener<Label> mOnItemClickListener;

    public LabelListAdapter(List<Label> labels) {
        this.mLabels = labels;
    }

    public void setOnItemClickListener(OnItemClickListener<Label> listener) {
        mOnItemClickListener = listener;
    }

    @NonNull @Override
    public LabelListAdapter.AppItemViewHolder onCreateViewHolder(
        @NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.app_info_item_list, parent, false);
        return new AppItemViewHolder(v);
    }

    @Override public void onBindViewHolder(@NonNull AppItemViewHolder holder, final int position) {
        final Label label = mLabels.get(position);
        holder.mAppItem.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.OnItemClickListener(view, label,position);
                }
            }
        });
        holder.bind(label);
    }

    public void setData(List<Label> labels) {
        mLabels.clear();
        mLabels.addAll(labels);
        notifyDataSetChanged();
    }

    @Override public int getItemCount() {
        return mLabels == null ? 0 : mLabels.size();
    }

    static class AppItemViewHolder extends RecyclerView.ViewHolder {

        FormTextItem mAppItem;

        public AppItemViewHolder(@NonNull View itemView) {
            super(itemView);
            mAppItem = itemView.findViewById(R.id.form_label_with_app_info);
        }

        void bind(Label label) {
            mAppItem.setDescText(label.name);
            mAppItem.setContentText(getAppInfos(label));
        }

        private String getAppInfos(Label label) {
            StringBuilder builder = new StringBuilder();
            List<AppInfo> appInfos = label.appInfos;
            if (appInfos == null || appInfos.size() == 0) {
                return "";
            }
            for (int i = 0; i < appInfos.size(); i++) {
                if (i == 3) {
                    builder.append("...");
                    break;
                }
                AppInfo info = appInfos.get(i);
                builder.append(info);
            }
            return builder.toString();
        }
    }

}
