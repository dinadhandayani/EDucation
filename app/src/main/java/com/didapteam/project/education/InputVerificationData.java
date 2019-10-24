package com.didapteam.project.education;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class InputVerificationData extends AppCompatActivity {

    private Button btnChooseKTP, btnChooseIjazah, btnSubmit;
    private Spinner spnDaerah, spnBidang;
    private FirebaseAuth auth;
    private DatabaseReference database;
    private String email, password, name;
    private int PICK_IMAGE_REQUEST = 111;
    private Uri filePath;
    private StorageReference storageReference;
    private DatabaseReference databaseReference;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_verification_data);

        Bundle bundle = getIntent().getBundleExtra("detail");
        email = bundle.getString("email");
        password = bundle.getString("password");
        name = bundle.getString("name");

        database = FirebaseDatabase.getInstance().getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        user = FirebaseAuth.getInstance().getCurrentUser();

        initView();
        userVerif();
    }

    private void initView(){
        btnChooseIjazah = findViewById(R.id.btn_ijazah);

        btnChooseIjazah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_PICK);
                startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE_REQUEST);
            }
        });

        btnChooseKTP = findViewById(R.id.btn_ktp);
        btnSubmit = findViewById(R.id.btn_submit);
        spnDaerah = findViewById(R.id.spin_kada);
        spnBidang = findViewById(R.id.spin_bidke);
    }

    private void userVerif(){
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String daerah = String.valueOf(spnDaerah.getSelectedItem());
                String pilihan = String.valueOf(spnBidang.getSelectedItem());
                Boolean status = false;
                String bidang = "null";

                submitUser(new Users(name, email, password, bidang, daerah, pilihan,  status));
            }
        });
    }

    public void submitUser(Users user){
        //menambahkan user ke firebase
        database.child("users").push().setValue(user);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            if(filePath != null) {
                Long tsLong = System.currentTimeMillis()/1000;
                String ts = tsLong.toString();

                StorageReference childRef = storageReference.child("Images");

                //uploading the image
                UploadTask uploadTask = childRef.putFile(filePath);

                uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        String download_uri = taskSnapshot.getMetadata().getReference().getDownloadUrl().toString();

                        ImageModel im = new ImageModel();
                        im.setImage_url(download_uri);
                        String key = databaseReference.child(user.getUid()).push().getKey();

                        databaseReference.child(user.getUid()).child(key).setValue(im);

                        Toast.makeText(InputVerificationData.this, "Upload successful", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(InputVerificationData.this, "Upload Failed -> " + e, Toast.LENGTH_SHORT).show();
                    }
                });
            }

        }
    }

}
