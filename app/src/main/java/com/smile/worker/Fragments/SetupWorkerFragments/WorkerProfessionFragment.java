package com.smile.worker.Fragments.SetupWorkerFragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.smile.worker.Activity.LoginActivity;
import com.smile.worker.Activity.RegisterActivity;
import com.smile.worker.Activity.SetupWorkerProfileActivity;
import com.smile.worker.Models.WorkerProfession;
import com.smile.worker.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WorkerProfessionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WorkerProfessionFragment extends Fragment {

    @BindView(R.id.tfProf_fr_workSetup_prof)
    TextInputLayout tilWorkProfession;
    @BindView(R.id.tfWorkDesc_fr_workSetup_prof)
    TextInputLayout tilWorkDescription;
    @BindView(R.id.btnBack_fr_workSetup_prof)
    Button btnBack;
    @BindView(R.id.btnNext_fr_workSetup_prof)
    Button btnNext;

    private SetupWorkerProfileActivity _parent;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public WorkerProfessionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WorkerProfessionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WorkerProfessionFragment newInstance(String param1, String param2) {
        WorkerProfessionFragment fragment = new WorkerProfessionFragment();
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
        View v = inflater.inflate(R.layout.fragment_worker_profession, container, false);
        ButterKnife.bind(this,v);
        _parent = (SetupWorkerProfileActivity) getContext();

        btnNext.setOnClickListener(v1 ->{
            //Fetch worker information
            String workerProfession = tilWorkProfession.getEditText().getText().toString().trim();
            String workerDescription = tilWorkDescription.getEditText().getText().toString().trim();

            //Check if information valid
            try{
                if(workerProfession.isEmpty())
                    throw new Exception("Worker Profession empty");
                else if(workerDescription.isEmpty())
                    throw new Exception("Worker Description empty");

                //Save and store data
                //go to education fragment
                WorkerProfession wp = new WorkerProfession(workerProfession, workerDescription);
                _parent.getWorkerProfesison(wp);
            }catch (Exception ex){
                ex.printStackTrace();
                //Errors exists here
            }
        });

        btnBack.setOnClickListener(v1 -> {
            _parent.onBackPressed();
        });

        return v;
    }
}