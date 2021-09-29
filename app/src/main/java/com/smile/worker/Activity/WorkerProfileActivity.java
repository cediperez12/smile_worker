package com.smile.worker.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.widget.Toast;

import com.shuhart.stepview.StepView;
import com.smile.worker.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WorkerProfileActivity extends AppCompatActivity {
@BindView(R.id.step_view)
    StepView stepView_verification;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_profile);

        ButterKnife.bind(this);


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
    }
}