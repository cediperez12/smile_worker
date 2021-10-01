package com.smile.worker.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.smile.worker.R;

public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.ViewHolder> {

    String chat_name[], chat_display[];
    int images[];
    Context context;

    public ChatListAdapter(Context ct, String s1[], String s2[], int img[]){
        context = ct;
        chat_name = s1;
        chat_display = s2;
        images = img;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.adapter_chat_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.chat_name.setText(chat_name[position]);
        holder.chat_convoDisplay.setText(chat_display[position]);
        holder.imageView.setImageResource(images[position]);
    }

    @Override
    public int getItemCount() {
        return chat_name.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView chat_name,chat_convoDisplay;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            chat_name = itemView.findViewById(R.id.tv_adapterChatList_Name);
            chat_convoDisplay = itemView.findViewById(R.id.tv_adapterChatList_chatDisplay);
            imageView = itemView.findViewById(R.id.imV_adapterChatList_profile);
        }
    }
}
