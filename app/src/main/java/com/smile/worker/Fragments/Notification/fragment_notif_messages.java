package com.smile.worker.Fragments.Notification;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smile.worker.Adapter.MessageNotificationListAdapter;
import com.smile.worker.R;

import java.util.ArrayList;
import java.util.List;

public class fragment_notif_messages extends Fragment {

    @BindView(R.id.fr_notif_messages_recyclerv)
    RecyclerView recyclerv;

    private MessageNotificationListAdapter adapter;

    public fragment_notif_messages() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_notif_messages, container, false);
        ButterKnife.bind(this,v);

        //Setup Adapter
        //Temporary data
        List<Object> list = new ArrayList<Object>();
        list.add(new Object());
        list.add(new Object());

        adapter = new MessageNotificationListAdapter(list);

        recyclerv.setHasFixedSize(true);
        recyclerv.setLayoutManager(new LinearLayoutManager(v.getContext()));
        recyclerv.setAdapter(adapter);

        return v;
    }
}