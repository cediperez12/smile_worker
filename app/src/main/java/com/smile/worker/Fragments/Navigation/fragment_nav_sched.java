package com.smile.worker.Fragments.Navigation;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smile.worker.Adapter.ScheduleListAdapter;
import com.smile.worker.R;

import java.util.ArrayList;
import java.util.List;

public class fragment_nav_sched extends Fragment {

    @BindView(R.id.rc_fr_nav_sched)
    RecyclerView recyclerView;

    private ScheduleListAdapter adapter;

    public fragment_nav_sched() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_nav_sched, container, false);
        ButterKnife.bind(this,v);

        //Setup adapter;
        //Temporary data
        List<Object> temp = new ArrayList<Object>();
        temp.add(new Object());
        temp.add(new Object());

        adapter = new ScheduleListAdapter(temp);

        //Setup recyclerview;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));
        recyclerView.setAdapter(adapter);

        return v;
    }
}