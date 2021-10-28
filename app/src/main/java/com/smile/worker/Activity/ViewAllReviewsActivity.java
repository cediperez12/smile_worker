package com.smile.worker.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.smile.worker.Adapter.ReviewsListAdapter;
import com.smile.worker.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewAllReviewsActivity extends AppCompatActivity {

    @BindView(R.id.act_viewAll_reviews_recycleView)
    RecyclerView act_viewAll_reviews_recycleView;

    @BindView(R.id.chipGroup_actViewAllReviews_cat)//Chip group for category filter
    ChipGroup chipGroup_actViewAllReviews_cat;
    @BindView(R.id.chip_actViewAllReviews_all)//Chips for filter category
    Chip chip_actViewAllReviews_all;
    @BindView(R.id.chip_actViewAllReviews_critical)
    Chip chip_actViewAllReviews_critical;
    @BindView(R.id.chip_actViewAllReviews_possitive)
    Chip chip_actViewAllReviews_possitive;


    @BindView(R.id.chipGroup_actViewAllReviews_rate)//chip group for rate
    ChipGroup chipGroup_actViewAllReviews_rate;
    @BindView(R.id.chip_actViewAllReviews_1s)//Chip Rate
            Chip chip_actViewAllReviews_1s;
    @BindView(R.id.chip_actViewAllReviews_2s)
    Chip chip_actViewAllReviews_2s;
    @BindView(R.id.chip_actViewAllReviews_3s)
    Chip chip_actViewAllReviews_3s;
    @BindView(R.id.chip_actViewAllReviews_4s)
    Chip chip_actViewAllReviews_4s;
    @BindView(R.id.chip_actViewAllReviews_5s)
    Chip chip_actViewAllReviews_5s;

    @BindView(R.id.tv_actViewAllReviews_1sPercentage)//TextView Percentage
    TextView tv_actViewAllReviews_1sPercentage;
    @BindView(R.id.tv_actViewAllReviews_2sPercentage)
    TextView tv_actViewAllReviews_2sPercentage;
    @BindView(R.id.tv_actViewAllReviews_3sPercentage)
    TextView tv_actViewAllReviews_3sPercentage;
    @BindView(R.id.tv_actViewAllReviews_4sPercentage)
    TextView tv_actViewAllReviews_4sPercentage;
    @BindView(R.id.tv_actViewAllReviews_5sPercentage)
    TextView tv_actViewAllReviews_5sPercentage;

    @BindView(R.id.pb_actViewAllReviews_1sbar)//Progress Bar
    ProgressBar pb_actViewAllReviews_1sbar;
    @BindView(R.id.pb_actViewAllReviews_2sbar)
    ProgressBar pb_actViewAllReviews_2sbar;
    @BindView(R.id.pb_actViewAllReviews_3sbar)
    ProgressBar pb_actViewAllReviews_3sbar;
    @BindView(R.id.pb_actViewAllReviews_4sbar)
    ProgressBar pb_actViewAllReviews_4sbar;
    @BindView(R.id.pb_actViewAllReviews_5sbar)
    ProgressBar pb_actViewAllReviews_5sbar;


    @BindView(R.id.btnBack_act_viewAllReviews)
    Button btnBack_act_viewAllReviews;

    String s1[],s2[];
    int image[] = {R.drawable.arey_sample_pic_chat,R.drawable.cd_sample_pic_chat};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_reviews);
        ButterKnife.bind(this);



        s1 = getResources().getStringArray(R.array.sample_name);
        s2 = getResources().getStringArray(R.array.sample_displayConvo);

        ReviewsListAdapter reviewsListAdapter = new ReviewsListAdapter(this, s1,s2,image);

        act_viewAll_reviews_recycleView.setLayoutManager(new LinearLayoutManager(this));
        act_viewAll_reviews_recycleView.setAdapter(reviewsListAdapter);

        btnBack_act_viewAllReviews.setOnClickListener(new View.OnClickListener() {
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