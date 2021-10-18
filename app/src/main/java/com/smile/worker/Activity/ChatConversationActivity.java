package com.smile.worker.Activity;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.smile.worker.Adapter.ChatConversationAdapter;
import com.smile.worker.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatConversationActivity extends AppCompatActivity {
    @BindView(R.id.autoCompleteTV_chatConversation)
    AutoCompleteTextView aucoCompleteTV_chatConversation;

    @BindView(R.id.recyclerView_act_chatConversation)
    RecyclerView recyclerView_act_chatConversation;

    @BindView(R.id.btn_chat_conversation_moreBtn)
    ImageButton btn_chat_conversation_moreBtn;

    @BindView(R.id.Llayout_act_chat_conversation_more)
    LinearLayout Llayout_act_chat_conversation_more;

    @BindView(R.id.btnBack_act_chatConversation)
    Button btnBack_act_chatConversation;

    private BottomSheetBehavior bottomSheet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_conversation);
        ButterKnife.bind(this);

        LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView_act_chatConversation.setLayoutManager(linearLayoutManager);
        recyclerView_act_chatConversation.setAdapter(new ChatConversationAdapter());
        linearLayoutManager.setReverseLayout(true);
        aucoCompleteTV_chatConversation.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                final int DRAWABLE_RIGHT = 2;


                if(event.getAction() == MotionEvent.ACTION_UP) {
                    if(event.getRawX() >= (aucoCompleteTV_chatConversation.getRight() - aucoCompleteTV_chatConversation.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        Toast.makeText(getApplicationContext()," Message Send", Toast.LENGTH_SHORT).show();
                        return true;
                    }
                }
                return false;
            }
        });

        Llayout_act_chat_conversation_more.setVisibility(View.GONE);

        btn_chat_conversation_moreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Llayout_act_chat_conversation_more.getVisibility() == view.GONE){
                    Llayout_act_chat_conversation_more.setVisibility(View.VISIBLE);
                }else{
                    Llayout_act_chat_conversation_more.setVisibility(View.GONE);
                }
            }
        });

        btnBack_act_chatConversation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        setupBottomSheet();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void setupBottomSheet(){
        bottomSheet = BottomSheetBehavior.from(findViewById(R.id.card_chat_convo_bottomsheet));
        bottomSheet.setHideable(true);
        bottomSheet.setState(BottomSheetBehavior.STATE_HIDDEN);
    }

    public void OpenBottomSheet(View view) {
        bottomSheet.setState(BottomSheetBehavior.STATE_COLLAPSED);
    }
}