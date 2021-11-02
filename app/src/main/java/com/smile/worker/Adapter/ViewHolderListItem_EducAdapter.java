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

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewHolderListItem_EducAdapter extends RecyclerView.Adapter<ViewHolderListItem_EducAdapter.ViewHolder>{

    private DataSnapshot data;

    private static final String WORKER_EDUCATION_SCHOOL_LINK =
            "_worker_profile/_education";
    private static final String WORKER_EDUCATION_SCHOOL_LEVEL_LINK =
            "_worker_profile/_education/schoolLevel";
    private static final String WORKER_EDUCATION_SCHOOL_NAME_LINK =
            "_worker_profile/_education/schoolName";
    private static final String WORKER_EDUCATION_SCHOOL_YEAR_LINK =
            "_worker_profile/_education/year";

    public ViewHolderListItem_EducAdapter (DataSnapshot data) {
        this.data = data;
    }

    public ViewHolderListItem_EducAdapter () {

    }

    @NonNull
    @Override
    public ViewHolderListItem_EducAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.adapter_view_worker_educ_item,parent,false);
        ViewHolderListItem_EducAdapter.ViewHolder viewHolder = new ViewHolderListItem_EducAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderListItem_EducAdapter.ViewHolder holder, int position) {
        holder.tv_adapterViewWorker_ItemEduc.setText(data.child(WORKER_EDUCATION_SCHOOL_LEVEL_LINK)
            .getValue(String.class));
        holder.tv_adapterViewWorker_ItemEducDesc.setText(data.child(WORKER_EDUCATION_SCHOOL_NAME_LINK)
            .getValue(String.class));
        holder.tv_adapterViewWorker_ItemEducYr.setText(data.child(WORKER_EDUCATION_SCHOOL_YEAR_LINK)
            .getValue(String.class));
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_adapterViewWorker_ItemEduc)
        TextView tv_adapterViewWorker_ItemEduc;

        @BindView(R.id.tv_adapterViewWorker_ItemEducDesc)
        TextView tv_adapterViewWorker_ItemEducDesc;

        @BindView(R.id.tv_adapterViewWorker_ItemEducYr)
        TextView tv_adapterViewWorker_ItemEducYr;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
