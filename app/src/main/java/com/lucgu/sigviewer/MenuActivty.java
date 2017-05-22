package com.lucgu.sigviewer;

import android.Manifest;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivty extends AppCompatActivity
{

    private Button btMaps;
    private Button btAbout;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_activty);

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION}, 1);

        btMaps = (Button) findViewById(R.id.bt_maps);
        btAbout = (Button) findViewById(R.id.bt_about);

        btMaps.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MenuActivty.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btAbout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MenuActivty.this, AboutActivity.class);
                startActivity(intent);
            }
        });
    }
}
