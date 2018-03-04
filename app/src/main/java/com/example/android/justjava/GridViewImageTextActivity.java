package com.example.android.justjava;

/**
 * Created by shannonbeach on 3/4/18.
 */

import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class GridViewImageTextActivity extends AppCompatActivity {

    GridView androidGridView;

    String[] gridViewString = {
            "Coffee", "Hot Chocolate", "Capaccuino", "Frape", "Cookie", "Brownie"


    } ;
    int[] gridViewImageId = {
            R.drawable.coffee_house, R.drawable.coffee_house, R.drawable.coffee_house, R.drawable.coffee_house, R.drawable.coffee_house, R.drawable.coffee_house


    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.options_menu);

        CustomGridViewActivity adapterViewAndroid = new CustomGridViewActivity(GridViewImageTextActivity.this, gridViewString, gridViewImageId);
        androidGridView=(GridView)findViewById(R.id.grid_view_image_text);
        androidGridView.setAdapter(adapterViewAndroid);
        androidGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int i, long id) {
                Toast.makeText(GridViewImageTextActivity.this, "GridView Item: " + gridViewString[+i], Toast.LENGTH_LONG).show();
            }
        });

    }
}
