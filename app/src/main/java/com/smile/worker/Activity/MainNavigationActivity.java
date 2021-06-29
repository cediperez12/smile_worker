package com.smile.worker.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.navigation.NavController;
import androidx.navigation.NavHostController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.smile.worker.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainNavigationActivity extends AppCompatActivity {

    @BindView(R.id.nav_tab)
    BottomNavigationView botNav;
    @BindView(R.id.imageButtonMainAct_notification)
    ImageButton btnNotification;
    @BindView(R.id.lLayout_mainNav_viewProfile)
    LinearLayout lLayoutViewProfile;

    FirebaseAuth auth;
    DatabaseReference userDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_navigation);
        ButterKnife.bind(this);
        botNav.setItemIconTintList(null);

        //Backend
        auth = FirebaseAuth.getInstance();
        userDatabaseReference = FirebaseDatabase.getInstance().getReference("users");
        userDatabaseReference
                .child(auth.getCurrentUser().getUid())
                .child("_worker_profile")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(!snapshot.exists()){
                            Intent intent = new Intent(MainNavigationActivity.this,
                                    SetupWorkerProfileActivity.class);
                            intent.putExtra(SetupWorkerProfileActivity.SETUP_WORKER_FLAG,
                                    SetupWorkerProfileActivity.NEW_SETUP);
                            startActivity(intent);
                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        NavController navController = Navigation.findNavController(this, R.id.fragmentContainerView);
        NavigationUI.setupWithNavController(botNav, navController);

        btnNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),NotificationActivity.class);
                startActivity(intent);
            }
        });

        lLayoutViewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"CLICKED",Toast.LENGTH_SHORT).show();

                //Temporary
                auth.signOut();
                Intent intent = new Intent(v.getContext(),WelcomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

}