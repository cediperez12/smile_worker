package com.smile.worker.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import com.smile.worker.R;

public class SkillListAdapter extends RecyclerView.Adapter<SkillListAdapter.ViewHolder>{

    private List<String> list;

    public SkillListAdapter(List<String> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_worker_skills_list, parent, false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String data = list.get(position);

        holder.txtv.setText(data);
        holder.btnRemove.setOnClickListener(v ->{
            list.remove(position);
            SkillListAdapter.this.notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvSkills_fr_workSetup_skills_list)
        TextView txtv;
        @BindView(R.id.tvRemove_fr_workSetup_skills_list)
        TextView btnRemove;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
