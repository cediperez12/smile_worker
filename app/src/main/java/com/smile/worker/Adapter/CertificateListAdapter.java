package com.smile.worker.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.smile.worker.Models.Certificate;
import com.smile.worker.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CertificateListAdapter extends RecyclerView.Adapter<CertificateListAdapter.ViewHolder> {

    private List<Certificate> list;

    public CertificateListAdapter (List<Certificate> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_worker_certificate_list, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Certificate cert = list.get(position);

        holder.txtvCertTitle.setText(cert.getTitle());
        holder.txtvEventName.setText(cert.getEvent());
        holder.txtvYr.setText(cert.getYear());
        holder.txtvRemove.setOnClickListener(v->{
            list.remove(position);
            notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvCertTitle_fr_workSetup_certificate_list)
        TextView txtvCertTitle;
        @BindView(R.id.tvCertEventName_fr_workSetup_certificate_list)
        TextView txtvEventName;
        @BindView(R.id.tvCertYr_fr_workSetup_certificate_list)
        TextView txtvYr;
        @BindView(R.id.tvRemove_fr_workSetup_certificate_list)
        TextView txtvRemove;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
