package com.didapteam.project.education;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

import java.util.Map;

import static com.android.volley.VolleyLog.TAG;

public class LoginActivity extends AppCompatActivity {
    private EditText edtEmail, edtPassword;
    private Button btnSubmit;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
        login();
    }
    private void initView() {
        edtEmail = findViewById(R.id.edt_email);
        edtPassword = findViewById(R.id.edt_password);
        btnSubmit = findViewById(R.id.btn_submit);
        auth = FirebaseAuth.getInstance();
    }
    private void login() {
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = edtEmail.getText().toString().trim();
                final String password = edtPassword.getText().toString().trim();

                if(email.isEmpty()){
                    edtEmail.setError("Email tidak boleh kosong!");
                }
                else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    edtEmail.setError("Email tidak valid!");
                }
                else if(password.isEmpty()){
                    edtPassword.setError("Password tidak boleh kosong!");
                }
                else {
                    auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            //ketika gagal login
                            if(!task.isSuccessful()){
                                Toast.makeText(LoginActivity.this,"Email tidak ditemukan!", Toast.LENGTH_LONG).show();
                            }else{
                                Bundle bundle = new Bundle();
                                bundle.putString("email", email);
                                bundle.putString("password", password);
                                DatabaseReference database;
                                database = FirebaseDatabase.getInstance().getReference("users");

                                Query databaseQuery = database.orderByChild("email").equalTo(email);

                                databaseQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        if(dataSnapshot.exists()){

                                            Map map = (Map) dataSnapshot.getChildren().iterator().next().getValue();

                                            String status = map.get("status").toString();

                                            UserData.bidang = map.get("bidang").toString();
                                            UserData.daerah = map.get("daerah").toString();
                                            UserData.email = map.get("email").toString();
                                            UserData.nama = map.get("nama").toString();
                                            UserData.pilihan = map.get("pilihan").toString();

                                            Log.i(TAG, UserData.bidang);

                                            if(status.equals("true")){
                                                // startActivity(new Intent(LoginActivity.this, MainActivity.class).putExtra("emailpass", bundle));
                                                startActivity(new Intent(LoginActivity.this, SelectPlace.class));
                                            } else {
                                                startActivity(new Intent(LoginActivity.this, VerificationResult.class));
                                                finish();
                                            }
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                            }
                        }
                    });
                }
            }
        });
    }


    public void Daftar(View view) {
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
    }
}
