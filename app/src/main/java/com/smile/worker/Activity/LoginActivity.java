package com.smile.worker.Activity;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.smile.worker.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.login_til_email)
    TextInputLayout tilEmail;
    @BindView(R.id.login_til_password)
    TextInputLayout tilPassword;
    @BindView(R.id.login_btn_back)
    Button btnBack;
    @BindView(R.id.login_btn_signin)
    Button btnSignin;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        init();
    }

    private void init(){
        //Firebase Auth
        auth = FirebaseAuth.getInstance();

        btnSignin.setOnClickListener((view)->{
            String strEmail = tilEmail.getEditText().getText().toString().trim();
            String strPassword = tilPassword.getEditText().getText().toString().trim();

            //Disable before checking email and password
            btnSignin.setEnabled(false);

            auth.signInWithEmailAndPassword(strEmail,strPassword)
                    //Successfully logged in.
                    .addOnSuccessListener(authResult->{
                        //Intent to Main
                        Intent intent = new Intent(getApplicationContext(),SetupWorkerProfileActivity.class);
                        startActivity(intent);
                        finish();
                    })
                    //Fails to login
                    .addOnFailureListener(exception->{
                        //Enable button if fails
                        btnSignin.setEnabled(true);

                        //Create error on password error box
                        tilPassword.setErrorEnabled(true);
                        tilPassword.setError("Invalid email or password");
                    });
        });

        btnBack.setOnClickListener(view->{
            onBackPressed();
        });
    }

    //Event on pressing back button.
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    //    private boolean isEmailValid(String email){
//        String regex = "^(.+)@(.+)$";
//        Pattern pattern = Pattern.compile(regex);
//        Matcher matcher = pattern.matcher(email);
//        return matcher.matches();
//    }
}