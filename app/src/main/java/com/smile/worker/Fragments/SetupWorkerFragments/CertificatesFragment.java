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
import com.smile.worker.Adapter.CertificateListAdapter;
import com.smile.worker.Models.Certificate;
import com.smile.worker.R;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CertificatesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CertificatesFragment extends Fragment {

    @BindView(R.id.btnBack_fr_workSetup_certificates)
    Button btnBack;
    @BindView(R.id.btnNext_fr_workSetup_certificates)
    Button btnNext;
    @BindView(R.id.btnSkip_fr_workSetup_certificates)
    Button btnSkip;
    @BindView(R.id.btnAddCert_fr_workSetup_certificate)
    Button btnAdd;

    @BindView(R.id.tfCertEvent_fr_workSetup_certificate)
    TextInputLayout tilCertEvent;
    @BindView(R.id.tfCertTitle_fr_workSetup_certificate)
    TextInputLayout tilCertTitle;
    @BindView(R.id.actCertYr_fr_workSetup_certificate)
    TextInputLayout tilCertYr;
    @BindView(R.id.fr_worker_certificate_recyclerview)
    RecyclerView recycler;

    private SetupWorkerProfileActivity _parent;

    private List<Certificate> listOfCertificate;
    private CertificateListAdapter adapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CertificatesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CertificatesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CertificatesFragment newInstance(String param1, String param2) {
        CertificatesFragment fragment = new CertificatesFragment();
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
        View v = inflater.inflate(R.layout.fragment_worker_certificates, container, false);

        ButterKnife.bind(this,v);
        _parent = (SetupWorkerProfileActivity)getContext();

        //Setup Year
        ArrayAdapter<String> yearAdapter = new ArrayAdapter<String>(v.getContext(),
                android.R.layout.simple_dropdown_item_1line,
                getResources().getStringArray(R.array.arr_year));

        AutoCompleteTextView yrEtxt = (AutoCompleteTextView)tilCertYr.getEditText();
        yrEtxt.setAdapter(yearAdapter);

        listOfCertificate = new ArrayList<>();
        adapter = new CertificateListAdapter(listOfCertificate);

        recycler.setHasFixedSize(false);
        recycler.setLayoutManager(new LinearLayoutManager(v.getContext()));
        recycler.setAdapter(adapter);

        btnAdd.setOnClickListener(v1->{
            String certTitle = tilCertTitle.getEditText().getText().toString().trim();
            String certEvent = tilCertEvent.getEditText().getText().toString().trim();
            String certYear = tilCertYr.getEditText().getText().toString().trim();

            try{
                //Check data
                if(certTitle.isEmpty()){
                    throw new Exception("No title");
                }else if(certEvent.isEmpty()){
                    throw new Exception("No title");
                }else if(certYear.isEmpty()){
                    throw new Exception("No title");
                }

                Certificate cert = new Certificate(certTitle,certEvent,certYear);

                listOfCertificate.add(cert);
                adapter.notifyDataSetChanged();
            }catch (Exception ex){
                ex.printStackTrace();
                //Error here
            }
        });

        btnBack.setOnClickListener(v1 -> {
            _parent.getGigsFragment();
        });
        btnNext.setOnClickListener(v1 -> {
            _parent.getVerificationFragment();
        });

        btnSkip.setOnClickListener(v1 -> {
            _parent.getCertificate(listOfCertificate);
        });
        return  v;


    }
}