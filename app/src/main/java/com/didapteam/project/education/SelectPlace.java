package com.didapteam.project.education;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

public class SelectPlace extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_place);

        ImageView imageView = (ImageView) findViewById(R.id.logo);
        imageView.setAlpha(250);
    }
}
