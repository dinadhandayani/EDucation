package com.didapteam.project.education;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PatternMatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    private EditText edtName, edtEmail, edtPassword, edtConfPassword;
    private Button btnCreateAccount;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        
        initView();
        registerUser();
    }

    private void initView() {
        edtName = findViewById(R.id.edt_name);
        edtEmail = findViewById(R.id.edt_email);
        edtPassword = findViewById(R.id.edt_password);
        edtConfPassword = findViewById(R.id.edt_confirmpassword);
        btnCreateAccount = findViewById(R.id.btn_submit);
        auth = FirebaseAuth.getInstance();
    }

    private void registerUser(){
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString().trim();
                String email = edtEmail.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();
                String confPassword = edtConfPassword.getText().toString().trim();

                if(name.isEmpty()){
                    edtName.setError("Nama Tidak Boleh Kosong!");
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    edtEmail.setError("Email tidak valid!");
                }else{
                    //membuat user dengan firebase auth
                    auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            //ketika gagal register
                            if(!task.isSuccessful()){
                                Toast.makeText(RegisterActivity.this, "Register Gagal karena "+task.getException().getMessage(),
                                        Toast.LENGTH_LONG).show();
                            }else {
                                //jika sukses menuju ke login
                                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                            }
                        }
                    });
                }
                if(password.isEmpty()){
                    edtPassword.setError("Password Tidak Boleh Kosong!");
                }
                if(confPassword.isEmpty()){
                    edtConfPassword.setError("Password Tidak Boleh Kosong!");
                }
            }
        });
    }

    public void Login(View view) {
        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
    }
}
