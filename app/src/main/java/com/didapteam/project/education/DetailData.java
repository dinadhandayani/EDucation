package com.didapteam.project.education;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class DetailData extends AppCompatActivity {

    TextView tvName, tvKebutuhan1, tvKebutuhan2, tvStatusBos, tvWaktu, tvDayaListrik, tvAksesInternet, tvSumberListrik, tvKarakteristik;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_data);

        Intent intent = getIntent();
        School school = (School) intent.getSerializableExtra("detail_sekolah");

        String nama = school.getNama();

        tvName = findViewById(R.id.txt_dtl_name);
        tvKebutuhan1 = findViewById(R.id.txt_kebutuhan1);
        tvKebutuhan2 = findViewById(R.id.txt_kebutuhan2);
        tvStatusBos = findViewById(R.id.txt_statusbos);
        tvDayaListrik = findViewById(R.id.txt_dayalistrik);
        tvAksesInternet = findViewById(R.id.txt_aksesinternet);
        tvSumberListrik = findViewById(R.id.txt_sumberlistrik);
        tvKarakteristik = findViewById(R.id.txt_karakteristik);
        tvWaktu = findViewById(R.id.txt_waktu);
        btnSubmit = findViewById(R.id.btn_submit);

        tvName.setText(nama);
        tvKebutuhan1.setText(school.getKebutuhan1());
        tvKebutuhan2.setText(school.getKebutuhan2());
        tvStatusBos.setText(school.getStatusBos());
        tvDayaListrik.setText(school.getDayaListrik());
        tvAksesInternet.setText(school.getAksesInternet());
        tvSumberListrik.setText(school.getSumberListrik());
        tvKarakteristik.setText(school.getKarakteristik());
        tvWaktu.setText(school.getWaktu());
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference database;
                database = FirebaseDatabase.getInstance().getReference("users");

                Query databaseQuery = database.orderByChild("email").equalTo(UserData.email);

               databaseQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                   @Override
                   public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                       if(dataSnapshot.exists()){
                           //dataSnapshot.getRef().child("sekolah").setValue(nama);
                           dataSnapshot.getChildren().iterator().next().getRef().child("sekolah").setValue(nama);
                       }
                   }

                   @Override
                   public void onCancelled(@NonNull DatabaseError databaseError) {

                   }
               });

               startActivity(new Intent(DetailData.this, VerificationResult.class ));

            }
        });


    }

}
