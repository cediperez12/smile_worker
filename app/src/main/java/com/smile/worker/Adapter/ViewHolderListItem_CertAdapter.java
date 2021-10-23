package com.smile.worker.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.smile.worker.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewHolderListItem_CertAdapter extends RecyclerView.Adapter<ViewHolderListItem_CertAdapter.ViewHolder>{
    @NonNull
    @Override
    public ViewHolderListItem_CertAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.adapter_view_worker_cert_item,parent,false);
        ViewHolderListItem_CertAdapter.ViewHolder viewHolder = new ViewHolderListItem_CertAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderListItem_CertAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_adapterViewWorker_ItemCert)
        TextView tv_adapterViewWorker_ItemCert;

        @BindView(R.id.tv_adapterViewWorker_ItemCertDesc)
        TextView tv_adapterViewWorker_ItemCertDesc;

        @BindView(R.id.tv_adapterViewWorker_ItemCertYr)
        TextView tv_adapterViewWorker_ItemCertYr;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
