package com.didapteam.project.education;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import static com.android.volley.VolleyLog.TAG;

public class DataVerification extends AppCompatActivity {

    ImageView imgBg;
    TextView tvCity, tvDesc, tvKaDa, tvBidKe;
    private RecyclerView rvSchool;
    ArrayList<School> schools;
    String[] dataName, dataKota, dataKarakteristik, dataKebutuhan1, dataKebutuhan2;
    String name, desc;
    int photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_verification);

        Intent intent = getIntent();
        Place place = (Place) intent.getSerializableExtra("detail");
        Log.i(TAG, place.getName());

        name = place.getName();
        desc = place.getDescription();

        imgBg = findViewById(R.id.img_bg_photo);
        tvCity = findViewById(R.id.txt_city_name);
        tvDesc = findViewById(R.id.txt_desc_city);
        tvBidKe = findViewById(R.id.txt_bidang_keahlian);
        tvKaDa = findViewById(R.id.txt_karakteristik_daerah);

        imgBg.setImageResource(place.getPhoto());
        tvCity.setText(name);
        tvDesc.setText(desc);
        tvBidKe.setText(UserData.pilihan);
        tvKaDa.setText(UserData.daerah);
        prepare();
        addItem();

        rvSchool = findViewById(R.id.list_school);
        rvSchool.setLayoutManager(new LinearLayoutManager(this));
        DetailPlaceListAdapter detailPlaceListAdapter = new DetailPlaceListAdapter(schools);
        rvSchool.setAdapter(detailPlaceListAdapter);
        detailPlaceListAdapter.setOnItemClickCallback(new DetailPlaceListAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(School data) {
                startActivity(new Intent(DataVerification.this, DetailData.class).putExtra("detail_sekolah", data));
            }
        });


    }

    private void prepare(){
        dataName = getResources().getStringArray(R.array.nama_sekolah);
        dataKota = getResources().getStringArray(R.array.kab_sekolah);
        dataKarakteristik = getResources().getStringArray(R.array.karakteristik);
        dataKebutuhan1 = getResources().getStringArray(R.array.kebutuhan1);
        dataKebutuhan2 = getResources().getStringArray(R.array.kebutuhan2);
    }

    private void addItem(){
        schools = new ArrayList<>();
        for(int i = 0; i < dataName.length; i++){
            if(dataKota[i].equals(name) && dataKarakteristik[i].equals(UserData.daerah) && (dataKebutuhan1[i].equals(UserData.pilihan) || dataKebutuhan2[i].equals(UserData.pilihan))){
                School school = new School();
                school.setNama(dataName[i]);
                schools.add(school);
            }
        }
    }
}
