package com.smile.worker.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.app.ActionBar;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthCredential;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
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
    private DatabaseReference userReference;

    public static final String GOOGLE_LOGIN = "google_account";
    public static final int GOOGLE_NO_PERSONAL_INFO = 1001;

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

        //Initiate User Reference
        userReference = FirebaseDatabase.getInstance().getReference("users");

        //Check if there's an account online in the app.
        FirebaseUser user = auth.getCurrentUser();
        if(user != null){
            //Go to Main
            Intent intent = new Intent(this,MainNavigationActivity.class);
            startActivity(intent);
            finish();
        }

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
            Intent intent = new Intent(view.getContext(), RegisterActivity.class);
            startActivity(intent);
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
                            FirebaseUser currentUser = task.getResult().getUser();

                            userReference.child(currentUser.getUid())
                                    .addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            Intent intent;

                                            //Check if the user has its personal information
                                            if(snapshot.exists()){
                                                intent = new Intent(getApplicationContext(),
                                                        MainNavigationActivity.class);
                                                startActivity(intent);
                                                finish();
                                            }else{
                                                intent = new Intent(getApplicationContext(),
                                                        RegisterActivity.class);
                                                intent.putExtra(RegisterActivity.REGISTER_FLAGS,
                                                        RegisterActivity.GOOGLE_REGISTRATION);
                                                startActivity(intent);
                                                finish();
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });

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