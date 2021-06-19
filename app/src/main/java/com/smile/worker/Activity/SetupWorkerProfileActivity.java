package com.smile.worker.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.smile.worker.Fragments.RegisterFragment.PersonalInformationFragment;
import com.smile.worker.Fragments.SetupWorkerFragments.CertificatesFragment;
import com.smile.worker.Fragments.SetupWorkerFragments.EducationFragment;
import com.smile.worker.Fragments.SetupWorkerFragments.GigsFragment;
import com.smile.worker.Fragments.SetupWorkerFragments.SkillsFragment;
import com.smile.worker.Fragments.SetupWorkerFragments.VerificationFragment;
import com.smile.worker.Fragments.SetupWorkerFragments.WorkerProfessionFragment;
import com.smile.worker.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SetupWorkerProfileActivity extends AppCompatActivity {

    @BindView(R.id.setupWorker_fr_layout_replaceable)
    FrameLayout frLayoutReplaceable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_worker_profile);
        ButterKnife.bind(this);
        getWorkerProfessionFragment();
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