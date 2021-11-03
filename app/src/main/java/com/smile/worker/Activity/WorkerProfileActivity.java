package com.smile.worker.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.shuhart.stepview.StepView;
import com.smile.worker.Fragments.WorkerProfileFragments.GigsWorkerFragment;
import com.smile.worker.Fragments.WorkerProfileFragments.ReviewsFragment;
import com.smile.worker.Fragments.WorkerProfileFragments.WorkerProfileFragment;
import com.smile.worker.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WorkerProfileActivity extends AppCompatActivity {
    @BindView(R.id.step_view)
        StepView stepView_verification;

    @BindView(R.id.act_worker_toolBar)
    Toolbar act_worker_toolbar;

    @BindView(R.id.act_worker_profile_ViewPager)
        ViewPager act_worker_profile_ViewPager;
    @BindView(R.id.act_worker_profile_tabLayout)
        TabLayout act_worker_profile_tabLayout;

    @BindView(R.id.btnBack_act_workerSetup)
        Button btnBack_act_workerSetup;

    @BindView(R.id.imgBtn_activityWorkerProfile_MENU)
        ImageButton imgBtn_activityWorkerProfile_MENU;

    @BindView(R.id.txtv_worker_profile_act_worker_name)
    TextView txtvWorkerName;
    @BindView(R.id.txtv_worker_profile_act_worker_profession)
    TextView txtvWorkerProfession;
    @BindView(R.id.txtv_worker_profile_act_work_description)
    TextView txtvWorkerDescription;

    private ReviewsFragment reviewsFragment;
    private WorkerProfileFragment workerProfileFragment;
    private GigsWorkerFragment gigsWorkerFragment;

    private DatabaseReference userReference;

    private static final String USER_LINK = "users";
    private static final String USER_FIRST_NAME_LINK = "_personal_information/first_name";
    private static final String USER_LAST_NAME_LINK = "_personal_information/last_name";
    private static final String WORKER_PROFESSION_LINK = "_worker_profile/_workerProfession/workerProfession";
    private static final String WORKER_DESCRIPTION_LINK = "_worker_profile/_workerProfession/workerDescription";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_profile);
        ButterKnife.bind(this);

        //Back end
        userReference = FirebaseDatabase.getInstance()
                .getReference(USER_LINK)
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid());

        //get personal and worker information
        Task<DataSnapshot> load_user_information = userReference.get();
        load_user_information.addOnCompleteListener(task->{
           if(task.isSuccessful()) {
               DataSnapshot result = task.getResult();

               String first_name = result.child(USER_FIRST_NAME_LINK).getValue(String.class);
               String last_name = result.child(USER_LAST_NAME_LINK).getValue(String.class);
               String combined_info = first_name.toUpperCase() + ", " + last_name.toUpperCase();

               String worker_profession = result.child(WORKER_PROFESSION_LINK).getValue(String.class);
               String worker_description = result.child(WORKER_DESCRIPTION_LINK).getValue(String.class);

               txtvWorkerName.setText(combined_info);
               txtvWorkerProfession.setText(worker_profession);
               txtvWorkerDescription.setText(worker_description);
           } else {
               task.getException().printStackTrace();
           }
        });

        act_worker_toolbar = findViewById(R.id.act_worker_toolBar);
        setSupportActionBar(act_worker_toolbar);
        getSupportActionBar().hide();

        reviewsFragment = new ReviewsFragment();
        workerProfileFragment = new WorkerProfileFragment();
        gigsWorkerFragment = new GigsWorkerFragment();

        act_worker_profile_tabLayout.setupWithViewPager(act_worker_profile_ViewPager);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),0);

        viewPagerAdapter.addFragment(reviewsFragment,"Reviews");
        viewPagerAdapter.addFragment(workerProfileFragment,"Worker Profile");
        viewPagerAdapter.addFragment(gigsWorkerFragment,"Gigs");
        act_worker_profile_ViewPager.setAdapter(viewPagerAdapter);

        act_worker_profile_tabLayout.getTabAt(0).setIcon(R.drawable.outline_star_border_24);
        act_worker_profile_tabLayout.getTabAt(1).setIcon(R.drawable.outline_badge_24);
        act_worker_profile_tabLayout.getTabAt(2).setIcon(R.drawable.outline_business_center_24);

        stepView_verification.getState()
                .animationType(StepView.ANIMATION_ALL)
                .steps(new ArrayList<String>() {{
                    add("NBI CLEARANCE");
                    add("VALID ID");
                    add("VERIFIED");
                }})
                .stepsNumber(3)
                .animationDuration(getResources().getInteger(android.R.integer.config_shortAnimTime))
                .commit();

        stepView_verification.setOnStepClickListener(new StepView.OnStepClickListener() {
            @Override
            public void onStepClick(int step) {

                stepView_verification.go(step,true);
                //stepView.done(true);
                Toast.makeText(getApplicationContext(),stepView_verification.getCurrentStep()+ "", Toast.LENGTH_SHORT).show();
            }
        });

        btnBack_act_workerSetup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(getSupportFragmentManager().getBackStackEntryCount() > 0)
                    getSupportFragmentManager().popBackStack();
                else
                    onBackPressed();
            }
        });

        imgBtn_activityWorkerProfile_MENU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
       WorkerProfileActivity.this.finish();
    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragmentList = new ArrayList<>();
        private List<String> fragmentTitle = new ArrayList<>();

        public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        public void addFragment(Fragment fragment,String title){
            fragmentList.add(fragment);
            fragmentTitle.add(title);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentTitle.get(position);
        }
    }
}