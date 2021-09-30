package com.smile.worker.Activity;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.os.Bundle;
import android.util.Log;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.smile.worker.Fragments.Notification.fragment_notif_messages;
import com.smile.worker.Fragments.Notification.fragment_notif_work;
import com.smile.worker.R;

public class NotificationActivity extends AppCompatActivity {

    @BindView(R.id.tablayout_notif)
    TabLayout tablayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        ButterKnife.bind(this);
        init();
    }

    private void init(){

        //Initial Tab
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.lLayout_notif_fragment_holder, new fragment_notif_messages())
                .commit();

        //Setup tablayout
        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 1:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.lLayout_notif_fragment_holder, new fragment_notif_work())
                                .commit();
                        break;

                    case 0:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.lLayout_notif_fragment_holder, new fragment_notif_messages())
                                .commit();
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 1:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.lLayout_notif_fragment_holder, new fragment_notif_work())
                                .commit();
                        break;

                    case 0:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.lLayout_notif_fragment_holder, new fragment_notif_messages())
                                .commit();
                        break;
                }
            }
        });
    }
}