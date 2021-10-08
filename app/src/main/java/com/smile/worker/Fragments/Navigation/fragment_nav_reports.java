package com.smile.worker.Fragments.Navigation;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.smile.worker.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class fragment_nav_reports extends Fragment {

    @BindView(R.id.fr_nav_report_txtv_income)
    TextView txtvIncome;
    @BindView(R.id.fr_nav_report_txtv_jobs)
    TextView txtvJobs;
    @BindView(R.id.fr_nav_report_til_month)
    TextInputLayout tilMonth;

    private final String[] MONTHS = {"January","February","March","April",
            "May","June","July","August","September","October","November","December"};


    public fragment_nav_reports() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_nav_reports, container, false);
        ButterKnife.bind(this,v);

        //To be continued with back-end. Meanwhile, search for a list.

        init();

        return v;
    }

    private void init() {
        //Setup Month list
        ArrayAdapter<String> monthlyListAdapter = new ArrayAdapter<String>(this.getContext(),
                android.R.layout.simple_dropdown_item_1line,MONTHS);
        AutoCompleteTextView monthlySpinner = (AutoCompleteTextView) tilMonth.getEditText();
        monthlySpinner.setAdapter(monthlyListAdapter);
    }
}