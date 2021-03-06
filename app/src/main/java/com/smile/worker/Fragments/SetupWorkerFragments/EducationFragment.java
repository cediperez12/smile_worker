package com.smile.worker.Fragments.SetupWorkerFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.smile.worker.Activity.SetupWorkerProfileActivity;
import com.smile.worker.Models.Education;
import com.smile.worker.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EducationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EducationFragment extends Fragment {

    @BindView(R.id.tfEducLevel_fr_workSetup_education)
    TextInputLayout tilEducationalLevel;

    @BindView(R.id.tfSchoolName_fr_workSetup_education)
    TextInputLayout tilSchoolName;

    @BindView(R.id.tfYear_fr_workSetup_education)
    TextInputLayout tilSchoolYear;

    @BindView(R.id.btnNext_fr_workSetup_education)
    Button btnNext;

    @BindView(R.id.btnBack_fr_workSetup_education)
    Button btnBack;

    @BindView(R.id.btnSkip_fr_workSetup_education)
    Button btnSkip;


    private SetupWorkerProfileActivity _parent;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EducationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EducationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EducationFragment newInstance(String param1, String param2) {
        EducationFragment fragment = new EducationFragment();
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
        View v= inflater.inflate(R.layout.fragment_worker_education, container, false);
        ButterKnife.bind(this,v);

        _parent = (SetupWorkerProfileActivity)getContext();

        //Setup Education Level & Year
        ArrayAdapter<String> educLevelAdapter = new ArrayAdapter<String>(v.getContext(),android.R.layout.simple_dropdown_item_1line,getResources().getStringArray(R.array.arr_educ_lvl));
        ArrayAdapter<String> yrAdapter = new ArrayAdapter<String>(v.getContext(),
                android.R.layout.simple_dropdown_item_1line,
                getResources().getStringArray(R.array.arr_year));

        AutoCompleteTextView educLevelEtxt =
                (AutoCompleteTextView)tilEducationalLevel.getEditText(),
        yearEtxt = (AutoCompleteTextView)tilSchoolYear.getEditText();
        educLevelEtxt.setAdapter(educLevelAdapter);
        yearEtxt.setAdapter(yrAdapter);

        btnBack.setOnClickListener(v1 -> {
            _parent.getWorkerProfessionFragment();
        });
        btnNext.setOnClickListener(v1 -> {
            //Fetch data
            String educationLevel = tilEducationalLevel.getEditText().getText().toString().trim();
            String schoolName = tilSchoolName.getEditText().getText().toString().trim();
            String year = tilSchoolYear.getEditText().getText().toString().trim();

            try{
                //Check data validity
                if(educationLevel.isEmpty())
                    throw new Exception("education level error");
                else if(schoolName.isEmpty())
                    throw new Exception("No school");
                else if(year.isEmpty())
                    throw new Exception("No year selected");

                //Save data and store
                //Then go to next
                Education education = new Education(educationLevel,schoolName,year);
                _parent.getEducation(education);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        });
        btnSkip.setOnClickListener(v1 ->{
            _parent.getSkillsFragment();
        });

        return v;
    }
}