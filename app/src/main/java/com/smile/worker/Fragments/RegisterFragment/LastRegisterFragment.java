package com.smile.worker.Fragments.RegisterFragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.smile.worker.Activity.MainNavigationActivity;
import com.smile.worker.Activity.RegisterActivity;
import com.smile.worker.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LastRegisterFragment extends Fragment {

    @BindView(R.id.fr_last_register_btn_done)
    Button btnDone;

    private RegisterActivity _parent;

    public LastRegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_last_register, container, false);
        ButterKnife.bind(this,v);

        _parent = (RegisterActivity)v.getContext();

        btnDone.setOnClickListener(view->{
            Intent intent = new Intent(this.getContext(), MainNavigationActivity.class);
            startActivity(intent);
        });

        return v;
    }
}