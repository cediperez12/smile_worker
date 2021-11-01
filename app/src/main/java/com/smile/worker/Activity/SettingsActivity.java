package com.smile.worker.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.smile.worker.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingsActivity extends AppCompatActivity {

    @BindView(R.id.cardView_actSettings_CHANGEPASS)
    CardView cardView_actSettings_CHANGEPASS;

    @BindView(R.id.cardView_actSettings_EWALLET)
    CardView cardView_actSettings_EWALLET;

    @BindView(R.id.cardView_actSettings_HISTORY)
    CardView cardView_actSettings_HISTORY;

    @BindView(R.id.cardView_actSettings_LOGOUT)
    CardView cardView_actSettings_LOGOUT;

    @BindView(R.id.cardView_actSettings_FEEDBACK)
    CardView cardView_actSettings_FEEDBACK;

    @BindView(R.id.btnBack_act_Settings)
    Button btnBack_act_Settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ButterKnife.bind(this);

        cardView_actSettings_EWALLET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), " E-WALLET ", Toast.LENGTH_SHORT).show();
            }
        });

        cardView_actSettings_HISTORY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), " HISTORY ", Toast.LENGTH_SHORT).show();
            }
        });

        cardView_actSettings_CHANGEPASS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), " CHANGE PASS ", Toast.LENGTH_SHORT).show();
            }
        });

        cardView_actSettings_FEEDBACK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), " FEEDBACK ", Toast.LENGTH_SHORT).show();
            }
        });

        cardView_actSettings_LOGOUT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), " LOGOUT ", Toast.LENGTH_SHORT).show();
            }
        });

        btnBack_act_Settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), WorkerProfileActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}