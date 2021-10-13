package com.smile.worker.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.smile.worker.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewScheduleActivity extends AppCompatActivity {

    @BindView(R.id.view_sched_btn_back)
    Button btnBack;
    @BindView(R.id.view_sched_btn_report)
    Button btnReport;
    @BindView(R.id.view_sched_txtv_customer_info)
    TextView txtvCustomerInfo;
    @BindView(R.id.view_sched_txtv_status)
    TextView txtvStatus;
    @BindView(R.id.view_sched_txtv_gig_name)
    TextView txtvGigName;
    @BindView(R.id.view_sched_btn_rate)
    Button btnRate;
    @BindView(R.id.view_sched_btn_fare)
    Button btnFare;
    @BindView(R.id.view_sched_txtv_date)
    TextView txtvDate;
    @BindView(R.id.view_sched_txtv_loc)
    TextView txtvLoc;
    @BindView(R.id.view_sched_btn_cancel_job)
    Button btnCancelJob;
    @BindView(R.id.view_sched_btn_send_agreement)
    Button btnSendAgreement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_schedule);
        ButterKnife.bind(this);
    }
}