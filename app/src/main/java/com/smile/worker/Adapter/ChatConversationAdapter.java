package com.smile.worker.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.smile.worker.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class ChatConversationAdapter extends RecyclerView.Adapter<ChatConversationAdapter.ViewHolder> {


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.adapter_chat_conversation,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return 1;
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //Customer
        @BindView(R.id.tv_adapterChatConversation_Cname)
        TextView customer_name;
        @BindView(R.id.tv_adapterChatConversation_Ccontent)
        TextView customer_content;
        @BindView(R.id.tv_adapterChatConversation_Cdate)
        TextView custtomer_date_content;
        @BindView(R.id.imv_adapterChatConversation_Cprofile)
        CircleImageView imageView;

        //Worker
        @BindView(R.id.tv_adapterChatConversation_Yname)
        TextView worker_name;
        @BindView(R.id.tv_adapterChatConversation_Ycontent)
        TextView worker_content;
        @BindView(R.id.tv_adapterChatConversation_Ydate)
        TextView worker_content_date;

        //SentReqSched
        @BindView(R.id.tv_adapter_chat_conversationSentScheduleReq_TIME)
        TextView tv_adapter_chat_conversationSentScheduleReq_TIME;
        @BindView(R.id.tv_adapter_chat_conversationSentScheduleReq_DATE)
        TextView tv_adapter_chat_conversationSentScheduleReq_DATE;

        @BindView(R.id.tv_adapter_chat_conversationSentScheduleReq_GigName)
        TextView tv_adapter_chat_conversationSentScheduleReq_GigName;
        @BindView(R.id.tv_adapter_chat_conversationSentScheduleReq_GigDesc)
        TextView tv_adapter_chat_conversationSentScheduleReq_GigDesc;

        @BindView(R.id.tv_adapter_chat_conversationSentScheduleReq_RATE)
        TextView tv_adapter_chat_conversationSentScheduleReq_RATE;
        @BindView(R.id.tv_adapter_chat_conversationSentScheduleReq_FARE)
        TextView tv_adapter_chat_conversationSentScheduleReq_FARE;

        @BindView(R.id.tv_adapter_chat_conversationSentScheduleReq_RESPONSE)
        TextView tv_adapter_chat_conversationSentScheduleReq_RESPONSE;
        @BindView(R.id.imV_adapter_chat_conversationSentScheduleReq_IconSTATUS)
        ImageView imV_adapter_chat_conversationSentScheduleReq_IconSTATUS;




        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
