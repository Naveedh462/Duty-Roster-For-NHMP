package com.example.dutyrosterfornhmp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPasswordActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;
    private Button login_Button, resetPasswordButton;
    private EditText emailRequired;
    private ImageView linkSent;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_required_phone_number);

        mAuth = FirebaseAuth.getInstance();
        linkSent=findViewById(R.id.checkStatus);
        mProgressBar = findViewById(R.id.progressBar);
        login_Button = findViewById(R.id.reset_password);
        emailRequired = findViewById(R.id.email_address_for_resetPasword);
        findViewById(R.id.reset_password).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.reset_password:
                resetPassword();
                break;
        }
    }

    private void resetPassword() {
        String emailIs = emailRequired.getText().toString().trim();

        if (emailIs.isEmpty()) {
            emailRequired.setError("Email is required");
            emailRequired.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(emailIs).matches()) {
            emailRequired.setError("required valid form of email");
            emailRequired.requestFocus();
            return;
        }
       mProgressBar.setVisibility(View.VISIBLE);

        mAuth.sendPasswordResetEmail(emailIs).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    mProgressBar.setVisibility(View.GONE);
                    linkSent.setImageResource(R.drawable.icon_check);
                    Toast.makeText(ForgetPasswordActivity.this, "Link is send on your email", Toast.LENGTH_SHORT).show();
                } else {
                    mProgressBar.setVisibility(View.GONE);
                    linkSent.setImageResource(R.drawable.icon_cancel);
                    Toast.makeText(ForgetPasswordActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}