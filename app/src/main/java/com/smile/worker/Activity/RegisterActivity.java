package com.smile.worker.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
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

    public static final String REGISTER_FLAGS = "reg_flags";
    public static final int NORMAL_REGISTRATION = 1;
    public static final int GOOGLE_REGISTRATION = 2;

    int flag = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        //Authentication init
        auth = FirebaseAuth.getInstance();

        //Create Database instance
        userDatabaseReference = FirebaseDatabase.getInstance().getReference("users");

        flag = getIntent().getExtras().getInt(REGISTER_FLAGS,-1);

        if(flag == NORMAL_REGISTRATION){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(frLayoutReplaceable.getId(), new FirstRegisterFragment())
                    .commit();
        }else if(flag == GOOGLE_REGISTRATION){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(frLayoutReplaceable.getId(), new PersonalInformationFragment())
                    .commit();
        }
    }

    public void continueSignUp(){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(frLayoutReplaceable.getId(),new PersonalInformationFragment())
                .commit();
    }

    public boolean fetchPersonalInformation(PersonalInformation pi){
        if(flag == NORMAL_REGISTRATION){
            //fetch data
            this._userPersonalInformation = pi;
            //go to credentials
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(frLayoutReplaceable.getId(),
                            new CredentialFragment())
                    .commit();
            return true;
        }else if(flag == GOOGLE_REGISTRATION){
            //Setup personal information
            this._userPersonalInformation = pi;
            //Then, create new google user
            createNewGoogleUser();
            return true;
        }

        return false;
    }

    public boolean fetchUserCredentials(UserCredential cred){
        //fetch data
        this._credentials = cred;
        //go to last fragment
        createNewUser();
        return true;
    }

    private void createNewGoogleUser(){
        FirebaseUser currentUser = auth.getCurrentUser();

        UserCredential creds = new UserCredential();
        creds.setUser_email(currentUser.getEmail());

        Map<String, Object> newData = new HashMap<>();
        newData.put("user_id",currentUser.getUid());
        newData.put("user_type",UserType.USER_TYPE_WORKER);
        newData.put("_personal_information",_userPersonalInformation);
        newData.put("_credentials",creds);

        userDatabaseReference.child(currentUser.getUid())
                .setValue(newData)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            new MaterialAlertDialogBuilder(RegisterActivity.this)
                                    .setTitle("Google Registration")
                                    .setMessage("Thank you for setting up your personal information, you may now login into the system.")
                                    .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            Intent intent = new Intent(getApplicationContext(),
                                                    MainNavigationActivity.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                    })
                                    .create()
                                    .show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        new MaterialAlertDialogBuilder(RegisterActivity.this)
                                .setTitle("Error")
                                .setMessage(e.getMessage())
                                .create()
                                .show();
                    }
                });
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