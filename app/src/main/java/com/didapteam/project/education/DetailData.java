package com.didapteam.project.education;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;

public class DetailData extends AppCompatActivity {

    ImageView imgBg;
    TextView tvCity, tvDesc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_data);

        Intent intent = getIntent();
        Place place = (Place) intent.getSerializableExtra("detail");

        imgBg = findViewById(R.id.img_bg_photo);
        tvCity = findViewById(R.id.txt_city_name);
        tvDesc = findViewById(R.id.txt_desc_city);

        imgBg.setImageResource(place.getPhoto());
        tvCity.setText(place.getName());
        tvDesc.setText(place.getDescription());

    }
}
