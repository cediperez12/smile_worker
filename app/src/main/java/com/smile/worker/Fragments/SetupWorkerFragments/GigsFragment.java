package com.smile.worker.Fragments.SetupWorkerFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.smile.worker.Activity.SetupWorkerProfileActivity;
import com.smile.worker.Models.Gig;
import com.smile.worker.R;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GigsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GigsFragment extends Fragment {

    @BindView(R.id.btnBack_fr_workSetup_gigs)
    Button btnBack;
    @BindView(R.id.btnSkip_fr_workSetup_gigs)
    Button btnSkip;
    @BindView(R.id.btnNext_fr_workSetup_Gigs)
    Button btnNext;

    @BindView(R.id.tfGigs_fr_workSetup_gigs)
    TextInputLayout tilGigsName;
    @BindView(R.id.tfGigsDesc_fr_workSetup_gigs)
    TextInputLayout tilGigsDescription;
    @BindView(R.id.tfGigsRate_fr_workSetup_gigs)
    TextInputLayout tilGigsRate;

    private SetupWorkerProfileActivity _parent;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public GigsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GigsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GigsFragment newInstance(String param1, String param2) {
        GigsFragment fragment = new GigsFragment();
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
        View v = inflater.inflate(R.layout.fragment_worker_gigs, container, false);

        ButterKnife.bind(this,v);
        _parent = (SetupWorkerProfileActivity)getContext();

        btnBack.setOnClickListener(v1 -> {
            _parent.getSkillsFragment();
        });
        btnNext.setOnClickListener(v1 -> {
            _parent.getCertificatesFragment();

            try{
                //fetch data
                String gigName = tilGigsName.getEditText().getText().toString().trim();
                String gigDescription = tilGigsDescription.getEditText().getText().toString().trim();
                double gigRate = Double.parseDouble(tilGigsRate.getEditText().getText()
                        .toString().trim());

                //Check input data
                if(gigName.isEmpty()){
                    throw new Exception("No gig name");
                }else if(gigDescription.isEmpty()){
                    throw new Exception("No gig description");
                } else if (gigRate <= 0) {
                    throw new Exception("Gig must be higher than 0");
                }

                //Store data
                //Then go to next
                Gig gig = new Gig(gigName,gigDescription,gigRate);
                _parent.getGig(gig);
            }catch (Exception ex){
                ex.printStackTrace();
                //Error here
            }
        });
        btnSkip.setOnClickListener(v1 -> {
            _parent.getCertificatesFragment();
        });

        return v;
    }
}