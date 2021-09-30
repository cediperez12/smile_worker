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

import com.smile.worker.Adapter.WorkNotificationListAdapter;
import com.smile.worker.R;

import java.util.ArrayList;
import java.util.List;

public class fragment_notif_work extends Fragment {

    @BindView(R.id.fr_notif_work_recyclerv)
    RecyclerView recyclerView;

    private WorkNotificationListAdapter adapter;

    public fragment_notif_work() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_notif_work, container, false);
        ButterKnife.bind(this,v);

        //Temporary Data
        List<Object> temp = new ArrayList<>();
        temp.add(new Object());
        temp.add(new Object());

        adapter = new WorkNotificationListAdapter(temp);

        //Setup Adapter
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));
        recyclerView.setAdapter(adapter);

        return v;
    }
}