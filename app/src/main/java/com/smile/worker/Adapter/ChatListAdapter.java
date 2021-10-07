package com.smile.worker.Adapter;

import android.content.Context;
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

import com.smile.worker.R;

import java.lang.reflect.Array;

import butterknife.ButterKnife;
import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

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

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
       @BindView(R.id.tv_adapterChatList_Name)
       TextView chat_name;
       @BindView(R.id.tv_adapterChatList_chatDisplay)
       TextView chat_convoDisplay;
       @BindView(R.id.imV_adapterChatList_profile)
       CircleImageView imageView;
        @BindView(R.id.spinner_chat_list_more)
        Spinner moreSettings;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);


            itemView.setOnClickListener(this);
            ArrayAdapter<String> spinAdapter = new ArrayAdapter<String>(context.getApplicationContext(),
                    android.R.layout.simple_list_item_1,
                    moreSettings.getResources().getStringArray(R.array.more_settings));
            spinAdapter.setDropDownViewResource(R.layout.spinner_more_settings);
            moreSettings.setAdapter(spinAdapter);
            moreSettings.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    String selected = moreSettings.getItemAtPosition(i).toString();
                    Toast.makeText(context.getApplicationContext(), selected,Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }


        @Override
        public void onClick(View view) {


            if (view == itemView){
                Toast.makeText(context.getApplicationContext(), " Item: " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
            }

        }
    }

}
