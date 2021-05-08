package com.smile.worker.Activity;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.smile.worker.Fragments.RegisterFragment.CredentialFragment;
import com.smile.worker.Fragments.RegisterFragment.FirstRegisterFragment;
import com.smile.worker.Fragments.RegisterFragment.LastRegisterFragment;
import com.smile.worker.Fragments.RegisterFragment.PersonalInformationFragment;
import com.smile.worker.Models.PersonalInformation;
import com.smile.worker.Models.UserCredential;
import com.smile.worker.Models.UserType;
import com.smile.worker.R;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.register_fr_layout_replaceable)
    FrameLayout frLayoutReplaceable;

    private PersonalInformation _userPersonalInformation;
    private UserCredential _credentials;

    private FirebaseAuth auth;
    private DatabaseReference userDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        //Authentication init
        auth = FirebaseAuth.getInstance();

        //Create Database instance
        userDatabaseReference = FirebaseDatabase.getInstance().getReference("users");

        getSupportFragmentManager()
                .beginTransaction()
                .replace(frLayoutReplaceable.getId(), new FirstRegisterFragment())
                .commit();
    }

    public void continueSignUp(){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(frLayoutReplaceable.getId(),new PersonalInformationFragment())
                .commit();
    }

    public boolean fetchPersonalInformation(PersonalInformation pi){
        //fetch data
        this._userPersonalInformation = pi;
        //go to credentials
        getSupportFragmentManager()
                .beginTransaction()
                .replace(frLayoutReplaceable.getId(),
                        new CredentialFragment())
                .commit();
        return true;
    }

    public boolean fetchUserCredentials(UserCredential cred){
        //fetch data
        this._credentials = cred;
        //go to last fragment
        createNewUser();
        return true;
    }

    private void createNewUser(){
        auth.createUserWithEmailAndPassword(_credentials.user_email,_credentials.user_password)
                .addOnSuccessListener(task->{
                    FirebaseUser newUser = task.getUser();

                    //Add to database.
                    userDatabaseReference = userDatabaseReference.child(newUser.getUid());
                    Map<String, Object> newData = new HashMap<>();
                    newData.put("user_id",newUser.getUid());
                    newData.put("user_type",UserType.USER_TYPE_WORKER);
                    newData.put("_personal_information",_userPersonalInformation);
                    newData.put("_credentials",_credentials);
                    userDatabaseReference.setValue(newData)
                            .addOnSuccessListener(succ_task->{
                                //successfully registered.
                                getSupportFragmentManager()
                                        .beginTransaction()
                                        .replace(frLayoutReplaceable.getId(),
                                                new LastRegisterFragment())
                                        .commit();
                            })
                            .addOnFailureListener(ex->{
                                ex.printStackTrace();
                                //fails
                            });
                })
                .addOnFailureListener(ex->{
                    ex.printStackTrace();
                });
    }
}