package com.smile.worker.Fragments.SetupWorkerFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.smile.worker.Activity.SetupWorkerProfileActivity;
import com.smile.worker.Adapter.SkillListAdapter;
import com.smile.worker.R;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SkillsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SkillsFragment extends Fragment {

    @BindView(R.id.tfSkills_fr_workSetup_skills)
    TextInputLayout tilSkills;

    @BindView(R.id.btnBack_fr_workSetup_skills)
    Button btnBack;

    @BindView(R.id.btnNext_fr_workSetup_skills)
    Button btnNext;

    @BindView(R.id.fr_worker_recyclerview)
    RecyclerView recyclerView;

    private SetupWorkerProfileActivity _parent;
    private List<String> skillList;
    private SkillListAdapter adapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SkillsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SkillsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SkillsFragment newInstance(String param1, String param2) {
        SkillsFragment fragment = new SkillsFragment();
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
        View v = inflater.inflate(R.layout.fragment_worker_skills, container, false);

        ButterKnife.bind(this,v);
        _parent = (SetupWorkerProfileActivity) getContext();

        skillList = new ArrayList<>();
        adapter = new SkillListAdapter(skillList);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));
        recyclerView.setAdapter(adapter);

        tilSkills.setEndIconOnClickListener(v1->{
            String skill = tilSkills.getEditText().getText().toString().trim();
            
            if(!skill.isEmpty()){
                tilSkills.getEditText().setText("");
                skillList.add(skill);
                adapter.notifyDataSetChanged();
            }
        });

        btnBack.setOnClickListener(v1 -> {
            _parent.getEducationFragment();
        });

        btnNext.setOnClickListener(v1 -> {
            _parent.getSkills(skillList);
        });

        return v;
    }
}