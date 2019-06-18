package com.rayanandisheh.peysepar.passenger.activities.map_choose_origin_tablayout;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import android.widget.Toast;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.rayanandisheh.peysepar.passenger.R;
import com.rayanandisheh.peysepar.passenger.helpers.App;

public class MapsActivityTabLayout extends FragmentActivity implements OnMapReadyCallback {


    private GoogleMap mMap;
    float flatSource, flngSource, flatDes, flngDes;
    Marker marker1, marker2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_tab_layout);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //get latlng from tablayout adapter
        Intent intent = getIntent();
        flatSource = Float.parseFloat(intent.getStringExtra("flatSourceTabLayout"));
        flngSource = Float.parseFloat(intent.getStringExtra("flngSourceTabLayout"));
        flatDes = Float.parseFloat(intent.getStringExtra("flatDesTabLayout"));
        flngDes = Float.parseFloat(intent.getStringExtra("flngDesTabLayout"));


        // Add a marker in Sydney and move the camera
        LatLng origin = new LatLng(flatSource, flngSource);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(origin, 4f));
        marker1 = mMap.addMarker(new MarkerOptions().position(origin)
                .title("مبدا"));
        marker1.showInfoWindow();


        LatLng destination = new LatLng(flatDes, flngDes);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(destination, 4f));
        marker2 = mMap.addMarker(new MarkerOptions().position(destination)
                .title("مقصد"));
        marker2.showInfoWindow();


        //zoom automatically in center of 2 markers
        Marker markers[] = {marker1, marker2};
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for (Marker marker : markers) {
            builder.include(marker.getPosition());
        }
        LatLngBounds bounds = builder.build();
        int width = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels;
        int padding = 200; // offset from edges of the map in pixels
        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, width,height,padding);
        googleMap.animateCamera(cu);
    }
}
