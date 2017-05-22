package com.lucgu.sigviewer;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.DateTypeAdapter;
import com.lucgu.sigviewer.database.DatabaseHandler;
import com.lucgu.sigviewer.helper.MyJsonReader;
import com.lucgu.sigviewer.model.FieldModel;
import com.lucgu.sigviewer.model.ListFieldModel;
import com.lucgu.sigviewer.sensor.GPSTracker;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback
{

    private GoogleMap gMap;
    private List<FieldModel> list;
    private DatabaseHandler handler;
    private Cursor cursor;
    private GPSTracker tracker;
    private double latitude;
    private double longitude;
    private ListFieldModel model;
    private ImageView ivOption;

    private Gson gson;

    private static final String DATABASE_NAME = "place.sql";
    private static final int DATABASE_VERSION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivOption = (ImageView) findViewById(R.id.iv_option);
        gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).registerTypeAdapter(Date.class, new DateTypeAdapter()).create();


        setMap();
    }

    public void getData() throws IOException
    {
        MyJsonReader myJsonReader = new MyJsonReader(this, R.raw.manti);
        String dataString = myJsonReader.readJson();

        model = gson.fromJson(dataString, ListFieldModel.class);

        for(int i=0; i <= model.getItem().size() - 1; i++)
        {
            LatLng currentPosition = new LatLng(model.getItem().get(i).getLongitude(), model.getItem().get(i).getLatitude());
            if (gMap != null)
            {
                if (model.getItem().get(i).getTipe().equalsIgnoreCase("Clinic"))
                {
                    gMap.addMarker(new MarkerOptions()
                            .position(currentPosition)
                            .title(model.getItem().get(i).getNama())
                            .snippet(model.getItem().get(i).getAlamat())
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
                }

                if (model.getItem().get(i).getTipe().equalsIgnoreCase("Pharmacy"))
                {
                    gMap.addMarker(new MarkerOptions()
                            .position(currentPosition)
                            .title(model.getItem().get(i).getNama())
                            .snippet(model.getItem().get(i).getAlamat())
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
                }

                if (model.getItem().get(i).getTipe().equalsIgnoreCase("Public Health Service"))
                {
                    gMap.addMarker(new MarkerOptions()
                            .position(currentPosition)
                            .title(model.getItem().get(i).getNama())
                            .snippet(model.getItem().get(i).getAlamat())
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
                }


                if (model.getItem().get(i).getTipe().equalsIgnoreCase("Salon & Spa"))
                {
                    gMap.addMarker(new MarkerOptions()
                            .position(currentPosition)
                            .title(model.getItem().get(i).getNama())
                            .snippet(model.getItem().get(i).getAlamat())
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)));
                }

                if (model.getItem().get(i).getTipe().equalsIgnoreCase("Hospital"))
                {
                    gMap.addMarker(new MarkerOptions()
                            .position(currentPosition)
                            .title(model.getItem().get(i).getNama())
                            .snippet(model.getItem().get(i).getAlamat())
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));

                }
            }

            Log.i("aaaa", model.getItem().get(i).getLongitude() + "");
            Log.i("aaaa", model.getItem().get(i).getLatitude() + "");
            Log.i("aaaa", currentPosition + "");

        }



    }

    public void markerSet(int type)
    {
        switch (type)
        {
            case 0:
                gMap.clear();
                for (int i=0; i <= model.getItem().size()-1; i++)
                {
                    LatLng currentPosition = new LatLng(model.getItem().get(i).getLongitude(), model.getItem().get(i).getLatitude());
                    if (model.getItem().get(i).getTipe().equalsIgnoreCase("Clinic"))
                    {
                        gMap.addMarker(new MarkerOptions()
                                .position(currentPosition)
                                .title(model.getItem().get(i).getNama())
                                .snippet(model.getItem().get(i).getAlamat())
                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
                    }
                }
                break;

            case 1:
                gMap.clear();
                for (int i=0; i <= model.getItem().size()-1; i++)
                {
                    LatLng currentPosition = new LatLng(model.getItem().get(i).getLongitude(), model.getItem().get(i).getLatitude());
                    if (model.getItem().get(i).getTipe().equalsIgnoreCase("Pharmacy"))
                    {
                        gMap.addMarker(new MarkerOptions()
                                .position(currentPosition)
                                .title(model.getItem().get(i).getNama())
                                .snippet(model.getItem().get(i).getAlamat())
                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
                    }
                }
                break;

            case 2:
                gMap.clear();
                for (int i=0;i<=model.getItem().size()-1;i++)
                {
                    LatLng currentPosition = new LatLng(model.getItem().get(i).getLongitude(), model.getItem().get(i).getLatitude());
                    if (model.getItem().get(i).getTipe().equalsIgnoreCase("Public Health Service"))
                    {
                        gMap.addMarker(new MarkerOptions()
                                .position(currentPosition)
                                .title(model.getItem().get(i).getNama())
                                .snippet(model.getItem().get(i).getAlamat())
                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
                    }
                }
                break;

            case 3:
                gMap.clear();
                for (int i=0;i<=model.getItem().size()-1;i++)
                {
                    LatLng currentPosition = new LatLng(model.getItem().get(i).getLongitude(), model.getItem().get(i).getLatitude());
                    if (model.getItem().get(i).getTipe().equalsIgnoreCase("Salon & Spa"))
                    {
                        gMap.addMarker(new MarkerOptions()
                                .position(currentPosition)
                                .title(model.getItem().get(i).getNama())
                                .snippet(model.getItem().get(i).getAlamat())
                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)));
                    }
                }
                break;

            case 4:
                gMap.clear();
                for (int i=0;i<=model.getItem().size()-1;i++)
                {
                    LatLng currentPosition = new LatLng(model.getItem().get(i).getLongitude(), model.getItem().get(i).getLatitude());
                    if (model.getItem().get(i).getTipe().equalsIgnoreCase("Hospital"))
                    {
                        gMap.addMarker(new MarkerOptions()
                                .position(currentPosition)
                                .title(model.getItem().get(i).getNama())
                                .snippet(model.getItem().get(i).getAlamat())
                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
                    }
                }
                break;
        }

    }




    public void setMap()
    {
        getCurrentLocation();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map_multimark);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        gMap = googleMap;

        LatLng currentPosition = new LatLng(latitude, longitude);
        Log.i("bbbb", currentPosition + "");
        gMap.moveCamera(CameraUpdateFactory.newLatLng(currentPosition));
        gMap.moveCamera( CameraUpdateFactory.newLatLngZoom(new LatLng(latitude,longitude) , 12.0f));

        try
        {
            getData();
        } catch (IOException e)
        {
            e.printStackTrace();
            Log.i("aaaaa", "ccccc");
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        gMap.setMyLocationEnabled(true);
        gMap.getUiSettings().setMyLocationButtonEnabled(true);

        ivOption.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                changeMap();
            }
        });
    }

    public void getCurrentLocation()
    {
        tracker = new GPSTracker(this);
        latitude = tracker.getLatitude();
        longitude = tracker.getLongitude();
    }

    public void changeMap()
    {
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(this);
        builderSingle.setTitle("Select map");
        String[] arrayCategory = getResources().getStringArray(R.array.type);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, arrayCategory);

        builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int position) {
                switch (position)
                {
                    case 0:
                        markerSet(0);
                        break;

                    case 1:
                        markerSet(1);
                        break;

                    case 2:
                        markerSet(2);
                        break;

                    case 3:
                        markerSet(3);
                        break;

                    case 4:
                        markerSet(4);
                        break;
                }
                dialog.dismiss();
            }
        });
        builderSingle.show();
    }
}
