package com.smile.worker.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthCredential;
import com.google.firebase.auth.GoogleAuthProvider;
import com.smile.worker.R;

public class WelcomeActivity extends AppCompatActivity {

    @BindView(R.id.welcome_btn_use_phone)
    Button btnUsePhone;
    @BindView(R.id.welcome_btn_use_with_google)
    Button btnWithGoogle;
    @BindView(R.id.welcome_txtv_signup)
    TextView txtvSignup;

    private GoogleSignInClient googleSignInClient;
    private FirebaseAuth auth;

    private static final int GOOGLE_SIGN_IN_RESULT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
        init();
    }

    private void init(){
        //FirebaseAuth
        auth = FirebaseAuth.getInstance();

        //Google Sign in
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(this,gso);

        btnUsePhone.setOnClickListener((view)->{
            Intent intent = new Intent(view.getContext(),LoginActivity.class);
            startActivity(intent);
        });

        btnWithGoogle.setOnClickListener((view)->{
            Intent intent = googleSignInClient.getSignInIntent();
            startActivityForResult(intent,GOOGLE_SIGN_IN_RESULT);
        });

        txtvSignup.setOnClickListener((view)->{

        });
    }

    //Logging in using google credentials
    private void firebaseLoginWithGoogle(String token){
        AuthCredential credential = GoogleAuthProvider.getCredential(token,null);
        auth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //Intent to main
                            Intent intent = new Intent();
                            startActivity(intent);
                            finish();
                        }else{
                            //Show error results
                            Log.d("Google Sign in error",task.getException().getMessage());
                        }
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == GOOGLE_SIGN_IN_RESULT){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try{
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d("firebase account",account.getId());
                firebaseLoginWithGoogle(account.getIdToken());
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }
}