package com.smile.worker.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.smile.worker.Fragments.RegisterFragment.PersonalInformationFragment;
import com.smile.worker.Fragments.SetupWorkerFragments.CertificatesFragment;
import com.smile.worker.Fragments.SetupWorkerFragments.EducationFragment;
import com.smile.worker.Fragments.SetupWorkerFragments.GigsFragment;
import com.smile.worker.Fragments.SetupWorkerFragments.SkillsFragment;
import com.smile.worker.Fragments.SetupWorkerFragments.VerificationFragment;
import com.smile.worker.Fragments.SetupWorkerFragments.WorkerProfessionFragment;
import com.smile.worker.Models.Certificate;
import com.smile.worker.Models.Education;
import com.smile.worker.Models.Gig;
import com.smile.worker.Models.WorkerProfession;
import com.smile.worker.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SetupWorkerProfileActivity extends AppCompatActivity {

    @BindView(R.id.setupWorker_fr_layout_replaceable)
    FrameLayout frLayoutReplaceable;

    public static final String SETUP_WORKER_FLAG = "setup_worker";
    public static final int NEW_SETUP = 1;

    private WorkerProfession profession;
    private Education education;
    private List<String> skills;
    private Gig gig;
    private List<Certificate> certificates;

    private FirebaseUser currentUser;
    private DatabaseReference userReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_worker_profile);
        ButterKnife.bind(this);
        getWorkerProfessionFragment();

        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        userReference = FirebaseDatabase.getInstance()
                .getReference("users");
    }

    public void getWorkerProfesison(WorkerProfession wp) {
        this.profession = wp;
        getEducationFragment();
    }

    public void getEducation(Education ed){
        this.education = ed;
        getSkillsFragment();
    }

    public void getSkills(List<String> skills){
        this.skills = skills;
        getGigsFragment();
    }

    public void getGig(Gig gig){
        this.gig = gig;
        getCertificatesFragment();
    }

    public void getCertificate(List<Certificate> list){
        certificates = list;
        getVerificationFragment();
    }

    public void createWorkerProfile() {
        Map<String, Object> workerProfile = new HashMap<>();
        workerProfile.put("_workerProfession",profession);
        workerProfile.put("_education", education);
        workerProfile.put("_skills", skills);
        workerProfile.put("_gig", gig);
        workerProfile.put("_certificates",certificates);
        userReference
                .child(currentUser.getUid())
                .child("_worker_profile")
                .setValue(workerProfile)
                .addOnCompleteListener(task->{
                    if(task.isSuccessful()){
                        Intent intent = new Intent(getApplicationContext()
                        , MainNavigationActivity.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .addOnFailureListener(ex->{
                    Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }

    public void getEducationFragment(){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(frLayoutReplaceable.getId(),new EducationFragment())
                .commit();
    }
    public void getWorkerProfessionFragment(){

        getSupportFragmentManager()
                .beginTransaction()
                .replace(frLayoutReplaceable.getId(),new WorkerProfessionFragment())
                .commit();
    }

    public void getSkillsFragment(){

        getSupportFragmentManager()
                .beginTransaction()
                .replace(frLayoutReplaceable.getId(),new SkillsFragment())
                .commit();
    }

    public void getGigsFragment(){

        getSupportFragmentManager()
                .beginTransaction()
                .replace(frLayoutReplaceable.getId(),new GigsFragment())
                .commit();
    }
    public void getCertificatesFragment(){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(frLayoutReplaceable.getId(),new CertificatesFragment())
                .commit();
    }
    public void getVerificationFragment(){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(frLayoutReplaceable.getId(),new VerificationFragment())
                .commit();

    }
}