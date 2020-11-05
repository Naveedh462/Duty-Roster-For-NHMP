package com.example.dutyrosterfornhmp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import AdminOfficer.AdminDashbordActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRefe;
    private Button login_Button, forget_Password;
    private EditText editTextEmail, editTextPassword;
    //private ProgressBar mProgressBar;
    private ProgressDialog mProgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //initialiaztion buttons, textview, edittext etc
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        mRefe = mDatabase.getReference("Admin");

        mProgressDialog = new ProgressDialog(this);
        //mProgressBar = findViewById(R.id.progressBar);
        login_Button = findViewById(R.id.login);
        editTextEmail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);
        forget_Password = findViewById(R.id.forget_password);

        login_Button.findViewById(R.id.login).setOnClickListener(this);
        forget_Password.findViewById(R.id.forget_password).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login:
                login();
                break;
            case R.id.forget_password:
                forgetPassword();
                break;
        }
    }

    private void forgetPassword() {
        Intent intent = new Intent(getApplicationContext(), ForgetPasswordActivity.class);
        // add animations
        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View, String>(forget_Password, "transition_forget_Password");

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this, pairs);
            startActivity(intent, options.toBundle());
        } else {
            startActivity(intent);
        }
    }

    private void login() {
        final String emailIs = editTextEmail.getText().toString().trim();
        String passwordIs = editTextPassword.getText().toString().trim();

        // to check that if email edittext(Email) is empty

        if (emailIs.isEmpty()) {
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(emailIs).matches()) {
            editTextEmail.setError("required valid form of email");
            editTextEmail.requestFocus();
            return;
        }
        // to check that if email edittext(Password) is empty
        if (passwordIs.isEmpty()) {
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;
        }
        // if the password is less then 6 show error message
        if (passwordIs.length() < 6) {
            editTextPassword.setError("atleast 6 character");
            editTextPassword.requestFocus();
            return;
        }
        //mProgressBar.setVisibility(View.Visibal);
        mProgressDialog.setTitle("Login");
        mProgressDialog.setMessage("please wait...");
        mProgressDialog.show();
        mAuth.signInWithEmailAndPassword(emailIs, passwordIs).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                mProgressDialog.hide();
                if (task.isSuccessful()) {
                    finish();

                    Intent intent = new Intent(getApplicationContext(), AdminDashbordActivity.class);

                    // add animations

                    Pair[] pairs = new Pair[1];
                    pairs[0] = new Pair<View, String>(login_Button, "transition_login");

                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this, pairs);
                        startActivity(intent,options.toBundle());
                    } else {
                        startActivity(intent);
                    }
                }else {

                    Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(mAuth.getCurrentUser()!=null)
        {
            finish();
            startActivity(new Intent(this, AdminDashbordActivity.class));
        }

    }
}