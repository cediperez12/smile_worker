package com.smile.worker.Fragments.RegisterFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.smile.worker.Activity.RegisterActivity;
import com.smile.worker.Models.UserCredential;
import com.smile.worker.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CredentialFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CredentialFragment extends Fragment {

    @BindView(R.id.fr_cred_til_email)
    TextInputLayout tilEmail;
    @BindView(R.id.fr_cred_til_phone)
    TextInputLayout tilPhone;
    @BindView(R.id.fr_cred_til_pass)
    TextInputLayout tilPassword;
    @BindView(R.id.fr_cred_til_confirm_pass)
    TextInputLayout tilConfirmPass;
    @BindView(R.id.fr_cred_btn_back)
    Button btnBack;
    @BindView(R.id.fr_cred_btn_next)
    Button btnNext;

    private RegisterActivity _parent;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CredentialFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CredentialFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CredentialFragment newInstance(String param1, String param2) {
        CredentialFragment fragment = new CredentialFragment();
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
        View view = inflater.inflate(R.layout.fragment_credential, container, false);
        ButterKnife.bind(this,view);

        btnNext.setOnClickListener(v->{

            String strEmail = tilEmail.getEditText().getText().toString().trim();
            String strPassword = tilPassword.getEditText().getText().toString().trim();
            String strPhone = tilPhone.getEditText().getText().toString().trim();
            String strConfirmPass = tilConfirmPass.getEditText().getText().toString().trim();

            try{
                if(strEmail.isEmpty()){
                    throw new Exception("empty");
                }else if(strPassword.isEmpty()){
                    throw new Exception("empty");
                }else if(strPassword.length() < 8){
                    throw new Exception("less than 8");
                }else if(strPhone.length() < 11){
                    throw new Exception("phone must be 11");
                }else if(!strConfirmPass.equals(strPassword)){
                    throw new Exception("password does not match");
                }

                UserCredential cred =
                        new UserCredential(strEmail,strPassword,strPhone);

                _parent = (RegisterActivity)getContext();
                _parent.fetchUserCredentials(cred);

                Log.d("Credentials",cred.user_email + " " + cred.user_password);
            }catch (Exception e){
                e.printStackTrace();
            }
        });

        return view;
    }
}