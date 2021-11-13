package com.smile.worker.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.smile.worker.R;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class ChatConversationAdapter extends RecyclerView.Adapter<ChatConversationAdapter.ViewHolder> {

    private List<DataSnapshot> messages_data;
    private String customerData;

    private String customerName;

    private DatabaseReference customerReference;

    private static final String USERS_LINK = "users";
    private static final String USER_FIRST_NAME_LINK = "_personal_information/first_name";
    private static final String USER_LAST_NAME_LINK = "_personal_information/last_name";
    private static final String MESSAGES_FROM = "from";
    private static final String MESSAGE_LINK = "message";
    private static final String MESSAGE_DATE = "dateSent";

    private static final SimpleDateFormat sdf = new SimpleDateFormat("hh:mm aa MM dd, yyyy");

    public ChatConversationAdapter (List<DataSnapshot> data, String customerData) {
        this.messages_data = data;
        this.customerData = customerData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.adapter_chat_conversation,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);

        customerReference = FirebaseDatabase.getInstance()
                .getReference(USERS_LINK)
                .child(customerData);

        Task<DataSnapshot> load_customer_data = customerReference.get();
        load_customer_data.addOnCompleteListener(task->{
            if(task.isSuccessful()) {
                DataSnapshot result = task.getResult();

                String firstName = result.child(USER_FIRST_NAME_LINK).getValue(String.class);
                String lastName = result.child(USER_LAST_NAME_LINK).getValue(String.class);
                customerName = firstName.toUpperCase() + ", " + lastName.toUpperCase();
            } else {

            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DataSnapshot data = messages_data.get(position);

        String from = data.child(MESSAGES_FROM).getValue(String.class);
        String message = data.child(MESSAGE_LINK).getValue(String.class);
        Long dateSent = data.child(MESSAGE_DATE).getValue(Long.class);

        //Customer sent the mssage
        if(from.equals(customerData)) {
            holder.self_layout.setVisibility(View.GONE);
            holder.notification_layout.setVisibility(View.GONE);

            holder.customer_name.setText(customerName);
            holder.customer_content.setText(message);
            holder.custtomer_date_content.setText(sdf.format(dateSent).toUpperCase());
        } else if(from.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())){
            //Either the user or a notification message
            holder.counter_layout.setVisibility(View.GONE);
            holder.notification_layout.setVisibility(View.GONE);

            holder.worker_content.setText(message);
            holder.worker_content_date.setText(sdf.format(dateSent).toUpperCase());
        }
    }

    @Override
    public int getItemCount() {
        if(messages_data.size() > 20) {
            return 20;
        }else {
            return messages_data.size();
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        //Customer
        @BindView(R.id.tv_adapterChatConversation_Cname)
        TextView customer_name;
        @BindView(R.id.tv_adapterChatConversation_Ccontent)
        TextView customer_content;
        @BindView(R.id.tv_adapterChatConversation_Cdate)
        TextView custtomer_date_content;
        @BindView(R.id.imv_adapterChatConversation_Cprofile)
        CircleImageView imageView;
        @BindView(R.id.layout_adapter_chat_conversation_counter)
        LinearLayout counter_layout;

        //Worker
        @BindView(R.id.tv_adapterChatConversation_Yname)
        TextView worker_name;
        @BindView(R.id.tv_adapterChatConversation_Ycontent)
        TextView worker_content;
        @BindView(R.id.tv_adapterChatConversation_Ydate)
        TextView worker_content_date;
        @BindView(R.id.layout_adapter_chat_conversation_self)
        LinearLayout self_layout;

        //Notification
        @BindView(R.id.adapter_chat_conversation_notification)
        LinearLayout notification_layout;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
