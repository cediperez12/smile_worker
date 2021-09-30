package com.smile.worker.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smile.worker.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MessageNotificationListAdapter extends RecyclerView.Adapter<MessageNotificationListAdapter.ViewHolder>{

    List<Object> listOfMessage;

    public MessageNotificationListAdapter(List<Object> listOfMessage) {
        this.listOfMessage = listOfMessage;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_messages_notification_list, parent, false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Object temp = listOfMessage.get(position);
    }

    @Override
    public int getItemCount() {
        return listOfMessage.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View item){
            super(item);
        }
    }
}
