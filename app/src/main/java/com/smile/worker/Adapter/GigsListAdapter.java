package com.smile.worker.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.smile.worker.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GigsListAdapter extends RecyclerView.Adapter<GigsListAdapter.ViewHolder>{
    @NonNull
    @Override
    public GigsListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.adapter_gigs_list,parent,false);
        GigsListAdapter.ViewHolder viewHolder = new GigsListAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GigsListAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.tv_adapterGigsList_TitleGigs)
        TextView gigs_title;
        @BindView(R.id.tv_adapterGigsList_GigsDesc)
        TextView gigs_desc;
        @BindView(R.id.tv_adapterGigsList_GigsRate)
        TextView gigs_rate;
        @BindView(R.id.imgBtn_adapterGigsList_btnEditGigs)
        ImageButton btn_edit;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        @Override
        public void onClick(View view) {

        }
    }

}
