package com.smile.worker.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.smile.worker.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChangePasswordActivity extends AppCompatActivity {

    @BindView(R.id.btn_act_changePassword_SavePassword)
    Button btn_act_changePassword_SavePassword;

    @BindView(R.id.tf_act_changePassword_oldPassoword)
    TextInputLayout tf_act_changePassword_oldPassoword;

    @BindView(R.id.tf_act_changePassword_NewPassoword)
    TextInputLayout tf_act_changePassword_NewPassoword;

    @BindView(R.id.tf_act_changePassword_ConfirmNewPassoword)
    TextInputLayout tf_act_changePassword_ConfirmNewPassoword;

    @BindView(R.id.btnHome_act_changePassword)
    Button btnHome_act_changePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        ButterKnife.bind(this);

        showVerificationDialog();
        btnHome_act_changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Go to HOME page", Toast.LENGTH_SHORT).show();
            }
        });

        btn_act_changePassword_SavePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!tf_act_changePassword_oldPassoword.getEditText().getText().toString().trim().isEmpty() &&
                        !tf_act_changePassword_NewPassoword.getEditText().getText().toString().trim().isEmpty() &&
                        !tf_act_changePassword_ConfirmNewPassoword.getEditText().getText().toString().trim().isEmpty()){

                    Toast.makeText(getApplicationContext(), "SAVE PASSWORD", Toast.LENGTH_SHORT).show();

                }
                else{

                    Toast.makeText(getApplicationContext(), "Please fill all the fields: ", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    public void showVerificationDialog(){
        final Dialog dialog = new Dialog(ChangePasswordActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.verification_dialog);

        TextInputLayout tf_act_changePassword_VerificationDialog_OTP = dialog.findViewById(R.id.tf_act_changePassword_VerificationDialog_OTP);
        Button btn_act_changePassword_VerificationDialog_GETCODE = dialog.findViewById(R.id.btn_act_changePassword_VerificationDialog_GETCODE);
        Button btn_act_changePassword_VerificationDialog_VERIFY = dialog.findViewById(R.id.btn_act_changePassword_VerificationDialog_VERIFY);

        btn_act_changePassword_VerificationDialog_VERIFY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),  "CODE : " + tf_act_changePassword_VerificationDialog_OTP.getEditText().getText(), Toast.LENGTH_SHORT).show();
            }
        });
        btn_act_changePassword_VerificationDialog_GETCODE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),  "CODE : " + tf_act_changePassword_VerificationDialog_OTP.getEditText().getText(), Toast.LENGTH_SHORT).show();
            }
        });

        dialog.show();
    }
}