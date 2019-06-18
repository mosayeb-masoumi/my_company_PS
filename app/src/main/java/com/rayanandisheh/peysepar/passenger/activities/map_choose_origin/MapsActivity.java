package com.rayanandisheh.peysepar.passenger.activities.map_choose_origin;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.*;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.*;
import com.rayanandisheh.peysepar.passenger.R;
import com.rayanandisheh.peysepar.passenger.helpers.App;
import com.rayanandisheh.peysepar.passenger.helpers.Cache;
import com.rayanandisheh.peysepar.passenger.helpers.Location;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, Contract.View {
    Contract.Presenter presenter = new Presenter();
    Context context;
    private GoogleMap mMap;
    Marker marker;
    Button btn;
    ProgressBar pbMap;
    ImageView img;
    RelativeLayout rlWait;
    FloatingActionButton fabMap;


//    boolean fabClear=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        context = this;
        presenter.attachView(context, this);
        bindView();


        fabMap.setOnClickListener(v -> {
            mMap.clear();
            if (marker != null) {
                marker.remove();


                App.userInfo.setfLat(0);
                App.userInfo.setfLat(0);
//                fabClear=true;

                App.selectedPosition = new LatLng(0, 0);
                App.marker_existance = false;
//                Cache.setLat("LAT", 0);
//                Cache.setLng("LNG", 0);
                Cache.setString("selectedPositionName", "");
            }
        });


        btn.setOnClickListener(v -> {
//            if (marker != null && App.marker_existance) {

            if (marker != null && App.marker_existance) {
                presenter.btnRegisterPressed(marker.getPosition());
                App.marker_existance = true;

            } else {
                presenter.btnRegisterPressed(new LatLng(0, 0));
                App.marker_existance = false;
            }

            Cache.setString("selectedPositionName" , "");

        });


        img.setOnClickListener(v -> {
            if (checkGPSon()) {
                //show Dot
//                displayLocationSettingsRequest(MapsActivity.this, 123);

                rlWait.setVisibility(View.VISIBLE);
                Location.getLocation(context);
                currentLocation();
            } else {
                Toast.makeText(context, "لطفا GPS را روشن و دوباره تلاش کنید", Toast.LENGTH_SHORT).show();
            }
        });


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }





    public boolean checkGPSon() {

        final LocationManager manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {

            displayLocationSettingsRequest(context,12345);
//            return false;
            // Call your Alert message
        }
        return true;
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //to show currentLocation Dot (optional)
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                (this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        //show Dot
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(false);

        ///////////////////////////////////////////////////////////////////////////

//        if (Cache.getLat("LAT", 0) == 0 && Cache.getLng("LNG", 0) == 0) {
        if (App.userInfo.getfLat() == 0 && App.userInfo.getfLon() == 0) {
            LatLng iran = new LatLng(33, 53);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(iran, 4.5f));
            App.marker_existance = false;
        } else {
//            LatLng origin = new LatLng(Cache.getLat("LAT", 0), Cache.getLng("LNG", 0));
            LatLng origin = new LatLng(App.userInfo.getfLat(), App.userInfo.getfLon());
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(origin, 15f));
            marker = mMap.addMarker(new MarkerOptions().position(origin)
                    .title("مبدا"));

            App.marker_existance = true;

            //to show tiltle as always
            marker.showInfoWindow();
        }


        mMap.setOnMapClickListener(latLng -> {
            mMap.clear();
            if (marker != null) {
                marker.remove();
            }

            marker = mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(latLng.latitude, latLng.longitude))
                    .draggable(true).visible(true).title("مبدا"));

            marker.showInfoWindow();
            App.marker_existance = true;

        });
    }


    public void currentLocation() {
        mMap.clear();
        if (App.lastLat > 20 && App.lastLng > 20) {
            markLocation();
        } else {
            new Handler().postDelayed(this::currentLocation, 1000);
        }
    }

    private void markLocation() {

        LatLng currentLatLng = new LatLng(App.lastLat, App.lastLng);
        marker = mMap.addMarker(new MarkerOptions().position(currentLatLng)
                .title("مبدا"));

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(App.lastLat, App.lastLng), 15.0f));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 15.0f));

        rlWait.setVisibility(View.GONE);

        App.marker_existance = true;
        marker.showInfoWindow();
    }


    private void bindView() {
        btn = findViewById(R.id.btnRegisterNewOriginLocation);
        pbMap = findViewById(R.id.pbMap);
        rlWait = findViewById(R.id.rlWait);
        img = findViewById(R.id.img_currentLocation);
        fabMap = findViewById(R.id.fabMap);
    }


//    public void displayLocationSettingsRequest(Context context, int requestCode) {
//        GoogleApiClient googleApiClient = new GoogleApiClient.Builder(context)
//                .addApi(LocationServices.API).build();
//        googleApiClient.connect();
//
//        LocationRequest locationRequest = LocationRequest.create();
//        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
//        locationRequest.setInterval(10000);
//        locationRequest.setFastestInterval(10000 / 2);
//
//        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);
//        builder.setAlwaysShow(true);
//
//        PendingResult<LocationSettingsResult> result = LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build());
//        result.setResultCallback(result1 -> {
//            final Status status = result1.getStatus();
//            if (status.getStatusCode() == LocationSettingsStatusCodes.RESOLUTION_REQUIRED)
//                try {
//                    status.startResolutionForResult((Activity) context, requestCode);
//                } catch (IntentSender.SendIntentException ignored) {
//                }
//        });
//    }


//    @Override
//    protected void onResume() {
//        super.onResume();
//        if (App.userInfo.getfLat() == 0 && App.userInfo.getfLon() == 0) {
//            LatLng iran = new LatLng(33, 53);
//            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(iran, 4.5f));
//            App.marker_existance = false;
//        }
//    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if (!App.marker_existance) {
            App.selectedPosition = new LatLng(0, 0);
            App.userInfo.setfLat(0);
            App.userInfo.setfLon(0);

        }

//        if(fabClear){
//            App.selectedPosition = new LatLng(0, 0);
//            App.userInfo.setfLat(0);
//            App.userInfo.setfLon(0);
//        }

    }







    public  void displayLocationSettingsRequest(Context context, int requestCode) {
        GoogleApiClient googleApiClient = new GoogleApiClient.Builder(context)
                .addApi(LocationServices.API).build();
        googleApiClient.connect();

        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(10000 / 2);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);
        builder.setAlwaysShow(true);

        PendingResult<LocationSettingsResult> result = LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build());
        result.setResultCallback(result1 -> {
            final Status status = result1.getStatus();
            if (status.getStatusCode() == LocationSettingsStatusCodes.RESOLUTION_REQUIRED)
                try {
                    status.startResolutionForResult((Activity) context, requestCode);
                } catch (IntentSender.SendIntentException ignored) {
                }
        });
    }





}