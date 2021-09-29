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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.smile.worker.Models.PersonalInformation;
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
    @BindView(R.id.tvMainAct_fname)
    TextView txtvFname;

    FirebaseAuth auth;
    DatabaseReference userDatabaseReference;
    FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_navigation);
        ButterKnife.bind(this);
        botNav.setItemIconTintList(null);

        //Backend
        auth = FirebaseAuth.getInstance();
        currentUser = auth.getCurrentUser();
        userDatabaseReference = FirebaseDatabase.getInstance()
                .getReference("users").child(currentUser.getUid());

        //Load worker profile
        userDatabaseReference
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

        //Load personal information
        userDatabaseReference
                .child("_personal_information")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            PersonalInformation personalInfo =
                                    snapshot.getValue(PersonalInformation.class);

                            String fname = personalInfo.getFirst_name();
                            String lname = personalInfo.getLast_name();

                            txtvFname.setText(fname + ", " + lname);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        error.toException().printStackTrace();
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
                //auth.signOut();
                Intent intent = new Intent(v.getContext(),WorkerProfileActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

}