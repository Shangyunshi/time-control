package com.shangyunshi.timecontrol.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.shangyunshi.timecontrol.R;
import com.shangyunshi.timecontrol.model.Label;
import java.util.ArrayList;
import java.util.List;

public class LabelListAdapter extends RecyclerView.Adapter<LabelListAdapter.LabelItemViewHolder> {

    private List<Label> mLabelList;

    public LabelListAdapter(List<Label> labels) {
        this.mLabelList = labels;
    }

    public LabelListAdapter() {
        this.mLabelList = new ArrayList<>();
    }

    @NonNull @Override
    public LabelListAdapter.LabelItemViewHolder onCreateViewHolder(
        @NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_label_list, parent, false);
        return new LabelItemViewHolder(v);
    }

    @Override public void onBindViewHolder(@NonNull LabelItemViewHolder holder, int position) {
        Label label = mLabelList.get(position);
        holder.bind(label);
    }

    @Override public int getItemCount() {
        return mLabelList == null ? 0 : mLabelList.size();
    }

    public void setData(List<Label> labels) {
        mLabelList.clear();
        mLabelList.addAll(labels);
        notifyDataSetChanged();
    }

    static class LabelItemViewHolder extends RecyclerView.ViewHolder {

        TextView txtLabel;

        public LabelItemViewHolder(@NonNull View itemView) {
            super(itemView);
            txtLabel = itemView.findViewById(R.id.txt_label_name);
        }

        void bind(Label label) {
            txtLabel.setText(label.name);
        }
    }
}
