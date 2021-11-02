package com.smile.worker.Fragments.WorkerProfileFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.smile.worker.Adapter.ViewHolderListItem_CertAdapter;
import com.smile.worker.Adapter.ViewHolderListItem_EducAdapter;
import com.smile.worker.Adapter.ViewWorkerListItem_SkillsAdapter;
import com.smile.worker.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WorkerProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WorkerProfileFragment extends Fragment {
    @BindView(R.id.fragment_worker_profileSkill_container)
    LinearLayout fragment_worker_profileSkill_container;


    @BindView(R.id.recyclerView_fragment_gigsCert)
    RecyclerView recyclerView_fragment_gigsCert;

    @BindView(R.id.recyclerView_fragment_gigsSkills)
    RecyclerView recyclerView_fragment_gigsSkills;

    @BindView(R.id.recyclerView_fragment_gigsEduc)
    RecyclerView recyclerView_fragment_gigsEduc;

    private static final String USERS_LINK = "users";

    private static final String WORKER_EDUCATION_SCHOOL_LINK =
            "_worker_profile/_education";
    private static final String WORKER_EDUCATION_SCHOOL_LEVEL_LINK =
            "_worker_profile/_education/schoolLevel";
    private static final String WORKER_EDUCATION_SCHOOL_NAME_LINK =
            "_worker_profile/_education/schoolName";
    private static final String WORKER_EDUCATION_SCHOOL_YEAR_LINK =
            "_worker_profile/_education/year";

    private static final String WORKER_SKILLS_LINK = "_worker_profile/_skills";

    private FirebaseUser curr_user;
    private DatabaseReference userReference;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public WorkerProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WorkerProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WorkerProfileFragment newInstance(String param1, String param2) {
        WorkerProfileFragment fragment = new WorkerProfileFragment();
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
        View v = inflater.inflate(R.layout.fragment_worker_profile, container, false);
        ButterKnife.bind(this,v);

        //Get current user
        curr_user = FirebaseAuth.getInstance().getCurrentUser();

        //Backend
        userReference = FirebaseDatabase.getInstance()
                .getReference(USERS_LINK)
                .child(curr_user.getUid());

        Task<DataSnapshot> load_user_data = userReference.get();
        load_user_data.addOnCompleteListener(task-> {
           if(task.isSuccessful()) {
               DataSnapshot result = task.getResult();

               DataSnapshot school_data = result.child(WORKER_EDUCATION_SCHOOL_LINK);
               DataSnapshot skills_data = result.child(WORKER_SKILLS_LINK);

               List<String> list_of_skills = new ArrayList<>();

               //Arrange Skills
               for (DataSnapshot data : skills_data.getChildren()) {
                   list_of_skills.add(data.getValue(String.class));
               }

               LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
               LinearLayoutManager layoutManager2 = new LinearLayoutManager(this.getContext());
               ViewHolderListItem_EducAdapter educ_adapter = new ViewHolderListItem_EducAdapter(school_data);
               ViewWorkerListItem_SkillsAdapter skill_adapter = new ViewWorkerListItem_SkillsAdapter(list_of_skills);

               recyclerView_fragment_gigsEduc.setLayoutManager(layoutManager);
               recyclerView_fragment_gigsEduc.setAdapter(educ_adapter);

               recyclerView_fragment_gigsSkills.setLayoutManager(layoutManager2);
               recyclerView_fragment_gigsSkills.setAdapter(skill_adapter);
           }
        });

        recyclerView_fragment_gigsCert.setLayoutManager(
                new LinearLayoutManager(getActivity()));
        recyclerView_fragment_gigsCert.setAdapter(new ViewHolderListItem_CertAdapter());

        return v;
    }
}