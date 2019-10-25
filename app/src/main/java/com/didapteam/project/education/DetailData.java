package com.didapteam.project.education;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetailData extends AppCompatActivity {

    TextView tvName;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_data);

        Intent intent = getIntent();
        School school = (School) intent.getSerializableExtra("detail_sekolah");

        String nama = school.getNama();

        tvName = findViewById(R.id.txt_dtl_name);
        btnSubmit = findViewById(R.id.btn_submit);
        tvName.setText(nama);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }


}
