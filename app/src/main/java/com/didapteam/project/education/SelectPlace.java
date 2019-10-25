package com.didapteam.project.education;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import static com.android.volley.VolleyLog.TAG;

import java.io.Serializable;
import java.util.ArrayList;

public class SelectPlace extends AppCompatActivity {

    String[] dataName, dataDescription;
    TypedArray dataPhoto;
    ArrayList<Place> places;
    private RecyclerView rvPlace;
    TextView tvBidKe, tvKaDa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_place);

        ImageView imageView = (ImageView) findViewById(R.id.logo);
        imageView.setAlpha(250);

        prepare();
        addItem();

        tvBidKe = findViewById(R.id.txt_bidang_keahlian);
        tvKaDa = findViewById(R.id.txt_karakteristik_daerah);


        tvBidKe.setText(UserData.pilihan);
        tvKaDa.setText(UserData.daerah);

        rvPlace = findViewById(R.id.rv_daerah);
        rvPlace.setLayoutManager(new LinearLayoutManager(this));
        PlaceListAdapter placeListAdapter = new PlaceListAdapter(places);
        rvPlace.setAdapter(placeListAdapter);
        placeListAdapter.setOnItemClickCallback(new PlaceListAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Place data) {
                startActivity(new Intent(SelectPlace.this, DataVerification.class).putExtra("detail", data));
            }
        });

    }

    private void prepare(){
        dataName = getResources().getStringArray(R.array.nama_kabupaten);
        dataDescription = getResources().getStringArray(R.array.deskripsi_daerah);
        dataPhoto = getResources().obtainTypedArray(R.array.foto_daerah);
    }

    private void addItem(){
        places = new ArrayList<>();
        for(int i = 0; i < dataName.length; i++){
            Place place = new Place();
            place.setPhoto(dataPhoto.getResourceId(i, -1));
            place.setName(dataName[i]);
            place.setDescription(dataDescription[i]);
            places.add(place);
        }
    }
}