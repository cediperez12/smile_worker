package com.smile.worker.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.smile.worker.Activity.ChatConversationActivity;
import com.smile.worker.R;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.ButterKnife;
import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.ViewHolder> {

    String chat_name[], chat_display[];
    int images[];
    Context context;

    private List<DataSnapshot> data;

    private FirebaseUser curr_user;
    private DatabaseReference conversationReference;
    private DatabaseReference customerReference;
    private SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm aa");

    private static final String USERS = "users";
    private static final String USERS_CONVERSATIONS = "_conversations";
    private static final String CONVERSATION = "conversations";
    private static final String CUSTOMER = "customer";
    private static final String MESSAGES = "_messages";
    private static final String MESSAGE= "message";
    private static final String FIRST_NAME = "_personal_information/first_name";
    private static final String LAST_NAME = "_personal_information/last_name";
    private static final String MESSAGE_DATE_SENT = "dateSent";
    private static final String INTENT_CONVERSATION_ID_KEY = "CONVERSATION_ID";
    private static final String INTENT_CUSTOMER_ID_KEY = "CUSTOMER_ID";

    public ChatListAdapter(List<DataSnapshot> data) {
        this.data = data;

        curr_user = FirebaseAuth.getInstance().getCurrentUser();
    }

    public ChatListAdapter(Context ct, String s1[], String s2[], int img[]){
        context = ct;
        chat_name = s1;
        chat_display = s2;
        images = img;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.adapter_chat_list,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Current Data
        DataSnapshot curr_data = data.get(position);
        String conversation_id = curr_data.getValue(String.class);
        String customer_id = curr_data.getKey();

        //Get Conversation
        conversationReference = FirebaseDatabase.getInstance()
                .getReference(CONVERSATION)
                .child(conversation_id);

        //Getting the last conversation message
        Task<DataSnapshot> load_conversation_last_message =
                conversationReference.child(MESSAGES).orderByChild(MESSAGE_DATE_SENT).limitToLast(1).get();
        load_conversation_last_message.addOnCompleteListener(task->{
           if(task.isSuccessful()) {
               DataSnapshot result = task.getResult();

               for(DataSnapshot ds : result.getChildren()){
                   String strLastMessage = ds.child(MESSAGE)
                           .getValue(String.class);
                   Long lastMessageDate = ds.child(MESSAGE_DATE_SENT)
                           .getValue(Long.class);

                   if(strLastMessage.length() >= 18) {
                       strLastMessage = strLastMessage.substring(0,18) + "...";
                   }

                   holder.txtvChatDate.setText(sdf.format(lastMessageDate));
                   holder.chat_convoDisplay.setText(strLastMessage);
               }
           }
        });

        //Get Customer Data
        customerReference = FirebaseDatabase.getInstance()
                .getReference(USERS)
                .child(customer_id);
        Task<DataSnapshot> load_customer_reference = customerReference.get();
        load_customer_reference.addOnCompleteListener(task->{
            if(task.isSuccessful()) {
                DataSnapshot result = task.getResult();

                String firstName = result.child(FIRST_NAME).getValue(String.class);
                String lastName = result.child(LAST_NAME).getValue(String.class);
                String combinedName = firstName.toUpperCase() + ", " + lastName.toUpperCase();

                holder.chat_name.setText(combinedName);
            }
        });

        holder.itemView.setOnClickListener(v->{
            Intent intentToConvo = new Intent(v.getContext(), ChatConversationActivity.class);
            intentToConvo.putExtra(INTENT_CONVERSATION_ID_KEY, conversation_id);
            intentToConvo.putExtra(INTENT_CUSTOMER_ID_KEY,customer_id);
            v.getContext().startActivity(intentToConvo);
        });
        ArrayAdapter<String> spinAdapter = new ArrayAdapter<String>(
                        holder.itemView.getContext(),
                        android.R.layout.simple_list_item_1,
                        holder.moreSettings.getResources().getStringArray(R.array.more_settings));
        spinAdapter.setDropDownViewResource(R.layout.spinner_more_settings);
        holder.moreSettings.setAdapter(spinAdapter);
        holder.moreSettings.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selected = holder.moreSettings.getItemAtPosition(i).toString();
                Toast.makeText(view.getContext().getApplicationContext(), selected,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
       @BindView(R.id.tv_adapterChatList_Name)
       TextView chat_name;
       @BindView(R.id.tv_adapterChatList_chatDisplay)
       TextView chat_convoDisplay;
       @BindView(R.id.imV_adapterChatList_profile)
       CircleImageView imageView;
       @BindView(R.id.tv_adapterChatList_chatDate)
       TextView txtvChatDate;
       @BindView(R.id.spinner_chat_list_more)
       Spinner moreSettings;

       public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
       }
    }

}
