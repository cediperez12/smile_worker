package com.smile.worker.Fragments.RegisterFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.textfield.TextInputLayout;
import com.smile.worker.Activity.RegisterActivity;
import com.smile.worker.Models.PersonalInformation;
import com.smile.worker.R;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PersonalInformationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PersonalInformationFragment extends Fragment {

    @BindView(R.id.fr_pi_til_first_name)
    TextInputLayout tilFirstName;
    @BindView(R.id.fr_pi_til_last_name)
    TextInputLayout tilLastName;
    @BindView(R.id.fr_pi_til_spn_month)
    TextInputLayout tilMonth;
    @BindView(R.id.fr_pi_til_spn_day)
    TextInputLayout tilSpnDay;
    @BindView(R.id.fr_pi_til_spn_yr)
    TextInputLayout tilSpnYear;
    @BindView(R.id.fr_pi_btngroup_sex)
    MaterialButtonToggleGroup btnGroupSex;
    @BindView(R.id.fr_pi_btn_sex_male)
    Button btnSexMale;
    @BindView(R.id.fr_pi_btn_sex_female)
    Button btnSexFemale;
    @BindView(R.id.fr_pi_btn_cancel)
    Button btnCancel;
    @BindView(R.id.fr_pi_btn_next)
    Button btnNext;

    private RegisterActivity _parent;

    private String strSex = "Male";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PersonalInformationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PersonalInformationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PersonalInformationFragment newInstance(String param1, String param2) {
        PersonalInformationFragment fragment = new PersonalInformationFragment();
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
        View view = inflater.inflate(R.layout.fragment_personal_information, container, false);
        ButterKnife.bind(this,view);
        init();
        return view;
    }

    private void init(){
        _parent = (RegisterActivity)getContext();//Kuwa parent na act

        AutoCompleteTextView actMonth = (AutoCompleteTextView)tilMonth.getEditText(),
        actYear = (AutoCompleteTextView)tilSpnYear.getEditText();

        ArrayAdapter<String> monthAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.arr_months)),
        yearAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.arr_year));

        actMonth.setAdapter(monthAdapter);
        actYear.setAdapter(yearAdapter);

        btnNext.setOnClickListener(view->{
            String strFirstName = tilFirstName.getEditText().getText().toString().trim();
            String strLastName = tilLastName.getEditText().getText().toString().trim();
            String strMonth = tilMonth.getEditText().getText().toString().trim();
            String strDay = tilSpnDay.getEditText().getText().toString().trim();
            String strYear = tilSpnYear.getEditText().getText().toString().trim();
            String strSex = this.strSex;
            //Check strings
            try{
                if(strFirstName.isEmpty()){
                    throw new Exception("empty");
                }else if(strLastName.isEmpty()){
                    throw new Exception("empty");
                }else if(strMonth.isEmpty()){
                    throw new Exception("empty");
                }else if(strDay.isEmpty()){
                    throw new Exception("empty");
                }else if(strYear.isEmpty()){
                    throw new Exception("empty");
                }else if(strSex.isEmpty()){
                    throw new Exception("empty");
                }

                //Create birthdate
                Calendar calendar = Calendar.getInstance();
                calendar.set(Integer.parseInt(strYear), monthToInt(strMonth), Integer.parseInt(strDay),0,0,0);
                Long l_birthdate = calendar.getTimeInMillis();

                char c_sex = strSex.equals("Male") ? 'm' : 'f';

                PersonalInformation _personalInformation =
                        new PersonalInformation(strFirstName,strLastName,l_birthdate,Character.toString(c_sex));

                //Changes fragment to credentials fragment
                _parent.fetchPersonalInformation(_personalInformation);

                Log.d("Personal information",_personalInformation.first_name + "/" + _personalInformation.last_name + "/" + _personalInformation.birthdate + "/" + _personalInformation.sex);
            }catch (Exception ex){
                ex.printStackTrace();
                Log.d("Next button tapped","error occured at " + ex.getMessage());
            }
        });

        btnGroupSex.addOnButtonCheckedListener((togglebtn,checkedId,isChecked)->{
            if(checkedId == btnSexMale.getId()){
                strSex = "Male";
            }else{
                strSex = "Female";
            }
        });

        btnCancel.setOnClickListener(view->{
            _parent.continueSignUp();
        });
    }

    private int monthToInt(String month){
        String[] months = {"January","February","March","April","May",
        "June","July","August","September","October","November","December"};

        for(int i = 0; i < months.length; i++){
            if(months[i].equals(month))
                return i;
        }

        return -1;
    }
}