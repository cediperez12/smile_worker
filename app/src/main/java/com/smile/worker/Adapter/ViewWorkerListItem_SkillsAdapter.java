package com.smile.worker.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.smile.worker.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewWorkerListItem_SkillsAdapter extends RecyclerView.Adapter<ViewWorkerListItem_SkillsAdapter.ViewHolder> {

    private List<String> data;

    public ViewWorkerListItem_SkillsAdapter(List<String> data) {
        this.data = data;
    }

    public ViewWorkerListItem_SkillsAdapter () {

    }

    @NonNull
    @Override
    public ViewWorkerListItem_SkillsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.adapter_view_worker_skill_item,parent,false);
        ViewWorkerListItem_SkillsAdapter.ViewHolder viewHolder = new ViewWorkerListItem_SkillsAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewWorkerListItem_SkillsAdapter.ViewHolder holder, int position) {
        String skill = data.get(position);
        holder.tv_adapterViewWorker_ItemSkills.setText(skill);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_adapterViewWorker_ItemSkills)
        TextView tv_adapterViewWorker_ItemSkills;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
