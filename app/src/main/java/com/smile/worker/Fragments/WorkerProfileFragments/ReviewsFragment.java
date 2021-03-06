package com.smile.worker.Fragments.WorkerProfileFragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.smile.worker.Activity.ViewAllReviewsActivity;
import com.smile.worker.Adapter.ChatListAdapter;
import com.smile.worker.Adapter.ReviewsListAdapter;
import com.smile.worker.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ReviewsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReviewsFragment extends Fragment implements View.OnClickListener {

    @BindView(R.id.tv_fragmentViewAllReviews)
    TextView tv_fragmentViewAllReviews;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    String s1[],s2[];
    int image[] = {R.drawable.arey_sample_pic_chat,R.drawable.cd_sample_pic_chat};

    public ReviewsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ReviewsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ReviewsFragment newInstance(String param1, String param2) {
        ReviewsFragment fragment = new ReviewsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_reviews, container, false);
        ButterKnife.bind(this,v);
        RecyclerView recyclerView =  v.findViewById(R.id.recyclerView_fragment_reviews_List);

        s1 = getResources().getStringArray(R.array.sample_name);
        s2 = getResources().getStringArray(R.array.sample_displayConvo);

        ReviewsListAdapter reviewsListAdapter = new ReviewsListAdapter(v.getContext(), s1,s2,image);

        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));
        recyclerView.setAdapter(reviewsListAdapter);

        tv_fragmentViewAllReviews.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_fragmentViewAllReviews:
                Toast.makeText(getActivity(), "View All", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(view.getContext(), ViewAllReviewsActivity.class);
                startActivity(intent);
                break;

        }
    }
}