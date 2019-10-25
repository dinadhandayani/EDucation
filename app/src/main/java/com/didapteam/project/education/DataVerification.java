package com.didapteam.project.education;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import static com.android.volley.VolleyLog.TAG;

public class DataVerification extends AppCompatActivity {

    ImageView imgBg;
    TextView tvCity, tvDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_verification);

        String name, desc;
        int photo;

        Intent intent = getIntent();
        Place place = (Place) intent.getSerializableExtra("detail");
        Log.i(TAG, place.getName());

        name = place.getName();
        desc = place.getDescription();

        imgBg = findViewById(R.id.img_bg_photo);
        tvCity = findViewById(R.id.txt_city_name);
        tvDesc = findViewById(R.id.txt_desc_city);

        imgBg.setImageResource(place.getPhoto());
        tvCity.setText(name);
        tvDesc.setText(desc);
    }
}
