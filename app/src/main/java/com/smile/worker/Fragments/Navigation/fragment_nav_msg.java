package com.smile.worker.Fragments.Navigation;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.smile.worker.Adapter.ChatListAdapter;
import com.smile.worker.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_nav_msg#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_nav_msg extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    @BindView(R.id.recyclerView_fragment_nav_msg_chatList)
    RecyclerView recyclerView;

    String s1[],s2[];
    int image[] = {R.drawable.arey_sample_pic_chat,R.drawable.cd_sample_pic_chat};

    private DatabaseReference userConversation;
    private FirebaseUser curr_user;

    private static final String USERS = "users";
    private static final String CONVERSATIONS = "_conversations";

    private List<DataSnapshot> data;

    public fragment_nav_msg() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_nav_msg.
     */
    // TODO: Rename and change types and number of parameters
    public static fragment_nav_msg newInstance(String param1, String param2) {
        fragment_nav_msg fragment = new fragment_nav_msg();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_nav_chat, container, false);
        ButterKnife.bind(this,v);

        init(v);

        return v;
    }

    private void init(View v) {
        curr_user = FirebaseAuth.getInstance().getCurrentUser();

        //Setup data and adapter
        data = new ArrayList<>();
        ChatListAdapter adapter = new ChatListAdapter(data);

        //Setup recyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(v.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        //Fetch user Conversations
        userConversation =
                FirebaseDatabase.getInstance()
                .getReference(USERS)
                .child(curr_user.getUid())
                .child(CONVERSATIONS);

        userConversation.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                data.add(snapshot);
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
    }
}