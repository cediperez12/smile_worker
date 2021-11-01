package com.smile.worker.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.smile.worker.Adapter.ChatConversationAdapter;
import com.smile.worker.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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

    @BindView(R.id.tv_chatConversation_nameProfile)
    TextView txtvProfileName;

    @BindView(R.id.txtInputLayout_chatConversation)
    TextInputLayout tilChatMessage;

    private BottomSheetBehavior bottomSheet;

    @BindView(R.id.btn_chat_act_chatConversationSheet_close)
    Button btn_chat_act_chatConversationSheet_close;

    @BindView(R.id.btn_chat_act_chatConversationSheet_DatePick)
    Button btn_chat_act_chatConversationSheet_DatePick;

    @BindView(R.id.btn_chat_act_chatConversationSheet_TimePick)
    Button btn_chat_act_chatConversationSheet_TimePick;

    @BindView(R.id.tv_chat_act_chatConversationSheet_displayDateSelected)
    TextView tv_chat_act_chatConversationSheet_displayDateSelected;

    @BindView(R.id.tv_chat_act_chatConversationSheet_displayTimeSelected)
    TextView tv_chat_act_chatConversationSheet_displayTimeSelected;

    final Calendar myCalendar = Calendar.getInstance();
    String am_pm = "";

    private static final String INTENT_CONVERSATION_ID_KEY = "CONVERSATION_ID";
    private static final String INTENT_CUSTOMER_ID_KEY = "CUSTOMER_ID";
    private static final String USER_LINK = "users";
    private static final String CUSTOMER_FIRST_NAME_LINK = "_personal_information/first_name";
    private static final String CUSTOMER_LAST_NAME_LINK = "_personal_information/last_name";
    private static final String CONVERSATION_LINK = "conversations";
    private static final String MESSAGES_LINK = "_messages";
    private static final String FROM_LINK = "from";
    private static final String MESSAGE_LINK = "message";
    private static final String DATE_SENT_LINK = "dateSent";

    private String conversation_id, customer_id, user_id;

    private DatabaseReference customerReference;
    private DatabaseReference messagesReference;

    private List<DataSnapshot> messages_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_conversation);
        ButterKnife.bind(this);

        //Back-end
        //Get user id
        user_id = FirebaseAuth.getInstance().getCurrentUser().getUid();

        //Get intent extras.
        conversation_id = getIntent().getStringExtra(INTENT_CONVERSATION_ID_KEY);
        customer_id = getIntent().getStringExtra(INTENT_CUSTOMER_ID_KEY);

        //Get customer data
        customerReference = FirebaseDatabase.getInstance()
                .getReference(USER_LINK)
                .child(customer_id);

        Task<DataSnapshot> load_customer_data = customerReference.get();
        load_customer_data.addOnCompleteListener(task->{
           if(task.isSuccessful()) {
               DataSnapshot result = task.getResult();

               String firstName = result.child(CUSTOMER_FIRST_NAME_LINK)
                       .getValue(String.class);
               String lastName = result.child(CUSTOMER_LAST_NAME_LINK)
                       .getValue(String.class);
               String combined = firstName.toUpperCase() + ", " + lastName.toUpperCase();

               txtvProfileName.setText(combined);
           } else {
               task.getException().printStackTrace();
           }
        });

        //Get data
        messages_data = new ArrayList<>();
        ChatConversationAdapter adapter = new ChatConversationAdapter(messages_data, customer_id);

        LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        linearLayoutManager.setReverseLayout(true);
        recyclerView_act_chatConversation.setLayoutManager(linearLayoutManager);
        recyclerView_act_chatConversation.setHasFixedSize(true);
        recyclerView_act_chatConversation.setAdapter(adapter);

        messagesReference = FirebaseDatabase.getInstance()
                .getReference(CONVERSATION_LINK)
                .child(conversation_id)
                .child(MESSAGES_LINK);

        messagesReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                messages_data.add(snapshot);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        setupBottomSheet();//init BottomSheet

        aucoCompleteTV_chatConversation.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                final int DRAWABLE_RIGHT = 2;

                if(event.getAction() == MotionEvent.ACTION_UP) {
                    if(event.getRawX() >= (aucoCompleteTV_chatConversation.getRight() - aucoCompleteTV_chatConversation.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        //EditText
                        String message = aucoCompleteTV_chatConversation.getText().toString();
                        sendMessage(message.trim());
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

        btn_chat_act_chatConversationSheet_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheet.setState(BottomSheetBehavior.STATE_HIDDEN);
            }
        });

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                datePickerResult();
            }
        };

        btn_chat_act_chatConversationSheet_DatePick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(ChatConversationActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        btn_chat_act_chatConversationSheet_TimePick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(ChatConversationActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        if (selectedHour < 12 && selectedHour >= 0) {
                            tv_chat_act_chatConversationSheet_displayTimeSelected.setVisibility(View.VISIBLE);
                            tv_chat_act_chatConversationSheet_displayTimeSelected.setText( selectedHour + ":" + selectedMinute + " AM");
                            btn_chat_act_chatConversationSheet_TimePick.setText(selectedHour + ":" + selectedMinute + " AM");

                        } else {
                            selectedHour -= 12;
                            if(selectedHour == 0) {
                                selectedHour = 12;
                            }
                            tv_chat_act_chatConversationSheet_displayTimeSelected.setVisibility(View.VISIBLE);
                            tv_chat_act_chatConversationSheet_displayTimeSelected.setText( selectedHour + ":" + selectedMinute + " PM");

                            btn_chat_act_chatConversationSheet_TimePick.setText(selectedHour + ":" + selectedMinute + " PM");


                        }
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });
    }

    public void sendMessage(String message) {
        Calendar cal = Calendar.getInstance();

        Long date = cal.getTimeInMillis();

        Map<String, Object> message_data = new HashMap<String, Object>();
        message_data.put(FROM_LINK,user_id);
        message_data.put(MESSAGE_LINK, message);
        message_data.put(DATE_SENT_LINK, date);

        messagesReference.push().setValue(message_data);
        tilChatMessage.getEditText().setText("");
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

    private void datePickerResult() {
        String myFormat = "EEE,MMM dd,yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ENGLISH);
        tv_chat_act_chatConversationSheet_displayDateSelected.setVisibility(View.VISIBLE);
        tv_chat_act_chatConversationSheet_displayDateSelected.setText(sdf.format(myCalendar.getTime()));
    }

    public void OpenBottomSheet(View view) {
        bottomSheet.setState(BottomSheetBehavior.STATE_COLLAPSED);
    }
}