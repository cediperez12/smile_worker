package com.smile.worker.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smile.worker.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class WorkNotificationListAdapter extends RecyclerView.Adapter<WorkNotificationListAdapter.ViewHolder>{

    List<Object> listOfWorkNotif;

    public WorkNotificationListAdapter(List<Object> listOfWorkNotif) {
        this.listOfWorkNotif = listOfWorkNotif;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_work_notification_list,parent,false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Object temp = listOfWorkNotif.get(position);
    }

    @Override
    public int getItemCount() {
        return listOfWorkNotif.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder (View item) {
            super(item);
        }
    }

}
