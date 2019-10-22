package com.didapteam.project.education;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class CategoryGridViewActivity extends AppCompatActivity {

    GridView androidGridView;

    String[] gridViewString;
    TypedArray gridViewImageIdTemp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_category);

        int[] gridViewImageId = new int[7];
        gridViewString = getResources().getStringArray(R.array.bidang);
        gridViewImageIdTemp = getResources().obtainTypedArray(R.array.category_photos);
        for(int i=0; i < 7; i++){
            gridViewImageId[i] = gridViewImageIdTemp.getResourceId(i, 0);
        }
        CategoryGridActivity adapterViewAndroid = new CategoryGridActivity(CategoryGridViewActivity.this, gridViewString, gridViewImageId);
        androidGridView=(GridView)findViewById(R.id.grid_view_category);
        androidGridView.setAdapter(adapterViewAndroid);
        androidGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int i, long id) {
                //Toast.makeText(CategoryGridViewActivity.this, "GridView Item: " + gridViewString[+i], Toast.LENGTH_LONG).show();
                Bundle bundle = new Bundle();
                bundle.putString("bidang", gridViewString[+i]);

                Intent intent = new Intent(CategoryGridViewActivity.this, ConsultanListActivity.class).putExtra("bidang", bundle);
                startActivity(intent);
            }
        });

    }
}
